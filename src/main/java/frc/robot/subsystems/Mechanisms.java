package frc.robot.subsystems;

public class Mechanisms {

    public ShooterSubsystem shooterSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private SensorSubsystem sensorSubsystem;

    private double stateStartTime;
    private static final int SHOOTING_TIME = 2500;

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
        init();
    }

    public void init(){
        shooterSubsystem.init();
        intakeSubsystem.init();
        sensorSubsystem.init();

        setState(MechanismStates.OFF); //TODO: figure out what state to start in
    }

    public void periodic(){
        System.out.println("=======CURRENT STATE IS: " + mechanismState + "=======");
        if (mechanismState == MechanismStates.INTAKING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.INTAKING);
            if (sensorSubsystem.detectNote()){
                setState(MechanismStates.SPEAKER_HOLDING); //TODO: this will change depending on if we're in teleop or auto        
            }
        } else if(mechanismState == MechanismStates.AMP_HOLDING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP_HOLDING);
            //we stop transition motor in the shooter subsystem right now
        } else if(mechanismState == MechanismStates.SPEAKER_HOLDING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER_HOLDING);
        } else if(mechanismState == MechanismStates.SHOOTING_AMP){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
            if(System.currentTimeMillis()-stateStartTime >= SHOOTING_TIME){ 
                setState(MechanismStates.OFF);
            }
        } else if(mechanismState == MechanismStates.SHOOTING_SPEAKER){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
            if(System.currentTimeMillis()-stateStartTime >= SHOOTING_TIME){ 
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