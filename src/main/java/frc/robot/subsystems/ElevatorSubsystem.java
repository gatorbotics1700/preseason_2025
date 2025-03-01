package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class ElevatorSubsystem extends SubsystemBase {
    private TalonFX motor;
    private static DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    //private static PositionVoltage positionVoltage = new PositionVoltage(0);
    
    // private final DigitalInput colorSensor;
    private final DigitalInput topLimitSwitch;
    private final DigitalInput bottomLimitSwitch;

    private final PIDController elevatorPIDController;

    // TODO: test and change these values
    private static final double kP = 0.0005;
    private static final double kI = 0.0;
    private static final double kD = 0.00002;

    private static final double DEADBAND = 0.5 * Constants.ELEVATOR_TICKS_PER_INCH; // 1 inch in ticks; TODO: test and change


    public ElevatorSubsystem(){
        motor = new TalonFX(Constants.ELEVATOR_CAN_ID, Constants.CANIVORE_BUS_NAME);
        motor.setNeutralMode(NeutralModeValue.Brake);
        
        // colorSensor = new DigitalInput(0);
        elevatorPIDController = new PIDController(kP, kI, kD);
        topLimitSwitch = new DigitalInput(Constants.TOP_LIMIT_SWITCH_PORT);
        bottomLimitSwitch = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH_PORT);
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("top limit switch", topLimitSwitch.get());
        SmartDashboard.putBoolean("bottom limit switch", bottomLimitSwitch.get());
        SmartDashboard.putNumber("elevator current", getMotorStatorCurrent());
    }

    public void setPosition(double desiredTicks){
        double currentTicks = getCurrentTicks(); 
        double error = desiredTicks - currentTicks;
        if(Math.abs(error) > DEADBAND){
            double output = elevatorPIDController.calculate(currentTicks, desiredTicks);
            System.out.println("ELEVATOR CURRENT PEAKED");
            motor.setControl(dutyCycleOut.withOutput(Constants.ELEVATOR_INVERT * output));
            //elevatorMotor.setControl(positionVoltage.withPosition(desiredTicks));
        }else{
            motor.setControl(dutyCycleOut.withOutput(0));
        }
    }

    public void setSpeed(double speed){
        if(getMotorStatorCurrent()>1000){ // TODO: set current limit value
            System.out.println("ELEVATOR CURRENT PEAKED");
            speed = 0;
        }
        motor.setControl(dutyCycleOut.withOutput(speed));
    }

    public double getCurrentTicks(){ //getPosition() is in rotations so rotations * ticks per rev should give position in ticks
        return (motor.getPosition().getValueAsDouble() * Constants.KRAKEN_TICKS_PER_REV);
    }

    public double determineInchesToTicks(double desiredInches){
        return desiredInches * Constants.ELEVATOR_TICKS_PER_INCH;
    }

    // public boolean isColorSensed(){
    //     return colorSensor.get();
    // }
    public double getMotorStatorCurrent(){
        return motor.getStatorCurrent().getValueAsDouble();
    }

    public boolean atTopLimitSwitch(){
        return topLimitSwitch.get();
    }

    public boolean atBottomLimitSwitch(){
        return bottomLimitSwitch.get();
    }
}
