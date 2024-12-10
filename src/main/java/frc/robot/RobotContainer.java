// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.ServoControlCommand;
import frc.robot.commands.TurretControlCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.networktables.NetworkTableInstance;


public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Joystick m_joystick = new Joystick(0);
  private static final TurretSubsystem m_turretsub = new TurretSubsystem();
  private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
  private static final ServoSubsystem m_servosub = new ServoSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    // m_drive.setDefaultCommand(new DrivewithJoysticks(m_drive, m_joystick.getRawAxis(0), m_joystick.getRawAxis(1)));
    
    // SendableRegistry.add(m_drive, "drive");
    // configureButtonBindings();
  }

  private void configureButtonBindings() {
    JoystickButton slower = new JoystickButton(m_joystick, 1);
    JoystickButton faster = new JoystickButton(m_joystick, 2);


    // slower.whileHeld(new SpinSlower(m_spinner));
    // faster.whileHeld(new SpinFaster(m_spinner));

  }

  public Command getLimelightCommand() {
    return new LimelightControlCommand(m_limelightsub, m_turretsub, 1);
  }

  public Command getServoCommand() {
    return new ServoControlCommand(m_servosub, 50);
  }

  public Command getTurretCommand() {
    return new TurretControlCommand(m_turretsub, m_limelightsub, 0.05);
  }

}