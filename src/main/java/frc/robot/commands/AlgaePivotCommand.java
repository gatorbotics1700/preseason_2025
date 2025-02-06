package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaePivotSubsytem;

public class AlgaePivotCommand extends Command {
    private AlgaePivotSubsytem algaePivotSubsystem;
    private double angle;
    private double desiredTicks;
    private final double DEADBAND;

    public AlgaePivotCommand(AlgaePivotSubsytem algaePivotSubsystem, double angle){
        this.algaePivotSubsystem = algaePivotSubsystem;
        this.angle = angle;
        desiredTicks = algaePivotSubsystem.degreesToTicks(angle);
        DEADBAND = algaePivotSubsystem.degreesToTicks(5);
        addRequirements(algaePivotSubsystem);
    }

    @Override
    public void execute(){
        algaePivotSubsystem.setPosition(desiredTicks);
    }

    @Override
    public boolean isFinished(){
        double error = desiredTicks - algaePivotSubsystem.getPosition(); //TODO: convert getPosition() to ticks
        if(Math.abs(error) < DEADBAND){
            algaePivotSubsystem.setSpeed(0);
            return true;
        }
        return false;
    }
}
