package frc.robot.commands;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;

public class CoralShooterCommand extends Command {
    private CoralShooterSubsystem coralShooterSubsystem;
    // private final double voltage;
    private final double speed;
    private double startTime;
    private boolean shootingCurrentPeaked; 
    
    public CoralShooterCommand(CoralShooterSubsystem coralShooterSubsystem, double speed){ //double voltage) {
        this.coralShooterSubsystem = coralShooterSubsystem; 
        // this.voltage = voltage;
        this.speed = speed;
        addRequirements(coralShooterSubsystem);
    }
    
    @Override
    public void initialize (){
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void execute() {
        coralShooterSubsystem.setSpeed(speed);
        // coralShooterSubsystem.setVoltage(voltage);
        if(speed > 0) {
            System.out.println("INTAKING");
        } else if (speed < 0) {
            System.out.println ("SHOOTING");
        }
        // System.out.println("MOTOR VOLTAGE: " + voltage); 
        System.out.println("SPEED: " + speed);
        System.out.println("battery voltage: " + RobotController.getBatteryVoltage());   
    }
    @Override
    public boolean isFinished() {  
        double timePassed = System.currentTimeMillis() - startTime;
        System.out.println("Milliseconds passed: " +  timePassed);  
        
        System.out.println("MOTOR2 CURRENT: " + coralShooterSubsystem.getMotor2StatorCurrent());
        
        if(speed > 0){ // if intaking
            if(timePassed > 2500){
                //coralShooterSubsystem.setVoltage(0);
                coralShooterSubsystem.setSpeed(0);
                System.out.println ("Finished intaking");
                return true;
            } 
            // else if(coralShooterSubsystem.getMotor2StatorCurrent() > Constants.CORAL_INTAKING_CURRENT_LIMIT){ // checks current limit of bottom motor to prevent stalling
            //     coralShooterSubsystem.setVoltage(0); // stops motor when we fully intake - might be helpful to also add a sensor to intake
            //     System.out.println("INTAKING CURRENT PEAKED: " + coralShooterSubsystem.getMotor2StatorCurrent());
            //     return true;
            // }
        } else if(speed == 0){ // if stopped, end command
            // System.out.println("MOTOR VOLTAGE: 0, STOPPING");
            System.out.println("SPEED: 0, STOPPING");
            return true;
        } else if(speed < 0){ // if shooting
            if(timePassed > 1500){
                //coralShooterSubsystem.setVoltage(0);
                coralShooterSubsystem.setSpeed(0);
                System.out.println("Finished shooting");
                return true;
            }
            //  else if(coralShooterSubsystem.getMotorStatorCurrent() > Constants.CORAL_SHOOTING_MAX_CURRENT){ 
            //     shootingCurrentPeaked = true; // notifies us that the coral in the shooter, still outtaking
            //     System.out.println("SHOOTING CURRENT PEAKED: " + coralShooterSubsystem.getMotorStatorCurrent());
            // } else if(shootingCurrentPeaked && coralShooterSubsystem.getMotorStatorCurrent() < Constants.CORAL_SHOOTING_MIN_CURRENT){ // if the coral has left the shooter
            //     coralShooterSubsystem.setVoltage(0); // stop because we have finished outtaking
            //     shootingCurrentPeaked = false;
            //     return true;
            // }
        }
        return false;
    }
}