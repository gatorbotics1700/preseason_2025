package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private static final double TURNING_SPEED = 0.02; // Reduced from previous value
    private static final double TOLERANCE = 2.0; // Degrees of acceptable error

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            // Get horizontal offset from limelight
            double targetOffset = limelightSubsystem.getHorizontalOffset();
            
            System.out.println("Target Offset: " + targetOffset);
            
            // Directly use the offset to determine turn direction and speed
            double turnSpeed = 0;
            if (Math.abs(targetOffset) > TOLERANCE) {
                // Use a smaller multiplier to reduce turn speed
                turnSpeed = Math.signum(targetOffset) * TURNING_SPEED;
            }
            
            turretSubsystem.setTurretSpeed(turnSpeed); // Removed the negative sign since Limelight is on turret
        } else {
            System.out.println("NO APRILTAG FOUND");
            turretSubsystem.setTurretSpeed(0); // Stop if no target
        }
    }

    @Override
    public boolean isFinished() {
        if (!limelightSubsystem.hasValidTarget()) {
            return false;
        }
        
        double offset = Math.abs(limelightSubsystem.getHorizontalOffset());
        boolean isAligned = offset < TOLERANCE;
        
        if (isAligned) {
            turretSubsystem.setTurretSpeed(0);
            System.out.println("Target Aligned! Offset: " + offset);
            return true;
        }
        return false;
    }
}
