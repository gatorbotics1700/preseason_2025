package frc.robot;

// import frc.robot.commands.TestDriveCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.ClimbingCommand;
import frc.robot.commands.CoralShooterCommand;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
    // private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    private final XboxController controller = new XboxController(0);
    private final XboxController controller_two = new XboxController(1);
    // private final SendableChooser<Command> autoChooser;
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
    private static final CoralShooterSubsystem m_coralShooterSub = new CoralShooterSubsystem();
    private static final ClimbingSubsystem m_climbingSub = new ClimbingSubsystem();


    public RobotContainer() {
        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        // new Trigger(controller::getBackButtonPressed)
        //         .onTrue(new InstantCommand(drivetrain::zeroGyroscope));

        // new Trigger(controller::getRightBumperPressed)
        //        .onTrue(new InstantCommand(drivetrain::setSlowDrive));

        //pipeline buttons
    //     new Trigger(controller::getAButtonPressed)
    //            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 0));
    //    new Trigger(controller::getBButtonPressed)
    //            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 1));

        // coral shooter intaking
        new Trigger(controller_two::getXButtonPressed)
                .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_SPEED));

        // coral shooter outtaking
        new Trigger(controller_two::getYButtonPressed)
                .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_OUTTAKING_SPEED));

        // button to stop intaking and outtake
        new Trigger(controller_two::getBButtonPressed)
            //.onTrue(new CoralShooterCommand(m_coralShooterSub, 0))
            .onTrue(new ClimbingCommand(m_climbingSub, 0));
        
        // climbing mechanism 
        new Trigger(controller_two::getAButtonPressed)
            .onTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED));

        // reverse climbing
        new Trigger(controller_two::getXButtonPressed)
            .onTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED)); // TODO: figure out if this value needs to be different
        // Auto chooser setup
        // autoChooser = AutoBuilder.buildAutoChooser();
        // SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    // public Command getAutonomousCommand() {
    //     try {
    //         Command auto = autoChooser.getSelected();
    //         System.out.println("Auto loaded successfully: " + autoChooser.getSelected().getName());
    //         return auto;
    //     } catch (Exception e) {
    //         System.err.println("Failed to load auto path: " + e.getMessage());
    //         e.printStackTrace();
    //         // return new TestDriveCommand(drivetrain);
    //     }
    // }

    // public void setDefaultTeleopCommand(){
    //     System.out.println("SETTING DEFAULT TELEOP COMMAND");
    //    drivetrain.setDefaultCommand(
    //        new TeleopDriveCommand(
    //             drivetrain,
    //            () -> -modifyAxis(controller.getRightY()),    // Changed to raw values
    //            () -> -modifyAxis(controller.getRightX()),     // Changed to raw values
    //            () -> -modifyAxis(controller.getLeftX())    // Changed to raw values
    //        )
    //    );
    // }

//     public DrivetrainSubsystem getDrivetrain(){
//         return drivetrain;
//    }

    // private double deadband(double value, double deadband) {
    //     if (Math.abs(value) > deadband) {
    //         if (value > 0.0) {
    //             return (value - deadband) / (1.0 - deadband);
    //         } else {
    //             return (value + deadband) / (1.0 - deadband);
    //         }
    //     } else {
    //         return 0.0;
    //     }
    // }

    // private double modifyAxis(double value) {
    //     value = deadband(value, 0.05);

    //     // Square the axis
    //     value = Math.copySign(value * value, value);

    //    if(drivetrain.getSlowDrive()){
    //        return (0.5 * value);
    //     }

    //     return value;
    // }
}
