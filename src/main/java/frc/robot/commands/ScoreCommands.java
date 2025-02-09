package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class ScoreCommands{
    public static Command Level(int level){
        System.out.println("IN SCORE COMMAND!!!");
        return Commands.waitSeconds(3);
    }
}
