package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

public class TeleopDriveCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private final DoubleSupplier translationXSupplier;
    private final DoubleSupplier translationYSupplier;
    private final DoubleSupplier rotationSupplier;
    // private static final double DEADBAND = 0.1;

    public TeleopDriveCommand(DrivetrainSubsystem drivetrain,
            DoubleSupplier translationXSupplier,
            DoubleSupplier translationYSupplier,
            DoubleSupplier rotationSupplier) {
        this.drivetrain = drivetrain;
        this.translationXSupplier = translationXSupplier;
        this.translationYSupplier = translationYSupplier;
        this.rotationSupplier = rotationSupplier;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // System.out.println("TeleopDriveCommand initialized - stopping drivetrain");
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public void execute() {
        double translationXPercent = translationXSupplier.getAsDouble(); // x speed
        double translationYPercent = translationYSupplier.getAsDouble(); // y speed
        double rotationPercent = rotationSupplier.getAsDouble(); // rotation speed

        drivetrain.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        translationXPercent * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                        translationYPercent * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                        rotationPercent * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
                        drivetrain.getRotation()));
    }

    @Override
    public void end(boolean interrupted) {
        // System.out.println("TeleopDriveCommand ended");
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}