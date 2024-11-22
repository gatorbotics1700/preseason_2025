package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Robot extends TimedRobot {
    @SuppressWarnings("unused")

    private Command m_autonomousCommand;
    private Command m_testCommand;
    private RobotContainer container;
    private Command m_teleCommand;
    private DrivetrainSubsystem m_drivetrainSubsystem;

    @Override
    
    public void robotInit(){
        container = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }
    @Override
    public void autonomousInit(){
        // m_testCommand = container.getAutonomousCommand();

        // if(m_testCommand != null){
        //     m_testCommand.schedule();
        // }

        m_autonomousCommand = container.getAutonomousCommand();

        if(m_autonomousCommand != null){
            m_autonomousCommand.schedule();
        }
    }
    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when teleop starts
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        
        // Remove the teleop command scheduling since we're using default command
        m_drivetrainSubsystem.setDefaultCommand(container.getDrivetrain().getDefaultCommand());
    }

    @Override
    public void teleopPeriodic() {
        // Remove this entire method or leave it empty
    }
}
