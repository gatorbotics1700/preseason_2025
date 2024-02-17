package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants;

public class ShooterSubsystem {
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX low;

    private final double AMP_SPEED = 0.3; // DO NOT TOUCH THIS VALUE!!
    private final double LOW_SHOOTING_SPEED = 0.7;
    private final double HIGH_SPEAKER_SPEED = 0.8;
    private final double MID_SPEAKER_SPEED = 0.8;
    private final double LOW_INTAKING_SPEED = 0.18;
    
    public static enum ShooterStates {
        OFF,
        INTAKING,
        WARMUP, //for auto only
        AMP_HOLDING,
        SPEAKER_HOLDING,
        AMP,
        SPEAKER;
    }

    private ShooterStates currentState;
    
    public ShooterSubsystem() {
        high = new TalonFX(Constants.SHOOTER_HIGH_CAN_ID);
        mid = new TalonFX(Constants.SHOOTER_MID_CAN_ID);
        low = new TalonFX(Constants.LOW_MOTOR_CAN_ID);
        init();
    }

    public void init(){
        high.setInverted(true); 
        mid.setInverted(false); 
        low.setInverted(false);
        high.setNeutralMode(NeutralMode.Brake);
        mid.setNeutralMode(NeutralMode.Brake);
        low.setNeutralMode(NeutralMode.Brake);
        currentState = ShooterStates.OFF;
    }

    public void periodic(){
        System.out.println("CURRENT SHOOTER STATE: " + currentState);
        if (currentState == ShooterStates.INTAKING){
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
            low.set(ControlMode.PercentOutput, LOW_INTAKING_SPEED);  
        }else if (currentState == ShooterStates.WARMUP){ //same as speaking holding but doesnt assume we have a note
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED); //negative
            low.set(ControlMode.PercentOutput, LOW_INTAKING_SPEED);  
        }else if (currentState == ShooterStates.AMP_HOLDING) { // DO NOT TOUCH THESE VALUES!!
            high.set(ControlMode.PercentOutput, -AMP_SPEED); //negative
            mid.set(ControlMode.PercentOutput, AMP_SPEED);
            low.set(ControlMode.PercentOutput, 0);
        } else if(currentState == ShooterStates.SPEAKER_HOLDING){
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED); //negative
            low.set(ControlMode.PercentOutput, 0);
        }else if(currentState == ShooterStates.AMP){ // DO NOT TOUCH THESE VALUES!!
            high.set(ControlMode.PercentOutput, -AMP_SPEED); //negative
            mid.set(ControlMode.PercentOutput, AMP_SPEED);
            low.set(ControlMode.PercentOutput, AMP_SPEED);
        }else if(currentState == ShooterStates.SPEAKER){
            System.out.println("==========WE ARE SHOOTING==========");
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);//TODO walk through logic
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED); //negative
            low.set(ControlMode.PercentOutput, LOW_SHOOTING_SPEED);
        }else if(currentState == ShooterStates.OFF){
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
            low.set(ControlMode.PercentOutput, 0);
        }else{
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
            low.set(ControlMode.PercentOutput, 0);
            System.out.println("=======UNRECOGNIZED SHOOTER STATE!!!!!======= current shooter state: " + currentState);
        }
    }
    public void setState(ShooterStates newState) {
        currentState = newState;
    }
}