package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingSubsystem extends SubsystemBase{
    private final TalonFX leftClimbingMotor;
    private final TalonFX rightClimbingMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    

    public ClimbingSubsystem() {
        leftClimbingMotor = new TalonFX(Constants.LEFT_CLIMBING_MOTOR_CAN_ID); //TODO: replace that value
        rightClimbingMotor = new TalonFX(Constants.RIGHT_CLIMBING_MOTOR_CAN_ID);
    }

    public void setSpeed(double speed) {
        DutyCycleOut dutyCycleOut = new DutyCycleOut(speed);
        leftClimbingMotor.setControl(dutyCycleOut.withOutput(speed));
        rightClimbingMotor.setControl(dutyCycleOut.withOutput(-speed)); 
    }
    
}
