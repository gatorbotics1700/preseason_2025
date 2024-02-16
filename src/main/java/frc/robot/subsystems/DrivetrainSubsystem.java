//DO NOT TOUCH THIS FILE UNLESS YOU HAVE CHECKED WITH MULTIPLE PEOPLE! THIS CODE IS FINALIZED!

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import frc.com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import frc.com.swervedrivespecialties.swervelib.SwerveModule;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.OI;

public class DrivetrainSubsystem {

  /*
   * The maximum voltage that will be delivered to the motors.
   * This can be reduced to cap the robot's maximum speed. Typically, this is useful during initial testing of the robot.
   */
   private static final double MAX_VOLTAGE = 12.0; //TODO: double check this; previously 16.3

   public static final double MIN_VELOCITY_METERS_PER_SECOND = 0.15;
   //public static final double MIN_VELOCITY_METERS_PER_SECOND = 1.5; //TODO: fix dummy value

  /* The formula for calculating the theoretical maximum velocity is:
   * <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> * pi
   * The maximum velocity of the robot in meters per second.
   * This is a measure of how fast the robot should be able to drive in a straight line.
   */
   private static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 * // <-- these are copied from SDS library 
      SdsModuleConfigurations.MK4_L2.getDriveReduction() *
      SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI;
      // = 5.38281261
  
  /*
   * The maximum angular velocity of the robot in radians per second.
   * This is a measure of how fast the robot can rotate in place.
   * Here we calculate the theoretical maximum angular velocity. You can also replace this with a measured amount.
   */
   private static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
      Math.hypot(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0);

   private SwerveDriveKinematics kinematics;

   /*
    * By default we use a Pigeon for our gyroscope. But if you use another gyroscope, like a NavX, you can change this.
    * The important thing about how you configure your gyroscope is that rotating the robot counter-clockwise should
    * cause the angle reading to increase until it wraps back over to zero.
    */ 
   private Pigeon2 pigeon;

   // These are our modules. We initialize them in the constructor.
   private SwerveModule frontLeftModule;
   private SwerveModule frontRightModule;
   private SwerveModule backLeftModule;
   private SwerveModule backRightModule;

   private SwerveDrivePoseEstimator positionManager;
   private ShuffleboardTab tab;

   //ChassisSpeeds takes in y velocity, x velocity, speed of rotation
   private ChassisSpeeds chassisSpeeds; //sets expected chassis speed to be called the next time drive is run
   //we need to use this fix drift that happens when we apply the offsets, this is how that drift is measured
   private double startingGyroRotation; 
  
