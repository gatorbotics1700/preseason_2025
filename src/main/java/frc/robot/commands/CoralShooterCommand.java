package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralShooterSubsystem;

public class CoralShooterCommand extends Command {
    private CoralShooterSubsystem coralShooterSubsystem; 

    public CoralShooterCommand(CoralShooterSubsystem coralShooterSubsystem) {
        this.coralShooterSubsystem = coralShooterSubsystem; 
        addRequirements(coralShooterSubsystem);
    }

    

    @Override
    public void execute() {

    }

}

