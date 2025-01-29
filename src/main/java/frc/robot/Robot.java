package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Robot extends TimedRobot {
    private RobotContainer container;
    private ShuffleboardTab visionTesting;

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
