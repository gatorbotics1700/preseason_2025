package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXSConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.signals.ExternalFeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.MotorArrangementValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StickSubsystem extends SubsystemBase {
    
    private final TalonFXS motor;

    public StickSubsystem(){
        motor = new TalonFXS(Constants.STICK_CAN_ID, Constants.CANIVORE_BUS_NAME);
        TalonFXSConfiguration toConfigure = new TalonFXSConfiguration();
        toConfigure.Commutation.MotorArrangement = MotorArrangementValue.Minion_JST;
        toConfigure.ExternalFeedback.ExternalFeedbackSensorSource = ExternalFeedbackSensorSourceValue.Quadrature;
        motor.getConfigurator().apply(toConfigure);
    }

    public void setSpeed(double speed){
        motor.set(speed);
    }

    public double getMotorStatorCurrent(){
        return motor.getStatorCurrent().getValueAsDouble();
    }
}