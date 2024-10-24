package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RotateFalconCommand;
import frc.robot.commands.SetServoAngleCommand;
import frc.robot.subsystems.MechanismSubsystem;

public class RobotContainer {
    // Subsystems
    private final MechanismSubsystem mechanismSubsystem = new MechanismSubsystem();

    // Commands
    private final Command rotateFalconCommand = new RotateFalconCommand(mechanismSubsystem, 0.5); // 50% speed

    public RobotContainer() {
        // No need to configure button bindings as no controller is used
    }

    // Provide the command to run during teleop
    public Command getTeleopCommand() {
        return rotateFalconCommand; // Run the Falcon motor at 50% speed during teleop
    }
}
