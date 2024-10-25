package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class TurretControlCommand extends InstantCommand {
    private final TurretSubsystem turretSubsystem;
    private final double turretSpeed;

    public TurretControlCommand(TurretSubsystem turretSubsystem, double turretSpeed) {
        this.turretSubsystem = turretSubsystem;
        this.turretSpeed = turretSpeed;
        addRequirements(turretSubsystem); // Ensure this command requires the turret subsystem
    }

    @Override
  public void initialize() {}
  
  @Override
    public void execute() {
        // Set the turret motor speed
        turretSubsystem.setTurretSpeed(turretSpeed);
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
