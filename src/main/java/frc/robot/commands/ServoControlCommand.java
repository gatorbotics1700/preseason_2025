package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ServoSubsystem;

public class ServoControlCommand extends InstantCommand {
    private final ServoSubsystem servoSubsystem;
    private final double servoAngle;

    public ServoControlCommand(ServoSubsystem servoSubsystem, double servoAngle) {
        this.servoSubsystem = servoSubsystem;
        this.servoAngle = servoAngle;
        addRequirements(servoSubsystem);
    }

    @Override
    public void execute() {
        servoSubsystem.setAngle(20, false);
       
        System.out.println("EXECUTE");
    }

}