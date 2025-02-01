package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralSubsystem extends SubsystemBase {
    // This is the subsystem for the NEW coral intake/outtake mechanism
    private final SparkMax coralMotor;

    public CoralSubsystem() {
        coralMotor = new SparkMax(Constants.CORAL_CAN_ID, MotorType.kBrushless);
    }

    @Override
    public void periodic(){

    }

    public void setSpeed(double speed){
        coralMotor.set(speed);
    }
}
