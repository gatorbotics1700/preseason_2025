package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final int pipeline;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
        Timer.delay(0.2); //added a
        System.out.println("Pipeline set to: " + pipeline);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();

            Pose2d currentPose = drivetrainSubsystem.getPose();

            Rotation2d desiredRotation = currentPose.getRotation().plus(Rotation2d.fromDegrees(horizontalOffset));

            Pose2d desiredPose = new Pose2d(currentPose.getX(), currentPose.getY(), desiredRotation);

            drivetrainSubsystem.driveToPose(desiredPose);

            System.out.println("Driving to pose: " + desiredPose);
        } else {

            System.out.println("No valid target detected.");
        }
    }

    @Override
    public boolean isFinished() {
        System.out.println("Finished: " + (Math.abs(limelightSubsystem.getHorizontalOffset()) < 1.0));
        return Math.abs(limelightSubsystem.getHorizontalOffset()) < 1.0;
    }

    @Override
    public void end(boolean interrupted) {
         }
}