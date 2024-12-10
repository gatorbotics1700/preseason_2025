package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {
    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final double speed;
    private final int targetID; // New field for target ID
    private double lastHorizontalOffset = 0.0; // Store the last known horizontal offset

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem, int targetID) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        this.speed = 0.02; // Set the speed for turret movement
        this.targetID = targetID; // Initialize target ID
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void initialize() {
        // 
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget() && limelightSubsystem.getTargetID() == targetID) { // Check for specific target ID
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();
            // Check if the change in horizontal offset is greater than 3 degrees
            if (Math.abs(horizontalOffset - lastHorizontalOffset) > 3) {
                turretSubsystem.turnToAngle(horizontalOffset - 12, speed);
                lastHorizontalOffset = horizontalOffset; // Update the last known offset
            }
        } else {
            turretSubsystem.MechStop(); // Stop turret if no valid target
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when the turret is aligned with the target
        return !limelightSubsystem.hasValidTarget() || limelightSubsystem.getTargetID() != targetID; // Check if the target ID is still valid
    }

    @Override
    public void end(boolean interrupted) {
        turretSubsystem.MechStop(); // Ensure turret stops when command ends
    }
}