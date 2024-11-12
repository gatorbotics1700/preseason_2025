package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BlinkinLEDController;
import frc.robot.subsystems.BlinkinLEDController.BlinkinPattern;


public class LEDsControlCommand extends InstantCommand{
    private final BlinkinLEDController blinkinLEDController;
    private final BlinkinPattern blinkinPattern;
    
    public LEDsControlCommand(BlinkinLEDController blinkinLEDController, BlinkinPattern blinkinPattern)  {
        this.blinkinLEDController = blinkinLEDController;
        this.blinkinPattern = blinkinPattern;
        addRequirements(blinkinLEDController);
        
    }

    @Override
    public void execute() {
        blinkinLEDController.setPattern(blinkinPattern);
        System.out.println("EXECUTE");
    }
}
