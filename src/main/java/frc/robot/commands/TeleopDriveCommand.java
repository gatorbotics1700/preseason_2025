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
    private static final double DEADBAND = 0.1;

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
        System.out.println("TeleopDriveCommand initialized - stopping drivetrain");
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public void execute() {
        // Get raw values
        double xSpeed = translationXSupplier.getAsDouble();
        double ySpeed = translationYSupplier.getAsDouble();
        double rot = rotationSupplier.getAsDouble();

        // Apply deadband
        // xSpeed = Math.abs(xSpeed) > DEADBAND ? xSpeed : 0.0;
        // ySpeed = Math.abs(ySpeed) > DEADBAND ? ySpeed : 0.0;
        // rot = Math.abs(rot) > DEADBAND ? rot : 0.0;

        // Print values if any are non-zero
        // if (xSpeed != 0.0 || ySpeed != 0.0 || rot != 0.0) {
        //     System.out.println("Joystick values after deadband - X: " + xSpeed + " Y: " + ySpeed + " Rot: " + rot);
        // }

        // If all inputs are zero, make sure we're stopped
        // if (xSpeed == 0.0 && ySpeed == 0.0 && rot == 0.0) {
        //     drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
        //     return;
        // }

        // Scale to max speeds
        xSpeed *= DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        ySpeed *= DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        rot *= DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

        // Drive robot
        drivetrain.drive(
            ChassisSpeeds.fromFieldRelativeSpeeds(
                xSpeed, ySpeed, rot, drivetrain.getRotation()
            )
        );
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("TeleopDriveCommand ended");
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
} 