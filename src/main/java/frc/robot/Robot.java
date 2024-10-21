package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.TurretSubsystem;


public class Robot extends TimedRobot {

    private LimelightSubsystem limelightSubsystem;
    private TurretSubsystem turretSubsystem;
    private ServoSubsystem servoSubsystem;
  //  private OI oi;

    @Override
    public void robotInit() {

        limelightSubsystem = new LimelightSubsystem();
        turretSubsystem = new TurretSubsystem();
        servoSubsystem = new ServoSubsystem(0);

       // oi = new OI(limelightSubsystem);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        turretSubsystem.rotateTurretSlow();

        servoSubsystem.moveUp();
    }

    @Override
    public void teleopPeriodic() {
        double tyValue = limelightSubsystem.getVerticalOffset();
        System.out.println("Limelight ty (vertical offset): " + tyValue);
    }

    @Override
    public void autonomousInit() {
      
    }

    @Override
    public void autonomousPeriodic() {
        
    }

    @Override
    public void disabledInit() {
        turretSubsystem.stopTurret();
        
        // Stop servo movement when robot is disabled
        servoSubsystem.stopServo();
    }

    @Override
    public void disabledPeriodic() {
       
    }
}
