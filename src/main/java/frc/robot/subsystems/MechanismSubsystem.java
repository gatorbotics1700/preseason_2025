package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MechanismSubsystem extends SubsystemBase {
    private final WPI_TalonFX falconMotor;
    private final Servo servoMotor;

    private static final double TICKS_PER_REVOLUTION = 2048.0;
    private static final double GEAR_RATIO = 1.0; 
    private static final double DEGREES_PER_REVOLUTION = 360.0;

    public MechanismSubsystem() {
        falconMotor = new WPI_TalonFX(Constants.FALCON500_MOTOR_PORT);
        servoMotor = new Servo(Constants.SERVO_MOTOR_PORT);
        
        falconMotor.setSelectedSensorPosition(0);
    }

    public void rotateFalconToPosition(double targetDegrees) {
        double targetTicks = degreesToTicks(targetDegrees);
        
        falconMotor.set(ControlMode.Position, targetTicks);
    }

    private double degreesToTicks(double degrees) {
        return (degrees / DEGREES_PER_REVOLUTION) * TICKS_PER_REVOLUTION * GEAR_RATIO;
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
