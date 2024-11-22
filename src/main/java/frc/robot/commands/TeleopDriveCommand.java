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
    }

    @Override
    public void execute() {
        // Get the joystick inputs
        double xSpeed = xSupplier.getAsDouble() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        double ySpeed = ySupplier.getAsDouble() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        double rotSpeed = rotSupplier.getAsDouble() * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

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
        return false; // Command never finishes on its own
    }
} 