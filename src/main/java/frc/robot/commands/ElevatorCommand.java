package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {

    private ElevatorSubsystem elevatorSubsystem;
    private double height; // in inches
    double desiredTicks;
    private double DEADBAND = 1 * Constants.ELEVATOR_TICKS_PER_INCH; // 1 inch in ticks; TODO: change this value
    
    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, double height){
        this.elevatorSubsystem = elevatorSubsystem;
        this.height = height;
        desiredTicks = elevatorSubsystem.determineInchesToTicks(height);
        addRequirements(elevatorSubsystem);
    }
    
    @Override 
    public void execute(){
        elevatorSubsystem.setPosition(desiredTicks);
        System.out.println("top limit switch: " + elevatorSubsystem.atTopLimitSwitch());
        System.out.println("bottom limit switch: " + elevatorSubsystem.atBottomLimitSwitch());
    }

    @Override
    public boolean isFinished() {
        double currentTicks = elevatorSubsystem.getCurrentTicks(); 
        System.out.println("CURRENT HEIGHT: " + (currentTicks / Constants.ELEVATOR_TICKS_PER_INCH) + " inches");
        
        if(elevatorSubsystem.atTopLimitSwitch() && desiredTicks > currentTicks){
            System.out.println("TOP LIMIT SWITCH TRIGGERED - STOPPING");
            elevatorSubsystem.setSpeed(0);
            return true;
        }

        if(elevatorSubsystem.atBottomLimitSwitch() && desiredTicks < currentTicks){ 
            System.out.println("BOTTOM LIMIT SWITCH TRIGGERED - STOPPING");
            elevatorSubsystem.setSpeed(0);
            return true;
        }

        double error = desiredTicks - currentTicks; 
        if(Math.abs(error) < DEADBAND){
            System.out.println("REACHED TARGET");
            elevatorSubsystem.setSpeed(0);
            return true;
        }

        return false;
    }
}
