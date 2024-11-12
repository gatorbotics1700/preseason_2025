package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    private final double turretSpeed;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem, double turretSpeed) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        this.turretSpeed = turretSpeed;

        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
        // check if limelight sees apriltag and spins if not
        if (limelightSubsystem.hasValidTarget()==false) {
            turretSubsystem.setTurretSpeed(turretSpeed);
        } 
    }

    @Override
    public boolean isFinished() {
        if (limelightSubsystem.hasValidTarget()) {
            System.out.println("FINISHED");
            return true;
        }
        System.out.println("NOT FINISHED");
        return false;
    }
}
