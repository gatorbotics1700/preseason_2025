package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimelightControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final XboxController controller;
    private final int pipeline;
    private Pose2d desiredPose;
    private Pose2d currentPose;

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
        if (desiredPose != null) {
            drivetrainSubsystem.driveToPose(desiredPose);
        }

        if (limelightSubsystem.hasValidTarget() && comparePipelineAndTarget()) { // makes sure we are looking at the
                                                                                 // correct id
            updateDesiredPose();
        } else {
            System.out.println("\tNo valid target detected.");
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

    private void updateDesiredPose() { //TODO: consider using poses instead of individual components
        currentPose = drivetrainSubsystem.getPose();
        double targetX = currentPose.getX() + limelightSubsystem.fieldXDistanceToTag();
        double targetY = currentPose.getY() + limelightSubsystem.fieldYDistanceToTag();
        Rotation2d targetRotation = (currentPose.getRotation()
                .minus(Rotation2d.fromDegrees(limelightSubsystem.getTagYaw())));
        desiredPose = new Pose2d(targetX, targetY, targetRotation);
        System.out.println("targetRotation" + targetRotation);
    }

    private boolean comparePipelineAndTarget() {
        if (pipeline == 0) {
            return limelightSubsystem.getTargetID() == 8;
        } else if (pipeline == 1) {
            return limelightSubsystem.getTargetID() == 2;
        }
        return false;
    }
}
