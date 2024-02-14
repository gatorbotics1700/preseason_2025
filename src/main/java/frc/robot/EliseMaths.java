package frc.robot;
import java.lang.Math;

public class EliseMaths {
    //if this doesn't make any sense, slack elise for a diagram
    double W = 10; //lateral distance from shooter hinge to limelight
    double D = 10; //lateral distance from limelight to apriltag
    double H_1 = 10; //vertical distance from shooter to aprilTag
    double H_2 = 10; //vertical distance from limelight to aprilTag
    double N = 10; //distance from the back of the shooter to where we want to aim
    double Ty = 10; //limelight will tell us; angle from limelight crosshair to center of aprilTag
    double shooter_angle = Math.atan((H_1/((H_2/Math.tan(Ty)+W-N)))); //this assumes all angles are in radians
}
