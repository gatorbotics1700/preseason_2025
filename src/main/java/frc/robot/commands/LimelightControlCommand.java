package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final PIDController pidController;
    
    private static final double TURNING_SPEED = 0.02;
    private static final double TOLERANCE = 3.0;
    private static final double SIGNIFICANT_CHANGE = 5.0;
    
    private static final double kP = 0.8;
    private static final double kI = 0.0;
    private static final double kD = 0.09;
    
    private static final boolean USE_PID = false;

private double lastTargetOffset = 0.0; // Track the last known target offset
    private static final double TOLERANCE = 3.0; // Tolerance for stopping
    private static final double SIGNIFICANT_CHANGE = 5.0; // Significant change for resuming movement

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            double targetOffset = limelightSubsystem.getHorizontalOffset();

            // Check if the target is within the tolerance
            if (Math.abs(targetOffset) <= TOLERANCE) {
                System.out.println("Target is within tolerance. Stopping turret.");
                turretSubsystem.setTurretSpeed(0); // Stop the turret
                lastTargetOffset = targetOffset; // Update last known offset to current
            } else {
                // If the target offset has changed significantly, update the turret speed
                if (Math.abs(targetOffset - lastTargetOffset) > SIGNIFICANT_CHANGE) {
                    lastTargetOffset = targetOffset; // Update the last known offset

                    double turnSpeed;
                    if (USE_PID) {
                        turnSpeed = -pidController.calculate(targetOffset);
                        turnSpeed = Math.max(-TURNING_SPEED, Math.min(TURNING_SPEED, turnSpeed));
                    } else {
                        turnSpeed = Math.signum(targetOffset) * TURNING_SPEED;
                    }

                    System.out.println("Target Offset: " + targetOffset + " Turn Speed: " + turnSpeed);
                    turretSubsystem.setTurretSpeed(turnSpeed); // Set turret speed to turn towards the target
                }
            }
        } else {
            System.out.println("NO APRILTAG FOUND");
            turretSubsystem.setTurretSpeed(0); // Stop the turret if no target is found
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