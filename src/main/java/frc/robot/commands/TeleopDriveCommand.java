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
        drivetrain.drive(new ChassisSpeeds(
            xSupplier.getAsDouble() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            ySupplier.getAsDouble() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            rotSupplier.getAsDouble() * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
        ));
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
} 