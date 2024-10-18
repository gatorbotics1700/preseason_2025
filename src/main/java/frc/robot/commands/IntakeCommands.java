// FROM 254 FRC-2024-Public IntakeFactory

package frc.robot.commands;

import java.util.function.DoubleSupplier; // Represents a supplier of double-valued results.

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Mechanisms;

public class IntakeCommands extends InstantCommand {
    
    private final IntakeSubsystem intakeSubsystem;
    
    public IntakeCommands(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {

    }
}
