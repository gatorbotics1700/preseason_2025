package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.util.PixelFormat;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cscore.MjpegServer;


public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private RobotContainer container;
    
    public Robot() {
        CameraServer.startAutomaticCapture();
        CvSink cvSink = CameraServer.getVideo();
        UsbCamera usbCamera = new UsbCamera("USB Camera 0",0);
        MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
        mjpegServer1.setSource(usbCamera);

        CvSink cvsink = new CvSink("opencv_USB Camera 0");
        cvSink.setSource(usbCamera);

        CvSource outputStream = new CvSource ("Blur", PixelFormat.kMJPEG, 640, 480, 30);
        MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1182);
        mjpegServer2.setSource(outputStream);



    }

    @Override
    public void robotInit() {
        container = new RobotContainer();
        SmartDashboard.putData(CommandScheduler.getInstance());
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        // m_autonomousCommand = container.getAutonomousCommand();

        // if (m_autonomousCommand != null) {
        //     m_autonomousCommand.schedule();
        // }

    }   

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when teleop starts
        // if (m_autonomousCommand != null) {
        //     m_autonomousCommand.cancel();
        // }
        // container.setDefaultTeleopCommand();
    }

    @Override
    public void teleopPeriodic() {
        // Leave empty - default command will handle teleop
    }
}
