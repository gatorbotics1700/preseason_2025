package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ServoSubsystem;

public class ButtonCommand extends InstantCommand {
  public static final CommandXboxController m_controller_two = new CommandXboxController(1);
  private final TurretSubsystem m_turretsub;
  private final LimelightSubsystem m_limelightsub;
  /** Creates a new SpinSlower. */
  public ButtonCommand(TurretSubsystem m_turretsub, LimelightSubsystem m_limelightsub) {
    this.m_turretsub = m_turretsub;
    this.m_limelightsub = m_limelightsub;
    addRequirements(m_turretsub); // Ensure this command requires the turret subsystem
    addRequirements(m_limelightsub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_controller_two.a().onTrue(new LimelightControlCommand(m_limelightsub, m_turretsub, 1));
    m_controller_two.b().onTrue(new LimelightControlCommand(m_limelightsub, m_turretsub, 0));
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
