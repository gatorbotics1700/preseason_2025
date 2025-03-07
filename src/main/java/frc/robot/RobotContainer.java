package frc.robot;

// import frc.robot.commands.TestDriveCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.ScoreCommands;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.TestDriveCommand;
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

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.pathplanner.lib.auto.NamedCommands;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    
    private final XboxController controller = new XboxController(0);
    private final XboxController controller_two = new XboxController(1); // TODO eventually delete this
    
    private final GenericHID buttonBoard1A = new GenericHID(1);
    private final GenericHID buttonBoard1B = new GenericHID(2);

    private final GenericHID buttonBoard2A = new GenericHID(3);
    private final GenericHID buttonBoard2B = new GenericHID(4);

    private final SendableChooser<Command> autoChooser;
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
    private static final ClimbingSubsystem m_climbingSub = new ClimbingSubsystem();
    // private static final ElevatorSubsystem m_elevatorSub = new ElevatorSubsystem();
    // private static final StickPivotSubsystem m_stickPivotSub = new StickPivotSubsystem();
    // private static final StickSubsystem m_stickSub = new StickSubsystem();
    private static final CoralShooterSubsystem m_coralShooterSub = new CoralShooterSubsystem();
    private static final ScoreCommands m_scoreCommands = new ScoreCommands();

    public RobotContainer() {
        // NamedCommands.registerCommand("Score Trough", ScoreCommands.Level(1, m_elevatorSub, m_stickSub, m_stickPivotSub));
        // NamedCommands.registerCommand("Score L2", ScoreCommands.Level(2, m_elevatorSub, m_stickSub, m_stickPivotSub));
        // NamedCommands.registerCommand("Score L3", ScoreCommands.Level(3, m_elevatorSub, m_stickSub, m_stickPivotSub));
        // NamedCommands.registerCommand("Score L4", ScoreCommands.Level(4, m_elevatorSub, m_stickSub, m_stickPivotSub));

        // NamedCommands.registerCommand("Score Coral Shooter", ScoreCommands.Shoot(m_coralShooterSub));
        // NamedCommands.registerCommand("Intake Coral Shooter", new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_SPEED));

        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::setSlowDrive));

        //pipeline buttons
        // new Trigger(controller::getAButtonPressed)
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller)); // id 6,19 A
        // new Trigger(controller::getBButtonPressed)
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller)); // id 7,18,10,21 B
        // new Trigger(controller::getXButtonPressed)
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller)); // id 8,17 X
        // new Trigger(controller::getYButtonPressed)
        //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller)); // id 9,22 Y
    
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

        // coral shooter intaking
        new Trigger(controller_two::getAButtonPressed)
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_SPEED));

        // coral shooter outtaking
        new Trigger(controller_two::getBButtonPressed)
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_L4_SHOOTING_SPEED));

        // coral shooter vomit and intake
        new Trigger(controller_two::getYButtonPressed)
            .onTrue(VomitAndIntake(m_coralShooterSub));

        // coral shooter stop
        new Trigger(controller_two::getXButtonPressed)
            .onTrue(new CoralShooterCommand(m_coralShooterSub, 0));

        new Trigger(controller_two::getRightBumperButtonPressed)
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_TROUGH_SHOOTING_SPEED));

        // new Trigger(controller_two::getXButtonPressed)
        //     .onTrue(new ElevatorCommand(m_elevatorSub, 16, 0));
        
        // new Trigger(controller_two::getYButtonPressed)
        //     .onTrue(new ElevatorCommand(m_elevatorSub, 0, 0));

        // TODO figure out how to use joystick to control elevator


        // climbing mechanism 
        // new Trigger(controller_two::getAButtonPressed)
        //     .onTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED));

        // // reverse climbing
        // new Trigger(controller_two::getBButtonPressed)//outake
        //     .onTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED)); // TODO: figure out if this value needs to be different
        
        // new Trigger(controller_two::getXButtonPressed)
        //     .onTrue(new ClimbingCommand(m_climbingSub, 0));


        /* CO-DRIVER BUTTON BOARD 1 BUTTONS */

        new Trigger(()->buttonBoard1A.getRawButtonPressed(1))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller));

        new Trigger(()->buttonBoard1A.getRawButtonPressed(2))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller));

        new Trigger(()->buttonBoard1A.getRawButtonPressed(3))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller));

        new Trigger(()->buttonBoard1A.getRawButtonPressed(4))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller));

        new Trigger(()->buttonBoard1A.getRawButtonPressed(5))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller));

        new Trigger(()->buttonBoard1A.getRawButtonPressed(6))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller));

        new Trigger(()->buttonBoard1B.getRawButtonPressed(1))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller));

        new Trigger(()->buttonBoard1B.getRawButtonPressed(2))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller));

        new Trigger(()->buttonBoard1B.getRawButtonPressed(3))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller));

        new Trigger(()->buttonBoard1B.getRawButtonPressed(4))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller));

        new Trigger(()->buttonBoard1B.getRawButtonPressed(5))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller));

        new Trigger(()->buttonBoard1B.getRawButtonPressed(6))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller));



        /* */

        
        /* CO-DRIVER BUTTON BOARD 2 BUTTONS */

        // climb
        new Trigger(()->buttonBoard2A.getRawButtonPressed(1))
            .whileTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED));
            
            
        // detach
        new Trigger(()->buttonBoard2B.getRawButtonPressed(2))
           .whileTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED));

        // lining up to intake
        new Trigger(()->buttonBoard2B.getRawButtonPressed(2))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 8, controller));

        // lining up to with reef to score trough
        new Trigger(()->buttonBoard2B.getRawButtonPressed(3))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller));
        
        new Trigger(()->buttonBoard2B.getRawButtonPressed(4))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller));

        new Trigger(()->buttonBoard2B.getRawButtonPressed(5))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller));

        new Trigger(()->buttonBoard2B.getRawButtonPressed(6))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller));

        new Trigger(()->buttonBoard2A.getRawButtonPressed(3))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller));

        new Trigger(()->buttonBoard2A.getRawButtonPressed(2))
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller));

        // vomit
        new Trigger(()->buttonBoard2B.getRawButtonPressed(7))
            .onTrue(VomitAndIntake(m_coralShooterSub)); 

        // score trough
        new Trigger(()->buttonBoard2B.getRawButtonPressed(8))
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_TROUGH_SHOOTING_SPEED));

        // score L4
        new Trigger(()->buttonBoard2A.getRawButtonPressed(4))
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_L4_SHOOTING_SPEED));

        // intake
        new Trigger(()->buttonBoard2A.getRawButtonPressed(5))
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_SPEED));
        

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

    public static Command VomitAndIntake(CoralShooterSubsystem coralShooterSubsystem){
        System.out.println("VOMITING CORAL AND INTAKING");
        return new CoralShooterCommand(coralShooterSubsystem, 0)
        .andThen(new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_VOMIT_SPEED))
        .andThen(new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_INTAKING_SPEED));
    }
    
    // public static Command scoreTrough(CoralShooterSubsystem coralShooterSubsystem){
    //     System.out.println("SCORING TROUGH");
    //     return new CoralShooterCommand(coralShooterSubsystem, 0.5);
    // }
    // ^ DELETE UNLESS ANY OTHER COMMANDS ARE NEEDED
}
