package frc.robot;

import frc.com.swervedrivespecialties.swervelib.MechanicalConfiguration;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

public class Constants {
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.2794;
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.2794;

    public static final int DRIVETRAIN_PIGEON_ID = 6;

    //hulk
    //public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(216.299);
    //public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(200.303);
   // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(128.584);
    //public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(13.623);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;

     //nemo
    //public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    //public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
   // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    //public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    //public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;

    //dory
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(59.326);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(316.58);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(7.378);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(242.49);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;



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
