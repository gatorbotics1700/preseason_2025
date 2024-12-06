package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {
    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final double speed;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        this.speed = 0.05; // Set the speed for turret movement
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void initialize() {
        // Initialization logic if needed
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();
            turretSubsystem.turnToAngle(horizontalOffset, speed);
        } else {
            turretSubsystem.MechStop(); // Stop turret if no valid target
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when the turret is aligned with the target
        return !limelightSubsystem.hasValidTarget();
    }

    @Override
    public void end(boolean interrupted) {
        turretSubsystem.MechStop(); // Ensure turret stops when command ends
    }
}
