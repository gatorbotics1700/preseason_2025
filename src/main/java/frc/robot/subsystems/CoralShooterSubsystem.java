package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralShooterSubsystem extends SubsystemBase{
    public final TalonFX coralShooterMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    
    public CoralShooterSubsystem(){
        coralShooterMotor = new TalonFX(0);//TODO: enter in can id later

    }

    @Override
    public void periodic(){

    }

    public void setSpeed(double speed){
        DutyCycleOut dutyCycleOut = new DutyCycleOut(speed);
        coralShooterMotor.setControl(dutyCycleOut.withOutput(speed));

    }
}
