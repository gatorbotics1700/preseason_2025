package frc.robot.command;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightControlCommand extends InstantCommand {

    private final LimelightSubsystem limelightSubsystem;
    private final boolean ledOn;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, boolean ledOn) {
        this.limelightSubsystem = limelightSubsystem;
        this.ledOn = ledOn;

        addRequirements(limelightSubsystem);
    }
    
    @Override
    public void initialize() {
        if (ledOn) {
            limelightSubsystem.turnOnLED();
        } else {
            limelightSubsystem.turnOffLED();
        }
    }
}
