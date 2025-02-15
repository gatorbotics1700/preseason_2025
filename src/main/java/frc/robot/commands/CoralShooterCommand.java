package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;
public class CoralShooterCommand extends Command {
    private CoralShooterSubsystem coralShooterSubsystem;
    private final double speed;  
    private double startTime;
    
    public CoralShooterCommand(CoralShooterSubsystem coralShooterSubsystem, double speed) {
        this.coralShooterSubsystem = coralShooterSubsystem; 
        this.speed = speed;
        addRequirements(coralShooterSubsystem);
    }
    
    @Override
    public void initialize (){
        startTime = System.currentTimeMillis();
        //coralShooterSubsystem.setAngle(0.0, false);
    }
    
    @Override
    public void execute() {
        //set speed to given value 
        coralShooterSubsystem.setSpeed(speed);
        if (speed > 0) {
            System.out.println("INTAKING");
        } else if (speed < 0) {
            System.out.println ("OUTTAKING");
        }
        System.out.println("SPEED: " + speed);    
        // if (speed < 0) { // if outtaking
        //     coralShooterSubsystem.setAngle(Constants.SERVO_ANGLE, false); // TODO: move angle to params and set speed to value passed in to command regardless of intaking/outtaking
        // } 
    }
    @Override
    public boolean isFinished() {
        // // if (speed > 0) { //in the case that positive is intaking
        //     if (coralShooterSubsystem.isBeamBroken()) {
        //         coralShooterSubsystem.setSpeed(0); 
        //         return true;
            
        // } else if (speed < 0 ) { //in the case that negative is shooting 
        //     //if game piece has left - maybe we can use a second beambreak?
        //         coralShooterSubsystem.setSpeed(0); 
        //         coralShooterSubsystem.setAngle(0, false);
        //         return true;
        // } else {
        //     coralShooterSubsystem.setSpeed(0);
        //     return true;
        // }
        // return false; 
        /* NOTE: This is commented out to test using a start/stop button. For outtaking, this code looks like it would 
        immediately stop as soon as outtaking starts. We should either include a line to wait or just use a button for
        stopping it
         */
        
        double timePassed = System.currentTimeMillis() - startTime;
        System.out.println("Milliseconds passed: " +  timePassed);  
        
        if(speed > 0){ // if intaking
            if(System.currentTimeMillis() - startTime > 10000){
                coralShooterSubsystem.setSpeed(0);
                System.out.println ("Finished intaking");
                return true;
            }
        } else if(speed == 0){
            return true;
        } else if(speed < 0){ // if outtaking
            if(System.currentTimeMillis() - startTime > 5000){
                coralShooterSubsystem.setSpeed(0);
                System.out.println("Finished outtaking");
                return true;
            } 
        }
        return false;
    }
}