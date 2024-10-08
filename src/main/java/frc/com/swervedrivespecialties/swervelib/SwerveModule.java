package frc.com.swervedrivespecialties.swervelib;

import edu.wpi.first.math.kinematics.SwerveModulePosition;
import com.ctre.phoenix6.hardware.CANcoder; //need to migrate

public interface SwerveModule {
    double getDriveVelocity();

    double getSteerAngle();

    // In rotations (not ticks)
    double getPositionRotation();

    SwerveModulePosition getSwerveModulePosition();

    void set(double driveVoltage, double steerAngle);
}
