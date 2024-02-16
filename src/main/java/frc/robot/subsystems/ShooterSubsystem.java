package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants;
 

public class ShooterSubsystem {
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX low;

    private final double AMP_SPEED = 0.2;
    private final double HIGH_SPEAKER_SPEED = 0.8;
    private final double MID_SPEAKER_SPEED = 0.7;

    public static enum ShooterStates {
        OFF,
        INTAKING,
        AMP_HOLDING,
        SPEAKER_HOLDING,
        AMP,
        SPEAKER; 
    }

    private ShooterStates currentShooterState; //REVIEW: previously initialized to ShooterStates.AMP

    public ShooterSubsystem() {
        high = new TalonFX(Constants.SHOOTER_HIGH_CAN_ID);
        mid = new TalonFX(Constants.SHOOTER_MID_CAN_ID);
        low = new TalonFX(Constants.AMP_MOTOR_CAN_ID);
        
        init();
    }

    public void init(){
        currentShooterState = ShooterStates.OFF;
    }

    public void periodic(){
        System.out.println("CURRENT SHOOTER STATE: " + currentShooterState);
        if (currentShooterState == ShooterStates.INTAKING){
            low.set(ControlMode.PercentOutput, AMP_SPEED);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);

        }else if (currentShooterState == ShooterStates.AMP_HOLDING) {
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);

        } else if(currentShooterState == ShooterStates.SPEAKER_HOLDING){
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);
            
        }else if(currentShooterState == ShooterStates.AMP){//check negative signs here
            low.set(ControlMode.PercentOutput, AMP_SPEED);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);

        }else if(currentShooterState == ShooterStates.SPEAKER){//check negative signs here
            low.set(ControlMode.PercentOutput, MID_SPEAKER_SPEED);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);

        }else if(currentShooterState == ShooterStates.OFF){
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);

        }else{
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
            System.out.println("====UNRECOGNIZED SHOOTER STATE!!!!!==== current shooter state: " + currentShooterState);
        }
    }

    public void setState(ShooterStates newState) {
        currentShooterState = newState;
    }
}
