package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralPivotSubsystem extends SubsystemBase {
    private final SparkMax motor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private final PIDController pidController;
    private final DigitalInput limitSwitch;

    private static final double kP = 0.005; // TODO: change this value
    private static final double kI = 0.0; // TODO: change this value
    private static final double kD = 0.0; // TODO: change this value

    private final double DEADBAND = degreesToTicks(5); //in ticks TODO: test and change

 
    public CoralPivotSubsystem () {
        motor = new SparkMax(Constants.CORAL_PIVOT_CAN_ID, MotorType.kBrushless);
        pidController = new PIDController(kP, kI, kD);
        limitSwitch = new DigitalInput(Constants.CORAL_LIMIT_SWITCH_PORT);
    }

    public void init(){

    }

    @Override
    public void periodic(){
        setPosition(0);
    }

    public void setSpeed(double speed){
        motor.set(speed);
    }

    public void setPosition(double desiredTicks){
        double currentTicks = getCurrentTicks();
        double error = desiredTicks - currentTicks;
        if(Math.abs(error) > DEADBAND){
            double output = pidController.calculate(currentTicks, desiredTicks);
            motor.set(output);
            //elevatorMotor.setControl(positionVoltage.withPosition(desiredTicks));
        }else{
            motor.set(0);
        }
    }

    public double getCurrentTicks(){
        return motor.getEncoder().getPosition() * Constants.NEO_TICKS_PER_REV;
    }

    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }
    
    public double degreesToTicks(double desiredAngle) {
        return desiredAngle * Constants.CORAL_PIVOT_TICKS_PER_DEGREE;
    }
}
