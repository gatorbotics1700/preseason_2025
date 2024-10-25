// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.autonomous.Paths;


/*
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
    private final SendableChooser<Boolean> inverted = new SendableChooser<>();
    public static final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
    public static Buttons m_buttons = new Buttons();

  
    /**
    * This function is run when the robot is turned on and should be used for any
    * initialization code.
    */
    @Override
    public void robotInit() { //creates options for different autopaths, names are placeholders    
        System.out.println("#I'm Awake");
        
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
        
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    /* This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() { //BEFORE TESTING: MAKE SURE YOU HAVE EITHER DEPLOYED OR ADDED DRIVETRAIN INIT
        m_turretSubsystem.init();

    }

    /* This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() { 
        m_turretSubsystem.periodic();
        System.out.println(m_turretSubsystem.turretAngle());
        // m_turretSubsystem.turnToAngle(361);
        // m_turretSubsystem.turnToAngle(1056);
        m_turretSubsystem.setDirection();
        m_turretSubsystem.turnToAngle(60);
      //  System.out.println("AT FIRST ANGLE");
        // m_turretSubsystem.setDirection();
        // m_turretSubsystem.turnToAngle(19);
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
    }

    /* This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }
    /* This function is called once when the robot is first started up. */
    @Override
    public void simulationInit() {}

    /* This function is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {}
}