   public DrivetrainSubsystem() {
      pigeon = new Pigeon2(Constants.DRIVETRAIN_PIGEON_ID);
      tab = Shuffleboard.getTab("Drivetrain");
      //startingGyroRotation = getGyroscopeRotation().getDegrees();

      // We will use mk4 modules with Falcon 500s with the L2 configuration. 
      frontLeftModule = Mk4SwerveModuleHelper.createFalcon500(
         // This parameter is optional, but will allow you to see the current state of the module on the dashboard.
         tab.getLayout("Front Left Module", BuiltInLayouts.kList)
               .withSize(2, 4)
               .withPosition(0, 0), //TODO: see if we can put this in constants
            // This can be any level from L1-L4 depending on the gear configuration (the levels allow different amounts of speed and torque)
            Mk4SwerveModuleHelper.GearRatio.L2,
            // This is the ID of the drive motor
            Constants.FRONT_LEFT_MODULE_DRIVE_MOTOR,
            // This is the ID of the steer motor
            Constants.FRONT_LEFT_MODULE_STEER_MOTOR,
            // This is the ID of the steer encoder
            Constants.FRONT_LEFT_MODULE_STEER_ENCODER,
            // This is how much the steer encoder is offset from true zero (In our case, zero is facing straight forward)
            Constants.FRONT_LEFT_MODULE_STEER_OFFSET
         );
        
      // We will do the same for the other modules
      //TODO: check if we want to construct on every enable
      frontRightModule = Mk4SwerveModuleHelper.createFalcon500(
         tab.getLayout("Front Right Module", BuiltInLayouts.kList)
               .withSize(2, 4)
               .withPosition(2, 0), //TODO: see if we can put this in constants
            Mk4SwerveModuleHelper.GearRatio.L2,
            Constants.FRONT_RIGHT_MODULE_DRIVE_MOTOR,
            Constants.FRONT_RIGHT_MODULE_STEER_MOTOR,
            Constants.FRONT_RIGHT_MODULE_STEER_ENCODER,
            Constants.FRONT_RIGHT_MODULE_STEER_OFFSET
      );
        
      backLeftModule = Mk4SwerveModuleHelper.createFalcon500(
         tab.getLayout("Back Left Module", BuiltInLayouts.kList)
               .withSize(2, 4)
               .withPosition(4, 0), //TODO: see if we can put this in constants
            Mk4SwerveModuleHelper.GearRatio.L2,
            Constants.BACK_LEFT_MODULE_DRIVE_MOTOR,
            Constants.BACK_LEFT_MODULE_STEER_MOTOR,
            Constants.BACK_LEFT_MODULE_STEER_ENCODER,
            Constants.BACK_LEFT_MODULE_STEER_OFFSET
      );
        
      backRightModule = Mk4SwerveModuleHelper.createFalcon500(
         tab.getLayout("Back Right Module", BuiltInLayouts.kList)
               .withSize(2, 4)
               .withPosition(6, 0), //TODO: see if we can put this in constants
            Mk4SwerveModuleHelper.GearRatio.L2,
            Constants.BACK_RIGHT_MODULE_DRIVE_MOTOR,
            Constants.BACK_RIGHT_MODULE_STEER_MOTOR,
            Constants.BACK_RIGHT_MODULE_STEER_ENCODER,
            Constants.BACK_RIGHT_MODULE_STEER_OFFSET
      );

      onEnable();
   }

   public void onEnable(){
      System.out.println("Initializing drivetrain subsystem vars");
      /* 
       * Positive x values represent moving toward the front of the robot whereas positive y values represent moving toward the left of the robot.
       * Setting up location of modules relative to the center of the robot 
       */
      kinematics = new SwerveDriveKinematics( 
         // Front left
         new Translation2d(Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0, Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0),
         //translation2d refers to the robot's x and y position in the larger field coordinate system
         // Front right
         new Translation2d(Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0, -Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0), 
         // Back left
         new Translation2d(-Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0, Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0),
         // Back right
         new Translation2d(-Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0, -Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0)
      );

      chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);
      
