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

    //offsets Hulk
    /* 
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(216.299);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(200.303);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(128.584);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(13.623);
    */
    
    //offsets Mcqueen
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(22.587890625 - 4);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(177.626953125);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(92.400051269531251 + 70);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(2.900390625);

    //even can ids are steer, odd can ids are drive
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

    public static final double TICKS_PER_REV = 2048;
    public static final double METERS_PER_INCH = 0.0254;
}