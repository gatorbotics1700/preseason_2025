package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.subsystems.IntakeSubsystem.IntakeStates;

public class ShooterSubsystem {
    private TalonFX lowLeft;
    private TalonFX midLeft;
    private TalonFX highLeft;
    private TalonFX lowRight;
    private TalonFX midRight;
    private TalonFX highRight;

    private final double AMPSPEED = 0.25;
    private final double SPEAKERHIGHSPEED = 0.5;
    private final double SPEAKERLOWSPEED = 0.45;

    public ShooterSubsystem() {
        lowLeft = new TalonFX(0);
        midLeft = new TalonFX(0);
        highLeft = new TalonFX(0);
        lowRight = new TalonFX(0);
        midRight = new TalonFX(0);
        highRight = new TalonFX(0);
        init();
    }

    public static enum ShooterStates {
        OFF,
        AMP, //default on
        SPEAKER_BLUE,
        SPEAKER_RED;
    }

    private ShooterStates currentState = ShooterStates.OFF;

    public void init(){
        System.out.println("Shooting Init!");
        lowLeft.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        lowLeft.setNeutralMode(NeutralModeValue.Coast); 
        midLeft.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        midLeft.setNeutralMode(NeutralModeValue.Coast); 
        highLeft.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        highLeft.setNeutralMode(NeutralModeValue.Coast); 
        lowRight.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        lowRight.setNeutralMode(NeutralModeValue.Coast); 
        midRight.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        midRight.setNeutralMode(NeutralModeValue.Coast); 
        highRight.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        highRight.setNeutralMode(NeutralModeValue.Coast); 
        setState(ShooterStates.OFF);
    }

    public void periodic(){
        if(currentState == ShooterStates.AMP){
            lowLeft.set(-AMPSPEED);
            midLeft.set(AMPSPEED);
            highLeft.set(AMPSPEED);
            lowRight.set(-AMPSPEED);
            midRight.set(AMPSPEED);
            highRight.set(AMPSPEED);
            //TODO: after x amount of time, set shot = true
        }
        else if(currentState == ShooterStates.SPEAKER_BLUE){
            lowLeft.set(-SPEAKERHIGHSPEED);
            midLeft.set(-SPEAKERHIGHSPEED);
            highLeft.set(SPEAKERHIGHSPEED);
            lowRight.set(-SPEAKERLOWSPEED);
            midRight.set(-SPEAKERLOWSPEED);
            highRight.set(SPEAKERLOWSPEED);
            //TODO: after x amount of time, set shot = true

        }
        else if(currentState == ShooterStates.SPEAKER_RED){
            lowLeft.set(-SPEAKERLOWSPEED);
            midLeft.set(-SPEAKERLOWSPEED);
            highLeft.set(SPEAKERLOWSPEED);
            lowRight.set(-SPEAKERHIGHSPEED);
            midRight.set(-SPEAKERHIGHSPEED);
            highRight.set(SPEAKERHIGHSPEED);
            //TODO: after x amount of time, set shot = true
        }
        else if(currentState == ShooterStates.OFF){
            lowLeft.set(0);
            midLeft.set(0);
            highLeft.set(0);
            lowRight.set(0);
            midRight.set(0);
            highRight.set(0);
        }
        else{
            System.out.println("===========UNRECOGNIZED SHOOTER STATE!!!!!===========");
            lowLeft.set(0);
            midLeft.set(0);
            highLeft.set(0);
            lowRight.set(0);
            midRight.set(0);
            highRight.set(0);
        }

    }
    public void setState(ShooterStates newState) {
        currentState = newState;
    }

    
}