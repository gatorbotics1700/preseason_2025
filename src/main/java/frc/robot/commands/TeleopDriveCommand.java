package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TeleopDriveCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private final DoubleSupplier xSupplier;
    private final DoubleSupplier ySupplier;
    private final DoubleSupplier rotSupplier;
    
    public TeleopDriveCommand(DrivetrainSubsystem drivetrain, 
                             DoubleSupplier xSupplier, 
                             DoubleSupplier ySupplier, 
                             DoubleSupplier rotSupplier) {
        this.drivetrain = drivetrain;
        this.xSupplier = xSupplier;
        this.ySupplier = ySupplier;
        this.rotSupplier = rotSupplier;
        addRequirements(drivetrain);
        System.out.println("TeleopDriveCommand constructed");
    }

    @Override
    public void initialize() {
        System.out.println("TeleopDriveCommand initialized");
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

    @Override
    public void execute() {
        // Get raw values
        double rawX = xSupplier.getAsDouble();
        double rawY = ySupplier.getAsDouble();
        double rawRot = rotSupplier.getAsDouble();

        // Print raw values if any are non-zero
        if (rawX != 0 || rawY != 0 || rawRot != 0) {
            System.out.println("Raw inputs - X: " + rawX + " Y: " + rawY + " Rot: " + rawRot);
        }

        // Calculate speeds
        double xSpeed = rawX * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        double ySpeed = rawY * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        double rotSpeed = rawRot * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

        // Print calculated speeds if any are non-zero
        if (xSpeed != 0 || ySpeed != 0 || rotSpeed != 0) {
            System.out.println("Calculated speeds - X: " + xSpeed + " Y: " + ySpeed + " Rot: " + rotSpeed);
        }

        // Create and send chassis speeds
        ChassisSpeeds speeds = new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);
        drivetrain.drive(speeds);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("TeleopDriveCommand ended. Interrupted: " + interrupted);
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
} 