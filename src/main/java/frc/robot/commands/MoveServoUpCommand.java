package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ServoSubsystem;

public class MoveServoUpCommand extends CommandBase {

    private final ServoSubsystem servoSubsystem;
    private final double increment = 10; // The amount to increment the angle
    private final double maxAngle = 180; // Maximum servo angle

    public MoveServoUpCommand(ServoSubsystem servoSubsystem) {
        this.servoSubsystem = servoSubsystem;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        // Increment the servo angle by the specified amount
        double currentAngle = servoSubsystem.getServoAngle();
        double newAngle = Math.min(currentAngle + increment, maxAngle); // Ensure it doesn't go above the max
        servoSubsystem.setServoAngle(newAngle);
    }

    @Override
    public boolean isFinished() {
        return true; // The command is instantaneous
    }
}
