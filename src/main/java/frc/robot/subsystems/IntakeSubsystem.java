package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants;

public class IntakeSubsystem {

    private TalonFX intakeMotor;

    private final double INTAKE_SPEED = 0.6; //0.5; //0.35; //used to be 0.45, changed to 0.35 on 02/26 for testing//build says this is optimal after testing, DO NOT CHANGE

    private IntakeStates intakeState;

    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

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
        intakeMotor.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        intakeMotor.setNeutralMode(NeutralModeValue.Coast); //coast mode bc so that it doesn't get stuck in the intake or transition
        setState(IntakeStates.OFF);
    }

    public void setState(IntakeStates newState) {
        intakeState = newState;
    }

    public void periodic() {
        System.out.println("CURRENT INTAKE STATE IS: " + intakeState);
        if(intakeState == IntakeStates.INTAKING) {
            //System.out.println("***********INTAKING*************");
            intakeMotor.setControl(dutyCycleOut.withOutput(INTAKE_SPEED));
        } else if (intakeState == IntakeStates.OFF){
            intakeMotor.setControl(dutyCycleOut.withOutput(0));
        } else {
            intakeMotor.setControl(dutyCycleOut.withOutput(0));
            System.out.println("====STATE UNRECOGNIZED==== current state: " + intakeState);
        }
    }

    public IntakeStates getCurrentIntakeState() {
        return intakeState;
    }

    public void testIntake(){
        intakeMotor.setControl(dutyCycleOut.withOutput(0.35));
    }
}
