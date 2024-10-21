package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ServoSubsystem;

public class MoveServoUpCommand extends InstantCommand {

    private final ServoSubsystem servoSubsystem;

    public MoveServoUpCommand(ServoSubsystem servoSubsystem) {
        this.servoSubsystem = servoSubsystem;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        // Move the servo up when the command is initialized
        servoSubsystem.moveUp();
    }

    @Override
    public boolean isFinished() {
        // End the command immediately after moving up
        return true;
    }
}
