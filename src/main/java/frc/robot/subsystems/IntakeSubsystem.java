package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants;

public class IntakeSubsystem {

    public TalonFX intakeMotor;
    public TalonFX transitionMotor; 
    // TODO: Patricia says to set motors to coast

    private final double INTAKE_SPEED = 0.45; //build says this is optimal after testing, DO NOT CHANGE

    private SensorSubsystem sensorSubsystem;
    private IntakeStates intakeState;

    public static enum IntakeStates {
        INTAKING,
        OFF;
    }

    //public static IntakeStates intakeStates = IntakeStates.OFF;

    public IntakeSubsystem(/*SensorSubsystem sensorSubsystem*/) {
        intakeMotor = new TalonFX(Constants.INTAKE_MOTOR_CAN_ID);
        transitionMotor = new TalonFX(Constants.PRE_TRANSITION_CAN_ID);
        //this.sensorSubsystem = sensorSubsystem;
        init();
    }

    public void init() {
        System.out.println("Intake Init!");
        intakeMotor.setInverted(true); //sets it to default sending a piece up (counterclockwise)
        intakeMotor.setNeutralMode(NeutralMode.Coast); //brake mode so nothing slips = locks in place when not getting power
        transitionMotor.setInverted(true);
        transitionMotor.setNeutralMode(NeutralMode.Coast); //TODO check what direction we want motors to run
        setState(IntakeStates.OFF);
    }

    public void setState(IntakeStates newState) {
        intakeState = newState;
    }

    public void periodic() {
        System.out.println("CURRENT INTAKE STATE IS: " + intakeState);
        if(intakeState == IntakeStates.INTAKING) {
            intakeMotor.set(ControlMode.PercentOutput, INTAKE_SPEED);
            transitionMotor.set(ControlMode.PercentOutput, INTAKE_SPEED);
        } else if (intakeState == IntakeStates.OFF){
            intakeMotor.set(ControlMode.PercentOutput, 0);
            transitionMotor.set(ControlMode.PercentOutput, 0);
        } else {
            intakeMotor.set(ControlMode.PercentOutput, 0);
            transitionMotor.set(ControlMode.PercentOutput, 0);
            System.out.println("====STATE UNRECOGNIZED==== current state: " + intakeState);
        }
    }

    public IntakeStates getCurrentIntakeState() {
        return intakeState;
    }

    public SensorSubsystem getSensorSubsystem(){
        return sensorSubsystem;
    }

    public void setIntakeState(IntakeStates newIntakeState){
        intakeState = newIntakeState;
    }
}
