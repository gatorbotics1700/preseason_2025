package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Robot extends TimedRobot {
    private RobotContainer container;

    @Override
    public void robotInit() {
        container = new RobotContainer();
        SmartDashboard.putData(CommandScheduler.getInstance());
        SmartDashboard.putBoolean("Is FD?", DrivetrainSubsystem.isFD);
        SmartDashboard.putNumber("Bus Utilization", DrivetrainSubsystem.busUtil);
        SmartDashboard.putNumber("Transmit errors", DrivetrainSubsystem.transmitErrors);
        SmartDashboard.putNumber("Receive errors", DrivetrainSubsystem.receiveErrors);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        
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
