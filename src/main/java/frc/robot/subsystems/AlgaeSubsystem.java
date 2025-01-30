package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgaeSubsystem extends SubsystemBase {
    
    private final SparkMax algaeMotor;

    public AlgaeSubsystem(){
        algaeMotor = new SparkMax(Constants.ALGAE_CAN_ID, MotorType.kBrushless);
    }

    public void setSpeed(double speed){
        algaeMotor.set(speed);
    }
}