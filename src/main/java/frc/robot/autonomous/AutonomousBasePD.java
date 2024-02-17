package frc.robot.autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.autonomous.PDState.AutoStates;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Mechanisms;

public class AutonomousBasePD extends AutonomousBase{
   //hulk
    private static final double turnKP= 0.2; //increased slight *** not tested
    private static final double turnKI= 0.05; 
    private static final double turnKD= 0.0;
    private static final double driveKP= 3.75; //1.3; //Robot.kP.getDouble(0.00006);//0.00006;
    private static final double driveKI= 0.0; //0.1; //Robot.kI.getDouble(0.0);//0.0;
    private static final double driveKD= 0.0; //0.3; //Robot.kD.getDouble(0.0);//0.0;
    private static final double DRIVE_DEADBAND = 3 * Constants.METERS_PER_INCH; //meters - previously 3 inches
    private static final double TURN_DEADBAND = 3; //degrees!

// //mcqueen
    // private static final double turnKP= 0.15; //increased slight *** not tested
    // private static final double turnKI= 0.0; 
    // private static final double turnKD= 0.005;
    // private static final double driveKP= 3.9; //Robot.kP.getDouble(0.00006);//0.00006;
    // private static final double driveKI= 0.0; //Robot.kI.getDouble(0.0);//0.0;
    // private static final double driveKD= 0.005; //Robot.kD.getDouble(0.0);//0.0;
    // private static final double DRIVE_DEADBAND = 1 * Constants.METERS_PER_INCH; //meters - previously 3 inches
    // private static final double TURN_DEADBAND = 1; //degrees!


    private PDState[] stateSequence;
    private int stateIndex;
    private boolean isFirstTimeInState;
    private double startTimeForState;
    
    private DrivetrainSubsystem drivetrainSubsystem;
    private Mechanisms mechanismSubsystem;
    public PDState currentState;
    private double testingDriveVelocity = 0.5; 
    private double testingSteerVelocity = 1; 
    

    //pids
    private PIDController turnController;
    private PIDController xController;
    private PIDController yController;

    public AutonomousBasePD(Pose2d startingCoordinate, PDState[] stateSequence){
        super(startingCoordinate);
        this.stateSequence =  stateSequence;

        init();
    }

    @Override
    public void init(){
        System.out.println("AUTONOMOUS INIT!\nINIT!\nINIT!");
        drivetrainSubsystem = Robot.m_drivetrainSubsystem;
        mechanismSubsystem = Robot.m_mechanismSubsystem;
        turnController = new PIDController(turnKP, turnKI, turnKD); 
        xController = new PIDController(driveKP, driveKI, driveKD);
        yController = new PIDController(driveKP, driveKI, driveKD);
        xController.reset();
        yController.reset();
        turnController.reset();
        turnController.enableContinuousInput(-180, 180); //turn controller reads rotation from 0 to 360 degrees 
        stateIndex = 0;
        isFirstTimeInState = true;
        startTimeForState = System.currentTimeMillis();
    }

    @Override
    public void periodic()
    {
        currentState = stateSequence[stateIndex];
        System.out.println("===========================================STATE: " + currentState.name + " ==========================================="); 

        if(isFirstTimeInState){
            startTimeForState = System.currentTimeMillis(); 
            isFirstTimeInState = false;
        }
        if(currentState.name == AutoStates.FIRST){ 
            //correct for twitching when offsets get applied
            double startingError = drivetrainSubsystem.getGyroscopeRotation().getDegrees() - drivetrainSubsystem.getStartingGyroRotation();
            Pose2d modifiedStartingCoordinate = new Pose2d(getStartingPoseX(), getStartingPoseY(), new Rotation2d(Math.toRadians(getStartingPoseRotation().getDegrees() + startingError))); 
            drivetrainSubsystem.getPositionManager().resetPosition(drivetrainSubsystem.getGyroscopeRotation(), drivetrainSubsystem.getModulePositionArray(), modifiedStartingCoordinate); //TODO: test this to make sure it works when using the getter for starting coordinate
            
            turnController.setTolerance(TURN_DEADBAND); 
            xController.setTolerance(DRIVE_DEADBAND);
            yController.setTolerance(DRIVE_DEADBAND);
            moveToNextState();
            return; //first is a pass through state, we don't have to call drive we can just move on
        } else if(currentState.name == AutoStates.DRIVE){
            mechanismSubsystem.setState(Mechanisms.MechanismStates.INTAKING);
            driveToLocation(currentState.coordinate);
            if(robotAtSetpoint()){
                if(currentState.mechState == Mechanisms.MechanismStates.SPEAKER_HOLDING || currentState.mechState == Mechanisms.MechanismStates.AMP_HOLDING){
                    moveToNextState();
                    System.out.println("REACHED SETPOINT");
                }else{
                    if(mechanismSubsystem.getMechanismState()!= currentState.mechState){
                        moveToNextState();
                        System.out.println("REACHED SETPOINT");
                    }
                }
            }
        } else if(currentState.name == AutoStates.HOLDING_TIMED){
            mechanismSubsystem.setState(Mechanisms.MechanismStates.SPEAKER_HOLDING);
            if(System.currentTimeMillis()-startTimeForState >= 3000){ //TODO: maybe lower time - if we have alr shot it should be warmed up to a degree so lower to 1 sec?
                moveToNextState();
            }
        } else if(currentState.name == AutoStates.STOP){
            drivetrainSubsystem.stopDrive();
            System.out.println("stopped in auto");
        } else {
            System.out.println("============================UNRECOGNIZED STATE!!!! PANICK!!!! " + currentState.name + "============================"); 
            drivetrainSubsystem.stopDrive();
        } 
        drivetrainSubsystem.drive(); 
    }

