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

    //hulk
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(118.6253437499); //116.806640625
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(283.7109375); //282.919921875
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(124.0988159179); //121.640624999
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(38.75976562499); //227.548
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;
    public static final String CANIVORE_BUS_NAME = "";
    public static final double LIMELIGHT_FORWARD_OFFSET = 0.3429;
    public static final double LIMELIGHT_SIDE_OFFSET = 0.0;
    public static final double LIMELIGHT_UP_OFFSET = 0.269875;
    public static final double LIMELIGHT_YAW_OFFSET = 0.0;
    public static final double LIMELIGHT_PITCH_OFFSET = -2.0;
    public static final double LIMELIGHT_ROLL_OFFSET = -1.0;

    //nemo
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    // public static final String CANIVORE_BUS_NAME = "CANivore Bus 1";
    // public static final double LIMELIGHT_FORWARD_OFFSET = 0.13335;
    // public static final double LIMELIGHT_SIDE_OFFSET = -0.30559;
    // public static final double LIMELIGHT_UP_OFFSET = 0.314325;
    // public static final double LIMELIGHT_YAW_OFFSET = 0.0;
    // public static final double LIMELIGHT_PITCH_OFFSET = 0.0;
    // public static final double LIMELIGHT_ROLL_OFFSET = 0.0;

    // dory
    /* 
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(98.173828125);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(149.23828125);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(333.10546875);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(102.8320312500);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    public static final String CANIVORE_BUS_NAME = "";
    public static final double LIMELIGHT_FORWARD_OFFSET = 0.36195;
    public static final double LIMELIGHT_SIDE_OFFSET = -0.2413;
    public static final double LIMELIGHT_UP_OFFSET = 0.200025;
    public static final double LIMELIGHT_YAW_OFFSET = 2.0;
    public static final double LIMELIGHT_PITCH_OFFSET = 28.0;
    public static final double LIMELIGHT_ROLL_OFFSET = 0.0;
*/
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
    public static final double CORAL_INTAKING_SPEED = 0.25;
    public static final double CORAL_OUTTAKING_SPEED = -0.74; 
    public static final double SERVO_ANGLE = 45.0;

    public static final double CLIMBING_SPEED = -0.15; 
    public static final int CLIMBING_MOTOR_CAN_ID = 0;

    //TODO: find the real values of ALL of these constants
    public static final int ELEVATOR_CAN_ID = 17; //TODO: get actual CAN ID
    public static final double ELEVATOR_SPROCKET_DIAMETER = 1.762; // inches
    public static final double ELEVATOR_GEAR_RATIO = 81.0;
    public static final double ELEVATOR_TICKS_PER_INCH = ((KRAKEN_TICKS_PER_REV * ELEVATOR_GEAR_RATIO) / ELEVATOR_SPROCKET_DIAMETER / Math.PI)/2;
    public static final double ELEVATOR_LEVEL_ONE = 0.0; //TODO: fill in
    public static final double ELEVATOR_LEVEL_TWO = 0.0; //TODO: fill in
    public static final double ELEVATOR_LEVEL_THREE = 0.0; //TODO: fill in
    public static final double ELEVATOR_LEVEL_FOUR = 30.0; //TODO: fill in this value is a temp value for testing
    public static final int TOP_LIMIT_SWITCH_PORT = 9;
    public static final int BOTTOM_LIMIT_SWITCH_PORT = 0;

    public static final int STICK_CAN_ID = 16;
    public static final int STICK_PIVOT_CAN_ID = 32;
    public static final double STICK_PIVOT_TICKS_PER_DEGREE = KRAKEN_TICKS_PER_REV * 75 / 360; //42 ticks per revolution for NeoMotor, 5:1 gear ratio TODO: check values for Kraken motors instead
    public static final int STICK_LIMIT_SWITCH_PORT = 5; // TODO: change
    public static final double STICK_PIVOT_SHOOTING_ANGLE = 30.0; //TODO: fill in
    public static final double STICK_SPEED = 0.2;

    /* other information
     * degrees to ticks conversion: ticks per rev * gear ratio / 360
     */
}   
