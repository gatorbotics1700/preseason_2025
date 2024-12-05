package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {

    private final TalonFX turretMotor;
    private final double TURRET_OFFSET = 0;

    public TurretSubsystem() {
        turretMotor = new TalonFX(Constants.TURRET_MOTOR_CAN_ID);
    }

    public void turnToAngle(double desiredAngle, double speed) {
        double currentAngle = getTurretAngle();
        double error = desiredAngle - currentAngle;

        // Normalize the error to -180 to 180 degrees
        if (error > 180) {
            error -= 360;
        } else if (error < -180) {
            error += 360;
        }

        if (Math.abs(error) >= 5) { // Adjust the threshold as needed
            double direction = Math.signum(error);
            setTurretSpeed(speed * direction); // Set speed based on direction
        } else {
            setTurretSpeed(0); // Stop if aligned
        }
    }

    public void setTurretSpeed(double speed) {
        DutyCycleOut dutyCycleOut = new DutyCycleOut(speed);
        turretMotor.setControl(dutyCycleOut.withOutput(speed));
    }

    public double getTurretAngle() {
        return ((turretMotor.getPosition().getValue() / 9.2) * 360) % 360 + TURRET_OFFSET;
    }
}