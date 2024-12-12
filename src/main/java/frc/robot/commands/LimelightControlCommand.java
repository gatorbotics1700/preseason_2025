package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {
    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final int pipeline;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        this.pipeline = pipeline;
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
        
        if (limelightSubsystem.hasValidTarget()) {
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();
            turretSubsystem.turnToAngle(horizontalOffset - 12, 0.02);
        } else {
            turretSubsystem.MechStop();
        }
    }

    @Override
    public boolean isFinished() {
        return true; // Instant command, so it finishes immediately after initialization
    }
}