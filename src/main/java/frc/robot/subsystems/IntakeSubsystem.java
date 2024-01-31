package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

//import com.ctre.phoenix6.hardware.TalonFX;
//import com.ctre.phoenix6.signals.NeutralModeValue;

//import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.Constants;

public class IntakeSubsystem {

    public TalonFX intakeMotor;
    public TalonFX preTransitionMotor;
    public TalonFX transitionMotor;
    private static double motorSpeed = -0.6; //build says this is optimal after testing, DO NOT CHANGE
    private SensorSubsystem sensorSubsystem;

    public static enum IntakeStates {
        INTAKING,
        LOADING_TO_SHOOTER,
        OFF;
    }

    public static IntakeStates intakeStates = IntakeStates.OFF;

    public IntakeSubsystem(/*SensorSubsystem sensorSubsystem*/) {
        intakeMotor = new TalonFX(Constants.INTAKE_MOTOR_CAN_ID);
        preTransitionMotor = new TalonFX(Constants.PRE_TRANSITION_CAN_ID);
        transitionMotor = new TalonFX(Constants.TRANSITION_CAN_ID);
        //this.sensorSubsystem = sensorSubsystem;
        init();

    }

    public void init() {
        System.out.println("Intake Init!");
        intakeMotor.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        intakeMotor.setNeutralMode(NeutralMode.Brake); //brake mode so nothing slips = locks in place when not getting power
        preTransitionMotor.setInverted(false);
        preTransitionMotor.setNeutralMode(NeutralMode.Brake); //TODO check what direction we want motors to run
        transitionMotor.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        transitionMotor.setNeutralMode(NeutralMode.Brake); //brake mode so nothing slips = locks in place when not getting power
        setState(IntakeStates.OFF);
    }

    public void setState(IntakeStates newState){
        intakeStates = newState;
    }

    public void periodic() {
        if(intakeStates == IntakeStates.INTAKING) {
            intakeMotor.set(ControlMode.PercentOutput, motorSpeed);
            preTransitionMotor.set(ControlMode.PercentOutput, motorSpeed);
            transitionMotor.set(ControlMode.PercentOutput, motorSpeed);
            if(sensorSubsystem.getSeesNote()){
                setState(IntakeStates.OFF);
            }
        } else if (intakeStates == IntakeStates.OFF){
            intakeMotor.set(ControlMode.PercentOutput, 0);
            preTransitionMotor.set(ControlMode.PercentOutput, 0);
            transitionMotor.set(ControlMode.PercentOutput, 0);
        } else if (intakeStates == IntakeStates.LOADING_TO_SHOOTER){
            intakeMotor.set(ControlMode.PercentOutput, 0);
            preTransitionMotor.set(ControlMode.PercentOutput, 0);
            transitionMotor.set(ControlMode.PercentOutput, motorSpeed);
        } else {
            intakeMotor.set(ControlMode.PercentOutput, 0);
            preTransitionMotor.set(ControlMode.PercentOutput, 0);
            transitionMotor.set(ControlMode.PercentOutput, 0);
            System.out.println("=================UNKNOWN INTAKE STATE WHAT HAPPENED?!?!?!?=================");
            System.out.println("Current intake state: " + intakeStates);
        }

    }

}
