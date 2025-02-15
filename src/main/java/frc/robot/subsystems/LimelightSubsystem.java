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

    public Pose2d getPositionFromTag(){
        System.out.println("**************" + limelightTable.getEntry("botpose_wpiblue").getDoubleArray(new double[1]).length);
        double tx = limelightTable.getEntry("botpose_wpiblue").getDoubleArray(new double[6])[0];
        // tx -= 0.105;
        double ty = limelightTable.getEntry("botpose_wpiblue").getDoubleArray(new double[6])[1];
        // ty -= 0.3175;
        double yaw = limelightTable.getEntry("botpose_wpiblue").getDoubleArray(new double[6])[4];
        Pose2d newPose = new Pose2d(tx,ty, new Rotation2d(Math.toRadians(yaw)));
        return newPose;
    } 
    

    /**
     * Calculates a target pose for the robot based on AprilTag detection from the Limelight.
     * Uses vision data to determine the relative position and orientation to an AprilTag,
     * then transforms this into field coordinates for robot positioning.
     *
     * @param robotPoseInFieldSpace The current Pose2d of the robot in field coordinates
     * @return A Pose2d representing the target position and rotation in field coordinates,
     *         calculated using the detected AprilTag's position and the robot's current pose.
     *         The returned pose includes:
     *         - X/Y coordinates offset from current position based on tag distance
     *         - Rotation aligned parallel to the detected tag
     *         Returns null if no AprilTag is detected or vision data is invalid.
     */
    public Pose2d aprilTagPoseInFieldSpace(Pose2d robotPoseInFieldSpace) {
        // distance to the camera from the tag (in camera's coordinate space)
        double[] aprilTagArrayInCameraSpace = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
        Pose2d aprilTagPoseInCameraSpace = arrayToPose(aprilTagArrayInCameraSpace);
        Pose2d aprilTagPoseInRobotSpace = convertCameraSpaceToRobotSpace(aprilTagPoseInCameraSpace);
        double xOffsetToFrontOfRobot = aprilTagPoseInRobotSpace.getX() - X_OFFSET;
        Pose2d finalRobotSpacePose = new Pose2d(xOffsetToFrontOfRobot, aprilTagPoseInRobotSpace.getY(), aprilTagPoseInRobotSpace.getRotation());
        return convertToFieldSpace(finalRobotSpacePose, robotPoseInFieldSpace);
    }

    Pose2d arrayToPose(double[] array){
        // I HATE THIS IT'S EVIL AAAAAAAAA - Patricia
        return new Pose2d(array[2], array[0], new Rotation2d(Math.toRadians(-array[4])));
    }

    Pose2d convertCameraSpaceToRobotSpace(Pose2d poseInCameraSpace){
        double offsetX = poseInCameraSpace.getX() + Constants.LIMELIGHT_FORWARD_OFFSET;
        double offsetY = -poseInCameraSpace.getY() - Constants.LIMELIGHT_SIDE_OFFSET;
        Rotation2d yaw = poseInCameraSpace.getRotation();
        return new Pose2d(offsetX, offsetY, yaw);
    }

    //
    Pose2d convertToFieldSpace(Pose2d targetPoseInRobotSpace, Pose2d robotPoseInFieldSpace) {
        System.out.println("Robot field space rotation: " + robotPoseInFieldSpace.getRotation());
        Transform2d transform = new Transform2d(targetPoseInRobotSpace.getTranslation(), targetPoseInRobotSpace.getRotation()); //defines a transform
        System.out.println("target camera space rotation: " + targetPoseInRobotSpace.getRotation());
        Pose2d fieldPose = robotPoseInFieldSpace.transformBy(transform); //applies the transform to the pose
        System.out.println("Robot field space transformed rotation: " + fieldPose.getRotation());
        return fieldPose;
    }

    public void setPipeline(int pipelineID) {
        limelightTable.getEntry("pipeline").setNumber(pipelineID); // Set the pipeline ID
    }

    private double getTZ(){
        // returns Z offset to apriltag, but in the camera relative coordinate system
        double[] targetPose = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
        double TZ = targetPose[2]; 
        System.out.println("TZ: " + TZ);
        //offset this so it's from the edge of the bumper and not the limelight
       // TZ -= Math.signum(TZ)*X_OFFSET;
        System.out.println("xoffset: " + X_OFFSET);
        
        return TZ;
    }
    
    private double getTY(){
        double[] targetPose = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
        // see above but it's y
        double TY = targetPose[1];
        System.out.println("TY: " + TY);
        //offset so its from center of edge of bumper
        //TY -= Constants.LIMELIGHT_SIDE_OFFSET;
       
        return TY;
    }

    public double distanceToTag() {
        double TZ = getTZ();
        System.out.println("TZ with offset: " + TZ);
        double TY = getTY();
        System.out.println("TY with offset: " + TY);
        
        System.out.println("DISTANCE TO TAG: "+  Math.sqrt((TZ * TZ) + (TY * TY)));
        return Math.sqrt((TZ * TZ) + (TY * TY)); // distance from camera to apriltag as the crow flies
    }

    public double fieldYDistanceToTag(double robotRotation) { // TODO add some sort of offset so that it lines up the way we want
        double d = (distanceToTag())
                * Math.sin(Math.toRadians((robotRotation) - getHorizontalOffsetAngle()));
        double fieldYOffset = Math.abs(Math.sin(Math.toRadians(robotRotation))*X_OFFSET);
        d -= Math.signum(d) * fieldYOffset;
        return d;
    }

    public double fieldXDistanceToTag(double robotRotation) { 
        // double limelightToCenterDist = Math.sqrt(LIMELIGHT_SIDE_OFFSET*LIMELIGHT_SIDE_OFFSET + LIMELIGHT_FORWARD_OFFSET*LIMELIGHT_FORWARD_OFFSET);
        // double limelightToCenterAngle = Math.atan(LIMELIGHT_SIDE_OFFSET/LIMELIGHT_FORWARD_OFFSET);
        // double fieldRelativeOffsetAngle = limelightToCenterAngle - Math.toRadians(robotRotation);
        // double xOffset = Math.cos(fieldRelativeOffsetAngle)*limelightToCenterDist;
        double d = (distanceToTag())
                * Math.cos(Math.toRadians((robotRotation) - getHorizontalOffsetAngle()));
        double fieldXOffset = Math.abs(Math.cos(Math.toRadians(robotRotation))*X_OFFSET);
        d -= Math.signum(d) * fieldXOffset;
        double centerXOffset = Math.sqrt(X_OFFSET*X_OFFSET + Constants.LIMELIGHT_SIDE_OFFSET*Constants.LIMELIGHT_SIDE_OFFSET) * Math.cos(Math.abs(robotRotation - Math.atan(Constants.LIMELIGHT_SIDE_OFFSET/X_OFFSET)));
        d -= Math.signum(d) * centerXOffset;

        // double xOffset =
      //  d -= (X_OFFSET*Math.signum(d));
      //  d += xOffset;
        return d;
    }

    @Override
    public void periodic() {

    }
}