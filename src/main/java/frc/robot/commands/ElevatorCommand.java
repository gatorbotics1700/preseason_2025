package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {

    private ElevatorSubsystem elevatorSubsystem;
    private double height; // in inches
    private double speed;
    double desiredTicks;
    private double DEADBAND = 1 * Constants.ELEVATOR_TICKS_PER_INCH; // 1 inch in ticks; TODO: change this value
    
    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, double height, double speed){
        this.elevatorSubsystem = elevatorSubsystem;
        this.height = height;
        this.speed = speed; // only used with joystick, otherwise set to 0
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize(){
        desiredTicks = elevatorSubsystem.determineInchesToTicks(height);
    }
    
    @Override 
    public void execute(){
        elevatorSubsystem.setSpeed(speed);
        elevatorSubsystem.setPosition(desiredTicks);
        System.out.println("top limit switch: " + elevatorSubsystem.atTopLimitSwitch());
        System.out.println("bottom limit switch: " + elevatorSubsystem.atBottomLimitSwitch());
    }

    @Override
    public boolean isFinished() {
        double currentTicks = elevatorSubsystem.getCurrentTicks(); 
        System.out.println("CURRENT HEIGHT: " + (currentTicks / Constants.ELEVATOR_TICKS_PER_INCH) + " inches");
        
        if(elevatorSubsystem.getMotorStatorCurrent()>1000){ // TODO: set current limit value
            System.out.println("ELEVATOR CURRENT PEAKED");
            elevatorSubsystem.setSpeed(0);
            return true;
        }

        if(elevatorSubsystem.atTopLimitSwitch() && desiredTicks > Math.abs(currentTicks)){
            System.out.println("TOP LIMIT SWITCH TRIGGERED - STOPPING");
            elevatorSubsystem.setSpeed(0);
            return true;
        }

        if(elevatorSubsystem.atBottomLimitSwitch() && desiredTicks < Math.abs(currentTicks)){ 
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
