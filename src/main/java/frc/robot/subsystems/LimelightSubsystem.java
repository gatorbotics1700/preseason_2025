package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.math.*;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable;
    private final double LIMELIGHT_HEIGHT = 0.355; // in meters
    private final double APRILTAG_HEIGHT =0.629; //also in meters
   // private final double DELTA_H = 0.1834156609;

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

    public void setPipeline(int pipelineID) {
        limelightTable.getEntry("pipeline").setNumber(pipelineID); // Set the pipeline ID
    }

    public double distanceToTag() {
    //     double d = (APRILTAG_HEIGHT-LIMELIGHT_HEIGHT)/Math.tan(Math.toRadians(getVerticalOffsetAngle()));
    //     System.out.println("v distance: " + (APRILTAG_HEIGHT-LIMELIGHT_HEIGHT));
    //    // System.out.println("TY: "+getVerticalOffsetAngle());
    //     System.out.println("tan: "+ Math.tan(Math.toRadians(getVerticalOffsetAngle())));
    //    return d;//(0.33/*APRILTAG_HEIGHT-LIMELIGHT_HEIGHT*/)/0.31/*Math.tan(getVerticalOffset())*/; //returns 2D distance to apriltag (so like distance from base of robot to the point on the floor directly below the apriltag) - Elise
        double TZ = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double [0])[2]; //returns Z offset to apriltag, but in the camera relative coordinate system
        double TY = limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double [0])[1]; //see above but it's y
        return Math.sqrt((TZ*TZ)+(TY*TY)); //distance from camera to apriltag as the crow flies
    }

    public double fieldYDistanceToTag(){
        // System.out.println("distance to tag: " + distanceToTag());
        // System.out.println("ROBOT ANGLE: " + DrivetrainSubsystem.getRobotAngle());
        // System.out.println("Robot Angle: " + (DrivetrainSubsystem.getRobotAngle()));

        double d = distanceToTag()*Math.sin(Math.toRadians((DrivetrainSubsystem.robotRotation)-getHorizontalOffsetAngle())) ;
        //if the direction we are driving in is negative, flip the variable d so d reflects this
        if((DrivetrainSubsystem.robotRotation+getHorizontalOffsetAngle())%360>180){
	        d=-d;
        }

        return d;
    }


    public double fieldXDistanceToTag(){
        System.out.println("distance to tag: " + distanceToTag());
        System.out.println("ROBOT ROTATION: " + DrivetrainSubsystem.robotRotation);

        double d = distanceToTag()*Math.cos(Math.toRadians((DrivetrainSubsystem.robotRotation)-getHorizontalOffsetAngle())) ;//- 0.7874/2;
        //if the direction we are driving in is negative, flip the variable d so d reflects this
        if((DrivetrainSubsystem.robotRotation+getHorizontalOffsetAngle())%360>90 && DrivetrainSubsystem.robotRotation+getHorizontalOffsetAngle()%360<270){
	        d=-d;
        }
        return d;
    }

    //public double 

    @Override
    public void periodic() {
        
    }
}