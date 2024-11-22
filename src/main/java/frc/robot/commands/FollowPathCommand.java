package frc.robot.commands;

import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class FollowPathCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private Command pathFollowingCommand;

    public FollowPathCommand(DrivetrainSubsystem drivetrain, String pathName) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);

        try {
            // Load the path from the deployed files
            PathPlannerPath path = PathPlannerPath.fromPathFile(pathName);
            // Create the path following command using AutoBuilder
            pathFollowingCommand = AutoBuilder.followPath(path);
        } catch (Exception e) {
            System.err.println("Failed to load path: " + pathName);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        if (pathFollowingCommand != null) {
            pathFollowingCommand.initialize();
        }
    }

    @Override
    public void execute() {
        if (pathFollowingCommand != null) {
            pathFollowingCommand.execute();
        }
    }

    @Override
    public boolean isFinished() {
        if (pathFollowingCommand != null) {
            return pathFollowingCommand.isFinished();
        }
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        if (pathFollowingCommand != null) {
            pathFollowingCommand.end(interrupted);
        }
        drivetrain.drive(new edu.wpi.first.math.kinematics.ChassisSpeeds()); // Stop the robot
    }
} 