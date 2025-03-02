package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import frc.com.swervedrivespecialties.swervelib.MechanicalConfiguration;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

public class Constants {
    //for a 25x25 drivetrain
    // public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.508;
    // public static final double DRIVETRAIN_WHEELBASE_METERS = 0.508;

    //for a 30x30 drivetrain
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.508 + 0.127;
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.508 + 0.127;

    public static final int DRIVETRAIN_PIGEON_ID = 6;

    // hulk
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(118.6253437499); //116.806640625
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(283.7109375); //282.919921875
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(124.0988159179); //121.640624999
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(38.75976562499); //227.548
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;
    // public static final String CANIVORE_BUS_NAME = "";

    //nemo
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    public static final String CANIVORE_BUS_NAME = "CANivore Bus 1";
    //position of robot in regular robot coordinates. NOTE: left used to be negative, but not it isn't since we're following the pigeon axes!
    public static final Pose3d LIMELIGHT_OFFSETS = new Pose3d(0.172, 0.325, 0.197, new Rotation3d(Math.toRadians(10.0), Math.toRadians(-23.0), Math.toRadians(-46.0)));
    public static final double CENTER_TO_BUMPER_OFFSET = 0.3937;

    public static final Pose2d FRONT_CENTER_ALIGN_OFFSET = new Pose2d(CENTER_TO_BUMPER_OFFSET, 0, new Rotation2d(0)); //offset from center of robot to where we want to line up with the april tag

    // dory
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(98.173828125);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(149.23828125);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(333.10546875);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(102.8320312500);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    // public static final String CANIVORE_BUS_NAME = "";
    // public static final double LIMELIGHT_FORWARD_OFFSET = 0.26035;
    // public static final double LIMELIGHT_SIDE_OFFSET = -0.00635;
    // public static final double LIMELIGHT_UP_OFFSET = 1.143;
    // public static final double LIMELIGHT_YAW_OFFSET = -4.0;
    // public static final double LIMELIGHT_PITCH_OFFSET = -32.0;
    // public static final double LIMELIGHT_ROLL_OFFSET = 0.0;
    // public static final double CENTER_TO_BUMPER_OFFSET = 0.4572; 

    // //the line up offsets are the point (in robot relative coordinates) that needs to align with the apriltag in order for us to score left/right post 
    // //(we flip the values in our offset method so that we can find the pose the center of the robot needs to be at, but they should not be flipped here!)
    // public static final Pose2d LEFT_POST_LINE_UP_OFFSET = new Pose2d(Constants.CENTER_TO_BUMPER_OFFSET,0, new Rotation2d(0)); //TODO: measure y value
    // public static final Pose2d RIGHT_POST_LINE_UP_OFFSET = new Pose2d(Constants.CENTER_TO_BUMPER_OFFSET, LEFT_POST_LINE_UP_OFFSET.getY() - 0.3302 , new Rotation2d(0)); //0.3302 is the distance between posts!

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 21;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 20; 
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 2;

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 23; 
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 22; 
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 3;

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 27; 
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 26; 
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 5; 

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 25; 
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 24; 
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 4;

    public static final double LOOPTIME_SECONDS = 0.02;
}
