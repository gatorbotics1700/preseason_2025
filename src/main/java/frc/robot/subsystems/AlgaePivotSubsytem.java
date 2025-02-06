package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgaePivotSubsytem extends SubsystemBase {
    //TODO: write periodic to show getPosition() on Smart Dashboard
    private final SparkMax motor;

    private final PIDController pidController;

    private DigitalInput limitSwitch;

    private static final double kP = 0.0; // TODO: change this value
    private static final double kI = 0.0; // TODO: change this value
    private static final double kD = 0.0; // TODO: change this value

    private final double DEADBAND = degreesToTicks(5); //in ticks TODO: test and change


    public AlgaePivotSubsytem(){
        motor = new SparkMax(Constants.ALGAE_PIVOT_CAN_ID, MotorType.kBrushless);
        pidController = new PIDController(kP, kI, kD);
        limitSwitch = new DigitalInput(Constants.ALGAE_LIMIT_SWITCH_PORT);
    }

    public void setPosition(double desiredTicks) {
        double currentTicks = getPosition();
        double error = desiredTicks - currentTicks;
        if(Math.abs(error) > DEADBAND){
            double output = pidController.calculate(currentTicks, desiredTicks);
            motor.set(output);
            //algaePivotMotor.setControl(positionVoltage.withPosition(desiredTicks));
        } else {
            motor.set(0);
        }
    }

    public double degreesToTicks(double desiredAngle) {
        return desiredAngle * Constants.ALGAE_PIVOT_TICKS_PER_DEGREE;
    }

    public void setSpeed(int speed) {
        motor.set(speed);
    }

    public double getPosition() {
        return motor.getEncoder().getPosition();
    }

    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }

}
