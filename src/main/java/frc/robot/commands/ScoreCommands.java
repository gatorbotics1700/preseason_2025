package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StickSubsystem;
import frc.robot.subsystems.StickPivotSubsystem;

public class ScoreCommands{
    public static Command Level(int level, ElevatorSubsystem m_elevatorSub, StickSubsystem m_stickSub, StickPivotSubsystem m_stickPivotSub){
        System.out.println("IN SCORE COMMAND!!!");
        if (level == 1){
            return Trough(m_elevatorSub, m_stickSub, m_stickPivotSub);
        } else if (level == 2){
            return LevelTwo(m_elevatorSub, m_stickSub, m_stickPivotSub);
        } else if (level == 3){
            return LevelThree(m_elevatorSub, m_stickSub, m_stickPivotSub);
        } else if (level == 4){
            return LevelFour(m_elevatorSub, m_stickSub, m_stickPivotSub);
        }

        return Commands.waitSeconds(2); //if all else fails, return a wait command
    }

    public static Command Trough(ElevatorSubsystem elevatorSubsystem, StickSubsystem stickSubsystem, StickPivotSubsystem stickPivotSubsystem){
        System.out.println("trough!");
        return new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_ONE, 0)
        .alongWith(new StickPivotCommand(stickPivotSubsystem, Constants.STICK_PIVOT_SHOOTING_ANGLE)
        .andThen(new StickCommand(stickSubsystem, Constants.STICK_SPEED)));
    }

    public static Command LevelTwo(ElevatorSubsystem elevatorSubsystem, StickSubsystem stickSubsystem, StickPivotSubsystem stickPivotSubsystem){
        System.out.println("level 2!");
        return new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_TWO, 0)
        .alongWith(new StickPivotCommand(stickPivotSubsystem, Constants.STICK_PIVOT_SHOOTING_ANGLE)
        .andThen(new StickCommand(stickSubsystem, Constants.STICK_SPEED)));
    }

    public static Command LevelThree(ElevatorSubsystem elevatorSubsystem, StickSubsystem stickSubsystem, StickPivotSubsystem stickPivotSubsystem){
        System.out.println("level 3!");
        return new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_THREE, 0)
        .alongWith(new StickPivotCommand(stickPivotSubsystem, Constants.STICK_PIVOT_SHOOTING_ANGLE)
        .andThen(new StickCommand(stickSubsystem, Constants.STICK_SPEED)));
    }

    public static Command LevelFour(ElevatorSubsystem elevatorSubsystem, StickSubsystem stickSubsystem, StickPivotSubsystem stickPivotSubsystem){
        System.out.println("level 4!");
        return new ElevatorCommand(elevatorSubsystem, Constants.ELEVATOR_LEVEL_FOUR, 0)
        .alongWith(new StickPivotCommand(stickPivotSubsystem, Constants.STICK_PIVOT_SHOOTING_ANGLE)
        .andThen(new StickCommand(stickSubsystem, Constants.STICK_SPEED)));
    }

    public static Command Shoot(CoralShooterSubsystem m_coralShooterSub){
            System.out.println("shooting!");
            CoralShooterCommand coralShooterCommand = new CoralShooterCommand(m_coralShooterSub,  Constants.CORAL_OUTTAKING_SPEED);
            return coralShooterCommand; //TODO: Change speed
    }
}
