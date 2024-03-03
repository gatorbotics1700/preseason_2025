package frc.robot.subsystems;

//v6 for mid, high motors
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ControlModeValue;
import com.ctre.phoenix6.controls.DutyCycleOut;

import frc.robot.Constants;
 

public class ShooterSubsystem {
    
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX low;

    private final double TESTING_SPEED = 0.5;
    private final double AMP_SPEED = 0.35; //0.3; // DO NOT TOUCH THIS VALUE!!
    private final double LOW_SHOOTING_SPEED = 0.7;
    private final double HIGH_SPEAKER_SPEED = 0.8;
    private final double MID_SPEAKER_SPEED = 0.8;
    private final double LOW_INTAKING_SPEED = 0.7;
    
    public static enum ShooterStates {
        OFF,
        INTAKING,
        WARMUP, //for auto only
        AMP_HOLDING,
        SPEAKER_HOLDING,
        AMP,
        SPEAKER,
        TESTING; 
    }

    private ShooterStates currentShooterState;

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

        high.setNeutralMode(NeutralModeValue.Brake);
        mid.setNeutralMode(NeutralModeValue.Brake);
        low.setNeutralMode(NeutralModeValue.Brake);
        currentShooterState = ShooterStates.OFF;
    }

    public void periodic(){
        System.out.println("CURRENT SHOOTER STATE: " + currentShooterState);
        if (currentShooterState == ShooterStates.INTAKING){
            high.setControl(dutyCycleOut.withOutput(0));
            mid.setControl(dutyCycleOut.withOutput(0));
            low.setControl(dutyCycleOut.withOutput(LOW_INTAKING_SPEED));
        }else if (currentShooterState == ShooterStates.WARMUP){ //same as speaking holding but doesnt assume we have a note
            high.setControl(dutyCycleOut.withOutput(HIGH_SPEAKER_SPEED));
            mid.setControl(dutyCycleOut.withOutput(-MID_SPEAKER_SPEED));
            low.setControl(dutyCycleOut.withOutput(LOW_INTAKING_SPEED));
        }else if (currentShooterState == ShooterStates.AMP_HOLDING) { // DO NOT TOUCH THESE VALUES!!
            high.setControl(dutyCycleOut.withOutput(0));
            mid.setControl(dutyCycleOut.withOutput(TESTING_SPEED));//(AMP_SPEED));//.35 IS PERFECT IN LAB, BUT .5 (SAME AS IN AMP WORKS BETTER IN PRACTICE) 
            low.setControl(dutyCycleOut.withOutput(0));
        } else if(currentShooterState == ShooterStates.SPEAKER_HOLDING){
            high.setControl(dutyCycleOut.withOutput(HIGH_SPEAKER_SPEED));
            mid.setControl(dutyCycleOut.withOutput(-MID_SPEAKER_SPEED)); //negative
            low.setControl(dutyCycleOut.withOutput(0));
        }else if(currentShooterState == ShooterStates.AMP){ // DO NOT TOUCH THESE VALUES!!
            high.setControl(dutyCycleOut.withOutput(TESTING_SPEED)); //AMP SPEED FOR MID/HIGH AT .35 WORKS!!!! 3/2 .45 WORKS FOR SHOOTING INTO AMP
            mid.setControl(dutyCycleOut.withOutput(TESTING_SPEED));
            low.setControl(dutyCycleOut.withOutput(AMP_SPEED)); // LOW SPEED AT .25 IS GREAT, and .35 is better
        }else if(currentShooterState == ShooterStates.SPEAKER){
            high.setControl(dutyCycleOut.withOutput(HIGH_SPEAKER_SPEED));
            mid.setControl(dutyCycleOut.withOutput(-MID_SPEAKER_SPEED)); //negative
            low.setControl(dutyCycleOut.withOutput(LOW_SHOOTING_SPEED)); //TESTING
        }else if(currentShooterState == ShooterStates.OFF){
            high.setControl(dutyCycleOut.withOutput(0));
            mid.setControl(dutyCycleOut.withOutput(0));
            low.setControl(dutyCycleOut.withOutput(0));
        } else if(currentShooterState == ShooterStates.TESTING){
            //high.setControl(dutyCycleOut.withOutput(TESTING_SPEED));
            mid.setControl(dutyCycleOut.withOutput(TESTING_SPEED));
            // low.setControl(dutyCycleOut.withOutput(TESTING_SPEED));
        }else{
            high.setControl(dutyCycleOut.withOutput(0));
            mid.setControl(dutyCycleOut.withOutput(0));
            low.setControl(dutyCycleOut.withOutput(0));
            System.out.println("====UNRECOGNIZED SHOOTER STATE!!!!!==== current shooter state: " + currentShooterState);
        }
    }

    public void setState(ShooterStates newState) {
        currentShooterState = newState;
    }
}