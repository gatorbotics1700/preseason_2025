package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();

    private final XboxController controller = new XboxController(0);

    public RobotContainer() {
        drivetrain.register();

        drivetrain.setDefaultCommand(new DriveCommand(
                drivetrain,
                () -> -modifyAxis(controller.getRightY()), // Axes are flipped here on purpose
                () -> -modifyAxis(controller.getRightX()),
                () -> -modifyAxis(controller.getLeftX())
        ));


        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrain::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrain::setSlowDrive));

            //    SmartDashboard.putData(new PowerDistribution());
    }
    //instant command means that it runs one time rather then run command which runs continuosly

    public DrivetrainSubsystem getDrivetrain() {
        return drivetrain;
    }

    private double deadband(double value, double deadband) {
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

    private double modifyAxis(double value) {
        // Deadband
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        if(drivetrain.getSlowDrive()){
            return (0.5 * value);
        }

        return value;
    }
}
