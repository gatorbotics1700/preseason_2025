// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Mechanisms;
import frc.robot.subsystems.Mechanisms.MechanismStates;
import frc.robot.autonomous.AutonomousBase;
import frc.robot.autonomous.Paths;


/*
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {

    private final SendableChooser<Boolean> inverted = new SendableChooser<>();
    private final SendableChooser<Boolean> allianceChooser = new SendableChooser<>();
    private final SendableChooser<Paths.AUTO_OPTIONS> auto_chooser = new SendableChooser<>();

    
    public static final Mechanisms m_mechanismSubsystem = new Mechanisms();
    public static final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem(); //if anything breaks in the future it might be this
    private AutonomousBase m_auto; 
    public static Buttons m_buttons = new Buttons();
    public static Boolean isBlueAlliance = true;

  
  /**
  * This function is run when the robot is turned on and should be used for any
  * initialization code.
  */
  @Override
  public void robotInit() { //creates options for different autopaths, names are placeholders    
    System.out.println("#I'm Awake");

    auto_chooser.setDefaultOption("PD testPath", Paths.AUTO_OPTIONS.PD_TESTPATH);
    //red paths
    auto_chooser.addOption("noGo-R!", Paths.AUTO_OPTIONS.R_NO_GO);
    auto_chooser.addOption("R-3 Piece 1", Paths.AUTO_OPTIONS.R_THREE_PIECE_1);
    auto_chooser.addOption("R-3 Piece 2", Paths.AUTO_OPTIONS.R_THREE_PIECE_2);
    auto_chooser.addOption("R-3 Piece AMP", Paths.AUTO_OPTIONS.R_THREE_PAMP);
    auto_chooser.addOption("R-4 Piece 3", Paths.AUTO_OPTIONS.R_FOUR_PIECE_3);
    auto_chooser.addOption("R-4 Piece 1", Paths.AUTO_OPTIONS.R_FOUR_PIECE_1);
    auto_chooser.addOption("R-5 Piece 2", Paths.AUTO_OPTIONS.R_FIVE_PIECE_2);
    auto_chooser.addOption("R-Anaika's Dream 1", Paths.AUTO_OPTIONS.R_ANAIKAS_DREAM_1);
    auto_chooser.addOption("R-Bread", Paths.AUTO_OPTIONS.R_BREAD);
    auto_chooser.addOption("R-Fallback 1", Paths.AUTO_OPTIONS.R_FALLBACK_1);
    auto_chooser.addOption("R-Fallback 2", Paths.AUTO_OPTIONS.R_FALLBACK_2);
    auto_chooser.addOption("R-Fallback 3", Paths.AUTO_OPTIONS.R_FALLBACK_3);
    //blue paths
    auto_chooser.addOption("noGo-B!", Paths.AUTO_OPTIONS.B_NO_GO);
    auto_chooser.addOption("B-3 Piece 1", Paths.AUTO_OPTIONS.B_THREE_PIECE_1);
    auto_chooser.addOption("B-3 Piece 2", Paths.AUTO_OPTIONS.B_THREE_PIECE_2);
    auto_chooser.addOption("B-3 Piece AMP", Paths.AUTO_OPTIONS.B_THREE_PAMP);
    auto_chooser.addOption("B-4 Piece 3", Paths.AUTO_OPTIONS.B_FOUR_PIECE_3);
    auto_chooser.addOption("B-4 Piece 2", Paths.AUTO_OPTIONS.B_FOUR_PIECE_2);
    auto_chooser.addOption("B-5 Piece 1", Paths.AUTO_OPTIONS.B_FIVE_PIECE_1);
    auto_chooser.addOption("B-Anaika's Dream 2", Paths.AUTO_OPTIONS.B_ANAIKAS_DREAM_2);
    auto_chooser.addOption("B-Bread", Paths.AUTO_OPTIONS.B_BREAD);
    auto_chooser.addOption("B-Fallback 1", Paths.AUTO_OPTIONS.B_FALLBACK_1);
    auto_chooser.addOption("B-Fallback 2", Paths.AUTO_OPTIONS.B_FALLBACK_2);
    auto_chooser.addOption("B-Fallback 3", Paths.AUTO_OPTIONS.B_FALLBACK_3);

    SmartDashboard.putData("Auto Choices", auto_chooser); 
    
    inverted.setDefaultOption("true", true);
    inverted.addOption("false", false);
  }

    /*
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */

    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("x odometry",m_drivetrainSubsystem.getPoseX()/Constants.METERS_PER_INCH);
        SmartDashboard.putNumber("y odometry",m_drivetrainSubsystem.getPoseY()/Constants.METERS_PER_INCH);
        SmartDashboard.putNumber("angle odometry",m_drivetrainSubsystem.getPoseDegrees()%360);
        SmartDashboard.putBoolean("detectNote", m_mechanismSubsystem.getSensorSubsystem().detectNote());
    }

    /*
     * This autonomous (along with the chooser code above) shows how to select between different
     * autonomous modes using the dashboard. The sendable chooser code works with the Java
     * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
     * uncomment the getString line to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the switch structure
     * below with additional strings. If using the SendableChooser make sure to add them to the
     * chooser code above as well.
     */
    @Override
  public void autonomousInit() {
   // m_drivetrainSubsystem.autoInitCalled = false;
    Paths.AUTO_OPTIONS selectedAuto = auto_chooser.getSelected(); 
    m_auto = Paths.constructAuto(selectedAuto); 
    m_mechanismSubsystem.init();
    
    //System.out.println("starting x: " + m_auto.getStartingPoseX() + "starting y: " + m_auto.getStartingPoseY() + "starting rotation: " + m_auto.getStartingPoseRotation());
    // m_drivetrainSubsystem.init(m_auto.getStartingPoseX(), m_auto.getStartingPoseY(), m_auto.getStartingPoseRotation());

    // m_drivetrainSubsystem.autoInitCalled = true;
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    m_auto.periodic();
    m_mechanismSubsystem.periodic();
    m_drivetrainSubsystem.drive();
    //System.out.println("current pose " + m_drivetrainSubsystem.getPose());
  }

    /* This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() { //BEFORE TESTING: MAKE SURE YOU HAVE EITHER DEPLOYED OR ADDED DRIVETRAIN INIT
        isBlueAlliance = allianceChooser.getSelected();
        m_drivetrainSubsystem.onEnable();
        m_mechanismSubsystem.setState(MechanismStates.OFF);
        m_mechanismSubsystem.init();
    }

    /* This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() { 
        m_buttons.buttonsPeriodic();
        m_drivetrainSubsystem.driveTeleop();
        m_drivetrainSubsystem.drive();   
        m_mechanismSubsystem.periodic();
    }

    /* This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

    /* This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /* This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
        //m_sensorSubsystem.init();

        m_mechanismSubsystem.init();
        //m_mechanismSubsystem.setState(MechanismStates.INTAKING);
    }

    /* This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
        //OFFSETS
        //m_drivetrainSubsystem.driveTeleop();
        //m_drivetrainSubsystem.setSpeed(ChassisSpeeds.fromFieldRelativeSpeeds(0.3, 0, 0, m_drivetrainSubsystem.getPoseRotation()));
        //m_drivetrainSubsystem.drive();
        m_buttons.buttonsPeriodic();
        
        //m_sensorSubsystem.periodic();
        //System.out.println("COLOR IS: " + m_sensorSubsystem.colorSensor.getColor());
        m_mechanismSubsystem.periodic();
        //m_mechanismSubsystem.setState(MechanismStates.INTAKING);
        //m_shooterSubsystem.lowMotor.set(ControlMode.Position, 0.6);
        //m_intakingSubsystem.intakeMotor.set(ControlMode.PercentOutput, -0.6);
        //m_intakingSubsystem.transitionMotor.set(ControlMode.PercentOutput, -0.6);

    }
    /* This function is called once when the robot is first started up. */
    @Override
    public void simulationInit() {}

    /* This function is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {}
}