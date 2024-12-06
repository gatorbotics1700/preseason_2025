package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class Robot extends TimedRobot {
    private Command m_teleopColor;
    private Command m_teleopCommand1;
    private Command m_autoColor;

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
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

        m_teleopColor = m_robotContainer.getTeleopColor();
        // m_teleopCommand = m_robotContainer.getTeleopCommand();

        // // Schedule the command to run during teleop
        if (m_teleopColor != null) {
            m_teleopColor.schedule();
        }


    }

    @Override
    public void teleopPeriodic() {
        m_teleopCommand1 = m_robotContainer.getTeleopCommand1();
        if (m_teleopCommand1 != null) {
            m_teleopCommand1.schedule();
        }

    }


    @Override
    public void autonomousInit(){
        m_autoColor = m_robotContainer.getAutoColor();

        if (m_autoColor != null) {
            m_autoColor.schedule();
        }
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
