package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;
public class CoralShooterCommand extends Command {
    private CoralShooterSubsystem coralShooterSubsystem;
    private final double speed;  
    private double startTime;
    private boolean shootingCurrentPeaked; 
    
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
            if(timePassed > 5000){
                coralShooterSubsystem.setSpeed(0);
                System.out.println ("Finished intaking");
                return true;
            } else if(coralShooterSubsystem.getMotor2StatorCurrent() > Constants.SHOOTER_INTAKING_CURRENT_LIMIT){ // checks current limit of bottom motor to prevent stalling
                coralShooterSubsystem.setSpeed(0); // stops motor when we fully intake - might be helpful to also add a sensor to intake
                return true;
            }
        } else if(speed == 0){ // if stopped, end command
            System.out.println("SPEED: 0, STOPPING");
            return true;
        } else if(speed < 0){ // if outtaking
            if(timePassed > 2500){
                coralShooterSubsystem.setSpeed(0);
                System.out.println("Finished outtaking");
                return true;
            } else if(coralShooterSubsystem.getMotorStatorCurrent() > Constants.SHOOTER_OUTTAKING_MAX_CURRENT){ 
                shootingCurrentPeaked = true; // notifies us that the coral in the shooter, still outtaking
                System.out.println("SHOOTING CURRENT PEAKED: " + coralShooterSubsystem.getMotorStatorCurrent());
            } else if(shootingCurrentPeaked && coralShooterSubsystem.getMotorStatorCurrent() < Constants.SHOOTER_OUTTAKING_MIN_CURRENT){ // if the coral has left the shooter
                coralShooterSubsystem.setSpeed(0); // stop because we have finished outtaking
                shootingCurrentPeaked = false;
                return true;
            }
        }
        return false;
    }
}