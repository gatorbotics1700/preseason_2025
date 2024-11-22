package frc.robot.commands;
import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.LimelightSubsystem;


public class TurretControlCommand extends InstantCommand { 
    private final TurretSubsystem turretSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    private final double turretSpeed;
    private static final double TOLERANCE = 2.0;
    
    private static final double kP = 0.2;
    private static final double kI = 0.0;
    private static final double kD = 0.0;
        
    private static final boolean USE_PID = true;

    public TurretControlCommand(TurretSubsystem turretSubsystem, LimelightSubsystem limelightSubsystem, double turretSpeed)  {
        this.turretSubsystem = turretSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.turretSpeed = turretSpeed;
    
        addRequirements(turretSubsystem); // Ensure this command requires the turret subsystem
        addRequirements(limelightSubsystem);
    }

    @Override
    public void execute() {
        System.out.println("Current Angle: " + turretSubsystem.getTurretAngle());
        turretSubsystem.turnToAngle(90, turretSpeed);
    }

    @Override
    public boolean isFinished() {
        double currentAngle = turretSubsystem.getTurretAngle();
        boolean isAtTarget = (currentAngle > 85) && (currentAngle < 95);
        
        if (isAtTarget) {
            System.out.println("Target reached: " + currentAngle);
            turretSubsystem.setTurretSpeed(0);
            return true;
        }
        return false;
    }

}
