package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
    
    public TalonFX elevatorMotor;
    private static DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private static PositionVoltage positionVoltage = new PositionVoltage(0);
    
    private static final double DEADBAND = 5000;//in ticks TODO: test and change

    private static final double kP = 0.0; // TODO: change this value
    private static final double kI = 0.0; // TODO: change this value
    private static final double kD = 0.0; // TODO: change this value
    private static final int kIzone = 0; // TODO: change this value
    private static final double kPeakOutput = 0.0; // TODO: change this value

    public ElevatorSubsystem(){
        elevatorMotor = new TalonFX(Constants.ELEVATOR_CAN_ID);
    }

    public void setPosition(double desiredTicks){
        if(Math.abs(desiredTicks - getPosition()) > DEADBAND){
            elevatorMotor.setControl(positionVoltage.withPosition(desiredTicks));
        }else{
            elevatorMotor.setControl(dutyCycleOut.withOutput(0));
        }
    }

    public double getPosition(){
        return elevatorMotor.getPosition().getValueAsDouble();
    }

    public double determineInchesToTicks(double desiredInches){
        return desiredInches * Constants.ELEVATOR_TICKS_PER_INCH;
    }


}
