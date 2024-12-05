package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.DutyCycleOut;

public class MotorSubsystem extends SubsystemBase {
    private final DutyCycleOut motorDutyCycleOut = new DutyCycleOut(0);

    private TalonFX motor;

public MotorSubsystem(){
    motor = new TalonFX(32)
}
    
}

