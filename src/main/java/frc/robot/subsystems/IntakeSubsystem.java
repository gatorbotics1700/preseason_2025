package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Constants;

public class IntakeSubsystem {

    private TalonFX intakeMotor;

    private final double INTAKE_SPEED = 0.45; //build says this is optimal after testing, DO NOT CHANGE

    private IntakeStates intakeState;

    public static enum IntakeStates {
        INTAKING,
        OFF;
    }

    public IntakeSubsystem() {
        intakeMotor = new TalonFX(Constants.INTAKE_MOTOR_CAN_ID);
        init();
    }

    public void init() {
        System.out.println("Intake Init!");
        intakeMotor.setInverted(true); //sets it to default sending a piece up (counterclockwise)
        intakeMotor.setNeutralMode(NeutralMode.Coast); //coast mode bc so that it doesn't get stuck in the intake or transition
        setState(IntakeStates.OFF);
    }

    public void setState(IntakeStates newState) {
        intakeState = newState;
    }

    public void periodic() {
        System.out.println("CURRENT INTAKE STATE IS: " + intakeState);
        if(intakeState == IntakeStates.INTAKING) {
            intakeMotor.set(ControlMode.PercentOutput, INTAKE_SPEED);
        } else if (intakeState == IntakeStates.OFF){
            intakeMotor.set(ControlMode.PercentOutput, 0);
        } else {
            intakeMotor.set(ControlMode.PercentOutput, 0);
            System.out.println("====STATE UNRECOGNIZED==== current state: " + intakeState);
        }
    }

    public IntakeStates getCurrentIntakeState() {
        return intakeState;
    }
}
