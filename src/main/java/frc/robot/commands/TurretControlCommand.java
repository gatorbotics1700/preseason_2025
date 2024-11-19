package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.LimelightSubsystem;


public class TurretControlCommand extends InstantCommand { 
    private final TurretSubsystem turretSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    private final double turretSpeed;

    public TurretControlCommand(TurretSubsystem turretSubsystem, LimelightSubsystem limelightSubsystem, double turretSpeed)  {
        this.turretSubsystem = turretSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.turretSpeed = turretSpeed;
        addRequirements(turretSubsystem); // Ensure this command requires the turret subsystem
        addRequirements(limelightSubsystem);
    }

    @Override
    public void execute() {
        // Set the turret motor speed
        System.out.println("EXECUTE");
        if(limelightSubsystem.hasValidTarget()){
             if (Math.abs(limelightSubsystem.getHorizontalOffset()) > 4){
                 turretSubsystem.turnToAngle(turretSubsystem.getTurretAngle()+limelightSubsystem.getHorizontalOffset());
                 System.out.print("offset greater than 4");
             } else {
                 turretSubsystem.setTurretSpeed(0);
                 System.out.print("offset less than 4");
             }
        } else {
            turretSubsystem.setTurretSpeed(turretSpeed);
            System.out.print("not seeing target");
        //turretSubsystem.turnToAngle(turretAngle);
        // if(Math.abs(limelightSubsystem.getHorizontalOffset())>3) {
        //     turretSubsystem.setTurretSpeed(turretSpeed);
        // } else {
        //     turretSubsystem.setTurretSpeed(0);
        // }
       // System.out.println(limelightSubsystem.getHorizontalOffset());
        }
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(limelightSubsystem.getHorizontalOffset()) < 4){
            System.out.println("FINISHED");
            return true;
        }
        System.out.println("NOT FINISHED");
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the turret when the command ends
        turretSubsystem.setTurretSpeed(0);
        System.out.println("STOPPING TURRET");
    }
}
