package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AprilTagProcessor {
    private final NetworkTable limelightTable;
    private final int targetAprilTagId;
    private final double tagX;
    private final double tagY;
    private final double tagZ;
    
    public AprilTagProcessor(int aprilTagId, double x, double y, double z) {
        this.limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        this.targetAprilTagId = aprilTagId;
        this.tagX = x;
        this.tagY = y;
        this.tagZ = z;
    }
    
    public boolean hasValidTarget() {
        double tv = limelightTable.getEntry("tv").getDouble(0);
        double tid = limelightTable.getEntry("tid").getDouble(-1);
        return tv == 1.0 && tid == targetAprilTagId;
    }
    
    public Pose2d getRobotPose() {
        if (!hasValidTarget()) {
            return null;
        }
        
        // Get targeting data from Limelight
        double tx = limelightTable.getEntry("tx").getDouble(0.0);
        double ty = limelightTable.getEntry("ty").getDouble(0.0);
        
        // Convert angles to radians
        double txRadians = Math.toRadians(tx);
        double tyRadians = Math.toRadians(ty);
        
        // Calculate distance using trigonometry
        double heightDiff = tagZ - LIMELIGHT_HEIGHT;
        double distance = heightDiff / Math.tan(LIMELIGHT_MOUNT_ANGLE + tyRadians);
        
        // Calculate robot position relative to tag
        double robotX = tagX - (distance * Math.cos(txRadians));
        double robotY = tagY - (distance * Math.sin(txRadians));
        
        // Calculate robot rotation (assuming robot is facing the tag)
        double robotRotation = Math.PI + Math.atan2(tagY - robotY, tagX - robotX);
        
        return new Pose2d(robotX, robotY, new Rotation2d(robotRotation));
    }
    
    // Constants - adjust these based on your robot's configuration
    private static final double LIMELIGHT_HEIGHT = 0.10795; // meters
    private static final double LIMELIGHT_MOUNT_ANGLE = Math.toRadians(0); // radians
}