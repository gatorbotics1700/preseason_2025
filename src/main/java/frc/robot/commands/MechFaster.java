package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class MechFaster extends InstantCommand {
  
  TurretSubsystem m_turretsub; 
  /** Creates a new SpinFaster. */
  public MechFaster(TurretSubsystem param_mech) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_turretsub = param_mech;
    addRequirements(m_turretsub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_turretsub.MechFaster();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
