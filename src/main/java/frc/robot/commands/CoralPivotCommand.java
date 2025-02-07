package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralPivotSubsystem;

public class CoralPivotCommand extends Command{
    private CoralPivotSubsystem coralPivotSubsystem;
    private double angle;
    private double desiredTicks;
    private final double DEADBAND;

    public CoralPivotCommand(CoralPivotSubsystem coralPivotSubsystem, double angle){
        this.coralPivotSubsystem = coralPivotSubsystem;
        this.angle = angle;
        desiredTicks = coralPivotSubsystem.degreesToTicks(angle);
        DEADBAND = coralPivotSubsystem.degreesToTicks(5);
        addRequirements(coralPivotSubsystem);
    }

    @Override
    public void execute(){
        coralPivotSubsystem.setPosition(desiredTicks);
        System.out.println("CURRENT ANGLE: " + (coralPivotSubsystem.getCurrentTicks() / Constants.CORAL_PIVOT_TICKS_PER_DEGREE) + " degrees");
    }

    @Override
    public boolean isFinished(){
        double error = desiredTicks - coralPivotSubsystem.getCurrentTicks();
        if(Math.abs(error) < DEADBAND){
            coralPivotSubsystem.setSpeed(0);
            return true;
        }
        // coralPivotSubsystem.setPosition(desiredTicks);
        return false;
    }
}
