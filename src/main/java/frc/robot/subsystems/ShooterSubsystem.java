package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class ShooterSubsystem {
    //private CANSparkMax lowLeft = new CANSparkMax(0, MotorType.kBrushless); 
    private TalonFX highLeft = new TalonFX(41); //not sure if these should be on the left or the right
    private TalonFX midRight = new TalonFX(42); //but there is sparkmax low, then talon on either side
    private final double AMPSPEED = 0.25;
    private final double SPEAKERSPEED = 0.5;

    public static enum ShooterStates {
        OFF,
        AMP,
        SPEAKER;
    }

    private ShooterStates currentState = ShooterStates.AMP;

    public void periodic(){
        if(currentState == ShooterStates.AMP){//check negative signs here
            //lowLeft.set(-AMPSPEED);
            highLeft.set(AMPSPEED);
            midRight.set(AMPSPEED);
        }else if(currentState == ShooterStates.SPEAKER){//check negative signs here
            //lowLeft.set(-SPEAKERSPEED);
            highLeft.set(SPEAKERSPEED);
            midRight.set(-SPEAKERSPEED);
        }else if(currentState == ShooterStates.OFF){
            //lowLeft.set(0);
            highLeft.set(0);
            midRight.set(0);
        }else{
            System.out.println("===========UNRECOGNIZED SHOOTER STATE!!!!!===========");
        }

    }
    public void setState(ShooterStates newState) {
        currentState = newState;
    }
}
