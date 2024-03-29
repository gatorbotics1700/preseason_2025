package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.PivotSubsystem.PivotStates;
import frc.robot.subsystems.IntakeSubsystem.IntakeStates;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;

public class Mechanisms {

    public ShooterSubsystem shooterSubsystem; //TODO: make private
    public IntakeSubsystem intakeSubsystem; //TODO: make private
    public SensorSubsystem sensorSubsystem;
    public PivotSubsystem pivotSubsystem; //TODO so we can refer to it for testing; make private

    private double stateStartTime;
    private static final int SPEAKER_SHOOTING_TIME = 2000;

    private MechanismStates mechanismState;

    public static enum MechanismStates{
        INTAKING,
        INTAKING_WITH_AMP_WARMUP, //needed for auto only
        INTAKING_WITH_SPEAKER_WARMUP, //needed for auto only
        AMP_HOLDING,
        SUBWOOFER_HOLDING,
        PODIUM_HOLDING,
        SHOOTING_AMP,
        SHOOTING_SUBWOOFER,
        SHOOTING_PODIUM,
        SWALLOWING,
        TESTING,
        MANUAL,
        OFF;
    }

    public Mechanisms(){
        shooterSubsystem = Robot.m_shooterSubsystem;
        sensorSubsystem = Robot.m_sensorSubsystem;
        intakeSubsystem = Robot.m_intakeSubsystem;
        pivotSubsystem = Robot.m_pivotSubsystem;
        init();
    }

    public void init(){
        shooterSubsystem.init();
        intakeSubsystem.init();
        pivotSubsystem.init();
        sensorSubsystem.init();

        setState(MechanismStates.MANUAL);
    }

    public void periodic(){
        System.out.println("=======CURRENT STATE IS: " + mechanismState + "=======");
        if (mechanismState == MechanismStates.INTAKING){
            //System.out.println("**************intaking*************");
            pivotSubsystem.setState(PivotStates.AMP); //need to be at amp angle in order to intake
            intakeSubsystem.setState(IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterStates.INTAKING);
            if (sensorSubsystem.detectNote()){
                setState(MechanismStates.OFF);      
            }
        } else if (mechanismState == MechanismStates.INTAKING_WITH_AMP_WARMUP){ //just for auto
            pivotSubsystem.setState(PivotStates.AMP);
            intakeSubsystem.setState(IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterStates.AMP_WARMUP);
            if (sensorSubsystem.detectNote()){
                setState(MechanismStates.AMP_HOLDING); //TODO: this will change depending on if we're in teleop or auto        
            }
        } else if (mechanismState == MechanismStates.INTAKING_WITH_SPEAKER_WARMUP){ //just for auto
            pivotSubsystem.setState(PivotStates.AMP);
            intakeSubsystem.setState(IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterStates.SPEAKER_WARMUP);
            if (sensorSubsystem.detectNote()){
                setState(MechanismStates.SUBWOOFER_HOLDING); //TODO: change pivot angle for auto speaker shooting distance
            }
        }else if(mechanismState == MechanismStates.AMP_HOLDING){
            pivotSubsystem.setState(PivotStates.AMP); 
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.AMP_HOLDING);
            //we stop transition motor in the shooter subsystem right now
        } else if(mechanismState == MechanismStates.SUBWOOFER_HOLDING){
            pivotSubsystem.setState(PivotStates.SUBWOOFER);
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.SPEAKER_HOLDING);
        }else if (mechanismState == MechanismStates.PODIUM_HOLDING){
            pivotSubsystem.setState(PivotStates.PODIUM);
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.SPEAKER_HOLDING);
        } else if(mechanismState == MechanismStates.SHOOTING_AMP){
            pivotSubsystem.setState(PivotStates.AMP);
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.AMP_SHOOTING); 
        }else if(mechanismState == MechanismStates.SHOOTING_SUBWOOFER){
            pivotSubsystem.setState(PivotStates.SUBWOOFER);
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.SPEAKER_SHOOTING);
           // System.out.println("**********IN SHOOTING SPEAKER**********");
            if(System.currentTimeMillis()-stateStartTime >= SPEAKER_SHOOTING_TIME){ 
              //  System.out.println("++++++++++SETTING INTAKING+++++++++");
                setState(MechanismStates.INTAKING);
            }
        }else if(mechanismState == MechanismStates.SHOOTING_PODIUM){
            pivotSubsystem.setState(PivotStates.PODIUM);
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.SPEAKER_SHOOTING);
           // System.out.println("**********IN SHOOTING SPEAKER**********");
            if(System.currentTimeMillis()-stateStartTime >= SPEAKER_SHOOTING_TIME){ 
              //  System.out.println("++++++++++SETTING INTAKING+++++++++");
                setState(MechanismStates.INTAKING);
            }
        } else if(mechanismState == MechanismStates.SWALLOWING){
            pivotSubsystem.setState(PivotStates.AMP);
            intakeSubsystem.setState(IntakeStates.OFF);
            shooterSubsystem.setState(ShooterStates.SWALLOWING);
            if (sensorSubsystem.detectNote()){ //|| sensorSubsystem.isBeamBroken()){ // once note is in place, set back to holding
                setState(MechanismStates.OFF);      
            }        
        } else if (mechanismState == MechanismStates.OFF){
            //let pivot stay wherever it was before
            shooterSubsystem.setState(ShooterStates.OFF);
            intakeSubsystem.setState(IntakeStates.OFF);
        }else if (mechanismState == MechanismStates.TESTING){
            //pivotSubsystem.setState(PivotStates.MANUAL);
            shooterSubsystem.setState(ShooterStates.TESTING);
            //intakeSubsystem.setState(IntakeStates.INTAKING);
        }else if (mechanismState == MechanismStates.MANUAL){
            pivotSubsystem.setState(PivotStates.MANUAL);
        } else {
            pivotSubsystem.setState(PivotStates.PANIC_OFF);
            shooterSubsystem.setState(ShooterStates.OFF);
            intakeSubsystem.setState(IntakeStates.OFF);
            System.out.println("WHAT ARE YOU DOING----STATE NOT RECOGNIZED!!!!! CURRENT STATE: " + mechanismState);
        }
        pivotSubsystem.periodic();
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