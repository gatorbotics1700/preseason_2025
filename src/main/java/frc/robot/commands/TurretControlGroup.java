package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.TurretSubsystem;

public class TurretControlGroup extends ParallelCommandGroup{
    public TurretControlGroup(TurretSubsystem turretsubsystem, double speed, double angle){
        addCommands(new TurretControlCommand(turretsubsystem,speed));
    }
    
}
