package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
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

    private ChassisSpeeds chassisSpeeds;
    
    private ShuffleboardTab shuffleboardTab;

    private boolean slowDrive;

    public DrivetrainSubsystem() {
        slowDrive = false;

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
                new SwerveModulePosition[]{ frontLeftModule.getPosition(), frontRightModule.getPosition(), backLeftModule.getPosition(), backRightModule.getPosition() },
                new Pose2d(0, 0, new Rotation2d(Math.toRadians(0)))
        );

        shuffleboardTab.addNumber("Gyroscope Angle", () -> getRotation().getDegrees());
        shuffleboardTab.addNumber("Pose X", () -> odometry.getEstimatedPosition().getX());
        shuffleboardTab.addNumber("Pose Y", () -> odometry.getEstimatedPosition().getY());
    }

    public void setSlowDrive(){
        slowDrive = !slowDrive;
        System.out.println("use of slow drive to not slow drive");
    }

    public boolean getSlowDrive(){
        return slowDrive;
    }

    public void zeroGyroscope() {
        odometry.resetPosition( // this line shouldn't work but it should - essentially we are only reseting angle instead of reseting position which is the whole point of reset position
                new Rotation2d(Math.toRadians(pigeon.getYaw().getValue())),
                new SwerveModulePosition[]{ frontLeftModule.getPosition(), frontRightModule.getPosition(), backLeftModule.getPosition(), backRightModule.getPosition() },
                new Pose2d(odometry.getEstimatedPosition().getX(), odometry.getEstimatedPosition().getY(), Rotation2d.fromDegrees(0.0))
        );
        System.out.println("you pressed the right button yay you");
    }

    public Rotation2d getRotation() {
        return odometry.getEstimatedPosition().getRotation();
    }

    public void drive(ChassisSpeeds chassisSpeeds) {
        this.chassisSpeeds = chassisSpeeds;
    }

    @Override
    public void periodic() {
        odometry.update(
                new Rotation2d(Math.toRadians(pigeon.getYaw().getValue())),
                new SwerveModulePosition[]{ frontLeftModule.getPosition(), frontRightModule.getPosition(), backLeftModule.getPosition(), backRightModule.getPosition() }
        );

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);

        frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[0].angle.getRadians());
        frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[1].angle.getRadians());
        backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[2].angle.getRadians());
        backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[3].angle.getRadians());
    }
}
