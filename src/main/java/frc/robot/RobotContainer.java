package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.FollowPathCommand;
import frc.robot.commands.TestDriveCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import com.pathplanner.lib.auto.AutoBuilder;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        System.out.println("Initial joystick values:");
        System.out.println("LeftY: " + controller.getLeftY());
        System.out.println("LeftX: " + controller.getLeftX());
        System.out.println("RightX: " + controller.getRightX());

        // Set up the default drive command
        // drivetrain.setDefaultCommand(
        //     new TeleopDriveCommand(
        //         drivetrain,
        //         () -> -modifyAxis(controller.getLeftY()),    // Changed to raw values
        //         () -> -modifyAxis(controller.getLeftX()),     // Changed to raw values
        //         () -> -modifyAxis(controller.getRightX())    // Changed to raw values
        //     )
        // );
        
        System.out.println("Default command set");

        // Zero gyroscope button binding
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new RunCommand(drivetrain::zeroGyroscope));

        // Auto chooser setup
        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    public Command getAutonomousCommand() {
        try {
            PathPlannerAuto auto = new PathPlannerAuto("Test Auto");
            System.out.println("Auto loaded successfully: Test Auto");
            return auto;
        } catch (Exception e) {
            System.err.println("Failed to load auto path: " + e.getMessage());
            e.printStackTrace();
            return new TestDriveCommand(drivetrain);
        }
    }

    public void setDefaultTeleopCommand(){
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
