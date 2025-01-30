package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralPivotSubsystem extends SubsystemBase {
    private final TalonFX coralPivotMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private final PIDController coralPivotPIDController;

    private static final double kP = 0.0; // TODO: change this value
    private static final double kI = 0.0; // TODO: change this value
    private static final double kD = 0.0; // TODO: change this value

    private static final double DEADBAND = 5000; //in ticks TODO: test and change

 
    public CoralPivotSubsystem () {
        coralPivotMotor = new TalonFX(Constants.CORAL_PIVOT_CAN_ID);
        coralPivotPIDController = new PIDController(kP, kI, kD);
    }

    @Override
    public void periodic(){

    }

    public void setSpeed(double speed){
        coralPivotMotor.setControl(dutyCycleOut.withOutput(speed));
    }

    public void setPosition(double desiredTicks){
        double currentTicks = getPosition();
        double error = desiredTicks - currentTicks;
        if(Math.abs(error) > DEADBAND){
            double output = coralPivotPIDController.calculate(currentTicks, desiredTicks);
            coralPivotMotor.setControl(dutyCycleOut.withOutput(output));
            //elevatorMotor.setControl(positionVoltage.withPosition(desiredTicks));
        }else{
            coralPivotMotor.setControl(dutyCycleOut.withOutput(0));
        }
    }

    public double getPosition(){
        return coralPivotMotor.getPosition().getValueAsDouble();
    }
}
