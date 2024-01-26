package frc.robot.subsystems;

import frc.robot.subsystems.*;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;

public class Mechanisms {

    public ShooterSubsystem shooterSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public SensorSubsystem sensorSubsystem;
    public ElevatorSubsystem elevatorSubsystem;

    private boolean isFirstTimeInState;
    private double stateStartTime;

    public static enum MechanismStates{
        INTAKING,
        HOLDING,
        SHOOTING_SPEAKER,
        SHOOTING_AMP,
        OFF,
        /*
        ZERO, 
        LOW_ELEVATOR_HEIGHT,  
        AMP_HEIGHT,  
        //MANUAL, later decide if we want manual setting
        STOPPED; 
        */
    }

    private MechanismStates mechanismState;

    public Mechanisms(){
        shooterSubsystem = new ShooterSubsystem();
        sensorSubsystem = new SensorSubsystem();
        elevatorSubsystem = new ElevatorSubsystem();
        intakeSubsystem = new IntakeSubsystem(sensorSubsystem);
        isFirstTimeInState = true;
        init();
    }

    public void init(){
        //shooterSubsystem.init();
        intakeSubsystem.init();
        sensorSubsystem.init();
        //elevatorSubsystem.init();

        mechanismState = MechanismStates.HOLDING; //TODO: figure out what state to start in
    }

    public void periodic(){
        if (mechanismState == MechanismStates.INTAKING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP); //default shooter on
            if (sensorSubsystem.getSeesNote()){
                mechanismState = MechanismStates.HOLDING;
            }
        } else if(mechanismState == MechanismStates.HOLDING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
        } else if(mechanismState == MechanismStates.SHOOTING_AMP){
            if (isFirstTimeInState){
                isFirstTimeInState = false;
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.LOADING_TO_SHOOTER);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirstTimeInState = true;
            }
        } else if(mechanismState == MechanismStates.SHOOTING_SPEAKER){
            if (isFirstTimeInState){
                isFirstTimeInState = false;
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.LOADING_TO_SHOOTER);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirstTimeInState = true;
            }
        } else if (mechanismState == MechanismStates.OFF){
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            sensorSubsystem.setState(SensorSubsystem.SensorStates.ON);
        } else {
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            System.out.println("WHAT ARE YOU DOING----STATE NOT RECOGNIZED!!!!!");
            System.out.println("CURRENT STATE: " + mechanismState);
        }
    }

     public void setState(MechanismStates newState){
            mechanismState = newState;
            stateStartTime = System.currentTimeMillis();
    }

}
