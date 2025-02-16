package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StickSubsystem;
import frc.robot.subsystems.StickPivotSubsystem;

public class ScoreCommands{
    public static Command Level(int level, ElevatorSubsystem m_elevatorSub, StickSubsystem m_stickSub, StickPivotSubsystem m_stickPivotSub){
        System.out.println("IN SCORE COMMAND!!!");
        AutoElevatorScorerCommand autoElevatorScorerCommand = new AutoElevatorScorerCommand(m_elevatorSub, m_stickSub, m_stickPivotSub, level);
        return autoElevatorScorerCommand;
    }

    public static Command Shoot(CoralShooterSubsystem m_coralShooterSub){
            System.out.println("shooting!");
            CoralShooterCommand coralShooterCommand = new CoralShooterCommand(m_coralShooterSub,  Constants.CORAL_OUTTAKING_SPEED);
            return coralShooterCommand; //TODO: Change speed
    }
}
