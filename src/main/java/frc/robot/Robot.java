package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.AprilTagProcessor;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class Robot extends TimedRobot {
    private Command m_teleopCommand;
    private Command m_teleopCommand1;
    private Command m_teleopCommand2;
    private AprilTagProcessor aprilTagProcessor;


    private RobotContainer m_robotContainer;

    @Override
    public void robotInit() {
        // Initialize RobotContainer
        m_robotContainer = new RobotContainer();
        aprilTagProcessor = new AprilTagProcessor(3, 5.5, 4.0, 2.0);

    }

    @Override
    public void robotPeriodic() {
      CommandScheduler.getInstance().run();
      Pose2d robotPose = aprilTagProcessor.getRobotPose();
        
        if (robotPose != null) {
            // Use the robot pose for navigation/targeting
            SmartDashboard.putNumber("Robot X", robotPose.getX());
            SmartDashboard.putNumber("Robot Y", robotPose.getY());
            SmartDashboard.putNumber("Robot Rotation", robotPose.getRotation().getDegrees());
        }
        
    }

    @Override
    public void teleopInit() {

        m_teleopCommand = m_robotContainer.getTeleopCommand();

        // // // Schedule the command to run during teleop
        if (m_teleopCommand != null) {
             m_teleopCommand.schedule();
        }
        // m_teleopCommand2 = m_robotContainer.getTeleopCommand2();
        // if (m_teleopCommand2 != null) {
        //     m_teleopCommand2.schedule();
        // }

    }

    @Override
    public void teleopPeriodic() {
        // m_teleopCommand1 = m_robotContainer.getTeleopCommand1();
        // if (m_teleopCommand1 != null) {
        //     m_teleopCommand1.schedule();
        // }

        // m_teleopCommand2 = m_robotContainer.getTeleopCommand2();
        // if (m_teleopCommand2 != null) {
        //     m_teleopCommand2.schedule();
        // }

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
