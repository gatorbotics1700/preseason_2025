package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.subsystems.IntakeSubsystem.IntakeStates;
import frc.robot.Constants;


public class ShooterSubsystem {
    
    private TalonFX high = new TalonFX(Constants.SHOOTER_HIGH_CAN_ID); 
    private TalonFX mid = new TalonFX(Constants.SHOOTER_MID_CAN_ID); //but there is sparkmax low, then talon on either side
    private TalonFX ampMotor;
    private final double AMPSPEED = 0.25;
    private final double HIGHSPEAKERSPEED = 0.8;
    private final double MIDSPEAKERSPEED = 0.7;

    public static enum ShooterStates {
        OFF,
        LOADING_TO_SHOOTER,
        AMP,
        SPEAKER;
    }

    private ShooterStates currentState; //REVIEW: previously initialized to ShooterStates.AMP
    
    public ShooterSubsystem() {
        high = new TalonFX(Constants.SHOOTER_HIGH_CAN_ID);
        mid = new TalonFX(Constants.SHOOTER_MID_CAN_ID);
        ampMotor = new TalonFX(Constants.AMP_MOTOR_CAN_ID);
        init();
    }

    public void init(){
        currentState = ShooterStates.OFF;
    }

    public void periodic(){
        if (currentState == ShooterStates.LOADING_TO_SHOOTER) {
            ampMotor.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, AMPSPEED);
            mid.set(ControlMode.PercentOutput, -AMPSPEED);
        } else if(currentState == ShooterStates.AMP){//check negative signs here
            /*if(!SensorSubsystem.getSeesNote()){//if no note seen
                lowLeft.set(-AMPSPEED);
            }else{
                lowLeft.set(0.0);//hold if sees note
            }*/
            high.set(ControlMode.PercentOutput, AMPSPEED);
            mid.set(ControlMode.PercentOutput, -AMPSPEED);
        }else if(currentState == ShooterStates.SPEAKER){//check negative signs here
            /*if(!SensorSubsystem.getSeesNote()){
                lowLeft.set(-SPEAKERSPEED);
            }else{
                lowLeft.set(0.0);//hold if sees note
            }*/
            high.set(ControlMode.PercentOutput, HIGHSPEAKERSPEED);
            mid.set(ControlMode.PercentOutput, -MIDSPEAKERSPEED);
        }else if(currentState == ShooterStates.OFF){
            //lowLeft.set(0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
        }else{
            System.out.println("====UNRECOGNIZED SHOOTER STATE!!!!!==== current shooter state: " + currentState);
        }

    }
    public void setState(ShooterStates newState) {
        currentState = newState;
    }

    public void setHigh(double inputPercentOutput){
        high.set(ControlMode.PercentOutput, inputPercentOutput);
    }

    public void setMid(double inputPercentOutput){
        mid.set(ControlMode.PercentOutput, inputPercentOutput);
    }

    /*
    public void setLowLeft(double inputPercentOutput){
        lowLeft.set(ControlMode.PercentOutput, inputPercentOutput);
    }
    */
}
