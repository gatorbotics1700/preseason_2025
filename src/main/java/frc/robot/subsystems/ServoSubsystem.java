package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoSubsystem extends SubsystemBase {

    private final Servo servo;

    public ServoSubsystem(int pwmPort) {
        // Initialize the servo motor connected to the specified PWM port
        servo = new Servo(pwmPort);
    }

    // Set the servo position to a specific angle (0 to 180 degrees)
    public void setServoAngle(double angle) {
        System.out.println("servo angle set to: " + angle);
        servo.setAngle(angle);
    }

    // Get the current angle of the servo
    public double getServoAngle() {
        return servo.getAngle();
    }
}
