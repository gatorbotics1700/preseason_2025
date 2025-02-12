package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.util.PixelFormat;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private RobotContainer container;
    private ShuffleboardTab visionTesting;

    public Robot() {
        CameraServer.startAutomaticCapture();
        CvSink cvSink = CameraServer.getVideo();
        CvSource outputStream = new CvSource ("Blur", PixelFormat.kMJPEG, 640, 480, 30);
    }

    @Override
    public void robotInit() {
        container = new RobotContainer();
        SmartDashboard.putData(CommandScheduler.getInstance());
        visionTesting = Shuffleboard.getTab("Vision Testing");
        //SmartDashboard.putBoolean("Is FD?", DrivetrainSubsystem.isFD);
        

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = container.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }

    }

    @Override
    public void teleopInit() {
        container.setDefaultTeleopCommand();
    }

    @Override
    public void teleopPeriodic() {
        // Leave empty - default command will handle teleop
    }
}
