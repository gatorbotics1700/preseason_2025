package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.PneumaticIntakeControlCommand;

public class OI {
    public static final CommandXboxController m_controller = new CommandXboxController(0);//main driver/driving controller
    public static final CommandXboxController m_controller_two = new CommandXboxController(1);//buttons/co-driver controller

    public static double getLeftAxis(){
        return m_controller_two.getLeftY();
    }

    public static double getRightAxis(){
        return m_controller_two.getRightY();
    }

    public OI(Command commandA) {
        // for pneumaticIntakeControl
        Trigger aButton = m_controller_two.a();
        aButton.onTrue(commandA); 
    }
}
