package frc.robot;

// import frc.robot.commands.TestDriveCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.AlgaePivotCommand;
import frc.robot.commands.AlgaeCommand;
import frc.robot.commands.ClimbingCommand;
import frc.robot.commands.CoralPivotCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.subsystems.AlgaePivotSubsystem;
import frc.robot.subsystems.AlgaeSubsystem;
//import frc.robot.commands.CoralShooterCommand;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.CoralPivotSubsystem;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.commands.CoralCommand;

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
    private static final CoralPivotSubsystem m_coralPivotSub = new CoralPivotSubsystem();
    private static final AlgaePivotSubsystem m_algaePivotSub = new AlgaePivotSubsystem();
    private static final CoralSubsystem m_coralSubsystem = new CoralSubsystem();
    private static final AlgaeSubsystem m_algaeSub = new AlgaeSubsystem();



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
        // new Trigger(controller_two::getXButtonPressed)
        //     .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_SPEED));

        // // coral shooter outtaking
        // new Trigger(controller_two::getYButtonPressed)
        //      .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_OUTTAKING_SPEED));

        // //coral outtaking
        new Trigger(controller_two::getYButtonPressed)
            .onTrue(new CoralCommand(m_coralSubsystem, Constants.CORAL_OUTTAKING_SPEED));

        //coral stop
        new Trigger(controller_two::getAButtonPressed)
            .onTrue(new CoralCommand(m_coralSubsystem, 0));

        // //coral intaking
        // new Trigger(controller_two::getXButtonPressed)
        // .onTrue(new CoralCommand(m_coralSubsystem, Constants.CORAL_INTAKING_SPEED));

        // //coral stop
        // new Trigger(controller_two::getBButtonPressed)
        // .onTrue(new CoralCommand(m_coralSubsystem, 0));

//algae outtaking
// new Trigger(controller_two::getYButtonPressed)
// .onTrue(new AlgaeCommand(m_algaeSub, -0.5));

// //algae intaking
// new Trigger(controller_two::getXButtonPressed)
// .onTrue(new AlgaeCommand(m_algaeSub, -Constants.CORAL_INTAKING_SPEED));

// //algae stop
// new Trigger(controller_two::getBButtonPressed)
// .onTrue(new AlgaeCommand(m_algaeSub, 0));

        //button to stop intake & outtake or climbing
        //new Trigger(controller_two::getBButtonPressed)
            //.onTrue(new CoralShooterCommand(m_coralShooterSub, 0));
            // .onTrue(new ClimbingCommand(m_climbingSub, 0)); //off
        
       // elevator button
        //  new Trigger(controller_two::getAButtonPressed)  
        //      .onTrue(new ElevatorCommand(m_elevatorSub, 0));


        // new Trigger(controller_two::getBButtonPressed)
        //     .onTrue(new ElevatorCommand(m_elevatorSub, 4));
        // new Trigger(controller_two::getAButtonPressed)
        //     .onTrue(new ElevatorCommand(m_elevatorSub, 0));

        // TODO figure out how to use joystick to control elevator - see 2023 code also requires using speed in ElevatorCommand
        
            // coral pivot propped up
    //    new Trigger(controller_two::getXButtonPressed)
    //        .onTrue(new CoralPivotCommand(m_coralPivotSub, 30));

        // new Trigger(controller_two::getYButtonPressed)
        //     .onTrue(new AlgaePivotCommand(m_algaePivotSub, 30));
        // 
        // climbing mechanism 
        // new Trigger(controller_two::getAButtonPressed)
        //     .onTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED)); //intakr

        // reverse climbing
        // new Trigger(controller_two::getXButtonPressed) //outake
        //     .onTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED)); // TODO: figure out if this value needs to be different
        
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
