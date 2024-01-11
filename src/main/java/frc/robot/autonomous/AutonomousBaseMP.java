package frc.robot.autonomous;

import java.util.ArrayList;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.geometry.*;
import frc.robot.Robot;
import frc.robot.autonomous.MPState.MPStateLabel;
import frc.robot.subsystems.DrivetrainSubsystem;
//import frc.robot.subsystems.Mechanisms.MechanismStates;
//import frc.robot.subsystems.PneumaticIntakeSubsystem.PneumaticIntakeStates;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.constraint.SwerveDriveKinematicsConstraint;
import edu.wpi.first.math.trajectory.constraint.TrajectoryConstraint;
import edu.wpi.first.math.trajectory.constraint.MaxVelocityConstraint;
//import frc.robot.subsystems.Mechanisms;


public class AutonomousBaseMP extends AutonomousBase{
    private double timeStart;
    private double timeElapsed = 0;
    private Trajectory.State currentTrajState;
    private HolonomicDriveController controller;
        // trapezoid profile takes in max rotation velocity and max rotation acceleration 
        // PIDController #1: the first arg rep how many m/s added in the x direction for every meter of error in the x direction
        // PIDController #2 : the first arg rep how many m/s added in the y direction for every meter of error in the y direction
    
    private static DrivetrainSubsystem drivetrainSubsystem;
    public MPStateLabel mpStates;
    //private Mechanisms mechanisms;
    private MPState[] mpStateSequence;

    private Boolean isFirst; //if a state has isFirst it is the first time that a specific state is being run; initializes a time started
    private double startTime;
    private int i;

    public AutonomousBaseMP(MPState[] mpStateSequence){
        /*Takes state sequence from MPStateSequence
         * Initializes a holonomic drive controller which is necessary for wpilib trajectory generator
         */
        System.out.println("In autobase structure!");
        this.mpStateSequence =  mpStateSequence;
        controller = new HolonomicDriveController(
            new PIDController(1, 0, 0), new PIDController(1, 0, 0), //TODO: CHANGE KP
            new ProfiledPIDController(1, 0, 0,
            new TrapezoidProfile.Constraints(6.28, Math.PI))); //Check max vel. 
        drivetrainSubsystem = Robot.m_drivetrainSubsystem;
        init();
    }

    //Avery Note: the state label system might actually make things a LOT more confusing
    private MPStateLabel currentStateLabel = MPStateLabel.FIRST; //initializes state machine

    public void setStates(MPStateLabel newStateLabel){
        currentStateLabel = newStateLabel;
    }

    @Override
    public void init(){
        timeStart = 0.0;
        i = 0;
        isFirst = true;
        currentStateLabel = mpStateSequence[i].stateLabel;
        drivetrainSubsystem.resetOdometry(getStartPose());
        System.out.println("Init pose: " + drivetrainSubsystem.getMPoseX());
        System.out.println("just started");
    }
    
    @Override
    public void periodic(){
        currentStateLabel = mpStateSequence[i].stateLabel; //name of the state
        System.out.println("state: " + currentStateLabel);
        System.out.println("trajectoryDone" + trajectoryDone(mpStateSequence[1].trajectory));
        if(currentStateLabel == MPStateLabel.FIRST){
            timeStart = System.currentTimeMillis();
            setStates(MPStateLabel.TRAJECTORY);  // made one state for trajectory and are putting individual trajectories in followTradjectory()
            System.out.println("Doing first");
            System.out.println("initial pose: " + drivetrainSubsystem.getMPoseX());
            i++;
            System.out.println("moving on to " + mpStateSequence[i]);
        } else if(currentStateLabel == MPStateLabel.TRAJECTORY){
            
            System.out.println("running trajectory:" + mpStateSequence[i].trajectory.toString());
            if(mpStateSequence[i].trajectory == null){
                System.out.println("No trajectory");
                setStates(MPStateLabel.STOP);
                i++;
                System.out.println("moving on to " + mpStateSequence[i]);
            }else{
                double timeCheck = mpStateSequence[i].trajectory.getTotalTimeSeconds();
                currentTrajState = mpStateSequence[i].trajectory.sample(timeCheck);
                System.out.println("total time: " + timeCheck);
                System.out.println("trajectory End X: "+ currentTrajState.poseMeters.getX() + " trajectory Get X: " + drivetrainSubsystem.getMPoseX()); 
                followTrajectory(mpStateSequence[i].trajectory); 
                if(trajectoryDone(mpStateSequence[i].trajectory)){
                    System.out.println("STOP");
                    setStates(MPStateLabel.STOP);
                    i++;
                    System.out.println("moving on to " + mpStateSequence[i]);
                }
            }
        } else if(currentStateLabel == MPStateLabel.STOP){
            drivetrainSubsystem.stopDrive();
            System.out.println("state STOP!/nstate STOP!/nstate STOP!");
        }else{
            drivetrainSubsystem.stopDrive();
            System.out.println("else STOP!/nelse STOP!/nelse STOP!");
        }

        timeElapsed = System.currentTimeMillis() - timeStart;
    }

    public Pose2d getStartPose(){
       //we go immediately to second state in this case because that is where all of our trajectory lives. fix this later. Maybe with a first trajectory boolean
        if(mpStateSequence[1].trajectory == null){
            return new Pose2d(); 
        }else{
            return mpStateSequence[1].trajectory.getInitialPose(); 
        }

    }

    public boolean trajectoryDone(Trajectory trajectory){
        double errorX = Math.abs(currentTrajState.poseMeters.getX() - drivetrainSubsystem.getMPoseX());
        double errorY = Math.abs(currentTrajState.poseMeters.getY() - drivetrainSubsystem.getMPoseY()); 
        double errorRotate = Math.abs((currentTrajState.poseMeters.getRotation()).getDegrees() - drivetrainSubsystem.getMPoseDegrees()); 

        System.out.println("x error: " + errorX + " y error: " + errorY + " rotate error: " + errorRotate);
        if(errorX <= 0.5 && errorY <= 0.5 && errorRotate <= 0.5){
            return true;
        }else{
            return false;
        }
    }

    public void followTrajectory(Trajectory trajectory){
        Trajectory.State goal = trajectory.sample(timeElapsed/1000); //Avery Note: maybe we should think about sampling something a little more in the future
        System.out.println("follow End X: "+ currentTrajState.poseMeters.getX() + " follow Get X: " + drivetrainSubsystem.getMPoseX()); 
        System.out.println("follow End Y: "+ currentTrajState.poseMeters.getY() + " follow Get Y: " + drivetrainSubsystem.getMPoseY()); 
        ChassisSpeeds adjustedSpeeds = controller.calculate(
            drivetrainSubsystem.getMPose(), goal, currentTrajState.poseMeters.getRotation());

        
        drivetrainSubsystem.setSpeed(adjustedSpeeds);
        System.out.println("Adjusted speed: " + adjustedSpeeds);
        drivetrainSubsystem.drive();
    }

}
