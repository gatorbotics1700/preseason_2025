package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.subsystems.*;
//import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix6.signals.NeutralModeValue;


public class TransitionSubsystem {

    public TalonFX transitionMotor; 
    private TransitionStates transitionState;
    private SensorSubsystem sensorSubsystem;
    private Mechanisms mechanisms;

    public TransitionSubsystem(){
        transitionMotor = new TalonFX(Constants.TRANSITION_CAN_ID);
        init();
    }

    public static enum TransitionStates{
        OFF,
        ON;
    }

    public void init(){
        System.out.println("transition init");
        transitionMotor.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        transitionMotor.setNeutralMode(NeutralMode.Brake); //brake mode so nothing slips = locks in place when not getting power
        setState(TransitionStates.OFF);
    }

    public void periodic(){
        if (sensorSubsystem.seesNote == false){ 
            setState(TransitionStates.ON); //going into transition
            transitionMotor.set(ControlMode.PercentOutput, 0);
        }
        else if (sensorSubsystem.seesNote == true){ 
            setState(TransitionStates.OFF); //inside transition
            transitionMotor.set(ControlMode.PercentOutput, 0);
        }
        else {
            setState(TransitionStates.OFF);
            transitionMotor.set(ControlMode.PercentOutput, 0);
        }
    }

    public void setState(TransitionStates newState){
        transitionState = newState;
    }
}
