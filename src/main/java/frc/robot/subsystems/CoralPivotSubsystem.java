package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralPivotSubsystem extends SubsystemBase {
    private final SparkMax coralPivotMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private final PIDController coralPivotPIDController;
    private final DigitalInput coralLimitSwitch;

    private static final double kP = 0.0; // TODO: change this value
    private static final double kI = 0.0; // TODO: change this value
    private static final double kD = 0.0; // TODO: change this value

    private final double DEADBAND = degreesToTicks(5); //in ticks TODO: test and change

 
    public CoralPivotSubsystem () {
        coralPivotMotor = new SparkMax(Constants.CORAL_PIVOT_CAN_ID, MotorType.kBrushless);
        coralPivotPIDController = new PIDController(kP, kI, kD);
        coralLimitSwitch = new DigitalInput(Constants.CORAL_LIMIT_SWITCH_PORT);
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

    public boolean getCoralLimitSwitch() {
        return coralLimitSwitch.get();
    }
    
    public double degreesToTicks(double desiredAngle) {
        return desiredAngle * Constants.CORAL_PIVOT_TICKS_PER_DEGREE;
    }
}
