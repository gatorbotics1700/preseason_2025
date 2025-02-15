package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingSubsystem extends SubsystemBase{
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    
    private final TalonFX motor;

    public ClimbingSubsystem() {
        motor = new TalonFX(Constants.CLIMBING_MOTOR_CAN_ID);
        motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setSpeed(double speed) {
        motor.setControl(dutyCycleOut.withOutput(-speed)); 
    }
    
}