    /* 
     * @param dPose is desired pose
     */
    public void driveToLocation(Pose2d dPose){      
        xController.setSetpoint(dPose.getX());
        yController.setSetpoint(dPose.getY());
        turnController.setSetpoint(dPose.getRotation().getDegrees());

        double speedX = xController.calculate(drivetrainSubsystem.getPoseX(), dPose.getX());
        double speedY = yController.calculate(drivetrainSubsystem.getPoseY(), dPose.getY());

        System.out.println("m_pose deg: " + drivetrainSubsystem.getPoseDegrees() % 360);
        System.out.println("d_pose deg: " + dPose.getRotation().getDegrees() % 360);

        double speedRotate = turnController.calculate(drivetrainSubsystem.getPoseDegrees(), dPose.getRotation().getDegrees());
        
        if(xAtSetpoint()){ 
            speedX = 0; 
            System.out.println("At x setpoint");
      
        } else {
            // speedX = Math.signum(speedX)*Math.max(Constants.DRIVE_MOTOR_MIN_VOLTAGE, Math.min(Constants.DRIVE_MOTOR_MAX_VOLTAGE, Math.abs(speedX)));
            speedX = 0.5 * speedX; 
        }

        if(yAtSetpoint()){
            speedY = 0; 
            System.out.println("At y setpoint");
        } else {
            // speedY = Math.signum(speedX)*Math.max(Constants.DRIVE_MOTOR_MIN_VOLTAGE, Math.min(Constants.DRIVE_MOTOR_MAX_VOLTAGE, Math.abs(speedY)));
            speedY = 0.5 * speedY; 
        }

        if(turnAtSetpoint()){
            speedRotate = 0;
            System.out.println("At rotational setpoint");
        } 
        else {
            // speedRotate = Math.signum(speedRotate)*Math.max(Constants.STEER_MOTOR_MIN_VOLTAGE, Math.min(Constants.STEER_MOTOR_MAX_VOLTAGE, Math.abs(speedRotate)));
            speedRotate = 0.5 * speedRotate; 
        }

        drivetrainSubsystem.setSpeed(ChassisSpeeds.fromFieldRelativeSpeeds(speedX, speedY, speedRotate, drivetrainSubsystem.getPoseRotation()));  
        double errorX = xController.getPositionError();
        double errorY = yController.getPositionError();
        double errorRotate = turnController.getPositionError();
        System.out.println("Speed X: " + speedX + " Speed Y: " + speedY + " Speed R: " + speedRotate);
        System.out.println("error:" + errorX + ", " + errorY + ", " + errorRotate);
    }

    private boolean xAtSetpoint(){
        return Math.abs(xController.getPositionError()) <= DRIVE_DEADBAND;
    }

    private boolean yAtSetpoint(){
        return Math.abs(yController.getPositionError()) <= DRIVE_DEADBAND;
    }

    private boolean turnAtSetpoint(){
        return Math.abs(turnController.getPositionError()) <= TURN_DEADBAND;
    }

    private boolean robotAtSetpoint(){
        return xAtSetpoint() && yAtSetpoint() && turnAtSetpoint(); 
    }

    private void moveToNextState(){
        stateIndex++;
        isFirstTimeInState = true;
        startTimeForState = System.currentTimeMillis();
    }
}
