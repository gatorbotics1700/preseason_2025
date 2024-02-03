package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.subsystems.IntakeSubsystem.IntakeStates;
import frc.robot.Constants;


public class ShooterSubsystem {
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX ampMotor;
    private final double AMPSPEED = 0.25;
    private final double HIGHSPEAKERSPEED = 0.8;
    private final double MIDSPEAKERSPEED = 0.7;
    private IntakeSubsystem intakeSubsystem;

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
        ampMotor = new TalonFX(Constants.AMP_MOTOR_CAN_ID);
        init();
    }

    public void init(){
        currentState = ShooterStates.OFF;
        intakeSubsystem = new IntakeSubsystem();
    }

    public void periodic(){
        if (currentState == ShooterStates.INTAKING){
            ampMotor.set(ControlMode.PercentOutput, AMPSPEED);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
            
        }else if (currentState == ShooterStates.AMP_HOLDING) {
            ampMotor.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, AMPSPEED);
            mid.set(ControlMode.PercentOutput, -AMPSPEED);
        } else if(currentState == ShooterStates.SPEAKER_HOLDING){
            ampMotor.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, HIGHSPEAKERSPEED);
            mid.set(ControlMode.PercentOutput, MIDSPEAKERSPEED);
        }else if(currentState == ShooterStates.AMP){//check negative signs here
            ampMotor.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, AMPSPEED);
            mid.set(ControlMode.PercentOutput, -AMPSPEED);
        }else if(currentState == ShooterStates.SPEAKER){//check negative signs here
            ampMotor.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, HIGHSPEAKERSPEED);
            mid.set(ControlMode.PercentOutput, -MIDSPEAKERSPEED);
        }else if(currentState == ShooterStates.OFF){
            ampMotor.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
        }else{
            ampMotor.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
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
}
