package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFXS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StickSubsystem extends SubsystemBase {
    
    private final TalonFXS motor;

    public StickSubsystem(){
        motor = new TalonFXS(Constants.STICK_CAN_ID);
    }

    public void setSpeed(double speed){
        motor.set(speed);
    }
}