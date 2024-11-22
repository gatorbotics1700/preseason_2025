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
    
    private static final double DEADBAND = 0.1;

    public TeleopDriveCommand(DrivetrainSubsystem drivetrain, 
                             DoubleSupplier xSupplier, 
                             DoubleSupplier ySupplier, 
                             DoubleSupplier rotSupplier) {
        this.drivetrain = drivetrain;
        this.xSupplier = xSupplier;
        this.ySupplier = ySupplier;
        this.rotSupplier = rotSupplier;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // Ensure we start with zero speeds
        drivetrain.drive(new ChassisSpeeds());
    }

    @Override
    public void execute() {
        // Get the raw joystick inputs
        double xInput = xSupplier.getAsDouble();
        double yInput = ySupplier.getAsDouble();
        double rotInput = rotSupplier.getAsDouble();

        // Apply deadband
        double xSpeed = Math.abs(xInput) > DEADBAND ? xInput : 0.0;
        double ySpeed = Math.abs(yInput) > DEADBAND ? yInput : 0.0;
        double rotSpeed = Math.abs(rotInput) > DEADBAND ? rotInput : 0.0;

        // Scale to max speeds
        xSpeed *= DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        ySpeed *= DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        rotSpeed *= DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

        // Debug print
        if (xSpeed != 0 || ySpeed != 0 || rotSpeed != 0) {
            System.out.println("Joystick inputs - X: " + xSpeed + 
                             " Y: " + ySpeed + 
                             " Rot: " + rotSpeed);
        }

        // Create chassis speeds from the inputs
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);

        // Drive the robot
        drivetrain.drive(chassisSpeeds);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the robot when the command ends
        drivetrain.drive(new ChassisSpeeds());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
} 