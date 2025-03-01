package frc.robot;

import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.ScoreCommands;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.TestDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.wpilibj.GenericHID;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    private final XboxController controller = new XboxController(0);
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
    private final SendableChooser<Command> autoChooser;
    private final GenericHID buttonBoard = new GenericHID(3);
    

    public RobotContainer() {
        NamedCommands.registerCommand("Score Trough", ScoreCommands.Level(1));
        NamedCommands.registerCommand("Score L2", ScoreCommands.Level(2));
        NamedCommands.registerCommand("Score L3", ScoreCommands.Level(3));
        NamedCommands.registerCommand("Score L4", ScoreCommands.Level(4));
        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::setSlowDrive));

        // //pipeline button board for reef shooting
        // new Trigger(()->buttonBoard.getRawButtonPressed(1))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.LEFT_POST_LINE_UP_OFFSET)); //id 6, 19 left side
        // new Trigger(()->buttonBoard.getRawButtonPressed(2))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.RIGHT_POST_LINE_UP_OFFSET)); //id 6, 19 right side
        // new Trigger(()->buttonBoard.getRawButtonPressed(3))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.LEFT_POST_LINE_UP_OFFSET)); // id 7, 18 left side
        // new Trigger(()->buttonBoard.getRawButtonPressed(4))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.RIGHT_POST_LINE_UP_OFFSET)); // id 7, 18 right side
        // new Trigger(()->buttonBoard.getRawButtonPressed(5))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.LEFT_POST_LINE_UP_OFFSET)); // id 10, 21 left side
        // new Trigger(()->buttonBoard.getRawButtonPressed(6))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.RIGHT_POST_LINE_UP_OFFSET)); // id 10, 21 right side
        // new Trigger(()->buttonBoard.getRawButtonPressed(7))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.LEFT_POST_LINE_UP_OFFSET)); // id 8, 17 left side
        // new Trigger(()->buttonBoard.getRawButtonPressed(8))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.RIGHT_POST_LINE_UP_OFFSET)); // id 8, 17 right side
        // new Trigger(()->buttonBoard.getRawButtonPressed(9))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.LEFT_POST_LINE_UP_OFFSET)); // id 9, 22 left side
        // new Trigger(()->buttonBoard.getRawButtonPressed(10))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.RIGHT_POST_LINE_UP_OFFSET)); // id 9, 22 right side
        // new Trigger(()->buttonBoard.getRawButtonPressed(11))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.LEFT_POST_LINE_UP_OFFSET)); // id 11, 20 left side
        // new Trigger(()->buttonBoard.getRawButtonPressed(12))
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.RIGHT_POST_LINE_UP_OFFSET)); // id 11, 20 right side

        new Trigger(controller::getAButtonPressed) 
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.FRONT_CENTER_ALIGN_OFFSET)); // id 6,19 A
        new Trigger(controller::getBButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.FRONT_CENTER_ALIGN_OFFSET)); // 7,18,10,21 B
        new Trigger(controller::getXButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.FRONT_CENTER_ALIGN_OFFSET)); // id 8,17 X
        new Trigger(controller::getYButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.FRONT_CENTER_ALIGN_OFFSET)); // id 9,22 Y

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
            return new TestDriveCommand(drivetrainSubsystem);
        }
    }


    public void setDefaultTeleopCommand(){
        System.out.println("SETTING DEFAULT TELEOP COMMAND");
        drivetrainSubsystem.setDefaultCommand(
            new TeleopDriveCommand(
                drivetrainSubsystem,
                () -> -modifyAxis(controller.getRightY()),    // Changed to raw values
                () -> -modifyAxis(controller.getRightX()),     // Changed to raw values
                () -> -modifyAxis(controller.getLeftX())    // Changed to raw values
            )
        );
    }

    public DrivetrainSubsystem getDrivetrainSubsystem(){
        return drivetrainSubsystem;
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

        if(drivetrainSubsystem.getSlowDrive()){
            return (0.5 * value);
        }

        return value;
    }
}
