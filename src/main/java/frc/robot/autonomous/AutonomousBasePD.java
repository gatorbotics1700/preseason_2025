package frc.robot.autonomous;

import javax.swing.SwingWorker.StateValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants;
import frc.robot.autonomous.PDState.AutoStates;
/* 
import frc.robot.autonomous.StateWithCoordinate;
import frc.robot.autonomous.StateWithCoordinate.AutoStates;
import frc.robot.subsystems.PneumaticIntakeSubsystem;
import frc.robot.subsystems.Mechanisms.MechanismStates;
import frc.robot.subsystems.PneumaticIntakeSubsystem.PneumaticIntakeStates;
import frc.robot.subsystems.Mechanisms;
*/

public class AutonomousBasePD extends AutonomousBase{
    private static final double turnKP= 0.0001; //increased slight *** not tested
    private static final double turnKI= 0.0; 
    private static final double turnKD= 0.0;
    private static final double driveKP= 0.75; //Robot.kP.getDouble(0.00006);//0.00006;
    private static final double driveKI= 0.0; //Robot.kI.getDouble(0.0);//0.0;
    private static final double driveKD= 0.0; //Robot.kD.getDouble(0.0);//0.0;
    private static final double DRIVE_DEADBAND = 6*Constants.METERS_PER_INCH; //meters - previously 3 inches
    private static final double TURN_DEADBAND = 6.0; //degrees!

    private Pose2d startingCoordinate;
    private PDState[] stateSequence;
    private int stateIndex;
    private Boolean isFirstTimeInState;
    private double startTimeForState;
    
    private DrivetrainSubsystem drivetrainSubsystem;
    public PDState currentState;

    //pids
    private PIDController turnController;
    private PIDController xController;
    private PIDController yController;

    public AutonomousBasePD(Pose2d startingCoordinate, PDState[] stateSequence){
        this.startingCoordinate = startingCoordinate;
        this.stateSequence =  stateSequence;
        init();
    }

    @Override
    public void init(){
        System.out.println("AUTONOMOUS INIT!\nINIT!\nINIT!");
        drivetrainSubsystem = Robot.m_drivetrainSubsystem;
        drivetrainSubsystem.resetOdometry(startingCoordinate);
        turnController = new PIDController(turnKP, turnKI, turnKD); 
        xController = new PIDController(driveKP, driveKI, driveKD);
        yController = new PIDController(driveKP, driveKI, driveKD);
        xController.reset(); //Avery question: function of resetting?
        yController.reset();
        turnController.reset();
        turnController.enableContinuousInput(0, 360); //turn controller reads rotation from 0 to 360 degrees 
        stateIndex = 0;
        isFirstTimeInState = true;
        startTimeForState = System.currentTimeMillis();
    }

    @Override
    public void periodic()
    {
        currentState = stateSequence[stateIndex];
        if(isFirstTimeInState){
            System.out.println("state: " + currentState); 
            startTimeForState = System.currentTimeMillis(); 
            isFirstTimeInState = false;

        }
        if (currentState.name == AutoStates.FIRST){
            turnController.setTolerance(TURN_DEADBAND); 
            xController.setTolerance(DRIVE_DEADBAND);
            yController.setTolerance(DRIVE_DEADBAND);
            xController.setSetpoint(0); //translation not pose component
            yController.setSetpoint(0);
            turnController.setSetpoint(0);
            stateIndex++;  
            return;
        }else if (currentState.name == AutoStates.DRIVE){
            driveToLocation(currentState.coordinate);
            if(robotAtSetpoint()){
                stateIndex++; 
            }
        } else if(currentState.name == AutoStates.INTAKING){
            //insert the code to intake things here for new mechanisms
            if(System.currentTimeMillis()-startTimeForState>=500){
                stateIndex++;
                isFirstTimeInState = true; 
            }
        } else if(currentState.name == AutoStates.STOP){
            drivetrainSubsystem.stopDrive();
        }else{
            System.out.println("============================UNRECOGNIZED STATE!!!! PANICK!!!! " + currentState.name + "============================"); 
            drivetrainSubsystem.stopDrive();
        } 
        drivetrainSubsystem.drive(); 
    }

    /** 
    @param dPose is desired pose
    */
    @Override
    public void driveToLocation(Pose2d dPose){      
        double speedX = xController.calculate(drivetrainSubsystem.getMPoseX(), dPose.getX());
        double speedY = yController.calculate(drivetrainSubsystem.getMPoseY(), dPose.getY());
        System.out.println("m_pose deg: " + drivetrainSubsystem.getMPoseDegrees() % 360);
        System.out.println("d_pose deg: " + dPose.getRotation().getDegrees() % 360);

        double speedRotate = turnController.calculate(drivetrainSubsystem.getMPoseDegrees(), dPose.getRotation().getDegrees());
        
        if(xAtSetpoint()){ 
            speedX = 0; 
            System.out.println("At x setpoint");
        } else {
            speedX = Math.signum(speedX)*Math.max(DrivetrainSubsystem.MIN_VOLTAGE, Math.abs(speedX));  
        }
 
        if(yAtSetpoint()){
            speedY = 0; 
            System.out.println("At y setpoint");
        } else {
            speedY = Math.signum(speedY)*Math.max(DrivetrainSubsystem.MIN_VOLTAGE, Math.abs(speedY));
        }

        if(turnAtSetpoint()){
            speedRotate = 0;
            System.out.println("At rotational setpoint");
        } else {
            speedRotate = Math.signum(speedRotate)*Math.max(DrivetrainSubsystem.MIN_VOLTAGE, Math.abs(speedRotate));
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

}
