package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralShooterSubsystem;

public class CoralShooterCommand extends Command {
    private CoralShooterSubsystem coralShooterSubsystem;
    private final double speed;  

    public CoralShooterCommand(CoralShooterSubsystem coralShooterSubsystem, double speed) {
        this.coralShooterSubsystem = coralShooterSubsystem; 
        this.speed = speed;
        addRequirements(coralShooterSubsystem);
        

    }



    @Override
    public void execute() {
        //set speed to given value 
        coralShooterSubsystem.setSpeed(speed);
        System.out.println("SPEED: " + speed);
        
    }

    @Override
    public boolean isFinished() {
        if (speed > 0) { //in the case that positive is intaking
            if (coralShooterSubsystem.isBeamBroken()) {
                coralShooterSubsystem.setSpeed(0); 
                return true;
            }
        }
        else if (speed < 0 ) { //in the case that negative is shooting 
            //if game piece has left
                coralShooterSubsystem.setSpeed(0); 
                return true;
        }
        else {
            coralShooterSubsystem.setSpeed(0);
            return true;
        } 
        return false; 
        

    }

}

