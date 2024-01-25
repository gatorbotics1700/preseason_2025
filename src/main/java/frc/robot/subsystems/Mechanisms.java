package frc.robot.subsystems;

import frc.robot.subsystems.*;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;

public class Mechanisms {

    public TransitionSubsystem transitionSubsystem;
    public ShooterSubsystem shooterSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public SensorSubsystem sensorSubsystem;

    public boolean loading;
    private boolean isFirst;
    private double timer;

    public static enum MechanismStates{
        INTAKING,
        HOLDING,
        SHOOTING_SPEAKER_R,
        SHOOTING_SPEAKER_B,
        SHOOTING_AMP,
        OFF;
    }

    private MechanismStates mechanismState;

    public Mechanisms(){
        transitionSubsystem = new TransitionSubsystem();
        shooterSubsystem = new ShooterSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        sensorSubsystem = new SensorSubsystem();
        init();
        loading = false;
        isFirst = true;
    }

    public void init(){
        transitionSubsystem.init();
        //shooterSubsystem.init();
        intakeSubsystem.init();

        mechanismState = MechanismStates.HOLDING; //fix
    }

    public void periodic(){
        if (mechanismState == MechanismStates.INTAKING){
            loading = false;
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.ON);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP); //default shooter on
            if (sensorSubsystem.seesNote){
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
                transitionSubsystem.setState(TransitionSubsystem.TransitionStates.OFF);
                mechanismState = MechanismStates.HOLDING;
            }
        }
        else if(mechanismState == MechanismStates.HOLDING){
            loading = false;
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.OFF);

        }
        else if(mechanismState == MechanismStates.SHOOTING_AMP){
            if (isFirst){
                loading = true;
                timer = System.currentTimeMillis();
                isFirst = false;
                transitionSubsystem.setState(TransitionSubsystem.TransitionStates.ON);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
            }
            if(System.currentTimeMillis()-timer >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirst = true;
            }
        }
        else if(mechanismState == MechanismStates.SHOOTING_SPEAKER_B){
            if (isFirst){
                loading = true;
                timer = System.currentTimeMillis();
                isFirst = false;
                transitionSubsystem.setState(TransitionSubsystem.TransitionStates.ON);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
            }
            if(System.currentTimeMillis()-timer >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirst = true;
            }
        }
         else if(mechanismState == MechanismStates.SHOOTING_SPEAKER_R){
            if (isFirst){
                loading = true;
                timer = System.currentTimeMillis();
                isFirst = false;
                transitionSubsystem.setState(TransitionSubsystem.TransitionStates.ON);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
            }
            if(System.currentTimeMillis()-timer >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirst = true;
            }
        }
        else if (mechanismState == MechanismStates.OFF){
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.OFF);
            loading = false;
        }
        else{
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.OFF);
            System.out.println("WHAT ARE YOU DOING----STATE NOT RECOGNIZED!!!!!");
        }

    }

     public void setState(MechanismStates newState){
            mechanismState = newState; 
    }

}
