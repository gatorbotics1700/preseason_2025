// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.ServoControlCommand;
import frc.robot.commands.TurretControlCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.TurretSubsystem;


public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Joystick m_joystick = new Joystick(0);
  public static final CommandXboxController m_controller_two = new CommandXboxController(1);
  private static final TurretSubsystem m_turretsub = new TurretSubsystem();
  private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
  private static final ServoSubsystem m_servosub = new ServoSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    // m_drive.setDefaultCommand(new DrivewithJoysticks(m_drive, m_joystick.getRawAxis(0), m_joystick.getRawAxis(1)));
    
    // SendableRegistry.add(m_drive, "drive");
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // JoystickButton slower = new JoystickgiButton(m_joystick, 1);
    // JoystickButton faster = new JoystickButton(m_joystick, 2);

    Trigger aButton = m_controller_two.a();
    Trigger bButton = m_controller_two.b();

    aButton.onTrue(new TurretControlCommand(m_turretsub, Constants.TURRET_SPEED, 90.0));
    bButton.onTrue(new TurretControlCommand(m_turretsub, Constants.TURRET_SPEED, 0.0));
    // slower.whileHeld(new SpinSlower(m_spinner));
    // faster.whileHeld(new SpinFaster(m_spinner));
  }

  public Command getLimelightCommand() {
    return new LimelightControlCommand(m_limelightsub, m_turretsub);
  }

  public Command getServoCommand() {
    return new ServoControlCommand(m_servosub, 50);
  }

  public Command getTurretCommand() {
    return new TurretControlCommand(m_turretsub, m_limelightsub, 0.05);
  }
}