package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class LimelightControlCommand extends Command {
    private static final double DEADBAND_DISTANCE = 0.05; // meters
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final XboxController controller;
    private final int pipeline;
    private Pose2d desiredPose;
    private Pose2d currentPose;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem,
            int pipeline, XboxController controller) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;
        this.controller = controller;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }


    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
        System.out.println("Pipeline set to: " + pipeline);
        currentPose = drivetrainSubsystem.getPose();
        desiredPose = currentPose; 
    }
    @Override
    public void execute() {
        System.out.println("currentpose: " + drivetrainSubsystem.getPose());

        if (limelightSubsystem.hasValidTarget() && ((limelightSubsystem.getTargetID() == 8  && pipeline ==1)|| (limelightSubsystem.getTargetID() == 2  && pipeline ==0))) { //makes sure we are looking at the correct id
            if(updateDesiredPose()){
                drivetrainSubsystem.driveToPose(desiredPose);
            }
        } else {
            System.out.println("\tNo valid target detected.");
        }

        System.out.println("\tDriving to pose: " + desiredPose);

        // System.out.println("*******************"+limelightSubsystem.distanceToTag());
        System.out.println("\tdx " + limelightSubsystem.fieldXDistanceToTag());

    }

    @Override
    public boolean isFinished() {
        // Check if the robot is at the desired pose

        
        // Check if either stick on the Xbox controller is moved
        boolean joystickMoved = Math.abs(controller.getLeftX()) > 0.05 ||
                                Math.abs(controller.getLeftY()) > 0.05 ||
                                Math.abs(controller.getRightX()) > 0.05 ||
                                Math.abs(controller.getRightY()) > 0.05;
        if (joystickMoved) {
            System.out.println("Joystick moved, ending command.");
            return true;
        }

        return false;
    }

    private boolean updateDesiredPose() { // returns true if the desired pose has changed
        currentPose = drivetrainSubsystem.getPose(); 

        if (limelightSubsystem.hasValidTarget() && ((limelightSubsystem.getTargetID() == 8  && pipeline ==1)|| (limelightSubsystem.getTargetID() == 2  && pipeline ==0))) {
            double targetX = currentPose.getX() + limelightSubsystem.fieldXDistanceToTag() - 0.7874/2;
            double targetY = currentPose.getY(); 
           // Rotation2d targetRotation = currentPose.getRotation().plus(Rotation2d.fromDegrees(limelightSubsystem.getHorizontalOffsetAngle()));
            Pose2d newDesiredPose = new Pose2d(targetX, targetY, currentPose.getRotation());
    
            if (Math.abs(newDesiredPose.getX() - desiredPose.getX()) <= DEADBAND_DISTANCE){ //&&
                //Math.abs(newDesiredPose.getY() - desiredPose.getY()) <= DEADBAND_DISTANCE) {
                desiredPose = newDesiredPose; 
                return true; 
            }
            desiredPose = newDesiredPose;
            return true;
        }
        
        return false; // No valid target or no update required
    }




}