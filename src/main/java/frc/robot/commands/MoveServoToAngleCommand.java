package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ServoSubsystem;

public class MoveServoToAngleCommand extends InstantCommand {

    private final ServoSubsystem servoSubsystem;
    private final double targetAngle; // Target angle to move the servo to

    public MoveServoToAngleCommand(ServoSubsystem servoSubsystem, double targetAngle) {
        this.servoSubsystem = servoSubsystem;
        this.targetAngle = targetAngle;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        // Set the servo to the desired angle
        servoSubsystem.setServoAngle(targetAngle);
    }

    @Override
    public boolean isFinished() {
        return true; // This command finishes immediately after setting the angle
    }
}
