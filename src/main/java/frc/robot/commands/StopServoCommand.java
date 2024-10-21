package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ServoSubsystem;

public class StopServoCommand extends InstantCommand {

    private final ServoSubsystem servoSubsystem;

    public StopServoCommand(ServoSubsystem servoSubsystem) {
        this.servoSubsystem = servoSubsystem;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        servoSubsystem.stopServo();
    }

    @Override
    public boolean isFinished() {
        return true; // Command ends immediately after execution
    }
}
