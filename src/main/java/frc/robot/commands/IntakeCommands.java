// FROM 254 FRC-2024-Public IntakeFactory

package frc.robot.commands;

import java.util.function.DoubleSupplier; // Represents a supplier of double-valued results.

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Robot;
import frc.robot.subsystems.Mechanisms;

public class IntakeCommands {
    public static Command runIntake(Robot robot, DoubleSupplier intakeSpeed) {
        return Robot.m_intakeSubsystem.dutyCycleCommand(intakeSpeed).withName("Duty Cycle Intake");
    }
}
