package frc.robot.commands;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.PneumaticIntakeSubsystem;

public class PneumaticIntakeControlCommand extends InstantCommand {
    
    private final PneumaticIntakeSubsystem pneumaticIntakeSubsystem;

    public PneumaticIntakeControlCommand(PneumaticIntakeSubsystem pneumaticIntakeSubsystem) {
        this.pneumaticIntakeSubsystem = pneumaticIntakeSubsystem;
        
        addRequirements(pneumaticIntakeSubsystem);
    }

    @Override
    public void execute() {
        // toggles between pinch and release
        // if it is kForward or kOff, release
        // if it is kReverse AND has game piece, pinch
        // if it is kReverse BUT does not have game piece, release

        if ((pneumaticIntakeSubsystem.getSolenoidOne() == kForward) || (pneumaticIntakeSubsystem.getSolenoidOne() == kOff)){
            pneumaticIntakeSubsystem.release();
            System.out.println("RELEASING GAME PIECE");
        } else if ((pneumaticIntakeSubsystem.getSolenoidOne() == kReverse) && (pneumaticIntakeSubsystem.hasGamePiece())){
            pneumaticIntakeSubsystem.pinch();
            System.out.println("INTAKING GAME PIECE");
        } else {
            pneumaticIntakeSubsystem.release();
            System.out.println("RELEASING GAME PIECE");
        }
    }
}

