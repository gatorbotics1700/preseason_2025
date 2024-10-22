package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ServoSubsystem;

public class MoveServoDownCommand extends CommandBase {

    private final ServoSubsystem servoSubsystem;
    private final double decrement = 10; // The amount to decrement the angle
    private final double minAngle = 0;   // Minimum servo angle

    public MoveServoDownCommand(ServoSubsystem servoSubsystem) {
        this.servoSubsystem = servoSubsystem;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        // Decrement the servo angle by the specified amount
        double currentAngle = servoSubsystem.getServoAngle();
        double newAngle = Math.max(currentAngle - decrement, minAngle); // Ensure it doesn't go below the min
        servoSubsystem.setServoAngle(newAngle);
    }

    @Override
    public boolean isFinished() {
        return true; // The command is instantaneous
    }
}
