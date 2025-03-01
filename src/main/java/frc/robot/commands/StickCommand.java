package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.StickSubsystem;

public class StickCommand extends Command {
    
    private StickSubsystem stickSubsystem;
    
    private final double speed;
    
    private double startTime;

    public StickCommand(StickSubsystem stickSubsystem, double speed){
        this.stickSubsystem = stickSubsystem;
        this.speed = speed;
        addRequirements(stickSubsystem);
    }

    @Override
    public void initialize(){
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void execute(){
        stickSubsystem.setSpeed(speed);
    }

    @Override
    public boolean isFinished(){
        double timePassed = System.currentTimeMillis() - startTime;
        System.out.println ("Milliseconds passed: " + timePassed);

        if(speed > 0){ // if intaking
            if(System.currentTimeMillis() - startTime > 5000){
                stickSubsystem.setSpeed(0);
                System.out.println ("Finished intaking");
                return true;
            } else if (stickSubsystem.getMotorStatorCurrent() > Constants.STICK_INTAKE_CURRENT_LIMIT){
                stickSubsystem.setSpeed(0);
                System.out.println("CURRENT LIMIT SURPASSED");
                return true;
            }
        } else if(speed == 0){
            return true;
        } else if(speed < 0){ // if outtaking
            if(System.currentTimeMillis() - startTime > 5000){
                stickSubsystem.setSpeed(0);
                System.out.println("Finished outtaking");
                return true;
            } 
        }
        return false;
    }
}
