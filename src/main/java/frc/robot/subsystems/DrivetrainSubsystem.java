package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
//import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
// import com.pathplanner.lib.config.PIDConstants;
import edu.wpi.first.wpilibj.DriverStation;


import frc.com.swervedrivespecialties.swervelib.MechanicalConfiguration;
import frc.com.swervedrivespecialties.swervelib.MkSwerveModuleBuilder;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import frc.com.swervedrivespecialties.swervelib.SwerveModule;
import frc.robot.Constants;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    private static final double MAX_VOLTAGE = 12.0;
    public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380 / 60 * SdsModuleConfigurations.MK4_L2.getDriveReduction() * SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI;
    public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
            Math.hypot(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0);

    private final SwerveModule frontLeftModule;
    private final SwerveModule frontRightModule;
    private final SwerveModule backLeftModule;
    private final SwerveModule backRightModule;

    private final Pigeon2 pigeon;

    private final SwerveDriveKinematics kinematics;
    
    private final SwerveDrivePoseEstimator odometry;


    private SwerveModuleState[] states; 

    private ChassisSpeeds chassisSpeeds;
    
    private ShuffleboardTab shuffleboardTab;

    public DrivetrainSubsystem() {
        shuffleboardTab = Shuffleboard.getTab("Drivetrain");

        pigeon = new Pigeon2(Constants.DRIVETRAIN_PIGEON_ID);

        kinematics = new SwerveDriveKinematics(
            new Translation2d(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
            new Translation2d(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
            new Translation2d(-Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
            new Translation2d(-Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0)
    );

        chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

        frontLeftModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Front Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(0, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.FRONT_LEFT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(Constants.FRONT_LEFT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.FRONT_LEFT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.FRONT_LEFT_MODULE_STEER_OFFSET)
                .build();

        frontRightModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Front Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(2, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.FRONT_RIGHT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(Constants.FRONT_RIGHT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.FRONT_RIGHT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.FRONT_RIGHT_MODULE_STEER_OFFSET)
                .build();

        backLeftModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Back Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(4, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.BACK_LEFT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(Constants.BACK_LEFT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.BACK_LEFT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.BACK_LEFT_MODULE_STEER_OFFSET)
                .build();

        backRightModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Back Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(6, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.BACK_RIGHT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(Constants.BACK_RIGHT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.BACK_RIGHT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.BACK_RIGHT_MODULE_STEER_OFFSET)
                .build();

        odometry = new SwerveDrivePoseEstimator(
                kinematics,
                new Rotation2d(Math.toRadians(pigeon.getYaw().getValue())),
                // Rotation2d.fromDegrees(pigeon.getAngle()),
                new SwerveModulePosition[]{ frontLeftModule.getPosition(), frontRightModule.getPosition(), backLeftModule.getPosition(), backRightModule.getPosition() },
                new Pose2d(0, 0, new Rotation2d(Math.toRadians(90))) //TODO: fix, bandaid setting it to not be 0 for further testing 12/2/24
        );
        states=kinematics.toSwerveModuleStates(chassisSpeeds);

        AutoBuilder.configureHolonomic(
            this::getPose,
            this::resetPose,
            this::getRobotRelativeSpeeds,
            this::driveRobotRelative,
            new HolonomicPathFollowerConfig(
                new PIDConstants(10,0,0),
                new PIDConstants(10,0,0),
                MAX_VELOCITY_METERS_PER_SECOND,
                0.449072,
                new ReplanningConfig()
            ),
            () -> {

                var alliance = DriverStation.getAlliance();
                if(alliance.isPresent()){
                    return alliance.get() == DriverStation.Alliance.Red;
                }
                    return false;
            
            }, 
            this
        );

    /*  RobotConfig config;
      try{
         config = RobotConfig.fromGUISettings();
      } catch (Exception e) {
         e.printStackTrace();
      }

      AutoBuilder.configure(
         this::getPose,
         this::resetPose,
         this::getRobotRelativeSpeeds,
         (speeds, feedforwards) -> driveRobotRelative(speeds),
         new PPHolonomicDriveController(new PIDConstants(5, 0, 0), new PIDConstants(5, 0, 0), MAX_VELOCITY_METERS_PER_SECOND, MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND),
         config, 
         () -> {
            var alliance = DriverStation.getAlliance();
            if(alliance.isPresent()){
               return alliance.get() == DriverStation.Alliance.Red;
            }
            return false;

         },
         this
      );
*/


        shuffleboardTab.addNumber("Gyroscope Angle", () -> getRotation().getDegrees());
        shuffleboardTab.addNumber("Pose X", () -> odometry.getEstimatedPosition().getX());
        shuffleboardTab.addNumber("Pose Y", () -> odometry.getEstimatedPosition().getY());
    }

    public void zeroGyroscope() {
        odometry.resetPosition( //shouldn't be using this
                new Rotation2d(Math.toRadians(pigeon.getYaw().getValue())),
                new SwerveModulePosition[]{ frontLeftModule.getPosition(), frontRightModule.getPosition(), backLeftModule.getPosition(), backRightModule.getPosition() },
                // new Pose2d(0, 0, new Rotation2d(Math.toRadians(0)))
                new Pose2d(odometry.getEstimatedPosition().getX(), odometry.getEstimatedPosition().getY(), Rotation2d.fromDegrees(0.0))
        );
        System.out.println("you pressed the right button yay you");
    }

    public Pose2d getPose() {
        return odometry.getEstimatedPosition();
    }

    public void resetPose(Pose2d pose) {
        odometry.resetPosition(new Rotation2d(Math.toRadians(pigeon.getYaw().getValue())), getModulePositionArray(), pose); 
    }

    public SwerveModulePosition[] getModulePositionArray(){
        return new SwerveModulePosition[]{ frontLeftModule.getPosition(), frontRightModule.getPosition(), backLeftModule.getPosition(), backRightModule.getPosition()};
    }
    
    public Rotation2d getRotation() {
        return odometry.getEstimatedPosition().getRotation();
    }

    public ChassisSpeeds getRobotRelativeSpeeds() {
        return kinematics.toChassisSpeeds(getModuleStates());
    }

    public SwerveModuleState[] getModuleStates(){
        return states;
    }



    public void setStates(SwerveModuleState[] targetStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(targetStates, MAX_VELOCITY_METERS_PER_SECOND);

        states = targetStates;

        // Calculate voltages (using a higher minimum voltage to ensure movement)
        double fl_voltage = targetStates[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE;
        double fr_voltage = targetStates[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE;
        double bl_voltage = targetStates[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE;
        double br_voltage = targetStates[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE;

        System.out.println("Setting voltages - FL: " + fl_voltage + 
                          " FR: " + fr_voltage +
                          " BL: " + bl_voltage + 
                          " BR: " + br_voltage);

        System.out.println("pose: " + getPose());

        // Set modules with calculated voltages
        frontLeftModule.set(fl_voltage, targetStates[0].angle.getRadians());
        frontRightModule.set(fr_voltage, targetStates[1].angle.getRadians());
        backLeftModule.set(bl_voltage, targetStates[2].angle.getRadians());
        backRightModule.set(br_voltage, targetStates[3].angle.getRadians());
    }

    public void drive(ChassisSpeeds chassisSpeeds) {
        this.chassisSpeeds = chassisSpeeds;
        
        // Convert chassis speeds to module states and apply them
        ChassisSpeeds targetSpeeds = ChassisSpeeds.discretize(chassisSpeeds, 0.02);
        SwerveModuleState[] targetStates = kinematics.toSwerveModuleStates(targetSpeeds);
        setStates(targetStates);
    }

    public void driveRobotRelative(ChassisSpeeds robotRelativeSpeeds) {
        System.out.println("Drive command received - vx: " + robotRelativeSpeeds.vxMetersPerSecond +
                          " vy: " + robotRelativeSpeeds.vyMetersPerSecond +
                          " omega: " + robotRelativeSpeeds.omegaRadiansPerSecond);
                          
        ChassisSpeeds targetSpeeds = ChassisSpeeds.discretize(robotRelativeSpeeds, 0.02);
        SwerveModuleState[] targetStates = kinematics.toSwerveModuleStates(targetSpeeds);
        
        // Print target states before applying them
        System.out.println("Target states:");
        for (int i = 0; i < targetStates.length; i++) {
            System.out.println("Module " + i + ": Speed=" + targetStates[i].speedMetersPerSecond +
                             " Angle=" + targetStates[i].angle.getDegrees());
        }
        
        setStates(targetStates);
    }

    @Override
    public void periodic() {
        odometry.update(
            new Rotation2d(Math.toRadians(pigeon.getYaw().getValue())),
            new SwerveModulePosition[]{ 
                frontLeftModule.getPosition(), 
                frontRightModule.getPosition(), 
                backLeftModule.getPosition(), 
                backRightModule.getPosition() 
            }
        );
    }
}
