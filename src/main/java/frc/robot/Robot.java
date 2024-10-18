package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;

public class Robot extends TimedRobot {

    private LimelightSubsystem limelightSubsystem;
  //  private OI oi;

    @Override
    public void robotInit() {

        limelightSubsystem = new LimelightSubsystem();

       // oi = new OI(limelightSubsystem);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
      
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
        
    }

    @Override
    public void disabledPeriodic() {
       
    }
}
