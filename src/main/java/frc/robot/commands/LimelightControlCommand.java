package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final PIDController pidController;
    private static final double TOLERANCE = 2.0; // Degrees of acceptable error

    // PID Constants - you'll need to tune these values
    private static final double kP = 0.01;  // Start small, maybe 0.01
    private static final double kI = 0.0;   // Start with 0
    private static final double kD = 0.001; // Start small, maybe 0.001

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        
        // Initialize PID controller
        this.pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(TOLERANCE);
        pidController.setSetpoint(0.0); // We want the offset to be 0
        
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            // Get horizontal offset from limelight
            double targetOffset = limelightSubsystem.getHorizontalOffset();
            
            // Calculate motor output using PID and invert it
            double turnSpeed = -pidController.calculate(targetOffset);
            
            // Clamp the output to prevent excessive speed
            turnSpeed = Math.max(-0.1, Math.min(0.1, turnSpeed));
            
            turretSubsystem.setTurretSpeed(-turnSpeed); // Negative because positive offset means target is to the right
        } else {
            System.out.println("NO APRILTAG FOUND");
            pidController.reset();
            turretSubsystem.setTurretSpeed(0);
        }
    }

    @Override
    public boolean isFinished() {
        if (!limelightSubsystem.hasValidTarget()) {
            return false;
        }
        
        double offset = Math.abs(limelightSubsystem.getHorizontalOffset());
        boolean isAligned = pidController.atSetpoint();
        
        if (isAligned) {
            turretSubsystem.setTurretSpeed(0);
            System.out.println("Target Aligned! Offset: " + offset);
            return true;
        }
        return false;
    }
}
