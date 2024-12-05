package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends CommandBase {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;

    private static final double TURNING_SPEED = 0.05; // Speed for turret turning

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;

        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            double targetOffset = limelightSubsystem.getHorizontalOffset();
            System.out.println("Target Offset: " + targetOffset);
            turretSubsystem.turnToAngle(targetOffset, TURNING_SPEED); // Turn turret towards the target
        } else {
            System.out.println("NO APRILTAG FOUND");
            turretSubsystem.setTurretSpeed(0); // Stop the turret if no target is found
        }
    }

    @Override
    public boolean isFinished() {
        // This command runs continuously until interrupted
        return false;
    }
}