package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final double turretSpeed;
    private static final double kP = 0.025; // Proportional control constant - adjust this value
    private static final double DEADBAND = 1.0;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem, double turretSpeed) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        this.turretSpeed = turretSpeed;

        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
        if (!limelightSubsystem.hasValidTarget()) {
            // Search for target by spinning
            turretSubsystem.setTurretSpeed(turretSpeed);
        } else {
            // Get horizontal offset from target (-27 to 27 degrees typically)
            double tx = limelightSubsystem.getHorizontalOffset();
            
            // Calculate motor output based on how far we need to turn
            double adjustment = tx * kP;
            
            // Only move if we're outside the deadband
            if (Math.abs(tx) > DEADBAND) {
                turretSubsystem.setTurretSpeed(adjustment);
            } else {
                turretSubsystem.setTurretSpeed(0);
            }
        }
    }

   @Override
    public boolean isFinished() {
        // Only finish if we have a target AND we're within the deadband
        if (limelightSubsystem.hasValidTarget()) {
            double tx = limelightSubsystem.getHorizontalOffset();
            return Math.abs(tx) <= DEADBAND;
        }
        return false;
    }
}
