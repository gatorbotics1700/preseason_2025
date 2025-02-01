package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralSubsystem;

public class CoralCommand extends Command {
    private CoralSubsystem coralSubsystem;
    private double speed;
    private double startTime;
    // This is the command for the new coral intake/outtake

    public CoralCommand(CoralSubsystem coralSubsystem, double speed) {
        this.coralSubsystem = new CoralSubsystem();
        this.speed = speed;
        addRequirements(coralSubsystem);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute(){
        coralSubsystem.setSpeed(speed);
    }

   @Override
   public boolean isFinished() {
        double timePassed = System.currentTimeMillis() - startTime;
        if (speed == 0){
            return true;
        }
        if (speed > 0 ){ //if intaking
            if (timePassed > 10000){
                coralSubsystem.setSpeed(0);
                return true;
            }else{
                return false;
            }
        }
        if (speed < 0){ // if outtaking
            if (timePassed > 8000){
                return true;
            }else{
                return false;
            }
        }
        return false;
   }
}