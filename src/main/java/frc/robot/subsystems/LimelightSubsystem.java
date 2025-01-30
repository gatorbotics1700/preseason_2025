package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable;
    private final double X_OFFSET = 0.2921; //distance from front of robot to limelight in meters

    public LimelightSubsystem() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
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

    public double getHorizontalOffsetAngle() {
        return limelightTable.getEntry("tx").getDouble(0.0);
    }

    public double getVerticalOffsetAngle() {
        double ty = limelightTable.getEntry("ty").getDouble(0.0);
        double calibratedAngle = (ty*1.02454) - 1.73401;
        System.out.println("TY: "+ ty + " calibratedAngle: " + calibratedAngle);
        return calibratedAngle;
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

    public double getTagYaw() {
        return limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double [0])[4]; //returns yaw of april tag relative to camera
    }
    public void setPipeline(int pipelineID) {
        limelightTable.getEntry("pipeline").setNumber(pipelineID); // Set the pipeline ID
    }

    

    public double distanceToTag() {
        double TZ = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double [0])[2]; //returns Z offset to apriltag, but in the camera relative coordinate system
        double TY = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double [0])[1]; //see above but it's y
        return Math.sqrt((TZ*TZ)+(TY*TY)); //distance from camera to apriltag as the crow flies
    }

    public double fieldYDistanceToTag(){
        double d = distanceToTag()*Math.sin(Math.toRadians((DrivetrainSubsystem.robotRotation)-getHorizontalOffsetAngle())) ;
        return d;
    }


    public double fieldXDistanceToTag(){
        System.out.println("distance to tag: " + distanceToTag());
        System.out.println("ROBOT ROTATION: " + DrivetrainSubsystem.robotRotation);

        double d = distanceToTag()*Math.cos(Math.toRadians((DrivetrainSubsystem.robotRotation)-getHorizontalOffsetAngle())) ;//- 0.7874/2;
        if(Math.signum(d) == -1){
            d += X_OFFSET;
        }else{
            d -= X_OFFSET;
        }
        System.out.println("dx " + d);
        return d;
    }

    @Override
    public void periodic() {
        
    }
}