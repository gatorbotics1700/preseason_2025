package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.ModuleConfig;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import edu.wpi.first.wpilibj.DriverStation;
import frc.com.swervedrivespecialties.swervelib.MkSwerveModuleBuilder;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import frc.com.swervedrivespecialties.swervelib.SwerveModule;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    private static final double MAX_VOLTAGE = 12.0;
    public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380 / 60
            * SdsModuleConfigurations.MK4_L2.getDriveReduction() * SdsModuleConfigurations.MK4_L2.getWheelDiameter()
            * Math.PI;
    public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
            Math.hypot(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0);

    private final SwerveModule frontLeftModule;
    private final SwerveModule frontRightModule;
    private final SwerveModule backLeftModule;
    private final SwerveModule backRightModule;

    private static Pigeon2 pigeon;

    private final SwerveDriveKinematics kinematics;

    private final SwerveDrivePoseEstimator odometry;

    private SwerveModuleState[] states;

    private ChassisSpeeds chassisSpeeds;

    private ShuffleboardTab shuffleboardTab;
    private final double TRANSLATION_kP = 0.7;
    private final double ROTATION_kP = 0.02;
    private final double TRANSLATION_MIN_SPEED = 0.15;
    private final double ROTATION_MIN_SPEED = 0.25;
    private final double DISTANCE_DEADBAND = 0.05;
    private final double ROTATION_DEADBAND = 2.0;
    private double robotRotation;

    private boolean slowDrive;
   

    public DrivetrainSubsystem() {
        slowDrive = false;
        shuffleboardTab = Shuffleboard.getTab("Drivetrain");

        pigeon = new Pigeon2(Constants.DRIVETRAIN_PIGEON_ID, Constants.CANIVORE_BUS_NAME);

        kinematics = new SwerveDriveKinematics(
                new Translation2d(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0,
                        Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
                new Translation2d(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0,
                        -Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
                new Translation2d(-Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0,
                        Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
                new Translation2d(-Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0,
                        -Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0));

        chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

        frontLeftModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Front Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(0, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.FRONT_LEFT_MODULE_DRIVE_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerMotor(Constants.FRONT_LEFT_MODULE_STEER_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerEncoderPort(Constants.FRONT_LEFT_MODULE_STEER_ENCODER, Constants.CANIVORE_BUS_NAME)
                .withSteerOffset(Constants.FRONT_LEFT_MODULE_STEER_OFFSET)
                .build();

        frontRightModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Front Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(2, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.FRONT_RIGHT_MODULE_DRIVE_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerMotor(Constants.FRONT_RIGHT_MODULE_STEER_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerEncoderPort(Constants.FRONT_RIGHT_MODULE_STEER_ENCODER, Constants.CANIVORE_BUS_NAME)
                .withSteerOffset(Constants.FRONT_RIGHT_MODULE_STEER_OFFSET)
                .build();

        backLeftModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Back Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(4, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.BACK_LEFT_MODULE_DRIVE_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerMotor(Constants.BACK_LEFT_MODULE_STEER_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerEncoderPort(Constants.BACK_LEFT_MODULE_STEER_ENCODER, Constants.CANIVORE_BUS_NAME)
                .withSteerOffset(Constants.BACK_LEFT_MODULE_STEER_OFFSET)
                .build();

        backRightModule = new MkSwerveModuleBuilder()
                .withLayout(shuffleboardTab.getLayout("Back Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(6, 0))
                .withGearRatio(Constants.MODULE_CONFIGURATION)
                .withDriveMotor(Constants.BACK_RIGHT_MODULE_DRIVE_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerMotor(Constants.BACK_RIGHT_MODULE_STEER_MOTOR, Constants.CANIVORE_BUS_NAME)
                .withSteerEncoderPort(Constants.BACK_RIGHT_MODULE_STEER_ENCODER, Constants.CANIVORE_BUS_NAME)
                .withSteerOffset(Constants.BACK_RIGHT_MODULE_STEER_OFFSET)
                .build();

        odometry = new SwerveDrivePoseEstimator(
                kinematics,
                new Rotation2d(Math.toRadians(pigeon.getYaw().getValueAsDouble())),
                new SwerveModulePosition[] { frontLeftModule.getPosition(), frontRightModule.getPosition(),
                        backLeftModule.getPosition(), backRightModule.getPosition() },
                new Pose2d(0, 0, new Rotation2d(Math.toRadians(0))));
        states = kinematics.toSwerveModuleStates(chassisSpeeds);
        DCMotor krakenMotor = DCMotor.getKrakenX60(1);// TODO: check if we change this number to 1 or keep

        ModuleConfig moduleConfig = new ModuleConfig(
                0.0508, // wheel radius in meters (example: 3 inches converted to meters)
                4.17, // max drive velocity in meters per second
                1.3, // coefficient of friction TODO: ask patricia
                krakenMotor, // DCMotor object
                55.0, // current limit in Amps
                1 // number of motors (e.g., 1 for swerve module)
        );
        RobotConfig config;
        try {
            config = RobotConfig.fromGUISettings();
        } catch (Exception e) {
            // Handle exception as needed???
            e.printStackTrace();
            config = new RobotConfig(16, 1.075, moduleConfig, 0.508);
        }

        AutoBuilder.configure(
                this::getPose,
                this::resetPose,
                this::getRobotRelativeSpeeds,
                this::driveRobotRelative,
                new PPHolonomicDriveController(
                        new PIDConstants(5, 0, 0.05),
                        new PIDConstants(10, 0, 0.01)
                // MAX_VELOCITY_METERS_PER_SECOND, -> WHAT IS THIS ???
                // 0.449072

                ),
                config, // The robot configuration
                // new RobotConfig(18, 1.45, moduleConfig, 0.449072),
                () -> {
                    //I believe this section mirrors our paths if we're on red, but we don't want to do that bc we have two sets of paths... commenting this out fixed our issue of hurtling across the field
                    // var alliance = DriverStation.getAlliance();
                    // if (alliance.isPresent()) {
                    //     return alliance.get() == DriverStation.Alliance.Red;
                    // }
                    return false;

                },
                this);

        shuffleboardTab.addNumber("Gyroscope Angle", () -> getRotation().getDegrees());
        shuffleboardTab.addNumber("Pose X", () -> odometry.getEstimatedPosition().getX());
        shuffleboardTab.addNumber("Pose Y", () -> odometry.getEstimatedPosition().getY());
    }

    public void setSlowDrive() {
        slowDrive = !slowDrive;
        // System.out.println("use of slow drive to not slow drive");
    }

    public boolean getSlowDrive() {
        return slowDrive;
    }

    public void zeroGyroscope() {
        odometry.resetPosition( // this line shouldn't work but it should - essentially we are only reseting
                                // angle instead of reseting position which is the whole point of reset position
                new Rotation2d(Math.toRadians(pigeon.getYaw().getValueAsDouble())),
                new SwerveModulePosition[] { frontLeftModule.getPosition(), frontRightModule.getPosition(),
                        backLeftModule.getPosition(), backRightModule.getPosition() },
                new Pose2d(odometry.getEstimatedPosition().getX(), odometry.getEstimatedPosition().getY(),
                        Rotation2d.fromDegrees(0.0)));
        // System.out.println("you pressed the right button yay you");
    }

    public Pose2d getPose() {
        return odometry.getEstimatedPosition();
    }

    public static double getRobotAngle() {
        return pigeon.getYaw().getValueAsDouble(); // in degrees?
    }

    public void resetPose(Pose2d pose) {
        odometry.resetPosition(new Rotation2d(Math.toRadians(pigeon.getYaw().getValueAsDouble())),
                getModulePositionArray(), pose);
    }

    public SwerveModulePosition[] getModulePositionArray() {
        return new SwerveModulePosition[] { frontLeftModule.getPosition(), frontRightModule.getPosition(),
                backLeftModule.getPosition(), backRightModule.getPosition() };
    }

    public Rotation2d getRotation() {
        return odometry.getEstimatedPosition().getRotation();
    }

    public double getRobotRotationDegrees(){
        return odometry.getEstimatedPosition().getRotation().getDegrees();
    }

    public ChassisSpeeds getRobotRelativeSpeeds() {
        return kinematics.toChassisSpeeds(getModuleStates());
    }

    public SwerveModuleState[] getModuleStates() {
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
        ChassisSpeeds targetSpeeds = ChassisSpeeds.discretize(robotRelativeSpeeds, 0.02);
        SwerveModuleState[] targetStates = kinematics.toSwerveModuleStates(targetSpeeds);
        setStates(targetStates);
    }

    @Override
    public void periodic() {
        updateShuffleboardVariables();
        odometry.update(
            new Rotation2d(Math.toRadians(pigeon.getYaw().getValueAsDouble())),
            new SwerveModulePosition[]{ 
                frontLeftModule.getPosition(), 
                frontRightModule.getPosition(), 
                backLeftModule.getPosition(), 
                backRightModule.getPosition() 
            }
        );
        
        // boolean doRejectUpdate = false;

        // LimelightHelpers.SetRobotOrientation("limelight", odometry.getEstimatedPosition().getRotation().getDegrees(), 0, 0, 0, 0, 0);
        // LimelightHelpers.PoseEstimate mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight");
        // if(Math.abs(pigeon.getAngularVelocityZWorld().getValueAsDouble()) > 720){
        //     doRejectUpdate = true;
        // }
        // if(mt2.tagCount == 0){
        //     doRejectUpdate = true;
        // }
        // if(!doRejectUpdate){
        //     odometry.setVisionMeasurementStdDevs(VecBuilder.fill(.7, .7, 9999999));
        //     odometry.addVisionMeasurement(mt2.pose, mt2.timestampSeconds);
        // }

        SmartDashboard.putNumber("Gyroscope Angle", getRotation().getDegrees());
        SmartDashboard.putNumber("Pose X", odometry.getEstimatedPosition().getX());
        SmartDashboard.putNumber("Pose Y", odometry.getEstimatedPosition().getY());
    }

    public void driveToPose(Pose2d desiredPose) {
        Pose2d currentPose = odometry.getEstimatedPosition();
        double xError = desiredPose.getX() - currentPose.getX();
        double yError = desiredPose.getY() - currentPose.getY();
        double rotationError = desiredPose.getRotation().getDegrees() - currentPose.getRotation().getDegrees();
        rotationError = MathUtil.inputModulus(rotationError, -180, 180); // sets the value between -180 and 180

        if (Math.abs(xError) < DISTANCE_DEADBAND) { // Stop if within deadband
            xError = 0.0;
            System.out.println("AT X DEADBAND");
        }

        if (Math.abs(yError) < DISTANCE_DEADBAND) {
            yError = 0.0;
            System.out.println("AT Y DEADBAND");
        }
        
        if (Math.abs(rotationError) < ROTATION_DEADBAND) {
            rotationError = 0.0;
            System.out.println("AT ROTATION DEADBAND");
        }

        boolean atDesiredPose = xError == 0.0 && yError == 0.0 && rotationError == 0.0;

        if (atDesiredPose) { // stop
            setStates(new SwerveModuleState[] {
                    new SwerveModuleState(0.0, new Rotation2d()),
                    new SwerveModuleState(0.0, new Rotation2d()),
                    new SwerveModuleState(0.0, new Rotation2d()),
                    new SwerveModuleState(0.0, new Rotation2d())
            });
            System.out.println("At desired pose, stopping.");
            return;
        }

        double xSpeed = Math.max(Math.abs(xError * TRANSLATION_kP), TRANSLATION_MIN_SPEED) * Math.signum(xError);
        double ySpeed = Math.max(Math.abs(yError * TRANSLATION_kP), TRANSLATION_MIN_SPEED) * Math.signum(yError);
        double rotationSpeed = Math.max(Math.abs(rotationError * ROTATION_kP), ROTATION_MIN_SPEED) * Math.signum(rotationError);

        drive(ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotationSpeed, currentPose.getRotation()));
    }

    //starts out pointing at apriltag, then turns to be parallel with the tag once it's close enough
    public void driveToPoseWithInitialAngle(Pose2d desiredPose, Rotation2d pointingToTagAngle) { 
        Pose2d currentPose = odometry.getEstimatedPosition();
        double xError = desiredPose.getX() - currentPose.getX();
        double yError = desiredPose.getY() - currentPose.getY();
        if (Math.abs(xError) > 0.2 && Math.abs(yError) > 0.2) {
            desiredPose = new Pose2d(desiredPose.getX(), desiredPose.getY(), pointingToTagAngle);
            System.out.println("POINTING TO ANGLE");
        }
        driveToPose(desiredPose);
    }

    private void updateShuffleboardVariables() {
        CANBus CANivore = new CANBus(Constants.CANIVORE_BUS_NAME);
        double busUtil = CANivore.getStatus().BusUtilization * 100;
        double transmitErrors = CANivore.getStatus().TEC;
        double receiveErrors = CANivore.getStatus().REC;
        SmartDashboard.putNumber("Bus Utilization", busUtil);
        SmartDashboard.putNumber("Transmit errors", transmitErrors);
        SmartDashboard.putNumber("Receive errors", receiveErrors);
        SmartDashboard.putNumber("Robot Angle", getRobotRotationDegrees());

    }

}