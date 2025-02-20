package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StickPivotSubsystem extends SubsystemBase {
    //TODO: write periodic to show getPosition() on Smart Dashboard
    private final TalonFX motor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private final PIDController pidController;
    private final DigitalInput limitSwitch;

    private static final double kP = 0.005; // TODO: change this value
    private static final double kI = 0.0; // TODO: change this value
    private static final double kD = 0.0001; // TODO: change this value

    private final double DEADBAND = degreesToTicks(3); //in ticks TODO: test and change


    public StickPivotSubsystem(){
        motor = new TalonFX(Constants.STICK_PIVOT_CAN_ID);
        motor.setNeutralMode(NeutralModeValue.Brake); //TODO: make sure brake mode works
        pidController = new PIDController(kP, kI, kD);
        limitSwitch = new DigitalInput(Constants.STICK_LIMIT_SWITCH_PORT);
    }

    public void init(){
        setPosition(0);
    }

    public void setPosition(double desiredTicks) {
        double currentTicks = getCurrentTicks();
        double error = desiredTicks - currentTicks;
        if(Math.abs(error) > DEADBAND){
            double output = pidController.calculate(currentTicks, desiredTicks);
            System.out.println("OUTPUT: " + output/20);
            motor.setControl(dutyCycleOut.withOutput(output/20));
            //algaePivotMotor.setControl(positionVoltage.withPosition(desiredTicks));
        } else {
            motor.setControl(dutyCycleOut.withOutput(0));
        }
    }

    public double degreesToTicks(double desiredAngle) {
        // System.out.println("*******" + desiredAngle + " degrees to ticks: " + desiredAngle * Constants.STICK_PIVOT_TICKS_PER_DEGREE);
        return desiredAngle * Constants.STICK_PIVOT_TICKS_PER_DEGREE;
    }

    public void setSpeed(int speed) {
        motor.setControl(dutyCycleOut.withOutput(speed));
    }

    public double getCurrentTicks() {
        return motor.getPosition().getValueAsDouble() * Constants.KRAKEN_TICKS_PER_REV;
    }

    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }
}
