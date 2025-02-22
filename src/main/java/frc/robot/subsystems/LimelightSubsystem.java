package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable;
    private final double X_OFFSET = 0.27; // distance from front of robot to limelight in meters
    

    public LimelightSubsystem() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        LimelightHelpers.setCameraPose_RobotSpace("limelight", Constants.LIMELIGHT_FORWARD_OFFSET , Constants.LIMELIGHT_SIDE_OFFSET, Constants.LIMELIGHT_UP_OFFSET, Constants.LIMELIGHT_ROLL_OFFSET, Constants.LIMELIGHT_PITCH_OFFSET, Constants.LIMELIGHT_YAW_OFFSET);
    }

    public void turnOnLED() {
        limelightTable.getEntry("ledMode").setNumber(3); // 3 means force on
    }

    public void turnOffLED() {
        limelightTable.getEntry("ledMode").setNumber(1); // 1 means force off
    }

    public boolean hasValidTarget() {
        return limelightTable.getEntry("tv").getDouble(0.0) == 1.0;
    }

    //angle between center of camera and center of apriltag (degrees) -- doesn't account for apriltag angle
    public double getHorizontalOffsetAngle() {
        return limelightTable.getEntry("tx").getDouble(0.0);
    }

    public double getVerticalOffsetAngle() { //we don't trust this method for distance calculations -- don't use for that
        return limelightTable.getEntry("ty").getDouble(0.0);
    }

    public double getTargetArea() {
        return limelightTable.getEntry("ta").getDouble(0.0);
    }

    public double getSkew() {
        return limelightTable.getEntry("ts").getDouble(0.0);
    }

    public double getLatency() {
        return limelightTable.getEntry("tl").getDouble(0.0);
    }

    public double getTargetID() {
        return limelightTable.getEntry("tid").getDouble(0.0);
    }

    //camera relative angle of apriltag (degrees)
    public double getTagYaw() {
        //returns yaw of april tag relative to camera
        return limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double[6])[4];
    }
    
    /**
     * Calculates a target pose for the robot based on AprilTag detection from the Limelight.
     * Uses vision data to determine the relative position and orientation to an AprilTag,
     * then transforms this into field coordinates for robot positioning.
     *
     * @param robotPoseInFieldSpace The current Pose2d of the robot in field coordinates
     * @return A Pose2d representing the target position and rotation of the center of the robot in field coordinates,
     *         such that the front center will be aligned with the AprilTag,
     *         calculated using the detected AprilTag's position and the robot's current pose.
     *         The returned pose includes:
     *         - X/Y coordinates offset from current position based on tag distance
     *         - Rotation aligned parallel to the detected tag
     *         Returns null if no AprilTag is detected or vision data is invalid.
     */
    public Pose2d aprilTagPoseInFieldSpace(Pose2d robotPoseInFieldSpace) {
        // distance to the camera from the tag (in camera's coordinate space)
        double[] aprilTagArrayInCameraSpace = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
       
        if(aprilTagArrayInCameraSpace == null){ // idk if this is really necessary but better safe than sorry?
            return null;
        }

        Pose2d aprilTagPoseInCameraSpace = arrayToPose(aprilTagArrayInCameraSpace);
        Pose2d aprilTagPoseInRobotSpace = convertCameraSpaceToRobotSpace(aprilTagPoseInCameraSpace);
        Pose2d aprilTagPoseFieldSpace = convertToFieldSpace(aprilTagPoseInRobotSpace, robotPoseInFieldSpace);
        Pose2d aprilTagPoseOffsetFrontCenter = offsetToFrontCenter(aprilTagPoseFieldSpace);
        return aprilTagPoseOffsetFrontCenter;
    }

    public Pose2d arrayToPose(double[] array){
        // I HATE THIS IT'S EVIL AAAAAAAAA - Patricia
        //the apriltag returns the camera relative pose as an array {TX, TY, TZ, PITCH, YAW, ROLL}
        //positive TX is to the right, equivalent to the y axis on the pigeon but flipped (so when we convert to a pose we flip this to match the axes on the pigeon)
        //positive TZ is straight forward, equivalent to the x axis on the pigeon
        //positive YAW is clockwise, so we flip it when we convert to pose so that it matches all of our other rotational components
        return new Pose2d(array[2], -array[0], new Rotation2d(Math.toRadians(-array[4])));
    }

    public Pose2d convertCameraSpaceToRobotSpace(Pose2d poseInCameraSpace){ //TODO: rewrite this as a transform (it works like this when the camera is parallel to the drivetrain, but as soon as the yaws are different it won't)
        Transform2d transform = new Transform2d(poseInCameraSpace.getTranslation(), poseInCameraSpace.getRotation());
        Pose2d cameraPoseFromRobotCenter = new Pose2d(Constants.LIMELIGHT_FORWARD_OFFSET, -Constants.LIMELIGHT_SIDE_OFFSET, new Rotation2d(Math.toRadians(Constants.LIMELIGHT_YAW_OFFSET)));
        Pose2d robotSpacePose = cameraPoseFromRobotCenter.transformBy(transform);
        System.out.println(robotSpacePose);
        return robotSpacePose;
    }

    
    Pose2d convertToFieldSpace(Pose2d targetPoseInRobotSpace, Pose2d robotPoseInFieldSpace) {
        Transform2d transform = new Transform2d(targetPoseInRobotSpace.getTranslation(), targetPoseInRobotSpace.getRotation()); //defines a transform
        Pose2d fieldPose = robotPoseInFieldSpace.transformBy(transform); //applies the transform to the pose (transforms the robot's pose by the target pose)
        return fieldPose;
    }

    //offsets a pose so that the front center of the robot will be at that point rather than the center
    Pose2d offsetToFrontCenter (Pose2d targetPoseInFieldSpace){ 
        Transform2d transform = new Transform2d(-Constants.CENTER_TO_BUMPER_OFFSET, 0, new Rotation2d(0)); //could change numbers here if we wanted to offset to a different part of the robot
        Pose2d result = targetPoseInFieldSpace.transformBy(transform);
        return result; 
    }

    public void setPipeline(int pipelineID) {
        limelightTable.getEntry("pipeline").setNumber(pipelineID); // Set the pipeline ID
    }

    @Override
    public void periodic() {

    }
}