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
        System.out.println("TeleopDriveCommand initialized");
        drivetrain.drive(new ChassisSpeeds());
    }

    @Override
    public void execute() {
        // Get the raw joystick inputs and print them
        double rawX = xSupplier.getAsDouble();
        double rawY = ySupplier.getAsDouble();
        double rawRot = rotSupplier.getAsDouble();

        System.out.println("Raw joystick values - X: " + rawX + 
                         " Y: " + rawY + 
                         " Rot: " + rawRot);

        // Only proceed if inputs are above deadband
        if (Math.abs(rawX) <= DEADBAND) rawX = 0.0;
        if (Math.abs(rawY) <= DEADBAND) rawY = 0.0;
        if (Math.abs(rawRot) <= DEADBAND) rawRot = 0.0;

        // Scale to max speeds
        double xSpeed = rawX * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        double ySpeed = rawY * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        double rotSpeed = rawRot * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

        // Create chassis speeds from the inputs
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);

        // Drive the robot
        drivetrain.drive(chassisSpeeds);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("TeleopDriveCommand ended");
        drivetrain.drive(new ChassisSpeeds());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
} 