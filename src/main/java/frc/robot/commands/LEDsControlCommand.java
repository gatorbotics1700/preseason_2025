package frc.robot.commands;

import edu.wpi.first.hal.can.CANStatus;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BlinkinLEDController;
import frc.robot.subsystems.BlinkinLEDController.BlinkinPattern;


public class LEDsControlCommand extends InstantCommand{
    private final BlinkinLEDController blinkinLEDController;
    private final BlinkinPattern blinkinPattern;
    private final BlinkinPattern errorPattern;
    
    public LEDsControlCommand(BlinkinLEDController blinkinLEDController, BlinkinPattern blinkinPattern, BlinkinPattern errorPattern)  {
        this.blinkinLEDController = blinkinLEDController;
        this.blinkinPattern = blinkinPattern;
        this.errorPattern = errorPattern;
        addRequirements(blinkinLEDController);
        
    }

    @Override
    public void execute() {
        blinkinLEDController.setPattern(blinkinPattern);
        System.out.println("EXECUTE");
        if (blinkinLEDController.getErrorCount()>0){
            blinkinLEDController.setPattern(errorPattern);
        }
    }

}
