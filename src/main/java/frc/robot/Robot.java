package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.commands.MoveServoToAngleCommand;

public class Robot extends TimedRobot {

    private ServoSubsystem servoSubsystem;
    private MoveServoToAngleCommand moveServoToAngleCommand;

    @Override
    public void robotInit() {
        // Initialize the ServoSubsystem
        servoSubsystem = new ServoSubsystem(0); // Servo on PWM port 1
    }

    @Override
    public void robotPeriodic() {
        // Run the CommandScheduler periodically
        CommandScheduler.getInstance().run();
        
    }

    @Override
    public void teleopInit() {
        System.out.println("in teleop");
        moveServoToAngleCommand = new MoveServoToAngleCommand(servoSubsystem, 90); // Rotate to 90 degrees
        moveServoToAngleCommand.schedule(); // Schedule the command to run immediately
       
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
