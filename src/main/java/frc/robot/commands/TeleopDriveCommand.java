package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TeleopDriveCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private final DoubleSupplier translationXSupplier;
    private final DoubleSupplier translationYSupplier;
    private final DoubleSupplier rotationSupplier;
    
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
    public void execute() {
        // Forward/backward is negative Y (forward is negative on joystick)
        // Left/right is negative X (left is negative on joystick)
        // Rotation is negative for counterclockwise
        drivetrain.drive(
            ChassisSpeeds.fromFieldRelativeSpeeds(
                -translationYSupplier.getAsDouble() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                -translationXSupplier.getAsDouble() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                -rotationSupplier.getAsDouble() * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
                drivetrain.getRotation()
            )
        );
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
} 