package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.StickPivotSubsystem;

public class StickPivotCommand extends Command {
    private StickPivotSubsystem stickPivotSubsystem;
    private double angle;
    private double desiredTicks;
    private final double DEADBAND;

    public StickPivotCommand(StickPivotSubsystem stickPivotSubsystem, double angle){
        this.stickPivotSubsystem = stickPivotSubsystem;
        this.angle = angle;
        desiredTicks = stickPivotSubsystem.degreesToTicks(angle);
        DEADBAND = stickPivotSubsystem.degreesToTicks(5);
        addRequirements(stickPivotSubsystem);
    }

    @Override
    public void execute(){
        stickPivotSubsystem.setPosition(desiredTicks);
        System.out.println("CURRENT ANGLE: " + (stickPivotSubsystem.getCurrentTicks() / Constants.STICK_PIVOT_TICKS_PER_DEGREE) + " degrees");
        System.out.println("DESIRED ANGLE: " + desiredTicks / Constants.STICK_PIVOT_TICKS_PER_DEGREE);
    }

    @Override
    public boolean isFinished(){
        double error = desiredTicks - stickPivotSubsystem.getCurrentTicks(); 
        if(Math.abs(error) < DEADBAND){
            stickPivotSubsystem.setSpeed(0);
            return true;
        }
        return false;
    }
}
