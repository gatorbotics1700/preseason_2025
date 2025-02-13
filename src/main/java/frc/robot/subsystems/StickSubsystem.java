package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StickSubsystem extends SubsystemBase {
    
    private final SparkMax motor;

    public StickSubsystem(){
        motor = new SparkMax(Constants.STICK_CAN_ID, MotorType.kBrushless);
    }

    public void setSpeed(double speed){
        motor.set(speed);
    }
}