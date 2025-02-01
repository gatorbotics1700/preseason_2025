package frc.robot;

import frc.com.swervedrivespecialties.swervelib.MechanicalConfiguration;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

public class Constants {
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.2794;
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.2794;

    public static final int DRIVETRAIN_PIGEON_ID = 6;

    // hulk
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(216.299);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(200.303);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(128.584);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(13.623);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;

    // nemo
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;

    // dory
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(59.414);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(194.941 + 180);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(7.207);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(242.574);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;

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
    
    public static final double TICKS_FOR_REV = 2048;
    
    public static final int CORAL_MOTOR_CAN_ID = 30;
    public static final double CORAL_INTAKING_SPEED = 0.05;
    public static final double CORAL_OUTTAKING_SPEED = -0.75; 
    public static final int SERVO_PWM_PORT = 0;
    public static final double SERVO_ANGLE = 45.0;

    public static final double CLIMBING_SPEED = 0.2; 
    public static final int LEFT_CLIMBING_MOTOR_CAN_ID = 0;
    public static final int RIGHT_CLIMBING_MOTOR_CAN_ID = 20;

    //TODO: find the real values of ALL of these constants
    public static final int ELEVATOR_CAN_ID = 17; //TODO: get actual CAN ID
    public static final double ELEVATOR_SPROCKET_DIAMETER = 1.762; // inches
    public static final double ELEVATOR_GEAR_RATIO = 36.0;
    public static final double ELEVATOR_TICKS_PER_INCH = TICKS_FOR_REV*ELEVATOR_GEAR_RATIO/ELEVATOR_SPROCKET_DIAMETER/Math.PI;

    public static final int ALGAE_CAN_ID = 16;
    public static final int ALGAE_PIVOT_CAN_ID = 15;
    public static final int ALGAE_PIVOT_TICKS_PER_DEGREE = 42 * 5 / 360; //TODO: find ticks per rev for neos and replace 2048
    public static final int ALGAE_LIMIT_SWITCH_PORT = 0; // TODO: change

    public static final int CORAL_CAN_ID = 12;
    public static final int CORAL_PIVOT_CAN_ID = 11;
    public static final int CORAL_PIVOT_TICKS_PER_DEGREE = 42 * 10 / 360; // 2048 ticks per revolution for TalonFX, 10:1 gear ratio
    public static final int CORAL_LIMIT_SWITCH_PORT = 13;

    /* other information
     * degrees to ticks conversion: ticks per rev * gear ratio / 360
     */
}   
