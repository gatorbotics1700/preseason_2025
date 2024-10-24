package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.RotateFalconCommand;
import frc.robot.commands.SetServoAngleCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.MechanismSubsystem;

public class Robot extends TimedRobot {
    private MechanismSubsystem mechanismSubsystem;
    private Command rotateFalconCommand;
    
    @Override
    public void robotInit() {
        mechanismSubsystem = new MechanismSubsystem();
        
        rotateFalconCommand = new RotateFalconCommand(mechanismSubsystem, 0.5);
    }

    @Override
    public void teleopInit() {
        mechanismSubsystem.setEnabled(true);
        rotateFalconCommand.schedule(); 
    }

    @Override
    public void teleopPeriodic() {
        
    }

    @Override
    public void disabledInit() {
        mechanismSubsystem.setEnabled(false);
    }

    @Override
    public void autonomousInit() {
       
    }

    @Override
    public void autonomousPeriodic() {

    }
}
