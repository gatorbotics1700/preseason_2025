package frc.robot.commands;
import com.ctre.phoenix6.controls.DutyCycleOut;

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
       turretSubsystem.turnToAngle(90, 0.1);
        //turretSubsystem.turnToAngle(turretAngle);
        // if(Math.abs(limelightSubsystem.getHorizontalOffset())>3) {
        //     turretSubsystem.setTurretSpeed(turretSpeed);
        // } else {
        //     turretSubsystem.setTurretSpeed(0);
        // }
       // System.out.println(limelightSubsystem.getHorizontalOffset());
        }

    @Override
    public boolean isFinished() {
        if ((turretSubsystem.getTurretAngle() > 85) && (turretSubsystem.getTurretAngle() < 95)){
            System.out.println("FINISHED");
            DutyCycleOut dutyCycleOut = new DutyCycleOut(0); 

            turretSubsystem.turretMotor.setControl(dutyCycleOut.withOutput(0));
            return true;
        }
        System.out.println("NOT FINISHED");
        return false;
    }

}
