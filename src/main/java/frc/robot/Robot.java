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

    @Override
    public void robotInit() {
        mechanismSubsystem = new MechanismSubsystem();
    }

    @Override
    public void teleopInit() {
        // Rotate Falcon 500 motor 90 degrees
        rotateFalconToPosition(90.0);
        
        // Set servo to 90 degrees
        mechanismSubsystem.setServoAngle(90.0);
    }

    public void rotateFalconToPosition(double targetDegrees) {
       
        mechanismSubsystem.rotateFalconToPosition(targetDegrees);
    }
}
