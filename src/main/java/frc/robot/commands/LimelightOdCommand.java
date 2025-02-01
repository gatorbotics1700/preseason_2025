package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class LimelightOdCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private Pose2d desiredPose;
    private Pose2d currentPose;

    public LimelightOdCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }


    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) { 
            drivetrainSubsystem.updateOdometryWithVision(limelightSubsystem.getPositionFromTag());
        } else {
            System.out.println("\tCan't see an april tag");
        }
    }

}
