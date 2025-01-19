package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbingSubsystem extends SubsystemBase{
    private final TalonFX leftClimbingMotor;
    private final TalonFX rightClimbingMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    

    public ClimbingSubsystem() {
        leftClimbingMotor = new TalonFX(0); //TODO: replace that value
        rightClimbingMotor = new TalonFX(0);
    }

    public void setSpeed(double speed) {
        DutyCycleOut dutyCycleOut = new DutyCycleOut(speed);
        leftClimbingMotor.setControl(dutyCycleOut.withOutput(speed));
        rightClimbingMotor.setControl(dutyCycleOut.withOutput(speed));
    }
    
}