      System.out.println("assigning a value to positionManager");
      positionManager = new SwerveDrivePoseEstimator(
         kinematics, 
         getGyroscopeRotation(), 
         getModulePositionArray(), 
         new Pose2d(0, 0, new Rotation2d(Math.toRadians(180)))
      ); 
      System.out.println("set position manager to:" + positionManager.getEstimatedPosition());
   }
  
   //from pigeon used for updating our odometry
   //in an unknown, arbitrary frame
   //"do not use unless you know what you are doing" - patricia
   public Rotation2d getGyroscopeRotation() {
      return new Rotation2d(Math.toRadians(pigeon.getYaw().getValue())); //getYaw() returns degrees
   }

   //from odometry used for field-relative rotation
   public Rotation2d getPoseRotation() {
      return getPose().getRotation(); 
   }

  //log the current position within the positionManager so that it knows what our encoders say about our position
  //the positionManager will then update its pose estimate, accounting for any drift
   public void updatePositionManager(){
      positionManager.update(getGyroscopeRotation(), getModulePositionArray());
   }
   //the next iteration of drive will use this speed  
   public void setSpeed(ChassisSpeeds chassisSpeeds) { 
      this.chassisSpeeds = chassisSpeeds;
   }
  
  /* 
   * this method is responsible for getting values from the xbox controller and setting the speed that drive will call
   * this will be called right before drive() in teleopPeriodic in Robot.java 
   */
   public void driveTeleop(){
      DoubleSupplier translationXSupplier;
      DoubleSupplier translationYSupplier;
      DoubleSupplier rotationSupplier;
      //TODO: check negative signs
      translationXSupplier = () -> -modifyJoystickAxis(OI.driver.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
      translationYSupplier = () -> -modifyJoystickAxis(OI.driver.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
      rotationSupplier = () -> modifyJoystickAxis(OI.driver.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND; //changed direction per ananya's request
      setSpeed(
         ChassisSpeeds.fromFieldRelativeSpeeds(
            translationXSupplier.getAsDouble(),
            translationYSupplier.getAsDouble(),
            rotationSupplier.getAsDouble(),
            getPoseRotation()
         )
      );
   }

   //responsible for moving the robot, called after a chassisSpeed is set
   public void drive() { //runs periodically
      updatePositionManager();

      //array of states filled with the speed and angle for each module (made from linear and angular motion for the whole robot) 
      SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
      //desaturatewheelspeeds checks and fixes if any module's wheel speed is above the max
      SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

      frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[0].angle.getRadians());
      frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[1].angle.getRadians());
      backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[2].angle.getRadians());
      backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[3].angle.getRadians());
      //System.out.println("robot position: "+ getPose());
      System.out.println("=====robot position   X: "+ getPoseX() + "   Y: " + getPoseY() + "   Rotation (Degrees): " + getPoseDegrees());

   }

   private static double joystickDeadband(double value, double deadband) {
      if (Math.abs(value) > deadband) {
         if (value > 0.0) {
            return (value - deadband) / (1.0 - deadband);
         } else {
            return (value + deadband) / (1.0 - deadband);
         }
      } else {
         return 0.0;
      }
   }

   //makes it so the joystick axis value is squared so the driving can ramp up faster
   private static double modifyJoystickAxis(double value) {
      // Deadband
      value = joystickDeadband(value, 0.05);

      // Square the axis
      value = Math.copySign(value * value, value);

      return value;
   }

    //AUTO AND FAILSAFE
   public void stopDrive() {
      System.out.println("=========================STOPPED DRIVE SOMETHING MIGHT HAVE GONE WRONG=========================");
      setSpeed(ChassisSpeeds.fromFieldRelativeSpeeds(0.0, 0.0, 0.0, getPoseRotation()));
      drive();
   }

    public double getPoseX(){
      return getPose().getX();
    }

    public double getPoseY(){
      return getPose().getY();
    }

    public double getPoseDegrees(){
      return getPose().getRotation().getDegrees();
    }
 
    public Pose2d getPose(){
      return positionManager.getEstimatedPosition();
    }

    public SwerveModulePosition[] getModulePositionArray(){
      return new SwerveModulePosition[] {
         new SwerveModulePosition(frontLeftModule.getPositionRotation()/Constants.SWERVE_TICKS_PER_METER, new Rotation2d(frontLeftModule.getSteerAngle())), //from steer motor
         new SwerveModulePosition(frontRightModule.getPositionRotation()/Constants.SWERVE_TICKS_PER_METER, new Rotation2d(frontRightModule.getSteerAngle())), 
         new SwerveModulePosition(backLeftModule.getPositionRotation()/Constants.SWERVE_TICKS_PER_METER, new Rotation2d(backLeftModule.getSteerAngle())),
         new SwerveModulePosition(backRightModule.getPositionRotation()/Constants.SWERVE_TICKS_PER_METER, new Rotation2d(backRightModule.getSteerAngle()))
      };
    }

    public void resetPositionManager(Pose2d currentPose){
      positionManager.resetPosition(getGyroscopeRotation(), getModulePositionArray(), currentPose);
   }

   public SwerveDrivePoseEstimator getPositionManager(){
      return positionManager;
   }

   public double getStartingGyroRotation(){
      return startingGyroRotation; 
   }
}

