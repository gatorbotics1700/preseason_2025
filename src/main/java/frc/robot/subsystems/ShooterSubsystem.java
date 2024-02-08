package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.subsystems.IntakeSubsystem.IntakeStates;
import frc.robot.Constants;


public class ShooterSubsystem {
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX low;
    // TODO: Patricia says to set motors to brake

    private final double AMP_SPEED = 0.2; // DO NOT TOUCH THIS VALUE!!
    private final double LOW_SHOOTING_SPEED = 0.7;
    private final double HIGH_SPEAKER_SPEED = 0.8;
    private final double MID_SPEAKER_SPEED = 0.8;
    
    public boolean holding;

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
        low = new TalonFX(Constants.LOW_MOTOR_CAN_ID);
        high.setInverted(true); 
        mid.setInverted(false); 
        low.setInverted(false);
        init();
    }

    public void init(){
        currentState = ShooterStates.OFF;
        low.setNeutralMode(NeutralMode.Coast);
        mid.setNeutralMode(NeutralMode.Coast);
        high.setNeutralMode(NeutralMode.Coast);

        holding = false;
    }

    public void periodic(){
        System.out.println("CURRENT SHOOTER STATE: " + currentState);
        if (currentState == ShooterStates.INTAKING){
            if(!holding){//if intaking, set low to intaking
                  low.set(ControlMode.PercentOutput, 0.18);//2/7 used to be AMP_SPEED            
            }else{//if holding, stop low
                low.set(ControlMode.Position, 0);
            }

            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
        }else if (currentState == ShooterStates.AMP_HOLDING) { // DO NOT TOUCH THESE VALUES!!
            //low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);
        } else if(currentState == ShooterStates.SPEAKER_HOLDING){
            //low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);//how to change direction for amp vs speaker?
        }else if(currentState == ShooterStates.AMP){ // DO NOT TOUCH THESE VALUES!!
            low.set(ControlMode.PercentOutput, AMP_SPEED);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);
        }else if(currentState == ShooterStates.SPEAKER){//check negative signs here
            System.out.println("==========WE ARE SHOOTING==========");
            low.set(ControlMode.PercentOutput, LOW_SHOOTING_SPEED);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);//TODO walk through logic
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);//when to flip to negative?? amp is never an option
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
    public void setState(ShooterStates newState) {
        currentState = newState;
    }

    public void setLow(double speed){
        low.set(ControlMode.PercentOutput, speed);  
    }
}
