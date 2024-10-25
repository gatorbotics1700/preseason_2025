package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class MechSlower extends InstantCommand {
  TurretSubsystem m_turretsub; 
  /** Creates a new SpinSlower. */
  public MechSlower(TurretSubsystem param_turret) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_turretsub = param_turret;
    addRequirements(m_turretsub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_turretsub.MechSlower();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
