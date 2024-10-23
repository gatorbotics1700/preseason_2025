package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.MechanismSubsystem;

public class RotateFalconCommand extends InstantCommand {
    private final MechanismSubsystem mechanism;
    private final double speed;

    public RotateFalconCommand(MechanismSubsystem subsystem, double speed) {
        mechanism = subsystem;
        this.speed = speed;
        addRequirements(mechanism);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        mechanism.rotateFalconToPosition(speed);
    }

    @Override
    public void end(boolean interrupted) {
        mechanism.stopMotors();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
