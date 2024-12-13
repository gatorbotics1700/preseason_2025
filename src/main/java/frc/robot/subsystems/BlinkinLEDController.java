package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;

public class BlinkinLEDController {
    private final PWM ledPWM;

    public BlinkinLEDController(int pwmPort) {
        ledPWM = new PWM(1);
    }

    public void setLEDColor(double color) {
        ledPWM.setSpeed(color); // Set the LED color using PWM
    }

    public void turnOff() {
        ledPWM.setSpeed(0); // Turn off the LEDs
    }
}
