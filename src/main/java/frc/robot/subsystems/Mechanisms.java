package frc.robot.subsystems;

import frc.robot.subsystems.*;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;

public class Mechanisms {

    public TransitionSubsystem transitionSubsystem;
    public ShooterSubsystem shooterSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public SensorSubsystem sensorSubsystem;

    private boolean loadingToShooter;
    private boolean isFirstTimeInState;
    private double stateStartTime;

    public static enum MechanismStates{
        INTAKING,
        HOLDING,
        SHOOTING_SPEAKER,
        SHOOTING_AMP,
        OFF;
    }

    private MechanismStates mechanismState;

    public Mechanisms(){
        transitionSubsystem = new TransitionSubsystem();
        shooterSubsystem = new ShooterSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        sensorSubsystem = new SensorSubsystem();
        loadingToShooter = false;
        isFirstTimeInState = true;
        init();
    }

    public void init(){
        transitionSubsystem.init();
        //shooterSubsystem.init();
        intakeSubsystem.init();
        sensorSubsystem.init();

        mechanismState = MechanismStates.HOLDING; //TODO: figure out what state to start in
    }

    public void periodic(){
        if (mechanismState == MechanismStates.INTAKING){
            loadingToShooter = false;
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.ON);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP); //default shooter on
            if (sensorSubsystem.getSeesNote()){
                mechanismState = MechanismStates.HOLDING;
            }
        }
        else if(mechanismState == MechanismStates.HOLDING){
            loadingToShooter = false;
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.OFF);

        }
        else if(mechanismState == MechanismStates.SHOOTING_AMP){
            if (isFirstTimeInState){
                loadingToShooter = true;
                stateStartTime = System.currentTimeMillis();
                isFirstTimeInState = false;
                transitionSubsystem.setState(TransitionSubsystem.TransitionStates.ON);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirstTimeInState = true;
            }
        }
         else if(mechanismState == MechanismStates.SHOOTING_SPEAKER){
            if (isFirstTimeInState){
                loadingToShooter = true;
                stateStartTime = System.currentTimeMillis();
                isFirstTimeInState = false;
                transitionSubsystem.setState(TransitionSubsystem.TransitionStates.ON);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirstTimeInState = true;
            }
        }
        else if (mechanismState == MechanismStates.OFF){
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.OFF);
            loadingToShooter = false;
        }
        else{
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            transitionSubsystem.setState(TransitionSubsystem.TransitionStates.OFF);
            System.out.println("WHAT ARE YOU DOING----STATE NOT RECOGNIZED!!!!!");
            System.out.println("CURRENT STATE: " + mechanismState);
        }

    }

     public void setState(MechanismStates newState){
            mechanismState = newState; 
    }

    public boolean getLoadingToShooterStatus(){
        return loadingToShooter;
    }

}
