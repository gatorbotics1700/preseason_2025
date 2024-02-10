package frc.com.swervedrivespecialties.swervelib;
import com.ctre.phoenix6.hardware.CANcoder;

public interface AbsoluteEncoder {
    /**
     * Gets the current angle reading of the encoder in radians.
     *
     * @return The current angle in radians. Range: [0, 2pi)
     */
    double getAbsoluteAngle();
    
    CANcoder getCANCoderFB();
}
