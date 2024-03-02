package frc.robot.subsystems;

import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.PivotSubsystem.PivotStates;
import frc.robot.subsystems.IntakeSubsystem.IntakeStates;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;

public class Mechanisms {

    public ShooterSubsystem shooterSubsystem; //TODO why is this public
    private IntakeSubsystem intakeSubsystem;
    private SensorSubsystem sensorSubsystem;
    public PivotSubsystem pivotSubsystem; //TODO so we can refer to it for testing; make private

    private double stateStartTime;
    private static final int SHOOTING_TIME = 2500;

    private MechanismStates mechanismState;

    public static enum MechanismStates{
        INTAKING,
        INTAKING_WITH_SHOOTER_WARMUP, //needed for auto only
        AMP_HOLDING,
        SPEAKER_HOLDING,
        SHOOTING_SPEAKER,
        SHOOTING_AMP,
        OFF;
    }

    public Mechanisms(){
        shooterSubsystem = new ShooterSubsystem();
        sensorSubsystem = new SensorSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        pivotSubsystem = new PivotSubsystem();
        init();
    }

    public void init(){
        shooterSubsystem.init();
        intakeSubsystem.init();
        sensorSubsystem.init();
        pivotSubsystem.init();

        setState(MechanismStates.OFF); //TODO: figure out what state to start in
    }

    public void periodic(){
        System.out.println("=======CURRENT STATE IS: " + mechanismState + "=======");
        if (mechanismState == MechanismStates.INTAKING){
            // pivotSubsystem.setState(PivotStates.SPEAKER); //need to be at speaker angle in order to intake
            intakeSubsystem.setState(IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterStates.INTAKING);
            if (sensorSubsystem.detectNote()){
                setState(MechanismStates.SPEAKER_HOLDING); //TODO: this will change depending on if we're in teleop or auto        
            }
        } else if (mechanismState == MechanismStates.INTAKING_WITH_SHOOTER_WARMUP){ //just for auto
            // pivotSubsystem.setState(PivotStates.SPEAKER);
            intakeSubsystem.setState(IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterStates.WARMUP);
            if (sensorSubsystem.detectNote()){
                setState(MechanismStates.SPEAKER_HOLDING); //TODO: this will change depending on if we're in teleop or auto        
            }
        } else if(mechanismState == MechanismStates.AMP_HOLDING){
            //pivotSubsystem.setState(PivotStates.AMP); 
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.AMP_HOLDING);
            //we stop transition motor in the shooter subsystem right now
        } else if(mechanismState == MechanismStates.SPEAKER_HOLDING){
            //pivotSubsystem.setState(PivotStates.SPEAKER);
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.SPEAKER_HOLDING);
        } else if(mechanismState == MechanismStates.SHOOTING_AMP){
            //if(!pivotSubsystem.getAmpLimitSwitch()){ // TODO: maybe add an override for safety block
                intakeSubsystem.setState(IntakeStates.OFF);
                shooterSubsystem.setState(ShooterStates.AMP);
                if(System.currentTimeMillis()-stateStartTime >= SHOOTING_TIME){ 
                    setState(MechanismStates.INTAKING); //sets to intaking right after shooting
                }
           // } else {
                System.out.println("======BLOCKED AMP SHOOTING BECAUSE AMP NOT UP======");
           // }
        } else if(mechanismState == MechanismStates.SHOOTING_SPEAKER){
            // pivotSubsystem.setState(PivotStates.SPEAKER); 
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.SPEAKER);
            if(System.currentTimeMillis()-stateStartTime >= SHOOTING_TIME){ 
                setState(MechanismStates.INTAKING); //sets to intaking right after shooting
            }
        } else if (mechanismState == MechanismStates.OFF){
            // pivotSubsystem.setState(PivotStates.OFF);
            shooterSubsystem.setState(ShooterStates.OFF);
            intakeSubsystem.setState(IntakeStates.OFF);
        } else {
            pivotSubsystem.setState(PivotStates.OFF);
            shooterSubsystem.setState(ShooterStates.OFF);
            intakeSubsystem.setState(IntakeStates.OFF);
            System.out.println("WHAT ARE YOU DOING----STATE NOT RECOGNIZED!!!!! CURRENT STATE: " + mechanismState);
        }
        intakeSubsystem.periodic();
        shooterSubsystem.periodic();
    }

    public void setState(MechanismStates newState){
        mechanismState = newState;
        stateStartTime = System.currentTimeMillis();
    }

    public MechanismStates getMechanismState(){
        return mechanismState;
    }

    public SensorSubsystem getSensorSubsystem(){
        return sensorSubsystem;
    }
}