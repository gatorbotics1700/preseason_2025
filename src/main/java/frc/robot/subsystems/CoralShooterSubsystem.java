package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;

public class CoralShooterSubsystem extends SubsystemBase{
    public final TalonFX motor; // top motor
    public final TalonFX motor2; // bottom motor
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    //private final DigitalInput beamBreakSensor;
    
    public CoralShooterSubsystem(){
        motor = new TalonFX(Constants.SHOOTER_MOTOR_CAN_ID, Constants.CANIVORE_BUS_NAME);
        motor2 = new TalonFX(Constants.SHOOTER_MOTOR2_CAN_ID, Constants.CANIVORE_BUS_NAME);

        motor2.getConfigurator().apply(new TalonFXConfiguration()
            .withMotorOutput(new MotorOutputConfigs()
                .withInverted(InvertedValue.Clockwise_Positive))); // TODO: test if this works by deleting ELEVATOR_MOTOR_INVERT
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("motor two current",  motor.getStatorCurrent().getValueAsDouble());
    }

    // public void setSpeed(double speed){
    //     motor.setControl(dutyCycleOut.withOutput(speed));
    //     motor2.setControl(dutyCycleOut.withOutput(speed));
    //    // System.out.println("motor stator current: " + motor.getStatorCurrent() + ", motor2 stator current: " + motor2.getStatorCurrent());
    // }

    public void setVoltage(double voltage){
        motor.setVoltage(voltage);
        motor2.setVoltage(voltage);
    }

    public double getMotor2StatorCurrent(){
        return motor2.getStatorCurrent().getValueAsDouble();
    }

    public double getMotorStatorCurrent(){
        return motor.getStatorCurrent().getValueAsDouble();
    }
}
