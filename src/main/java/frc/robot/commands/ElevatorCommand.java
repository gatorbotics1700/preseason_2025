package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {

    private ElevatorSubsystem elevatorSubsystem;
    private double position;
    
    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, double position){
        this.elevatorSubsystem = elevatorSubsystem;
        this.position = position;
        addRequirements(elevatorSubsystem);
    }
    
    @Override 
    public void execute(){
        elevatorSubsystem.setPosition(position);
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return super.isFinished();
    }

}
