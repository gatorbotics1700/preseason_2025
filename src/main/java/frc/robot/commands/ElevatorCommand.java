package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {

    private ElevatorSubsystem elevatorSubsystem;
    private double height; // in inches
    double desiredTicks;
    private double DEADBAND = 5000; // TODO: change this value
    
    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, double height){
        this.elevatorSubsystem = elevatorSubsystem;
        this.height = height;
        desiredTicks = elevatorSubsystem.determineInchesToTicks(height);
        addRequirements(elevatorSubsystem);
    }
    
    @Override 
    public void execute(){
        elevatorSubsystem.setPosition(desiredTicks);
    }

    @Override
    public boolean isFinished() {
        double error = desiredTicks - elevatorSubsystem.getPosition();
        if(Math.abs(error) < DEADBAND){
            elevatorSubsystem.setSpeed(0);
            return true;
        }
        //elevatorSubsystem.setPosition(desiredTicks);
        return false;
    }
}
