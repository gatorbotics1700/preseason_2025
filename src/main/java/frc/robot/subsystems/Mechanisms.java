package frc.robot.subsystems;

public class Mechanisms {

    private ShooterSubsystem shooterSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private SensorSubsystem sensorSubsystem;

    private boolean isFirstTimeInState;
    private double stateStartTime;

    private MechanismStates mechanismState;

    public static enum MechanismStates{
        INTAKING,
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
        isFirstTimeInState = true;
        init();
    }

    public void init(){
        shooterSubsystem.init();
        intakeSubsystem.init();
        sensorSubsystem.init();

        setState(MechanismStates.OFF); //TODO: figure out what state to start in
    }

    public void periodic(){
        System.out.println("CURRENT STATE IS: " + mechanismState);
        if (mechanismState == MechanismStates.INTAKING){
            System.out.println("======IN INTAKING=======");
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.INTAKING);
            if (sensorSubsystem.detectNote()){
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF); 
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);           
            }
        } else if(mechanismState == MechanismStates.AMP_HOLDING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP_HOLDING);
            //we stop transition motor in the shooter subsystem right now
        } else if(mechanismState == MechanismStates.SPEAKER_HOLDING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER_HOLDING);
        }else if(mechanismState == MechanismStates.SHOOTING_AMP){
            if (isFirstTimeInState){ // dictates our timing
                isFirstTimeInState = false;
                stateStartTime = System.currentTimeMillis();
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2500){ // 5 secs should be too long for shooting but just in case
                setState(MechanismStates.OFF);
            }
        } else if(mechanismState == MechanismStates.SHOOTING_SPEAKER){
            if (isFirstTimeInState){ 
                isFirstTimeInState = false;
                stateStartTime = System.currentTimeMillis();
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2500){ // 5 secs should be too long for shooting but just in case
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
        intakeSubsystem.periodic();
        shooterSubsystem.periodic();
        System.out.println(sensorSubsystem.detectNote());
    }

    public void setState(MechanismStates newState){
        mechanismState = newState;
        isFirstTimeInState = true;
    }

    public MechanismStates getMechanismState(){
        return mechanismState;
    }
}