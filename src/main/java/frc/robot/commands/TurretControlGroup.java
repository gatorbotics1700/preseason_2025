package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.TurretSubsystem;

public class TurretControlGroup extends ParallelCommandGroup {
    public TurretControlGroup(TurretSubsystem turretSubsystem, double turretSpeed, double targetAngle) {
        addCommands(
            new TurretControlCommand(turretSubsystem, turretSpeed),  // Control turret motor speed
            new TurretRotateCommand(turretSubsystem, targetAngle)  // Rotate turret to a specific angle
        );
    }
}
