package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final int pipeline;

    // Proportional control constants
    private static final double kPTranslation = 0.02;  // Adjust for X movement
    private static final double kPRotation = 0.01;     // Adjust for rotation

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
            // Get horizontal offset (tx) and target distance (optional)
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();

            // Calculate forward speed (X movement) based on horizontal offset
            double forwardSpeed = horizontalOffset * kPTranslation;

            // Calculate rotation speed based on horizontal offset
            double rotationSpeed = horizontalOffset * kPRotation;

            // Drive the robot with calculated speeds
            drivetrainSubsystem.arcadeDrive(forwardSpeed, rotationSpeed);

            System.out.println("Driving with forward speed: " + forwardSpeed + ", rotation speed: " + rotationSpeed);
        } else {
            drivetrainSubsystem.stop();
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
        drivetrainSubsystem.stop();
        System.out.println("Limelight alignment command ended. Interrupted: " + interrupted);
    }
}