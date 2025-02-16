package frc.robot;

// import frc.robot.commands.TestDriveCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.StickPivotCommand;
import frc.robot.commands.StickCommand;
import frc.robot.commands.ClimbingCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.subsystems.StickPivotSubsystem;
import frc.robot.subsystems.StickSubsystem;
import frc.robot.commands.CoralShooterCommand;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LimelightSubsystem;



import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    private final XboxController controller = new XboxController(0);
    private final XboxController controller_two = new XboxController(1);
    // private final SendableChooser<Command> autoChooser;
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
    private static final ClimbingSubsystem m_climbingSub = new ClimbingSubsystem();
    private static final ElevatorSubsystem m_elevatorSub = new ElevatorSubsystem();
    private static final StickPivotSubsystem m_stickPivotSub = new StickPivotSubsystem();
    private static final StickSubsystem m_stickSub = new StickSubsystem();
    private static final CoralShooterSubsystem m_coralShooterSub = new CoralShooterSubsystem();



    public RobotContainer() {
        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        // new Trigger(controller::getBackButtonPressed)
        //      .onTrue(new InstantCommand(drivetrain::zeroGyroscope));

        // new Trigger(controller::getRightBumperPressed)
        //      .onTrue(new InstantCommand(drivetrain::setSlowDrive));

        //pipeline buttons
        // new Trigger(controller::getAButtonPressed)
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 0));
        // new Trigger(controller::getBButtonPressed)
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 1));


        /* MECHANISMS BUTTONS */

        // //coral outtaking
        // new Trigger(controller_two::getYButtonPressed)
        //     .onTrue(new CoralCommand(m_coralSubsystem, Constants.CORAL_OUTTAKING_SPEED));

        //coral stop
        // new Trigger(controller_two::getAButtonPressed)
        //     .onTrue(new CoralCommand(m_coralSubsystem, 0));

        // //coral intaking
        // new Trigger(controller_two::getXButtonPressed)
        // .onTrue(new CoralCommand(m_coralSubsystem, Constants.CORAL_INTAKING_SPEED));

        // coral pivot propped up
    //    new Trigger(controller_two::getXButtonPressed)
    //        .onTrue(new CoralPivotCommand(m_coralPivotSub, 30));

    
        // coral outtaking
        // new Trigger(controller_two::getYButtonPressed)
            // .onTrue(new StickCommand(m_stickSub, -0.5));

        // coral intaking
        // new Trigger(controller_two::getXButtonPressed)
        //     .onTrue(new StickCommand(m_stickSub, Constants.CORAL_INTAKING_SPEED));

        //algae stop
        // new Trigger(controller_two::getYButtonPressed)
        //     .onTrue(new StickCommand(m_stickSub, 0));

        // new Trigger(controller_two::getYButtonPressed)
        //     .onTrue(new StickPivotCommand(m_stickPivotSub, 30));
        // 

        // // coral shooter intaking
        // new Trigger(controller_two::getYButtonPressed)
        //     .onTrue(new CoralShooterCommand(m_coralShooterSub, 0.1));

        // // coral shooter outtaking
        // new Trigger(controller_two::getXButtonPressed)
        //     .onTrue(new CoralShooterCommand(m_coralShooterSub, -0.73));

        // // button to stop intake & outtake or climbing
        // new Trigger(controller_two::getBButtonPressed)
        //     .onTrue(new CoralShooterCommand(m_coralShooterSub, 0));
            // .onTrue(new ClimbingCommand(m_climbingSub, 0)); //off

        // new Trigger(controller_two::getBButtonPressed)
        //     .onTrue(new ElevatorCommand(m_elevatorSub, 36, 0));
        
        // new Trigger(controller_two::getAButtonPressed)
        //     .onTrue(new ElevatorCommand(m_elevatorSub, 0, 0));

        // TODO figure out how to use joystick to control elevator


        // climbing mechanism 
        // new Trigger(controller_two::getAButtonPressed)
        //     .onTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED));

        // reverse climbing
        // new Trigger(controller_two::getXButtonPressed) //outake
        //     .onTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED)); // TODO: figure out if this value needs to be different
        
        // new Trigger(controller_two::getRightBumperPressed)
        //     .whileTrue(new ElevatorCommand(m_elevatorSub, 45, 0)); // replace the height with the MAXIMUM height of the elevator

        // new Trigger(controller_two::getLeftBumperPressed)
        //     .whileTrue(new ElevatorCommand(m_elevatorSub, 0, 0));

        // stick pivot go to 45 deg
        new Trigger(controller_two::getAButtonPressed)
            .onTrue(new StickPivotCommand(m_stickPivotSub, 45)); //TODO: figure out angles
        

        //stick pivot go back down
        new Trigger(controller_two::getBButtonPressed)
            .onTrue(new StickPivotCommand(m_stickPivotSub, 0)); //TODO: figure out angles

        // new Trigger(controller_two::getXButtonPressed)
        //     .onTrue(new StickCommand(m_stickSub, 0.5));
        
        // new Trigger(controller_two::getYButtonPressed)
        //     .onTrue(new StickCommand(m_stickSub, 0));

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
