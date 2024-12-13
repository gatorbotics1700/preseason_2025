package frc.robot;

import frc.robot.commands.TestDriveCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    private final XboxController controller = new XboxController(0);
    private final SendableChooser<Command> autoChooser;

    public RobotContainer() {
        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new RunCommand(drivetrain::zeroGyroscope));

        // Auto chooser setup
        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    public Command getAutonomousCommand() {
        try {
            Command auto = autoChooser.getSelected();
            System.out.println("Auto loaded successfully: " + autoChooser.getSelected().getName());
            return auto;
        } catch (Exception e) {
            System.err.println("Failed to load auto path: " + e.getMessage());
            e.printStackTrace();
            return new TestDriveCommand(drivetrain);
        }
    }

    public void setDefaultTeleopCommand(){
        System.out.println("SETTING DEFAULT TELEOP COMMAND");
        drivetrain.setDefaultCommand(
            new TeleopDriveCommand(
                drivetrain,
                () -> -modifyAxis(controller.getLeftY()),    // Changed to raw values
                () -> -modifyAxis(controller.getLeftX()),     // Changed to raw values
                () -> -modifyAxis(controller.getRightX())    // Changed to raw values
            )
        );
    }

    public DrivetrainSubsystem getDrivetrain(){
        return drivetrain;
    }

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    private static double modifyAxis(double value) {
        // Deadband
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        return value;
    }
}
