package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {

    private ElevatorSubsystem elevatorSubsystem;
    private double height; // in inches
    private double DEADBAND = 5000; // TODO: change this value
    
    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, double height){
        this.elevatorSubsystem = elevatorSubsystem;
        this.height = height;
        addRequirements(elevatorSubsystem);
    }
    
    @Override 
    public void execute(){
        elevatorSubsystem.setPosition(elevatorSubsystem.determineInchesToTicks(height));
    }

    @Override
    public boolean isFinished() {
        if(Math.abs(elevatorSubsystem.getPosition() - height) < DEADBAND){
            return true;
        }
        return false;
    }

}
