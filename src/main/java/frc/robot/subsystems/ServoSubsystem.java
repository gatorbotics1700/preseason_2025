package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoSubsystem extends SubsystemBase {

    private final Servo servo;
    private static final double UP_POSITION = 1.0; // Max servo position
    private static final double DOWN_POSITION = 0.0; // Min servo position
    private static final double MID_POSITION = 0.5; // Neutral position

    public ServoSubsystem(int servoPort) {
        // Initialize the servo object with the port number
        servo = new Servo(servoPort);
    }

    public void moveUp() {
        servo.set(UP_POSITION); // Move to up position
    }

    public void moveDown() {
        servo.set(DOWN_POSITION); // Move to down position
    }

    public void stopServo() {
        servo.set(MID_POSITION); // Stop at the neutral position
    }
}
