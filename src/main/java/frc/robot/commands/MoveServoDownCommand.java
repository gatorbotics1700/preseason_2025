package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ServoSubsystem;

public class MoveServoDownCommand extends InstantCommand {

    private final ServoSubsystem servoSubsystem;

    public MoveServoDownCommand(ServoSubsystem servoSubsystem) {
        this.servoSubsystem = servoSubsystem;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        // Move the servo down when the command is initialized
        servoSubsystem.moveDown();
    }

    @Override
    public boolean isFinished() {
        // End the command immediately after moving down
        return true;
    }
}
