package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.commands.MoveServoUpCommand;
import frc.robot.commands.MoveServoDownCommand;

public class Robot extends TimedRobot {

    private ServoSubsystem servoSubsystem;
    private boolean hasMovedUp = false;
    private boolean hasMovedDown = false;

    @Override
    public void robotInit() {
        // Initialize the servo subsystem (use correct servo port)
        servoSubsystem = new ServoSubsystem(0); // Change 0 to the appropriate port number
    }

    @Override
    public void teleopInit() {
        // Reset flags when teleop starts
        hasMovedUp = false;
        hasMovedDown = false;
    }

    @Override
    public void teleopPeriodic() {
        // Hard-coded logic to move the servo up and then down using commands
        if (!hasMovedUp) {
            new MoveServoUpCommand(servoSubsystem).schedule(); // Move servo up
            hasMovedUp = true; // Set flag to avoid repeated movement
        } else if (!hasMovedDown && hasMovedUp) {
            // Add a delay before moving the servo down
            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new MoveServoDownCommand(servoSubsystem).schedule(); // Move servo down
            hasMovedDown = true; // Set flag to avoid repeated movement
        }
    }

    @Override
    public void disabledInit() {
        // Stop the servo when the robot is disabled
        servoSubsystem.stopServo();
    }
}
