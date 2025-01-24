package frc.robot;

import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    private final XboxController controller = new XboxController(0);
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();



    public RobotContainer() {
        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrain::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrain::setSlowDrive));

        //pipeline buttons
        new Trigger(controller::getAButtonPressed)
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 0)); //id 2
        new Trigger(controller::getBButtonPressed)
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 1)); //id 8

    }


    public void setDefaultTeleopCommand(){
        System.out.println("SETTING DEFAULT TELEOP COMMAND");
        drivetrain.setDefaultCommand(
            new TeleopDriveCommand(
                drivetrain,
                () -> -modifyAxis(controller.getRightY()),    // Changed to raw values
                () -> -modifyAxis(controller.getRightX()),     // Changed to raw values
                () -> -modifyAxis(controller.getLeftX())    // Changed to raw values
            )
        );
    }

    public DrivetrainSubsystem getDrivetrain(){
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
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        if(drivetrain.getSlowDrive()){
            return (0.5 * value);
        }

        return value;
    }
}
