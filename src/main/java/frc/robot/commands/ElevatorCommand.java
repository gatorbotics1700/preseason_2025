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
        if(elevatorSubsystem.atTopLimitSwitch() && elevatorSubsystem.desiredTicks > elevatorSubsystem.getPosition()){
            elevatorSubsystem.setSpeed(0);
            return true;
        }

        if(elevatorSubsystem.atBottomLimitSwitch() && elevatorSubsystem.desiredTicks < elevatorSubsystem.getPosition()){
            elevatorSubsystem.setSpeed(0);
            return true;
        }

        double error = desiredTicks - elevatorSubsystem.getPosition() * Constants.NEO_TICKS_PER_REV;
        if(Math.abs(error) < DEADBAND){
            elevatorSubsystem.setSpeed(0);
            return true;
        }
        //elevatorSubsystem.setPosition(desiredTicks);

        

        return false;
    }
}
