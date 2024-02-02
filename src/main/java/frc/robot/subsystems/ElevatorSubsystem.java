package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Constants;
import frc.robot.Gains;
import com.ctre.phoenix.motorcontrol.ControlMode;

//NOTES ON MEASUREMENTS
//22.5inches on inside of metal frame that chain moves in
//10 in on moving mechanism thing

public class ElevatorSubsystem {
    private final double _kP = 0.2;//0.15;
    private final double _kI = 0.0;
    private final double _kD = 0.0;
    private final int _kIzone = 0; 
    private final double _kPeakOutput = 1.0;
    private final double LOW_HEIGHT_INCHES = 0;
    private final double AMP_HEIGHT_INCHES = 26; //2 feet 2 inches is amp height to pocket
    private final double ELEVATOR_SPROCKET_DIAMETER = 1.28;
    private final double ELEVATOR_GEAR_RATIO = 25.0;
    private final double ELEVATOR_TICKS_PER_INCH = Constants.TICKS_PER_REV*ELEVATOR_GEAR_RATIO/ELEVATOR_SPROCKET_DIAMETER/Math.PI;

    private TalonFX elevatorMotor; 
    private ElevatorStates elevatorState;
    
    private Gains elevatorGains = new Gains(_kP, _kI, _kD, _kIzone, _kPeakOutput);
    private final double ELEVATOR_DEADBAND = 5000; //15000;

    public static enum ElevatorStates{
        ZERO, 
        LOW_HEIGHT,  
        AMP_HEIGHT,  
        //MANUAL, later decide if we want manual setting
        STOPPED; 
    }

    public ElevatorSubsystem(){
        elevatorMotor = new TalonFX(Constants.ELEVATOR_CAN_ID);
        init();
    }

    public void init(){
        System.out.println("elevator init!!!!");
        elevatorMotor.setInverted(true); // looking from the front of the robot, clockwise is false (:
        elevatorMotor.setNeutralMode(NeutralMode.Brake);
        setState(ElevatorStates.LOW_HEIGHT);
        //configuring deadband
        elevatorMotor.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		/* Config Position Closed Loop gains in slot0, typically kF stays zero. */
		elevatorMotor.config_kP(Constants.kPIDLoopIdx, elevatorGains.kP, Constants.kTimeoutMs);
		elevatorMotor.config_kI(Constants.kPIDLoopIdx, elevatorGains.kI, Constants.kTimeoutMs);
		elevatorMotor.config_kD(Constants.kPIDLoopIdx, elevatorGains.kD, Constants.kTimeoutMs);
    }

    public void setZeroForAutoHeight(){
        elevatorMotor.setSelectedSensorPosition(309563.0);
    }

    public void periodic(){
        if (elevatorState == ElevatorStates.ZERO){
            elevatorDeadband(0);
        } else if (elevatorState == ElevatorStates.LOW_HEIGHT){
            double desiredTicks = determineRightTicks(LOW_HEIGHT_INCHES);
            elevatorDeadband(desiredTicks);
        } else if (elevatorState == ElevatorStates.AMP_HEIGHT){
            double desiredTicks = determineRightTicks(AMP_HEIGHT_INCHES);
            elevatorDeadband(desiredTicks);
        } else { //emergency stop again for safety
            elevatorMotor.set(ControlMode.PercentOutput, 0.0);
        }
    }

    private double determineRightTicks(double desiredInches){
        return desiredInches * ELEVATOR_TICKS_PER_INCH; 
    }

    private void elevatorDeadband(double desiredTicks){
        if (Math.abs(desiredTicks - elevatorMotor.getSelectedSensorPosition()) > ELEVATOR_DEADBAND){
            elevatorMotor.set(ControlMode.Position, desiredTicks); 
        } else {
            elevatorMotor.set(ControlMode.PercentOutput, 0);
        }
    }

    public void setState(ElevatorStates newElevatorState){
        elevatorState = newElevatorState;
    }


    public boolean isAtAmp(){
        return Math.abs(elevatorMotor.getSelectedSensorPosition()-AMP_HEIGHT_INCHES*ELEVATOR_TICKS_PER_INCH)<ELEVATOR_DEADBAND;
    }

    public boolean isAtLow(){
        return Math.abs(elevatorMotor.getSelectedSensorPosition()-LOW_HEIGHT_INCHES*ELEVATOR_TICKS_PER_INCH)<ELEVATOR_DEADBAND;
    }


    public boolean isAtZero(){
        return elevatorMotor.getSelectedSensorPosition() < ELEVATOR_DEADBAND;
    }

    public double getSelectedSensorPosition(){ // returns position of selected sensor
        return elevatorMotor.getSelectedSensorPosition();
    }

}