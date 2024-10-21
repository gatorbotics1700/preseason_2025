package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoSubsystem extends SubsystemBase {

    private final Servo servo;
    private static final double UP_POSITION = 1.0; // Max servo position (adjust as needed)
    private static final double DOWN_POSITION = 0.0; // Min servo position (adjust as needed)
    private static final double MID_POSITION = 0.5; // Midpoint for the servo

    public ServoSubsystem(int servoPort) {
        // Initialize the servo object with the port number
        servo = new Servo(servoPort);
    }

    // Method to move the servo up
    public void moveUp() {
        servo.set(UP_POSITION); // Set the servo to the up position
    }

    // Method to move the servo down
    public void moveDown() {
        servo.set(DOWN_POSITION); // Set the servo to the down position
    }

    // Method to stop the servo (set it to a neutral position)
    public void stopServo() {
        servo.set(MID_POSITION); // Stop at the middle position
    }
}
