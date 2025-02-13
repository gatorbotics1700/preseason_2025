package frc.robot;

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
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(216.299);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(200.303);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(128.584);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(13.623);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;

    // nemo
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    // public static final String CANIVORE_BUS_NAME = "CANivore Bus 1";
    // public static final double LIMELIGHT_FORWARD_OFFSET = 0.13335;
    // public static final double LIMELIGHT_SIDE_OFFSET = -0.30559;
    // public static final double LIMELIGHT_UP_OFFSET = 0.314325;

    // dory
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(98.173828125);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(149.23828125);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(333.10546875);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(102.8320312500);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    public static final String CANIVORE_BUS_NAME = "";
    public static final double LIMELIGHT_FORWARD_OFFSET = 0.3222625;
    public static final double LIMELIGHT_SIDE_OFFSET = -0.1524;
    public static final double LIMELIGHT_UP_OFFSET = 0.56356;

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
}
