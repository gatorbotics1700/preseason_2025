// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import frc.com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import frc.com.swervedrivespecialties.swervelib.SwerveModule;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.*;

import frc.robot.Constants;
import frc.robot.OI;

public class DrivetrainSubsystem {
   private static final double SWERVE_GEAR_RATIO = 6.75; //TODO: double check the gear ratio
   private static final double SWERVE_WHEEL_DIAMETER = 4.0; //inches //TODO: check to make sure it is actually 4 inches
   private static final double SWERVE_TICKS_PER_INCH = Constants.TICKS_PER_REV*SWERVE_GEAR_RATIO/(SWERVE_WHEEL_DIAMETER*Math.PI); //talonfx drive encoder
   private static final double SWERVE_TICKS_PER_METER = SWERVE_TICKS_PER_INCH/Constants.METERS_PER_INCH;

  /*
   * The maximum voltage that will be delivered to the motors.
   * This can be reduced to cap the robot's maximum speed. Typically, this is useful during initial testing of the robot.
   */
  private static final double MAX_VOLTAGE = 12.0; //TODO: double check this; previously 16.3

  /* The formula for calculating the theoretical maximum velocity is:
   * <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> * pi
   * By default this value is setup for a Mk3 standard module using Falcon500s to drive.
   * An example of this constant for a Mk4 L2 module with NEOs to drive is:
   * 5880.0 / 60.0 / SdsModuleConfigurations.MK4_L2.getDriveReduction() * SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI
   * The maximum velocity of the robot in meters per second.
   * This is a measure of how fast the robot should be able to drive in a straight line.
   */
  private static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 * // <-- these are copied from SDS library //TODO: check motor free speed RPM
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

  // By default we use a Pigeon for our gyroscope. But if you use another gyroscope, like a NavX, you can change this.
  // The important thing about how you configure your gyroscope is that rotating the robot counter-clockwise should
  // cause the angle reading to increase until it wraps back over to zero.
  private PigeonIMU pigeon;

  // These are our modules. We initialize them in the constructor.
  private SwerveModule frontLeftModule;
  private SwerveModule frontRightModule;
  private SwerveModule backLeftModule;
  private SwerveModule backRightModule;

  private SwerveDrivePoseEstimator positionManager;
  private ShuffleboardTab tab;

  //ChassisSpeeds takes in y velocity, x velocity, speed of rotation
  private ChassisSpeeds chassisSpeeds; //sets expected chassis speed to be called the next time drive is run

  public DrivetrainSubsystem() {
        pigeon = new PigeonIMU(Constants.DRIVETRAIN_PIGEON_ID);
        tab = Shuffleboard.getTab("Drivetrain");

        // We will use mk4 modules with Falcon 500s with the L2 configuration. 
        frontLeftModule = Mk4SwerveModuleHelper.createFalcon500(
                // This parameter is optional, but will allow you to see the current state of the module on the dashboard.
                tab.getLayout("Front Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(0, 0), //TODO: see if we can put this in constants
                // This can be any level from L1-L4 depending on the gear configuration (the levels allow different amounts of speed and torque)
                Mk4SwerveModuleHelper.GearRatio.L2,
                // This is the ID of the drive motor
                FRONT_LEFT_MODULE_DRIVE_MOTOR,
                // This is the ID of the steer motor
                FRONT_LEFT_MODULE_STEER_MOTOR,
                // This is the ID of the steer encoder
                FRONT_LEFT_MODULE_STEER_ENCODER,
                // This is how much the steer encoder is offset from true zero (In our case, zero is facing straight forward)
                FRONT_LEFT_MODULE_STEER_OFFSET
        );
        
        // We will do the same for the other modules
        //TODO: check if we want to construct on every enable
        frontRightModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Front Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(2, 0), //TODO: see if we can put this in constants
                Mk4SwerveModuleHelper.GearRatio.L2,
                FRONT_RIGHT_MODULE_DRIVE_MOTOR,
                FRONT_RIGHT_MODULE_STEER_MOTOR,
                FRONT_RIGHT_MODULE_STEER_ENCODER,
                FRONT_RIGHT_MODULE_STEER_OFFSET
        );
        
        backLeftModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Back Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(4, 0), //TODO: see if we can put this in constants
                Mk4SwerveModuleHelper.GearRatio.L2,
                BACK_LEFT_MODULE_DRIVE_MOTOR,
                BACK_LEFT_MODULE_STEER_MOTOR,
                BACK_LEFT_MODULE_STEER_ENCODER,
                BACK_LEFT_MODULE_STEER_OFFSET
        );
        
        backRightModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Back Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(6, 0), //TODO: see if we can put this in constants
                Mk4SwerveModuleHelper.GearRatio.L2,
                BACK_RIGHT_MODULE_DRIVE_MOTOR,
                BACK_RIGHT_MODULE_STEER_MOTOR,
                BACK_RIGHT_MODULE_STEER_ENCODER,
                BACK_RIGHT_MODULE_STEER_OFFSET
        );

        init();
  }

  public void init(){ //TODO: is the naming this init an issue? check with Kim
        System.out.println("Initializing drivetrain subsystem vars");
        kinematics = new SwerveDriveKinematics( //Positive x values represent moving toward the front of the robot whereas positive y values represent moving toward the left of the robot.
                // Setting up location of modules relative to the center of the robot
                // Front left
                new Translation2d(DRIVETRAIN_WHEELBASE_METERS / 2.0, DRIVETRAIN_TRACKWIDTH_METERS / 2.0),
                //translation2d refers to the robot's x and y position in the larger field coordinate system
                // Front right
                new Translation2d(DRIVETRAIN_WHEELBASE_METERS / 2.0, -DRIVETRAIN_TRACKWIDTH_METERS / 2.0), 
                // Back left
                new Translation2d(-DRIVETRAIN_WHEELBASE_METERS / 2.0, DRIVETRAIN_TRACKWIDTH_METERS / 2.0),
                // Back right
                new Translation2d(-DRIVETRAIN_WHEELBASE_METERS / 2.0, -DRIVETRAIN_TRACKWIDTH_METERS / 2.0)
        );

        chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);
    
        positionManager = new SwerveDrivePoseEstimator(
                kinematics, 
                getGyroscopeRotation(), 
                new SwerveModulePosition[] {
                        frontLeftModule.getSwerveModulePosition(), 
                        frontRightModule.getSwerveModulePosition(), 
                        backLeftModule.getSwerveModulePosition(), 
                        backRightModule.getSwerveModulePosition()
                }, 
                new Pose2d(0, 0, new Rotation2d(Math.toRadians(180)))); //assumes 180 degrees rotation is facing driver station
  }
  
  //from pigeon used for updating our odometry
  //in an unknown, arbitrary frame
  //"do not use unless you know what you are doing" - patricia
  private Rotation2d getGyroscopeRotation() {
        return new Rotation2d(Math.toRadians(pigeon.getYaw())); //getYaw() returns degrees
  }
  //from odometry used for field-relative rotation
  public Rotation2d getPoseRotation() {
        return getPose().getRotation(); 
  }

  //log the current position within the positionManager so that it knows what our encoders say about our position
  //the positionManager will then update its pose estimate, accounting for any drift
  public void updatePositionManager(){
        SwerveModulePosition[] positionArray =  new SwerveModulePosition[] {
                new SwerveModulePosition(frontLeftModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(frontLeftModule.getSteerAngle())),
                new SwerveModulePosition(frontRightModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(frontRightModule.getSteerAngle())), 
                new SwerveModulePosition(backLeftModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(backLeftModule.getSteerAngle())),
                new SwerveModulePosition(backRightModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(backRightModule.getSteerAngle()))};
        positionManager.update(getGyroscopeRotation(), positionArray);
}
       
//the next iteration of drive will use this speed  
public void setSpeed(ChassisSpeeds chassisSpeed) { 
        chassisSpeeds = chassisSpeed;
  }
  
  /*this method is responsible for getting values from the xbox controller and setting the speed that drive will call
   this will be called right before drive() in teleopPeriodic in Robot.java */
  public void driveTeleop(){
        DoubleSupplier m_translationXSupplier;
        DoubleSupplier m_translationYSupplier;
        DoubleSupplier m_rotationSupplier;
        //TODO: check negative signs
        m_translationXSupplier = () -> -modifyJoystickAxis(OI.m_controller.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        m_translationYSupplier = () -> -modifyJoystickAxis(OI.m_controller.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND;
        m_rotationSupplier = () -> -modifyJoystickAxis(OI.m_controller.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;
        setSpeed(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        m_translationXSupplier.getAsDouble(),
                        m_translationYSupplier.getAsDouble(),
                        m_rotationSupplier.getAsDouble(),
                        getPoseRotation()
                )
        );
  }

  //responsible for moving the robot, called after a chassisSpeed is set
  public void drive() { //runs periodically
        //TODO: check getSteerAngle() is correct and that we shouldn't be getting from cancoder
        SwerveModulePosition[] modulePositions =  {
                new SwerveModulePosition(frontLeftModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(frontLeftModule.getSteerAngle())), //from steer motor
                new SwerveModulePosition(frontRightModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(frontRightModule.getSteerAngle())), 
                new SwerveModulePosition(backLeftModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(backLeftModule.getSteerAngle())),
                new SwerveModulePosition(backRightModule.getPosition()/SWERVE_TICKS_PER_METER, new Rotation2d(backRightModule.getSteerAngle()))
        };
        positionManager.update(getGyroscopeRotation(), modulePositions); 

        //array of states filled with the speed and angle for each module (made from linear and angular motion for the whole robot) 
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
        //desaturatewheelspeeds checks and fixes if any module's wheel speed is above the max
        SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

        frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[0].angle.getRadians());
        frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[1].angle.getRadians());
        backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[2].angle.getRadians());
        backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[3].angle.getRadians());
}

  private static double deadband(double value, double deadband) {
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
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        return value;
    }

    //AUTO AND FAILSAFE
    public void stopDrive() {
        System.out.println("STOPPED DRIVE SOMETHING MIGHT HAVE GONE WRONG");
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
 
    private Pose2d getPose(){
        return positionManager.getEstimatedPosition();
    }
}
