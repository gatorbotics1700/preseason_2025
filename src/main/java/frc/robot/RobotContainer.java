// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.ServoControlCommand;
import frc.robot.commands.TurretControlCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class RobotContainer {
    public static final CommandXboxController m_controller_two = new CommandXboxController(1);
    private static final TurretSubsystem m_turretsub = new TurretSubsystem();
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
    private static final ServoSubsystem m_servosub = new ServoSubsystem();

    private int currentTargetID = 1; // Initialize with the first target ID

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        Trigger aButton = m_controller_two.a();
        Trigger bButton = m_controller_two.b();

        aButton.onTrue(() -> {
            currentTargetID = 9; // Set target ID to 9 when A is pressed
            System.out.println("Current Target ID: " + currentTargetID);
        });

        bButton.onTrue(() -> {
            currentTargetID = 8; // Reset target ID to 0 when B is pressed
            System.out.println("Current Target ID: " + currentTargetID);
        });
    }

    public Command getLimelightCommand() {
        return new LimelightControlCommand(m_limelightsub, m_turretsub, 9, 1); // Pass the specific target ID and pipeline ID
    }

    public Command getServoCommand() {
        return new ServoControlCommand(m_servosub, 50);
    }

    public Command getTurretCommand() {
        return new TurretControlCommand(m_turretsub, m_limelightsub, 0.05);
    }
}