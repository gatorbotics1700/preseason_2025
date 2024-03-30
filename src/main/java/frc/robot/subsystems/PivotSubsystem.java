package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.OI;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class PivotSubsystem{
    private TalonFX pivot;
    private DigitalInput ampLimitSwitch;
    private DigitalInput stageLimitSwitch;

    private static final double _kP = 0.025;//0.04;//TODO tune PID
    private static final double _kI = 0.0;
    private static final double _kD = 0.005;
    private static final int _kIzone = 0; //not in use
    private static final double _kPeakOutput = 1.0; //not in use

    private Gains pivotGains = new Gains(_kP, _kI, _kD, _kIzone, _kPeakOutput);
    private PivotStates pivotState;

    private final double PIVOT_TICKS_PER_DEGREE = 2048 * 100 / 360;
    private final double MANUAL_SPEED = 0.10;
    // TODO check if angle values work - changed so that selectedSensorPosition is 0 in init (amp)
    private final double AMP_ANGLE = 0.0;
    private final double SUBWOOFER_ANGLE = -55.0;//THIS ANGLE IS GREAT DO NOT CHANGE
    private final double STAGE_ANGLE = -60.0; //THIS ANGLE IS GREAT DO NOT CHANGE
    private final double UNDER_STAGE_ANGLE = -75.0;//-70.0;//25; //TODO: test
    private double deadband = 2 * PIVOT_TICKS_PER_DEGREE;
    
    public static enum PivotStates{
        AMP,
        SUBWOOFER,
        PODIUM,
        UNDER_STAGE,
        MANUAL,
        PANIC_OFF;
        //TODO add climb state?
    }

    public PivotSubsystem(){
        pivot = new TalonFX(Constants.PIVOT_MOTOR_CAN_ID);
        ampLimitSwitch = new DigitalInput(Constants.AMP_LIMIT_SWITCH_PORT); //check DIO port numbers
        stageLimitSwitch = new DigitalInput(Constants.STAGE_LIMIT_SWITCH_PORT);

        init();
    }

    public void init(){
        pivot.setNeutralMode(NeutralMode.Brake);
        pivot.configAllowableClosedloopError(Constants.SLOT_IDX, Constants.PID_LOOP_IDX, Constants.CONFIG_TIMEOUT_MS);
		/* Config Position Closed Loop gains in slot0, typically kF stays zero. */
        pivot.config_kP(Constants.PID_LOOP_IDX, pivotGains.kP, Constants.CONFIG_TIMEOUT_MS);
        pivot.config_kI(Constants.PID_LOOP_IDX, pivotGains.kI, Constants.CONFIG_TIMEOUT_MS);
        pivot.config_kD(Constants.PID_LOOP_IDX, pivotGains.kD, Constants.CONFIG_TIMEOUT_MS);
        //pivot.setSelectedSensorPosition(AMP_ANGLE*PIVOT_TICKS_PER_DEGREE);//sets encoder to recognize starting position as amp (flat to ground is 0 deg)
        // setSelectedSensorPosition() takes in sensorPos, pidIdx, and timeoutMs, so TODO add kPIDLoopIdx and kTimeoutMs
        // ^ test this by changing the angle constants back to original versions and see print values are nonzero (should be abt 90 degrees and 51200 ticks)
        System.out.println("********PIVOT POSITION IN INIT: " + pivot.getSelectedSensorPosition());
        
        setState(PivotStates.AMP);
    }

    public void periodic(){
        System.out.println("CURRENT PIVOT STATE: " + pivotState);
        //System.out.println("position ticks: " + pivot.getSelectedSensorPosition());//prints O.0
        System.out.println("*************************************************************************" + pivot.getSelectedSensorPosition());
        //System.out.println("is at amp: " + atAmp());
        //System.out.println("is at subwoofer: " + atSubwoofer());
        //System.out.println("is at stage: " + atStage());
        //System.out.println("is at under stage: " + atUnderStage());
        if(pivotState == PivotStates.AMP){
            setPivot(AMP_ANGLE);
        }else if(pivotState == PivotStates.SUBWOOFER){
            setPivot(SUBWOOFER_ANGLE);
        }else if(pivotState == PivotStates.PODIUM){
            setPivot(STAGE_ANGLE);
        }else if(pivotState == PivotStates.UNDER_STAGE){
            System.out.println("IN STAGE!!!!!!!!");
            setPivot(UNDER_STAGE_ANGLE); 
        }else if(pivotState == PivotStates.MANUAL){
            manual();
        }else if(pivotState == PivotStates.PANIC_OFF){
            pivot.set(ControlMode.PercentOutput, 0); 
        }else{
            System.out.println("=========UNRECOGNIZED PIVOT STATE: " + pivotState.toString() + "========");
            setState(PivotStates.PANIC_OFF);
        }
    }
    
    public void manual() {
        System.out.println("amp: " + getAmpLimitSwitch());
        System.out.println("stage: " + getStageLimitSwitch());
        if((OI.getCodriverRightAxis() < -0.2) && !stageLimitSwitch.get()){
            System.out.println("+++++++++++IN MANUAL++++++++++");
            pivot.set(ControlMode.PercentOutput, -MANUAL_SPEED);    
        } else if((OI.getCodriverRightAxis() > 0.2) && !ampLimitSwitch.get()){
            System.out.println("+++++++++++IN MANUAL++++++++++");
            pivot.set(ControlMode.PercentOutput, MANUAL_SPEED);  
        } else {
            pivot.set(ControlMode.PercentOutput, 0); 
        }
    }

    public void setPivot(double desiredAngle){
        double desiredTicks = desiredAngle * PIVOT_TICKS_PER_DEGREE; //calculates right ticks
        System.out.println("desiredTicks: " + desiredTicks);
        double diff = desiredTicks - pivot.getSelectedSensorPosition();
        System.out.println("diff: " + diff);
        System.out.println("stage limit switch: " + stageLimitSwitch.get()); 
        System.out.println("amp limit switch: " + ampLimitSwitch.get());
        boolean runningIntoStage = diff<0 && stageLimitSwitch.get();
        boolean runningIntoAmp = diff>0 && ampLimitSwitch.get();


        if (runningIntoStage || runningIntoAmp || Math.abs(diff)<deadband){
            pivot.set(ControlMode.PercentOutput, 0);
        } else { //sets motor to right ticks
            System.out.println("setting PID");
            pivot.set(ControlMode.Position, Math.signum(diff) * desiredTicks);
        }
    }

    public void setState(PivotStates newState) {
        pivotState = newState;
    }

    public PivotStates getState(){
        return pivotState;
    }

    public boolean atAmp(){
        return (Math.abs(pivot.getSelectedSensorPosition()-(AMP_ANGLE*PIVOT_TICKS_PER_DEGREE)) < deadband);
    }

    public boolean atSubwoofer(){
        return (Math.abs(pivot.getSelectedSensorPosition()-(SUBWOOFER_ANGLE*PIVOT_TICKS_PER_DEGREE)) < deadband);
    }

    public boolean atStage(){
        return (Math.abs(pivot.getSelectedSensorPosition()-(STAGE_ANGLE*PIVOT_TICKS_PER_DEGREE)) < deadband);
    }

    public boolean atUnderStage(){
        return (Math.abs(pivot.getSelectedSensorPosition()-(UNDER_STAGE_ANGLE*PIVOT_TICKS_PER_DEGREE)) < deadband);
    }

    public boolean getAmpLimitSwitch(){ // false when NOT pressed, true when pressed
        return ampLimitSwitch.get();
    }

    public boolean getStageLimitSwitch(){ // false when NOT pressed, true when pressed
        return stageLimitSwitch.get();
    }
    
}