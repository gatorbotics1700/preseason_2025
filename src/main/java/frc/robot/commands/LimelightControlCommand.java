package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {
    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final double speed;
    private final int pipeline; // New field for pipeline number
    private double lastHorizontalOffset = 0.0; // Store the last known horizontal offset

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        this.speed = 0.02; // Set the speed for turret movement
        this.pipeline = pipeline; // Initialize pipeline number
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline); // Set the pipeline in the subsystem
        System.out.println("Pipeline set to: " + pipeline); // Debug statement
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            System.out.println("Valid target detected."); // Debug statement
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();
            // Check if the change in horizontal offset is greater than 3 degrees
            if (Math.abs(horizontalOffset - lastHorizontalOffset) > 3) {
                turretSubsystem.turnToAngle(horizontalOffset - 12, speed);
                lastHorizontalOffset = horizontalOffset; // Update the last known offset
            }
        } else {
            System.out.println("No valid target detected."); // Debug statement
            turretSubsystem.MechStop(); // Stop turret if no valid target
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when the turret is aligned with the target
        return !limelightSubsystem.hasValidTarget(); // Check if the target is still valid
    }

    @Override
    public void end(boolean interrupted) {
        turretSubsystem.MechStop(); // Ensure turret stops when command ends
    }
}