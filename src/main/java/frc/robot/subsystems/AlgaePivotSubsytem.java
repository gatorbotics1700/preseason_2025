package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgaePivotSubsytem extends SubsystemBase {
    private final SparkMax algaePivotMotor;
    public AlgaePivotSubsytem(){
        algaePivotMotor = new SparkMax(Constants.ALGAE_PIVOT_CAN_ID, MotorType.kBrushless);
    }

    public void setSpeed(int speed){
        
    }

}
