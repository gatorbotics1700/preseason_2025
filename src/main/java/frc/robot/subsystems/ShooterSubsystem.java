package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

public class ShooterSubsystem {
    private TalonFX lowLeft = new TalonFX(0);
    private TalonFX midLeft = new TalonFX(0);
    private TalonFX highLeft = new TalonFX(0);
    private TalonFX lowRight = new TalonFX(0);
    private TalonFX midRight = new TalonFX(0);
    private TalonFX highRight = new TalonFX(0);
    private final double AMPSPEED = 0.25;
    private final double SPEAKERHIGHSPEED = 0.5;
    private final double SPEAKERLOWSPEED = 0.45;

    public static enum ShooterStates {
        OFF,
        AMP,
        SPEAKER_BLUE,
        SPEAKER_RED;
    }

    private ShooterStates currentState = ShooterStates.OFF;

    public void periodic(){
        if(currentState == ShooterStates.AMP){
            lowLeft.set(-AMPSPEED);
            midLeft.set(AMPSPEED);
            highLeft.set(AMPSPEED);
            lowRight.set(-AMPSPEED);
            midRight.set(AMPSPEED);
            highRight.set(AMPSPEED);

        }
        else if(currentState == ShooterStates.SPEAKER_BLUE){
            lowLeft.set(-SPEAKERHIGHSPEED);
            midLeft.set(-SPEAKERHIGHSPEED);
            highLeft.set(SPEAKERHIGHSPEED);
            lowRight.set(-SPEAKERLOWSPEED);
            midRight.set(-SPEAKERLOWSPEED);
            highRight.set(SPEAKERLOWSPEED);
        }
        else if(currentState == ShooterStates.SPEAKER_RED){
            lowLeft.set(-SPEAKERLOWSPEED);
            midLeft.set(-SPEAKERLOWSPEED);
            highLeft.set(SPEAKERLOWSPEED);
            lowRight.set(-SPEAKERHIGHSPEED);
            midRight.set(-SPEAKERHIGHSPEED);
            highRight.set(SPEAKERHIGHSPEED);
        }
        else if(currentState == ShooterStates.OFF){

        }
        else{
            System.out.println("===========UNRECOGNIZED SHOOTER STATE!!!!!===========");
        }

    }
    public void setState(ShooterStates newState) {
        currentState = newState;
    }

    
}