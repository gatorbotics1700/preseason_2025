package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class TurretControlCommand extends InstantCommand {
    private final TurretSubsystem turretSubsystem;
    private final double turretSpeed;
    private final double turretAngle;

    public TurretControlCommand(TurretSubsystem turretSubsystem, double turretSpeed, double turretAngle)  {
        this.turretSubsystem = turretSubsystem;
        this.turretSpeed = turretSpeed;
        this.turretAngle = turretAngle;
        addRequirements(turretSubsystem); // Ensure this command requires the turret subsystem
    }

    @Override
    public void execute() {
        // Set the turret motor speed
        if (turretSubsystem.getTurretAngle() < turretAngle){
            turretSubsystem.setTurretSpeed(turretSpeed);
        } else {
            turretSubsystem.setTurretSpeed(-turretSpeed);
        }
        //turretSubsystem.turnToAngle(turretAngle);
        // System.out.println("EXECUTE");
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(turretSubsystem.getTurretAngle() - turretAngle) < 1){
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
