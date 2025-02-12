package frc.robot;

import frc.com.swervedrivespecialties.swervelib.MechanicalConfiguration;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

public class Constants {
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.2794;
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.2794;

    public static final int DRIVETRAIN_PIGEON_ID = 6;

    // hulk
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(216.299);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(200.303);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(128.584);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(13.623);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;

    // nemo
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;

    // dory
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(98.173828125);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(149.23828125);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(333.10546875);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(102.8320312500);
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
    
    public static final double KRAKEN_TICKS_PER_REV = 2048; //same for falcons
    public static final double NEO_TICKS_PER_REV = 42;
    
    // for the old coral shooter mechanism
    public static final int CORAL_SHOOTER_CAN_ID = 30;
    public static final double CORAL_INTAKING_SPEED = -0.5;
    public static final double CORAL_OUTTAKING_SPEED = 0.1; 
    public static final double SERVO_ANGLE = 45.0;

    public static final double CLIMBING_SPEED = -0.15; 
    public static final int CLIMBING_MOTOR_CAN_ID = 0;

    //TODO: find the real values of ALL of these constants
    public static final int ELEVATOR_CAN_ID = 17; //TODO: get actual CAN ID
    public static final double ELEVATOR_SPROCKET_DIAMETER = 1.762; // inches
    public static final double ELEVATOR_GEAR_RATIO = 81.0;
    public static final double ELEVATOR_TICKS_PER_INCH = ((KRAKEN_TICKS_PER_REV * ELEVATOR_GEAR_RATIO) / ELEVATOR_SPROCKET_DIAMETER / Math.PI)/2;
    public static final int TOP_LIMIT_SWITCH_PORT = 9;
    public static final int BOTTOM_LIMIT_SWITCH_PORT = 0;

    public static final int ALGAE_CAN_ID = 16;
    public static final int ALGAE_PIVOT_CAN_ID = 15;
    public static final double ALGAE_PIVOT_TICKS_PER_DEGREE = KRAKEN_TICKS_PER_REV * 5 / 360; //42 ticks per revolution for NeoMotor, 5:1 gear ratio TODO: check values for Kraken motors instead
    public static final int ALGAE_LIMIT_SWITCH_PORT = 5; // TODO: change

    public static final int CORAL_CAN_ID = 12;
    public static final int CORAL_PIVOT_CAN_ID = 11;
    public static final double CORAL_PIVOT_TICKS_PER_DEGREE = KRAKEN_TICKS_PER_REV * 10 / 360; // 42 ticks per revolution for NeoMotor, 10:1 gear ratio TODO: check values for Kraken motors instead
    public static final int CORAL_LIMIT_SWITCH_PORT = 13;



    /* other information
     * degrees to ticks conversion: ticks per rev * gear ratio / 360
     */
}   
