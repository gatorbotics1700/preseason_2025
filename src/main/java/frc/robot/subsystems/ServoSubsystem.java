package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class ServoSubsystem extends SubsystemBase {
    private static Servo pen;
    private static final double MIN_ANGLE = -48.0;
    private static final double MAX_ANGLE = 8.0;
    
    public ServoSubsystem() {
        pen = new Servo(Constants.SERVO_PWM_PORT);

        setAngle(0.0, false);
    }

    public void setAngle(double angle, boolean invert) {
        if(angle > MAX_ANGLE) {
            angle = MAX_ANGLE;
        } else if(angle < MIN_ANGLE){
            angle = MIN_ANGLE;
        }
        
        if(invert) {
            angle = MAX_ANGLE - angle;
        }

        pen.setAngle(angle);
    }

    public double getAngle(boolean inverted) {
        double angle = pen.getAngle();

        if(inverted) {
            angle = -angle + MAX_ANGLE;
        }

        return angle;
    }
}
