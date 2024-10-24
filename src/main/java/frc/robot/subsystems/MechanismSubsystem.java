package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MechanismSubsystem extends SubsystemBase {
    private final TalonFX falconMotor;
    private final Servo servoMotor;

    private final double SPEED = 0.6; 

    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private boolean isEnabled;


    public MechanismSubsystem() {
        falconMotor = new TalonFX(Constants.FALCON500_MOTOR_PORT);
        servoMotor = new Servo(Constants.SERVO_MOTOR_PORT);
        isEnabled = false;
        falconMotor.getConfigurator().setPosition(0);
        
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }


    public void rotateFalcon(double speed) {
        if (isEnabled) {
            falconMotor.setControl(dutyCycleOut.withOutput(speed)); 
        } else {
            falconMotor.stopMotor();
        }
    }

    public void setServoAngle(double angle) {
        angle = Math.max(Constants.SERVO_MIN_ANGLE, Math.min(angle, Constants.SERVO_MAX_ANGLE));
        servoMotor.setAngle(angle);
    }

    public void stopMotors() {
        falconMotor.stopMotor();
        servoMotor.setDisabled();
    }
}