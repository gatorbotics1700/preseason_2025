package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticIntakeSubsystem extends SubsystemBase {
    private DoubleSolenoid solenoidOne; 
    private DigitalInput beambreakSensor;


    public PneumaticIntakeSubsystem() {
        solenoidOne = new DoubleSolenoid (10, PneumaticsModuleType.CTREPCM, 4, 5); // from 2023
        beambreakSensor = new DigitalInput(Constants.BEAM_BREAK_RECEIVER); 
    }

    @Override
    public void periodic() { // called once per scheduler run
        
    }

    public void pinch(){
        solenoidOne.set(kForward); 
    }

    public void release(){
        solenoidOne.set(kReverse);
    }

    public boolean hasGamePiece(){
        return beambreakSensor.get();
    }

    public Value getSolenoidOne(){
        return solenoidOne.get();
    }
}
