package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.FollowPathCommand;
import frc.robot.commands.TestDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import com.pathplanner.lib.auto.AutoBuilder;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
    private final SendableChooser<Command> autoChooser;
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();

    private final XboxController controller = new XboxController(0);

    public RobotContainer() {
        drivetrain.register();

        drivetrain.setDefaultCommand(new DriveCommand(
                drivetrain,
                () -> -modifyAxis(controller.getLeftY()), // Axes are flipped here on purpose
                () -> -modifyAxis(controller.getLeftX()),
                () -> -modifyAxis(controller.getRightX())
        ));

        new Trigger(controller::getBackButtonPressed)
                 .onTrue(new RunCommand(drivetrain::zeroGyroscope));


        autoChooser = AutoBuilder.buildAutoChooser();


        SmartDashboard.putData("Auto Chooser", autoChooser);         

    }


    public Command getAutonomousCommand() {
        try {
            // Use "Test Auto" to match the exact file name
            PathPlannerAuto auto = new PathPlannerAuto("Test Auto");
            System.out.println("Auto loaded successfully: Test Auto");
            return auto;
        } catch (Exception e) {
            System.err.println("Failed to load auto path: " + e.getMessage());
            e.printStackTrace();
            // Fallback to test command if path fails to load
            return new TestDriveCommand(drivetrain);
        }
    }

    public Command getTestCommand(){
        return new TestDriveCommand(drivetrain);
    }

    public DrivetrainSubsystem getDrivetrain() {
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

    public Command getTeleopCommand() {
        // Return your default teleop command here
        return new RunCommand(
            () -> drivetrain.drive(
                new ChassisSpeeds(
                    -controller.getLeftY() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                    -controller.getLeftX() * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                    -controller.getRightX() * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
                )
            ),
            drivetrain
        );
    }
}
