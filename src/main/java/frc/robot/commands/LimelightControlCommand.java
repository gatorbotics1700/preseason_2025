package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final TurretSubsystem turretSubsystem;
    
   
    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.turretSubsystem = turretSubsystem;
        
        
        
        addRequirements(limelightSubsystem, turretSubsystem);
    }

    @Override
    public void execute() {
        // if (limelightSubsystem.hasValidTarget()) {
        //     double targetAngle = limelightSubsystem.getHorizontalOffset();
        //     double turnSpeed;
            
        //     // if (USE_PID) {

        //     //     // turnSpeed = -pidController.calculate(targetAngle);
                
        //     //     // turnSpeed = Math.max(-TURNING_SPEED, Math.min(TURNING_SPEED, turnSpeed));
        //     // } else {
        //     //     // turnSpeed = 0;
        //     //     // if (Math.abs(targetAngle) > TOLERANCE) {
        //     //     //     turnSpeed = Math.signum(targetAngle) * TURNING_SPEED;
        //     //     // }
        //     // }
            
        //     System.out.println("Target Offset: " + targetAngle);
        //     //turretSubsystem.setTurretSpeed(turnSpeed);
        //     turretSubsystem.turnToAngle(targetAngle,0.1);
            
        // } else {
        //     System.out.println("NO APRILTAG FOUND");
        //     if (USE_PID) {
        //         pidController.reset();
        //     }
        //     turretSubsystem.setTurretSpeed(0);
        // }
    }

    // @Override
    // public boolean isFinished() {
    //     if (!limelightSubsystem.hasValidTarget()) {
    //         return false;
    //     }
        
    //     double offset = Math.abs(limelightSubsystem.getHorizontalOffset());
    //     boolean isAligned = offset < TOLERANCE;
        
    //     if (isAligned) {
    //         turretSubsystem.setTurretSpeed(0);
    //         System.out.println("Target Aligned! Offset: " + offset);
    //         return true;
    //     }
    //     return false;
    // }
}
