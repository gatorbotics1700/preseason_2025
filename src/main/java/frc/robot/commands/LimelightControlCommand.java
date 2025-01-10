package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final int pipeline;

    // Constants for proportional control
    private static final double kPTranslation = 0.02;  // Adjust this value for better X correction
    private static final double kPRotation = 0.01;     // Adjust for smoother rotation

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
        System.out.println("Pipeline set to: " + pipeline);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            // Get the horizontal offset from the Limelight
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();

            // Get current pose of the robot
            Pose2d currentPose = drivetrainSubsystem.getPose();

            // Calculate desired rotation (rotational alignment)
            Rotation2d desiredRotation = currentPose.getRotation().plus(Rotation2d.fromDegrees(horizontalOffset * kPRotation));

            // Calculate X translation adjustment
            double xAdjustment = horizontalOffset * kPTranslation;

            // Create new desired pose with translation and rotation adjustments
            Pose2d desiredPose = new Pose2d(
                currentPose.getX() + xAdjustment,
                currentPose.getY(),
                desiredRotation
            );

            // Drive to the calculated pose
            drivetrainSubsystem.driveToPose(desiredPose);

            System.out.println("Driving to pose: " + desiredPose);
        } else {
            System.out.println("No valid target detected.");
        }
    }

    @Override
    public boolean isFinished() {
        // Finish when the horizontal offset is sufficiently small
        boolean isAligned = Math.abs(limelightSubsystem.getHorizontalOffset()) < 1.0;
        System.out.println("Finished: " + isAligned);
        return isAligned;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Limelight alignment command ended. Interrupted: " + interrupted);
    }
}