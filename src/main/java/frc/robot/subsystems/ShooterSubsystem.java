package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class ShooterSubsystem {
    // private CANSparkMax lowLeft = new CANSparkMax(0, MotorType.kBrushless); 
    private TalonFX highLeft = new TalonFX(41); //not sure if these should be on the left or the right
    private TalonFX midRight = new TalonFX(42); //but there is sparkmax low, then talon on either side
    private final double AMPSPEED;
    private final double SPEAKERSPEED;

    public static enum ShooterStates {
        OFF,
        AMP,
        SPEAKER;
    }

    private ShooterStates currentState = ShooterStates.AMP;
    
    public ShooterSubsystem() { //nothing to pass in?
        AMPSPEED = 0.25;
        SPEAKERSPEED = 0.5;
    }

    public void init(){
        currentState = ShooterStates.OFF;
    }

    public void periodic(){
        if(currentState == ShooterStates.AMP){//check negative signs here
            //lowLeft.set(-AMPSPEED);
            highLeft.set(ControlMode.PercentOutput, AMPSPEED);
            midRight.set(ControlMode.PercentOutput, AMPSPEED);
        }else if(currentState == ShooterStates.SPEAKER){//check negative signs here
            //lowLeft.set(-SPEAKERSPEED);
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
}
