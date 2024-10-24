package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.RotateFalconCommand;
import frc.robot.commands.SetServoAngleCommand;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.MechanismSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class Robot extends TimedRobot {
    private Command teleopCommand;
    private RobotContainer robotContainer;

    @Override
    public void robotInit() {
        // Initialize RobotContainer
        robotContainer = new RobotContainer();
    }

    @Override
    public void teleopInit() {
        // Get the teleop command from RobotContainer (rotate the Falcon at 0.5 speed)
        teleopCommand = robotContainer.getTeleopCommand();

        // Schedule the command to run during teleop
        if (teleopCommand != null) {
            teleopCommand.schedule();
        }
    }

    @Override
    public void teleopPeriodic() {
        // No additional logic required for now; command is already running
    }

    @Override
    public void teleopExit() {
        // Stop the command when teleop ends
        if (teleopCommand != null) {
            teleopCommand.cancel();
        }
    }
}
