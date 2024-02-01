package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.subsystems.IntakeSubsystem.IntakeStates;


public class ShooterSubsystem {
    // private CANSparkMax lowLeft = new CANSparkMax(0, MotorType.kBrushless); 
    private TalonFX highLeft = new TalonFX(41); //not sure if these should be on the left or the right
    private TalonFX midRight = new TalonFX(42); //but there is sparkmax low, then talon on either side
    private final double AMPSPEED = 0.25;
    private final double SPEAKERSPEED = 0.5;

    public static enum ShooterStates {
        OFF,
        AMP,
        SPEAKER;
    }

    private ShooterStates currentState; //REVIEW: previously initialized to ShooterStates.AMP
    
    public ShooterSubsystem() {
        init();
    }

    public void init(){
        currentState = ShooterStates.OFF;
    }

    public void periodic(){
        if(currentState == ShooterStates.AMP){//check negative signs here
            /*if(!SensorSubsystem.getSeesNote()){//if no note seen
                lowLeft.set(-AMPSPEED);
            }else{
                lowLeft.set(0.0);//hold if sees note
            }*/
            highLeft.set(ControlMode.PercentOutput, AMPSPEED);
            midRight.set(ControlMode.PercentOutput, -AMPSPEED);
        }else if(currentState == ShooterStates.SPEAKER){//check negative signs here
            /*if(!SensorSubsystem.getSeesNote()){
                lowLeft.set(-SPEAKERSPEED);
            }else{
                lowLeft.set(0.0);//hold if sees note
            }*/
            highLeft.set(ControlMode.PercentOutput, SPEAKERSPEED);
            midRight.set(ControlMode.PercentOutput, -SPEAKERSPEED);
        }else if(currentState == ShooterStates.OFF){
            //lowLeft.set(0);
            highLeft.set(ControlMode.PercentOutput, 0);
            midRight.set(ControlMode.PercentOutput, 0);
        }else{
            System.out.println("===========UNRECOGNIZED SHOOTER STATE!!!!!===========");
        }

    }
    public void setState(ShooterStates newState) {
        currentState = newState;
    }

    public void setHighLeft(double inputPercentOutput){
        highLeft.set(ControlMode.PercentOutput, inputPercentOutput);
    }

    public void setMidRight(double inputPercentOutput){
        midRight.set(ControlMode.PercentOutput, inputPercentOutput);
    }

    /*
    public void setLowLeft(double inputPercentOutput){
        lowLeft.set(ControlMode.PercentOutput, inputPercentOutput);
    }
    */
}
