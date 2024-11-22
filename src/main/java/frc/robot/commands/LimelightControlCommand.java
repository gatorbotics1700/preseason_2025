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
    private static final double TOLERANCE = 2.0;
    
    private static final double kP = 0.8;
    private static final double kI = 0.0;
    private static final double kD = 0.09;
    
    private static final boolean USE_PID = true;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        
        this.pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(TOLERANCE);
        pidController.setSetpoint(0.0);
        
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            double targetOffset = limelightSubsystem.getHorizontalOffset();
            double turnSpeed;
            
            if (USE_PID) {
                turnSpeed = -pidController.calculate(targetOffset);
                
                turnSpeed = Math.max(-TURNING_SPEED, Math.min(TURNING_SPEED, turnSpeed));
            } else {
                turnSpeed = 0;
                if (Math.abs(targetOffset) > TOLERANCE) {
                    turnSpeed = Math.signum(targetOffset) * TURNING_SPEED;
                }
            }
            
            System.out.println("Target Offset: " + targetOffset + " Turn Speed: " + turnSpeed);
            turretSubsystem.setTurretSpeed(turnSpeed);
            
        } else {
            System.out.println("NO APRILTAG FOUND");
            if (USE_PID) {
                pidController.reset();
            }
            turretSubsystem.setTurretSpeed(0);
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
