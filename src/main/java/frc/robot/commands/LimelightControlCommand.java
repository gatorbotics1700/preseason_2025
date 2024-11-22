package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private static final double TURNING_SPEED = 0.05;
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
            double currentAngle = turretSubsystem.getTurretAngle();
            
            // Calculate the desired angle by adding the offset to current angle
            double desiredAngle = currentAngle + targetOffset;
            
            System.out.println("Target Offset: " + targetOffset);
            System.out.println("Current Angle: " + currentAngle);
            System.out.println("Desired Angle: " + desiredAngle);
            
            // Turn the turret to eliminate the offset
            turretSubsystem.turnToAngle(desiredAngle, TURNING_SPEED);
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
        
        // Command is finished when horizontal offset is near zero
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
