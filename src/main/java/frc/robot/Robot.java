package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class Robot extends TimedRobot {
    private Command m_limelightCommand;
    private Command m_servoCommand;
    private Command m_buttonCommand;

    private Command m_turretCommand;
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

        // m_limelightCommand = m_robotContainer.getLimelightCommand();

        // if (m_limelightCommand != null) {
        //      m_limelightCommand.schedule();
        // }

        m_buttonCommand = m_robotContainer.getButtonCommand();

        if (m_buttonCommand != null) {
            m_buttonCommand.schedule();
        }
    }

    @Override
    public void teleopPeriodic() {


        // m_limelightCommand = m_robotContainer.getLimelightCommand();

        // if (m_limelightCommand != null) {
        //      m_limelightCommand.schedule();
        // }

        m_buttonCommand = m_robotContainer.getButtonCommand();

        if (m_buttonCommand != null) {
            m_buttonCommand.schedule();
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