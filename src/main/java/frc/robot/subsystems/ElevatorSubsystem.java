package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
    public TalonFX elevatorMotor;
    private static PositionVoltage positionVoltage = new PositionVoltage(0);

    public ElevatorSubsystem(){
        elevatorMotor = new TalonFX(Constants.ELEVATOR_CAN_ID);
    }

    public void setPosition(double position){
        elevatorMotor.setControl(positionVoltage.withPosition(position));
    }
}
