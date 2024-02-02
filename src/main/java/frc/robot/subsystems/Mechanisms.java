package frc.robot.subsystems;

public class Mechanisms {

    public ShooterSubsystem shooterSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public SensorSubsystem sensorSubsystem;
    public ElevatorSubsystem elevatorSubsystem;

    private boolean isFirstTimeInState;
    private double stateStartTime;

    private MechanismStates mechanismState;

    public static enum MechanismStates{
        INTAKING,
        HOLDING,
        SHOOTING_SPEAKER,
        SHOOTING_AMP,
        LOW_ELEVATOR_HEIGHT,
        AMP_HEIGHT,
        OFF;
    }

    public Mechanisms(){
        shooterSubsystem = new ShooterSubsystem();
        sensorSubsystem = new SensorSubsystem();
        elevatorSubsystem = new ElevatorSubsystem();
        intakeSubsystem = new IntakeSubsystem(/*sensorSubsystem*/);
        isFirstTimeInState = true;
        init();
    }

    public void init(){
        shooterSubsystem.init();
        intakeSubsystem.init();
        sensorSubsystem.init();
        elevatorSubsystem.init();

        mechanismState = MechanismStates.HOLDING; //TODO: figure out what state to start in
    }

    public void periodic(){
        if (mechanismState == MechanismStates.INTAKING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.INTAKING);
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP); //default shooter on
            elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.LOW_HEIGHT);
            if (sensorSubsystem.detectNote()){
                mechanismState = MechanismStates.HOLDING;
            }
        } else if(mechanismState == MechanismStates.HOLDING){
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.LOW_HEIGHT);
            //we stop transition motor in the shooter subsystem right now
        } else if(mechanismState == MechanismStates.SHOOTING_AMP){
            if (isFirstTimeInState){ // dictates our timing
                isFirstTimeInState = false;
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.LOADING_TO_SHOOTER);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.AMP);
                elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.AMP_HEIGHT);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2000){ // 5 secs should be too long for shooting but just in case
                mechanismState = MechanismStates.OFF;
                isFirstTimeInState = true; //why back to true
            }
        } else if(mechanismState == MechanismStates.SHOOTING_SPEAKER){
            if (isFirstTimeInState){
                isFirstTimeInState = false;
                intakeSubsystem.setState(IntakeSubsystem.IntakeStates.LOADING_TO_SHOOTER);
                shooterSubsystem.setState(ShooterSubsystem.ShooterStates.SPEAKER);
                elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.LOW_HEIGHT);
            }
            if(System.currentTimeMillis()-stateStartTime >= 2000){
                mechanismState = MechanismStates.OFF;
                isFirstTimeInState = true;
            }
        } else if(mechanismState == MechanismStates.LOW_ELEVATOR_HEIGHT){ // TODO: figure out if we need this bc setting it in SHOOTING_SPEAKER might be enough
            elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.LOW_HEIGHT);
        } else if(mechanismState == MechanismStates.AMP_HEIGHT){
            elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.AMP_HEIGHT);
        } else if (mechanismState == MechanismStates.OFF){
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.ZERO);
        } else {
            shooterSubsystem.setState(ShooterSubsystem.ShooterStates.OFF);
            intakeSubsystem.setState(IntakeSubsystem.IntakeStates.OFF);
            elevatorSubsystem.setState(ElevatorSubsystem.ElevatorStates.ZERO);
            System.out.println("WHAT ARE YOU DOING----STATE NOT RECOGNIZED!!!!!");
            System.out.println("CURRENT STATE: " + mechanismState);
        }
    }

    public void setState(MechanismStates newState){
        mechanismState = newState;
        stateStartTime = System.currentTimeMillis(); //first time we set stateStartTime. what's this for? should this be in init? mayhaps
        isFirstTimeInState = true;
    }

}
