package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class LimelightControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final XboxController controller;
    private final int pipeline;
    private Pose2d desiredPose;
    private Pose2d currentPose;
    private Rotation2d pointingToTagAngle; //field relative angle to point the robot at the apriltag

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem,
            int pipeline, XboxController controller) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;
        this.controller = controller;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
    }

    @Override
    public void execute() {
        // makes sure we are looking at the correct id
        // System.out.println("pipeline: " + pipeline);
        // System.out.println("valid target: " + limelightSubsystem.hasValidTarget());
        // System.out.println("matching: " + targetMatchesPipeline());
        if (limelightSubsystem.hasValidTarget() && targetMatchesPipeline()) { 
            updateDesiredPose();
        } else {
            System.out.println("\tNo valid target detected.");
        }

        if (desiredPose != null) {
            drivetrainSubsystem.driveToPoseWithInitialAngle(desiredPose, pointingToTagAngle);
        }
    }

    @Override
    public boolean isFinished() {
        // Check if either stick on the Xbox controller is moved
        boolean joystickMoved = Math.abs(controller.getLeftX()) > 0.05 ||
                Math.abs(controller.getLeftY()) > 0.05 ||
                Math.abs(controller.getRightX()) > 0.05 ||
                Math.abs(controller.getRightY()) > 0.05;
        if (joystickMoved) {
            System.out.println("Joystick moved, ending command.");
            return true;
        }
        return false;
        // TODO: allow mech commands to end this as well
    }

    private void updateDesiredPose() { // TODO: consider using poses instead of individual components
        currentPose = drivetrainSubsystem.getPose();
        double targetX = currentPose.getX() + limelightSubsystem.fieldXDistanceToTag(drivetrainSubsystem.getRobotRotationDegrees());
        double targetY = currentPose.getY() + limelightSubsystem.fieldYDistanceToTag(drivetrainSubsystem.getRobotRotationDegrees());
        Rotation2d targetRotation = (currentPose.getRotation()
                .minus(Rotation2d.fromDegrees(limelightSubsystem.getTagYaw()))); //this is the parallel to tag angle
        pointingToTagAngle = (currentPose.getRotation()
                .minus(Rotation2d.fromDegrees(limelightSubsystem.getHorizontalOffsetAngle())));
        desiredPose = new Pose2d(targetX, targetY, targetRotation);
        System.out.println("targetRotation" + targetRotation);
    }

    private boolean targetMatchesPipeline() {
        if (pipeline == 1) {
            return limelightSubsystem.getTargetID() == 6 || limelightSubsystem.getTargetID() == 19;
        } else if (pipeline == 2) {
            return limelightSubsystem.getTargetID() == 7 || limelightSubsystem.getTargetID() == 10 ||limelightSubsystem.getTargetID() == 18 || limelightSubsystem.getTargetID() == 21; 
        } else if (pipeline == 3){
            return limelightSubsystem.getTargetID() == 8 || limelightSubsystem.getTargetID() == 17; 
        } else if (pipeline == 4){ 
            return limelightSubsystem.getTargetID() == 9 || limelightSubsystem.getTargetID() == 12;
        } else if (pipeline == 5){ 
            return limelightSubsystem.getTargetID() == 11 || limelightSubsystem.getTargetID() == 20;
        } else if (pipeline == 6){ 
            return limelightSubsystem.getTargetID() == 3 || limelightSubsystem.getTargetID() == 16;
        } else if (pipeline == 7){ 
            return limelightSubsystem.getTargetID() == 1 || limelightSubsystem.getTargetID() == 2 || limelightSubsystem.getTargetID() == 12 || limelightSubsystem.getTargetID() == 13;
        }  else if (pipeline == 8){ 
            return limelightSubsystem.getTargetID() == 4 || limelightSubsystem.getTargetID() == 14;
        } else if (pipeline == 9){ 
            return limelightSubsystem.getTargetID() == 5 || limelightSubsystem.getTargetID() == 15;
        } else{
            return false;
        }
    }
}
