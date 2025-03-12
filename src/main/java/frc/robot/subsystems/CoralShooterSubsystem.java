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
    public final TalonFX topMotorLeft;
    public final TalonFX topMotorRight;
    public final TalonFX bottomMotor;
    public final TalonFX testMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    //private final DigitalInput beamBreakSensor;
    
    public CoralShooterSubsystem(){
        topMotorLeft = new TalonFX(Constants.SHOOTER_MOTOR_TOP_LEFT_CAN_ID, Constants.CANIVORE_BUS_NAME);
        topMotorRight = new TalonFX(Constants.SHOOTER_MOTOR_TOP_RIGHT_CAN_ID, Constants.CANIVORE_BUS_NAME);
        bottomMotor = new TalonFX(Constants.SHOOTER_MOTOR_BOTTOM_ID, Constants.CANIVORE_BUS_NAME);
        testMotor = new TalonFX(36, Constants.CANIVORE_BUS_NAME);

        topMotorRight.setInverted(true);
        bottomMotor.getConfigurator().apply(new TalonFXConfiguration()
            .withMotorOutput(new MotorOutputConfigs()
                .withInverted(InvertedValue.Clockwise_Positive))); // TODO: test if this works by deleting ELEVATOR_MOTOR_INVERT
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("bottom motor current",  getBottomMotorStatorCurrent());
        SmartDashboard.putNumber("top motor left current", getTopMotorLeftStatorCurrent());
    }

    public void setSpeed(double speed){
        topMotorLeft.setControl(dutyCycleOut.withOutput(speed));
        topMotorRight.setControl(dutyCycleOut.withOutput(speed));
        bottomMotor.setControl(dutyCycleOut.withOutput(speed));
       // System.out.println("motor stator current: " + motor.getStatorCurrent() + ", motor2 stator current: " + motor2.getStatorCurrent());
    }

    public void setTopMotorSpeed(double speed){
        topMotorLeft.setControl(dutyCycleOut.withOutput(speed));
        topMotorRight.setControl(dutyCycleOut.withOutput(speed));
    }

    public void setBottomMotorSpeed(double speed){
        bottomMotor.setControl(dutyCycleOut.withOutput(speed));
    }

    // public void setVoltage(double voltage){
    //     motor.setVoltage(voltage);
    //     motor2.setVoltage(voltage);
    // }

    public double getTopMotorLeftStatorCurrent(){
        return topMotorLeft.getStatorCurrent().getValueAsDouble();
    }

    public double getBottomMotorStatorCurrent(){
        return bottomMotor.getStatorCurrent().getValueAsDouble();
    }

    public double getTopMotorLeftSpeed(){
        return topMotorLeft.getVelocity().getValueAsDouble();
    }

    public double getBottomMotorSpeed(){
        return bottomMotor.getVelocity().getValueAsDouble();
    }

}
