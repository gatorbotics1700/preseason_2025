package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbingSubsystem;

public class ClimbingCommand extends Command {
   
    private ClimbingSubsystem climbingSubsystem; 
    
    private final double speed;
    
    private double startTime;

    
    public ClimbingCommand(ClimbingSubsystem climbingSubsystem, double speed) {
        this.climbingSubsystem = climbingSubsystem; 
        this.speed = speed; 
        addRequirements(climbingSubsystem); 
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        System.out.println("CLIMBING COMMAND");
    }
    
    @Override
    public void execute() {
        climbingSubsystem.setSpeed(speed);
        if (speed > 0) {
            System.out.println("REVERSE CLIMBING");
        } else if (speed < 0) {
            System.out.println("CLIMBING");
        }
    }

    @Override 
    public boolean isFinished() {
        //if hanging successfully--maybe using pigeon to determine position? 
            // climbingSubsystem.setSpeed(0);
            //return true; // commented out for testing shooter - should just stop command on start
        // else {
        //     return false; 
        // } 

        double timePassed = System.currentTimeMillis() - startTime;
        System.out.println("Milliseconds passed: " +  timePassed);  
        
        if(speed > 0){ // if detatching / reverse climbing
            if(System.currentTimeMillis() - startTime > 20000){
                climbingSubsystem.setSpeed(0);
                System.out.println ("Finished detaching");
                return true;
            }
        } else if(speed == 0){
            return true;
        } else if(speed < 0){ // if climbing
            if(System.currentTimeMillis() - startTime > 20000){
                climbingSubsystem.setSpeed(0);
                System.out.println("Finished climbing");
                return true;
            } 
        }
        return false;
    }
}
