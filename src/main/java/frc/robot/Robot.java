package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.PneumaticIntakeSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class Robot extends TimedRobot {
    private Command m_limelightCommand;
    private Command m_servoCommand;
    private Command m_turretCommand;
    private Command m_pneumaticIntakeCommand;
    private TurretSubsystem m_turretSubsystem;
    private PneumaticIntakeSubsystem m_pneumaticIntakeSubsystem;

    private RobotContainer m_robotContainer;
    private OI m_OI;

    @Override
    public void robotInit() {
        // Initialize RobotContainer
        m_robotContainer = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
      CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {

        // m_teleopCommand2 = m_robotContainer.getTeleopCommand2();

        // if (m_teleopCommand2 != null) {
        //      m_teleopCommand2.schedule();
        // }
        m_limelightCommand = m_robotContainer.getLimelightCommand();
        // m_turretSubsystem = m_robotContainer.getTurretSubsystem() ;
        if (m_limelightCommand != null) {
             m_limelightCommand.schedule();
        }
        // m_turretSubsystem.zeroTurret();

        m_turretCommand = m_robotContainer.getTurretCommand();
        m_turretSubsystem = m_robotContainer.getTurretSubsystem() ;
        if (m_turretCommand != null) {
             m_turretCommand.schedule();
        }
        m_turretSubsystem.zeroTurret();

        m_pneumaticIntakeSubsystem = m_robotContainer.getPneumaticIntakeSubsystem();
        m_pneumaticIntakeCommand = m_robotContainer.getPneumaticIntakeCommand();

        m_OI = new OI(m_pneumaticIntakeCommand);
    }

    @Override
    public void teleopPeriodic() {

        // m_teleopCommand2 = m_robotContainer.getTeleopCommand2();
        // if (m_teleopCommand2 != null) {
        //      m_teleopCommand2.schedule();
        // }

        m_limelightCommand = m_robotContainer.getLimelightCommand();

        if (m_limelightCommand != null) {
             m_limelightCommand.schedule();
        }

        m_turretCommand = m_robotContainer.getTurretCommand();

        if (m_turretCommand != null) {
             m_turretCommand.schedule();
        }


    }


    @Override
    public void autonomousInit() {
       
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void disabledInit() {}
  
    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {
      // Cancels all running commands at the start of test mode.
     // CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}
}
