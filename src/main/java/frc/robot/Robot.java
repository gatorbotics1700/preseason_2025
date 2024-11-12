package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    @SuppressWarnings("unused")

    private Command m_autonomousCommand;
    private RobotContainer container;

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
        m_autonomousCommand = container.getAutonomousCommand();

        if(m_autonomousCommand != null){
            m_autonomousCommand.schedule();
        }
    }
    @Override
    public void teleopInit (){
        if(m_autonomousCommand != null){
            m_autonomousCommand.cancel();

        }
    }
}
