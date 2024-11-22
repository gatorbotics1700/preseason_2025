package frc.robot.commands;

import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.DrivetrainSubsystem;

public class FollowPathCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private Command pathFollowingCommand;
    private PathPlannerPath path;

    public FollowPathCommand(DrivetrainSubsystem drivetrain, String pathName) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);

        try {
            // Load the path from the deployed files
            path = PathPlannerPath.fromPathFile(pathName);
            System.out.println("Path loaded successfully: " + pathName);

            
            // Create the path following command using AutoBuilder
            pathFollowingCommand = AutoBuilder.followPath(path);
        } catch (Exception e) {
            System.err.println("Failed to load path: " + pathName);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        System.out.println("FollowPathCommand initialized");
        if (pathFollowingCommand != null) {
            pathFollowingCommand.initialize();
        } else {
            System.err.println("Path following command is null!");
        }
        
        // Reset robot pose to start of path
        if (path != null) {
            drivetrain.resetPose(path.getStartingDifferentialPose());
            System.out.println("Reset pose to path start position");
        }
    }

    @Override
    public void execute() {
        if (pathFollowingCommand != null) {
            pathFollowingCommand.execute();
            
            // Debug current robot state
            ChassisSpeeds currentSpeeds = drivetrain.getRobotRelativeSpeeds();
            System.out.println("Current speeds - vx: " + currentSpeeds.vxMetersPerSecond + 
                             " vy: " + currentSpeeds.vyMetersPerSecond + 
                             " omega: " + currentSpeeds.omegaRadiansPerSecond);
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
        System.out.println("FollowPathCommand ending. Interrupted: " + interrupted);
        if (pathFollowingCommand != null) {
            pathFollowingCommand.end(interrupted);
        }
        drivetrain.drive(new ChassisSpeeds()); // Stop the robot
    }
} 