package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.MechanismSubsystem;

public class SetServoAngleCommand extends InstantCommand {
    private final MechanismSubsystem mechanism;
    private final double angle;

    public SetServoAngleCommand(MechanismSubsystem subsystem, double angle) {
        mechanism = subsystem;
        this.angle = angle;
        addRequirements(mechanism);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        mechanism.setServoAngle(angle);
    }

    @Override
    public void end(boolean interrupted) {
        mechanism.stopMotors();
    }

    @Override
    public boolean isFinished() {
        return true; // This command finishes immediately after setting the servo
    }
}
