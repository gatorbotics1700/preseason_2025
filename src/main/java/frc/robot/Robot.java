package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;

public class Robot extends TimedRobot {

    private LimelightSubsystem limelightSubsystem;
  //  private OI oi;

    @Override
    public void robotInit() {
        // Initialize subsystems
        limelightSubsystem = new LimelightSubsystem();

        // Initialize operator interface
       // oi = new OI(limelightSubsystem);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // You can add any commands to run when teleop starts
    }

    @Override
    public void teleopPeriodic() {
        // Called periodically during teleop
    }

    @Override
    public void autonomousInit() {
        // Add autonomous commands here if needed
    }

    @Override
    public void autonomousPeriodic() {
        // Called periodically during autonomous
    }

    @Override
    public void disabledInit() {
        // Called once when the robot is disabled
    }

    @Override
    public void disabledPeriodic() {
        // Called periodically while disabled
    }
}
