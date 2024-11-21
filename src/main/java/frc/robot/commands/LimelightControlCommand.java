package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private static final double TURNING_SPEED = 0.05; // Adjust this value as needed

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
      //  System.out.println("*********************************************************");

        if (limelightSubsystem.hasValidTarget()) {
            System.out.println("*********************************************************");
            // Get current turret angle
            //double currentAngle = turretSubsystem.getTurretAngle();
            
            // Get horizontal offset from limelight (-27 to 27 degrees typically)
            double targetOffset = limelightSubsystem.getHorizontalOffset();
            
            // Calculate desired angle by adding the offset to current angle
           // double desiredAngle = currentAngle + targetOffset;
            
            // Normalize the angle to stay within 0-360 range
            targetOffset = ((targetOffset % 360) + 360) % 360;
            System.out.println("DESIRED ANGLE: " + targetOffset);
            
            // Use the existing turnToAngle method
            turretSubsystem.turnToAngle(targetOffset, TURNING_SPEED);
        } else {
            // If no target is found, you might want to implement a search pattern
            // For example, slowly rotate the turret
           // turretSubsystem.setTurretSpeed(0.05);
           System.out.println("NO APRILTAG FOUND");
        }
    }

    @Override
    public boolean isFinished() {
        // Command continues running until explicitly stopped
        if(Math.abs(turretSubsystem.getTurretAngle()-limelightSubsystem.getHorizontalOffset()) < 5){
            return true;
        }
        return false;
    }
}
