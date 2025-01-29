package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class ElevatorSubsystem extends SubsystemBase {
    
    public TalonFX elevatorMotor;
    private static DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private static PositionVoltage positionVoltage = new PositionVoltage(0);
    
    private static final double DEADBAND = 5000;//in ticks TODO: test and change

    private final DigitalInput colorSensor;

    private final PIDController elevatorPidController;

    private static final double kP = 0.0; // TODO: change this value
    private static final double kI = 0.0; // TODO: change this value
    private static final double kD = 0.0; // TODO: change this value
    
    private static final int kIzone = 0; // TODO: change this value
    private static final double kPeakOutput = 0.0; // TODO: change this value
    
    private static final int SLOT_IDX = 0;
    private static final int PID_LOOP_IDX = 0;
    private static final int CONFIG_TIMEOUT_MS = 0;
    //add height and stuff

    public ElevatorSubsystem(){
        elevatorMotor = new TalonFX(Constants.ELEVATOR_CAN_ID);
        colorSensor = new DigitalInput(0);
        elevatorPidController = new PIDController(kP, kI, kD);
    }

    public void setPosition(double desiredTicks){
        double currentTicks = getPosition();
        double error = desiredTicks - currentTicks;
        if(Math.abs(error) > DEADBAND){
            double output = elevatorPidController.calculate(currentTicks, desiredTicks);
            elevatorMotor.setControl(dutyCycleOut.withOutput(output));
            //elevatorMotor.setControl(positionVoltage.withPosition(desiredTicks));
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

    public boolean isColorSensed(){
        return colorSensor.get();
    }


}
