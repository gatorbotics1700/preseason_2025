package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class Robot extends TimedRobot {
    private Command m_teleopCommand;
    private Command m_teleopCommand1;
    private Command m_teleopCommand2;
    private TurretSubsystem m_turretSubsystem;

    private RobotContainer m_robotContainer;

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

        //m_teleopCommand2 = m_robotContainer.getTeleopCommand2();

        // if (m_teleopCommand2 != null) {
        //      m_teleopCommand2.schedule();
        // }
        m_teleopCommand = m_robotContainer.getTeleopCommand();

        if (m_teleopCommand != null) {
             m_teleopCommand.schedule();
        }
    }

    @Override
    public void teleopPeriodic() {

        // m_teleopCommand2 = m_robotContainer.getTeleopCommand2();
        // if (m_teleopCommand2 != null) {
        //      m_teleopCommand2.schedule();
        // }

        m_teleopCommand = m_robotContainer.getTeleopCommand();

        if (m_teleopCommand != null) {
             m_teleopCommand.schedule();
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
