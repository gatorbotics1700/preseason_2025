package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;

public class CoralShooterSubsystem extends SubsystemBase{
    public final TalonFX motor;
    public final TalonFX motor2;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    //private final DigitalInput beamBreakSensor;
    
    public CoralShooterSubsystem(){
        motor = new TalonFX(Constants.CORAL_SHOOTER_CAN_ID);//TODO: enter in can id later
        motor2 = new TalonFX(34);
        //beamBreakSensor = new DigitalInput(0); //TODO: replace with beambreak receiver 
    }

    @Override
    public void periodic(){

    }

    public void setSpeed(double speed){
        motor.setControl(dutyCycleOut.withOutput(speed));
        motor2.setControl(dutyCycleOut.withOutput(speed));
    }

    // public boolean isBeamBroken() {
    //     return beamBreakSensor.get();
    // }
}
