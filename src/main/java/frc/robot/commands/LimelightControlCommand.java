package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightControlCommand extends Command {
    private static final double DEADBAND_DISTANCE = 0.05;  // meters
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final int pipeline;
    private Pose2d desiredPose;
    private Pose2d currentPose;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem,
            int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
        System.out.println("Pipeline set to: " + pipeline);
    }


    @Override
    public void execute() {
        System.out.println("currentpose: " + drivetrainSubsystem.getPose());

        if (limelightSubsystem.hasValidTarget()) {
            if(updateDesiredPose()){
                drivetrainSubsystem.driveToPose(desiredPose);
            }
        } else {
            System.out.println("\tNo valid target detected.");
        }
        
        if (atDesiredPose(currentPose, desiredPose)) {
            System.out.println("\t******************* at desired pose {0}" + desiredPose);
        }

        System.out.println("\tDriving to pose: " + desiredPose);

        // System.out.println("*******************"+limelightSubsystem.distanceToTag());
        System.out.println("\tdx " + limelightSubsystem.fieldXDistanceToTag());

    }

    @Override
    public boolean isFinished() {
        boolean finished = atDesiredPose(currentPose, desiredPose);
        if (finished) {
            System.out.println("Finished: " + finished);
        }
        return finished;
    }

    private boolean updateDesiredPose() { // returns true if the desired pose has changed
        currentPose = drivetrainSubsystem.getPose();
        Pose2d oldDesiredPose = desiredPose;

        double deltaX = oldDesiredPose.getX() - limelightSubsystem.fieldXDistanceToTag();

        if (Math.abs(deltaX) > DEADBAND_DISTANCE) {
            desiredPose = new Pose2d(
                    currentPose.getX() + limelightSubsystem.fieldXDistanceToTag(),
                    currentPose.getY(), // + limelightSubsystem.fieldYDistanceToTag(),
                    currentPose.getRotation()); //.plus(Rotation2d.fromDegrees(limelightSubsystem.getHorizontalOffsetAngle())));
            return true; 
        }

        return false;
    
    }

    private boolean atDesiredPose(Pose2d current, Pose2d desired) {
        return Math.abs(current.getX() - desired.getX()) < DEADBAND_DISTANCE;
                //&& Math.abs(current.getY() - desired.getY()) < DEADBAND_DISTANCE;
    }

}