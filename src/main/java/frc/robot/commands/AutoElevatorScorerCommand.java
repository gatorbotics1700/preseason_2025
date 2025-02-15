package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StickPivotSubsystem;
import frc.robot.subsystems.StickSubsystem;

public class AutoElevatorScorerCommand extends Command{
    private ElevatorSubsystem elevatorSubsystem;
    private StickSubsystem stickSubsystem;
    private StickPivotSubsystem stickPivotSubsystem;
    private int level;

    public AutoElevatorScorerCommand(ElevatorSubsystem elevatorSubsystem, StickSubsystem stickSubsystem, StickPivotSubsystem stickPivotSubsystem, int level){
        this.elevatorSubsystem = elevatorSubsystem;
        this.stickSubsystem = stickSubsystem;
        this.stickPivotSubsystem = stickPivotSubsystem;
        this.level = level;
    }

    public void execute(){
        if(level == 1){
            new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_ONE, 0); 
            new StickCommand(stickSubsystem, 0.2); 
            new StickPivotCommand(stickPivotSubsystem,  Constants.STICK_PIVOT_SHOOTING_ANGLE); 
            System.out.println("level 1!");
            
        } else if(level == 2){
            new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_TWO, 0); 
            new StickCommand(stickSubsystem, 0.2); 
            new StickPivotCommand(stickPivotSubsystem,  Constants.STICK_PIVOT_SHOOTING_ANGLE); 
            System.out.println("level 2!");

        } else if(level == 3){
            new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_THREE, 0); 
            new StickCommand(stickSubsystem, 0.2); 
            new StickPivotCommand(stickPivotSubsystem,  Constants.STICK_PIVOT_SHOOTING_ANGLE); 
            System.out.println("level 3!");

        } else if(level == 4){
            new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_FOUR, 0); 
            new StickCommand(stickSubsystem, 0.2); 
            new StickPivotCommand(stickPivotSubsystem,  Constants.STICK_PIVOT_SHOOTING_ANGLE); 
            System.out.println("level 4!");
        }
    }

    @Override
    public boolean isFinished(){
            return true; //TODO: MAKE SURE THIS WORKS!!!!
    }
    
}
