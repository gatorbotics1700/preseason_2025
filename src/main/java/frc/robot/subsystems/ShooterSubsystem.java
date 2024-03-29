package frc.robot.subsystems;

//v6 for mid, high motors
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.DutyCycleOut;

import frc.robot.Constants;
 

public class ShooterSubsystem {
    
    private final DutyCycleOut lowDutyCycleOut = new DutyCycleOut(0);
    private final DutyCycleOut midDutyCycleOut = new DutyCycleOut(0);
    private final DutyCycleOut highDutyCycleOut = new DutyCycleOut(0);
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX low;

    private final double TESTING_SPEED = 0.5;
    private final double AMP_SPEED = 0.2; //35; //0.3; // DO NOT TOUCH THIS VALUE!!
    private final double LOW_SHOOTING_SPEED = 0.7; //0.7;
    private final double HIGH_SPEAKER_SPEED = 0.7; //0.7;
    private final double MID_SPEAKER_SPEED = 0.7; //0.7;
    private final double LOW_INTAKING_SPEED = 0.3;
    private final double HIGH_STAGE_SPEED = 0.7; //watch current limits
    private final double MID_STAGE_SPEED = 0.7; //watch current limits
    
    public static enum ShooterStates {
        OFF,
        INTAKING,
        SPEAKER_WARMUP, //for auto only
        AMP_HOLDING,
        SPEAKER_HOLDING,
        AMP_SHOOTING,
        AMP_WARMUP,
        SPEAKER_SHOOTING,
        SWALLOWING,
        TESTING; 
    }

    private ShooterStates currentShooterState;

    public ShooterSubsystem() {
        high = new TalonFX(Constants.SHOOTER_HIGH_CAN_ID);//TODO mid and high are KRAKENS!!!!
        mid = new TalonFX(Constants.SHOOTER_MID_CAN_ID);
        low = new TalonFX(Constants.LOW_MOTOR_CAN_ID);
        
        init();
    }

    public void init(){
        high.setInverted(true);
        mid.setInverted(false);
        low.setInverted(false);

        high.setNeutralMode(NeutralModeValue.Coast);
        mid.setNeutralMode(NeutralModeValue.Coast);
        low.setNeutralMode(NeutralModeValue.Brake);
        currentShooterState = ShooterStates.OFF;
    }

    public void periodic(){
        System.out.println("CURRENT SHOOTER STATE: " + currentShooterState);
        if (currentShooterState == ShooterStates.INTAKING){
            high.setControl(highDutyCycleOut.withOutput(0));
            mid.setControl(midDutyCycleOut.withOutput(0));
            low.setControl(lowDutyCycleOut.withOutput(LOW_INTAKING_SPEED));
        } else if (currentShooterState == ShooterStates.SPEAKER_WARMUP){ //same as speaking holding but doesnt assume we have a note
            high.setControl(highDutyCycleOut.withOutput(HIGH_SPEAKER_SPEED));
            mid.setControl(midDutyCycleOut.withOutput(-MID_SPEAKER_SPEED));
            low.setControl(lowDutyCycleOut.withOutput(LOW_INTAKING_SPEED));
        } else if (currentShooterState == ShooterStates.AMP_WARMUP){ //same as amp holding but doesnt assume we have a note
            high.setControl(highDutyCycleOut.withOutput(0));
            mid.setControl(midDutyCycleOut.withOutput(TESTING_SPEED));
            low.setControl(lowDutyCycleOut.withOutput(LOW_INTAKING_SPEED));
        } else if (currentShooterState == ShooterStates.AMP_HOLDING) { // DO NOT TOUCH THESE VALUES!!
            high.setControl(highDutyCycleOut.withOutput(0));
            mid.setControl(midDutyCycleOut.withOutput(TESTING_SPEED));//(AMP_SPEED));//.35 IS PERFECT IN LAB, BUT .5 (SAME AS IN AMP WORKS BETTER IN PRACTICE) 
            low.setControl(lowDutyCycleOut.withOutput(0));
        } else if(currentShooterState == ShooterStates.SPEAKER_HOLDING){
            high.setControl(highDutyCycleOut.withOutput(HIGH_STAGE_SPEED));
            mid.setControl(midDutyCycleOut.withOutput(-MID_STAGE_SPEED)); //negative
            low.setControl(lowDutyCycleOut.withOutput(0));
        } else if(currentShooterState == ShooterStates.AMP_SHOOTING){ // DO NOT TOUCH THESE VALUES!!
            high.setControl(highDutyCycleOut.withOutput(0)); //TESTING_SPEED)); //AMP SPEED FOR MID/HIGH AT .35 WORKS!!!! 3/2 .45 WORKS FOR SHOOTING INTO AMP
            mid.setControl(midDutyCycleOut.withOutput(TESTING_SPEED));
            low.setControl(lowDutyCycleOut.withOutput(AMP_SPEED)); // LOW SPEED AT .25 IS GREAT, and .35 is better
        } else if(currentShooterState == ShooterStates.SPEAKER_SHOOTING){
            high.setControl(highDutyCycleOut.withOutput(HIGH_STAGE_SPEED));
            mid.setControl(midDutyCycleOut.withOutput(-MID_STAGE_SPEED)); //negative
            low.setControl(lowDutyCycleOut.withOutput(LOW_SHOOTING_SPEED));
        } else if(currentShooterState == ShooterStates.SWALLOWING){ 
            high.setControl(highDutyCycleOut.withOutput(-TESTING_SPEED)); // TODO double check if we need high running - prob good idea in case note gets stuck between high and mid
            mid.setControl(midDutyCycleOut.withOutput(TESTING_SPEED));
            low.setControl(lowDutyCycleOut.withOutput(-AMP_SPEED));    
        } else if(currentShooterState == ShooterStates.OFF){
            high.setControl(highDutyCycleOut.withOutput(0));
            mid.setControl(midDutyCycleOut.withOutput(0));
            low.setControl(lowDutyCycleOut.withOutput(0));
        } else if(currentShooterState == ShooterStates.TESTING){
            //high.setControl(highDutyCycleOut.withOutput(TESTING_SPEED));
            mid.setControl(midDutyCycleOut.withOutput(TESTING_SPEED));
            // low.setControl(lowDutyCycleOut.withOutput(TESTING_SPEED));
        }else{
            high.setControl(highDutyCycleOut.withOutput(0));
            mid.setControl(midDutyCycleOut.withOutput(0));
            low.setControl(lowDutyCycleOut.withOutput(0));
            System.out.println("====UNRECOGNIZED SHOOTER STATE!!!!!==== current shooter state: " + currentShooterState);
        }
    }

    public void setState(ShooterStates newState) {
        currentShooterState = newState;
    }

    public void testShooter(){
        low.setControl(lowDutyCycleOut.withOutput(0.2));
    }
}