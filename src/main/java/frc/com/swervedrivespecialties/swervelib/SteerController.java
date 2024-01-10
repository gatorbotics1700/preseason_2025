package frc.com.swervedrivespecialties.swervelib;
import com.ctre.phoenix.sensors.CANCoder;

public interface SteerController {
    double getReferenceAngle();

    void setReferenceAngle(double referenceAngleRadians);

    double getStateAngle();

    double getAbsoluteAngleSC();
}
