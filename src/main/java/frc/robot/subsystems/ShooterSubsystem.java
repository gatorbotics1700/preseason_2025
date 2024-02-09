package frc.robot.subsystems;

import static frc.robot.Constants.TICKS_PER_REV;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.subsystems.IntakeSubsystem.IntakeStates;
import frc.robot.Constants;
import frc.robot.OI;


public class ShooterSubsystem {
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX low;
    private TalonFX pivot;
    private final double AMP_SPEED = 0.2;
    private final double HIGH_SPEAKER_SPEED = 0.8;
    private final double MID_SPEAKER_SPEED = 0.7;
    private final double AMP_ANGLE = 85.0; // TODO: this is a placeholder value, please test
    private final double SPEAKER_ANGLE = 45.0; // TODO: this is a placeholder value, please test
    private final double PIVOT_TICKS_PER_DEGREE = (Constants.TICKS_PER_REV * Constants.REVS_PER_ROTATION)/180.0;
    private final double PIVOT_DEADBAND_TICKS = 5000;

    public static enum ShooterStates {
        OFF,
        INTAKING,
        AMP_HOLDING,
        SPEAKER_HOLDING,
        AMP,
        SPEAKER; 
    }

    private ShooterStates currentState; //REVIEW: previously initialized to ShooterStates.AMP
    
    public ShooterSubsystem() {
        high = new TalonFX(Constants.SHOOTER_HIGH_CAN_ID);
        mid = new TalonFX(Constants.SHOOTER_MID_CAN_ID);
        low = new TalonFX(Constants.AMP_MOTOR_CAN_ID);
        pivot = new TalonFX(Constants.PIVOT_MOTOR_CAN_ID);
        init();
    }

    public void init(){
        currentState = ShooterStates.OFF;
    }

    public void periodic(){
        System.out.println("CURRENT SHOOTER STATE: " + currentState);
        if (currentState == ShooterStates.INTAKING){
            low.set(ControlMode.PercentOutput, AMP_SPEED);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);

        }else if (currentState == ShooterStates.AMP_HOLDING) {
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);
            double desiredTicks = determineRightTicks(AMP_ANGLE);
            pivotDeadband(desiredTicks);

        } else if(currentState == ShooterStates.SPEAKER_HOLDING){
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);
            double desiredTicks = determineRightTicks(SPEAKER_ANGLE);
            pivotDeadband(desiredTicks);

        }else if(currentState == ShooterStates.AMP){//check negative signs here
            low.set(ControlMode.PercentOutput, AMP_SPEED);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);

        }else if(currentState == ShooterStates.SPEAKER){//check negative signs here
            low.set(ControlMode.PercentOutput, MID_SPEAKER_SPEED);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);

        }else if(currentState == ShooterStates.OFF){
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);

        }else{
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
            System.out.println("====UNRECOGNIZED SHOOTER STATE!!!!!==== current shooter state: " + currentState);
        }

    }

    private double determineRightTicks(double desiredDegrees){
        return desiredDegrees * PIVOT_TICKS_PER_DEGREE;
    }

    private void pivotDeadband(double desiredTicks){
        if (Math.abs(desiredTicks - pivot.getSelectedSensorPosition()) > PIVOT_DEADBAND_TICKS){
            pivot.set(ControlMode.Position, desiredTicks);
        } else {
            pivot.set(ControlMode.PercentOutput, 0);
        }
    }

    public void adjusting() {
        // TODO: when we know the max rotation of the pivot motor we need to intergrate that here 
        if(OI.getTwoRightAxis() > 0.2) {
            pivot.set(ControlMode.PercentOutput, 0.2);    
        } else if(OI.getTwoRightAxis() < - 0.2) {
            pivot.set(ControlMode.PercentOutput, - 0.2);  
        } else {
            pivot.set(ControlMode.PercentOutput, 0);
        }
    }

    public void setState(ShooterStates newState) {
        currentState = newState;
    }
}
