package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgaeSubsystem extends SubsystemBase {
    TalonFX algaeMotor;
    TalonFX algaePivot;
    DutyCycleOut dutyCycleOut;

    public AlgaeSubsystem(){
        algaeMotor = new TalonFX(Constants.ALGAE_CAN_ID);
        algaePivot = new TalonFX(Constants.ALGAE_PIVOT_CAN_ID);
        dutyCycleOut = new DutyCycleOut(0);
    }

    public void setSpeed(double speed){
        algaeMotor.setControl(dutyCycleOut.withOutput(speed));
    }
}