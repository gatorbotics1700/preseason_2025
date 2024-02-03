package frc.robot.subsystems;

public class Mechanisms {

    public ShooterSubsystem shooterSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public SensorSubsystem sensorSubsystem;

    private boolean isFirstTimeInState;
    private double stateStartTime;

    private MechanismStates mechanismState;

    public static enum MechanismStates{
        INTAKING,
        HOLDING,
        SHOOTING_SPEAKER,
        SHOOTING_AMP,
        OFF;
    }

    public Mechanisms(){
        shooterSubsystem = new ShooterSubsystem();
        sensorSubsystem = new SensorSubsystem();
        intakeSubsystem = new IntakeSubsystem(/*sensorSubsystem*/);
        isFirstTimeInState = true;
        init();
    }

    public void init(){
        shooterSubsystem.init();
        intakeSubsystem.init();
        sensorSubsystem.init();

        setState(MechanismStates.HOLDING); //TODO: figure out what state to start in
    }

    public void periodic(){
        if (mechanismState == MechanismStates.INTAKING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP); //default shooter on
            if (sensorSubsystem.detectNote()){
                setState(MechanismStates.HOLDING);
            }
        } else if(mechanismState == MechanismStates.HOLDING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
            //we stop transition motor in the shooter subsystem right now
        } else if(mechanismState == MechanismStates.SHOOTING_AMP){
            if (isFirstTimeInState){ // dictates our timing
                isFirstTimeInState = false;
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.LOADING_TO_SHOOTER);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
            }
            if(System.currentTimeMillis()-stateStartTime >= 5000){ // 5 secs should be too long for shooting but just in case
                setState(MechanismStates.OFF);
            }
        } else if(mechanismState == MechanismStates.SHOOTING_SPEAKER){
            if (isFirstTimeInState){
                isFirstTimeInState = false;
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.LOADING_TO_SHOOTER);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
            }
            if(System.currentTimeMillis()-stateStartTime >= 5000){ // 5 secs should be too long for shooting but just in case
                setState(MechanismStates.OFF); // we could change this to intaking 
            }
        } else if (mechanismState == MechanismStates.OFF){
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
        } else {
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            System.out.println("WHAT ARE YOU DOING----STATE NOT RECOGNIZED!!!!! CURRENT STATE: " + mechanismState);
        }
    }

    public void setState(MechanismStates newState){
        mechanismState = newState;
        stateStartTime = System.currentTimeMillis(); //first time we set stateStartTime. what's this for? should this be in init? mayhaps
        isFirstTimeInState = true;
    }

    public MechanismStates getMechanismState(){
        return mechanismState;
    }
}
