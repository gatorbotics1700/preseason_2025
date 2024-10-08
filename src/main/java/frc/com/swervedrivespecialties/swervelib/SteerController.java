package frc.com.swervedrivespecialties.swervelib;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.InvertedValue;
    //import com.ctre.phoenix.sensors.CANCoder; v5


public interface SteerController {
    double getReferenceAngle();

    void setReferenceAngle(double referenceAngleRadians);

    double getStateAngle();

    double getAbsoluteAngleSC();
}
