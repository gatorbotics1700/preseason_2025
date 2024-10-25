package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class TurretRotateCommand extends InstantCommand {
    private final TurretSubsystem turretSubsystem;
    private double desiredAngle;
    private static final double angleTolerance = 1.0;

    public TurretRotateCommand(TurretSubsystem turretSubsystem, double desiredAngle) {
        this.turretSubsystem = turretSubsystem;
        this.desiredAngle = desiredAngle;
        addRequirements(turretSubsystem); // Ensure this command requires the turret subsystem
    }

    @Override
  public void initialize() {}
  
  @Override
    public void execute() {
        // Set the turret motor speed
        turretSubsystem.turnToAngle(desiredAngle);
    }

    @Override
    public boolean isFinished() {
        // Command runs continuously in teleop, so it never finishes
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the turret when the command ends
        turretSubsystem.setTurretSpeed(0);
    }
}
