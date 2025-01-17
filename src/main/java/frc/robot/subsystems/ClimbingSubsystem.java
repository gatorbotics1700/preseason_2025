package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbingSubsystem extends SubsystemBase{
    private final TalonFX climbingMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    

    public ClimbingSubsystem() {
        climbingMotor = new TalonFX(0); //TODO: replace that value
    
    }

    public void setSpeed(double speed) {
        DutyCycleOut dutyCycleOut = new DutyCycleOut(speed);
        climbingMotor.setControl(dutyCycleOut.withOutput(speed));

    }
    
}
