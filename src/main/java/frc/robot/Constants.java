// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/*
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {
    /*
     * The left-to-right distance between the drivetrain wheels
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.4690872; //TODO: check this (distance between wheels, not size of drivetrain //units = meters //previously 18.468
    /*
     * The front-to-back distance between the drivetrain wheels.
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.4690872; //TODO: check this (distance between wheels, not size of drivetrain //units = meter //previously 18.468

    public static final int DRIVETRAIN_PIGEON_ID = 6; 

    public static final double TICKS_PER_REV = 2048;

    public static final double SWERVE_GEAR_RATIO = 6.75; 
    public static final double SWERVE_WHEEL_DIAMETER = 4.0; //inches
    public static final double SWERVE_TICKS_PER_INCH = TICKS_PER_REV*SWERVE_GEAR_RATIO/(SWERVE_WHEEL_DIAMETER*Math.PI); //talonfx drive encoder
    public static final double SWERVE_TICKS_PER_METER = SWERVE_TICKS_PER_INCH/Constants.METERS_PER_INCH;

    //WARNING: FRONT RIGHT AND BACK RIGHT STEER ENCODERS CAN IDS NEED CHANGES FOR HULK VS. MCQUEEN
    //offsets Hulk 
     
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(216.299);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(200.303);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(128.584);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(13.623);
    
    //offsets McQueen

    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(197); //254 vals: 356.924);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(95); //352.705)
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(171); //2.109);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(253.91); //8.086);


   // even can ids are steer, odd can ids are drive
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

    public static final double DRIVE_MOTOR_MIN_VOLTAGE = 0.15;
    public static final double DRIVE_MOTOR_MAX_VOLTAGE = 0.7;
    public static final double STEER_MOTOR_MIN_VOLTAGE = 0.15;
    public static final double STEER_MOTOR_MAX_VOLTAGE = 0.6;

    public static final double METERS_PER_INCH = 0.0254;

    public static final int INTAKE_MOTOR_CAN_ID = 28;
    public static final int PIVOT_MOTOR_CAN_ID = 32; 
    public static final int TURRET_MOTOR_CAN_ID = 30;

    public static final int LOW_MOTOR_CAN_ID = 30;
    public static final int SHOOTER_HIGH_CAN_ID = 34;
    public static final int SHOOTER_MID_CAN_ID = 38;

    public static final int CONFIG_TIMEOUT_MS = 500;
    public static final int PID_LOOP_IDX = 0;
    public static final int SLOT_IDX = 0;

    public static final int AMP_LIMIT_SWITCH_PORT = 9;
    //public static final int STAGE_LIMIT_SWITCH_PORT = 5;
    public static final int LED_PORT = 0;
    public static final int NUMLED = 60;

    //public static final int BEAMBREAK_PORT = 0;
}