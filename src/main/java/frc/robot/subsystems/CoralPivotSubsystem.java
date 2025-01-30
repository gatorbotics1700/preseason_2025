package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralPivotSubsystem extends SubsystemBase {
    private final TalonFX coralPivotMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
 
    public CoralPivotSubsystem () {
        coralPivotMotor = new TalonFX(Constants.CORAL_PIVOT_CAN_ID);
    }

    @Override
    public void periodic(){

    }

    public void setSpeed(double speed){
        coralPivotMotor.setControl(dutyCycleOut.withOutput(speed));
    }
}
