package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbingSubsystem;

public class ClimbingCommand extends Command {
   
    private ClimbingSubsystem climbingSubsystem; 
    private final double speed;

    public ClimbingCommand(ClimbingSubsystem climbingSubsystem, double speed) {
        this.climbingSubsystem = climbingSubsystem; 
        this.speed = speed; 
        addRequirements(climbingSubsystem); 
    }
    
    @Override
    public void execute() {
        climbingSubsystem.setSpeed(speed);
    }

    @Override 
    public boolean isFinished() {
        //if hanging successfully--maybe using pigeon to determine position? 
            // climbingSubsystem.setSpeed(0);
            return true; // commented out for testing shooter - should just stop command on start
        // else {
        //     return false; 
        // } 
    }

}
