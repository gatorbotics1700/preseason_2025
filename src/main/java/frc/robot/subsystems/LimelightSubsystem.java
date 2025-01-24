package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.math.*;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable;
    private final double LIMELIGHT_HEIGHT = 0.37; // in meters
    private final double APRILTAG_HEIGHT = 0.675; //also in meters

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

    public void setPipeline(int pipelineID) {
        limelightTable.getEntry("pipeline").setNumber(pipelineID); // Set the pipeline ID
    }

    public double distanceToTag() {
        double d = (APRILTAG_HEIGHT-LIMELIGHT_HEIGHT)/Math.tan(Math.toRadians(getVerticalOffsetAngle()));
        // System.out.println("v distance: " + (APRILTAG_HEIGHT-LIMELIGHT_HEIGHT));
        // System.out.println("TY: "+getVerticalOffsetAngle());
        // System.out.println("tan: "+ Math.tan(Math.toRadians(getVerticalOffsetAngle())));
       return d;//(0.33/*APRILTAG_HEIGHT-LIMELIGHT_HEIGHT*/)/0.31/*Math.tan(getVerticalOffset())*/; //returns 2D distance to apriltag (so like distance from base of robot to the point on the floor directly below the apriltag) - Elise

    }

    public double fieldYDistanceToTag(){
        // System.out.println("distance to tag: " + distanceToTag());
        // System.out.println("ROBOT ANGLE: " + DrivetrainSubsystem.getRobotAngle());
        // System.out.println("Robot Angle: " + (DrivetrainSubsystem.getRobotAngle()));

        double d = distanceToTag()*Math.sin(Math.toRadians((DrivetrainSubsystem.getRobotAngle())-getHorizontalOffsetAngle()));
        //if the direction we are driving in is negative, flip the variable d so d reflects this
        if((DrivetrainSubsystem.getRobotAngle()+getHorizontalOffsetAngle())%360>180){
	        d=-d;
        }

        return d;
    }

    public double fieldXDistanceToTag(){
        System.out.println("distance to tag: " + distanceToTag());
        System.out.println("ROBOT ANGLE: " + DrivetrainSubsystem.getRobotAngle());
        System.out.println("Robot Angle: " + (DrivetrainSubsystem.getRobotAngle()));

        double d = distanceToTag()*Math.cos(Math.toRadians((DrivetrainSubsystem.getRobotAngle())-getHorizontalOffsetAngle()));
        //if the direction we are driving in is negative, flip the variable d so d reflects this
        if((DrivetrainSubsystem.getRobotAngle()+getHorizontalOffsetAngle())%360>180){
	        d=-d;
        }
        return d;
    }

    //public double 

    @Override
    public void periodic() {
        
    }
}