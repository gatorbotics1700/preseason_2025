package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.AlgaePivotSubsystem;

public class AlgaePivotCommand extends Command {
    private AlgaePivotSubsystem algaePivotSubsystem;
    private double angle;
    private double desiredTicks;
    private final double DEADBAND;

    public AlgaePivotCommand(AlgaePivotSubsystem algaePivotSubsystem, double angle){
        this.algaePivotSubsystem = algaePivotSubsystem;
        this.angle = angle;
        desiredTicks = algaePivotSubsystem.degreesToTicks(angle);
        DEADBAND = algaePivotSubsystem.degreesToTicks(5);
        addRequirements(algaePivotSubsystem);
    }

    @Override
    public void execute(){
        algaePivotSubsystem.setPosition(desiredTicks);
        System.out.println("CURRENT ANGLE: " + (algaePivotSubsystem.getCurrentTicks() / Constants.ALGAE_PIVOT_TICKS_PER_DEGREE) + " degrees");
    }

    @Override
    public boolean isFinished(){
        double error = desiredTicks - algaePivotSubsystem.getCurrentTicks(); 
        if(Math.abs(error) < DEADBAND){
            algaePivotSubsystem.setSpeed(0);
            return true;
        }
        return false;
    }
}
