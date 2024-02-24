package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.OI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class PivotSubsystem{
    public TalonFX pivot;//public for testing
    // limit switches are true when not pressed (motor should run) and false when pressed (motor should stop)
    public DigitalInput speakerLimitSwitch;
    public DigitalInput ampLimitSwitch;

    private final double PIVOT_SPEED = 0.08;
    private final double MANUAL_SPEED = 0.06;
    
    public static enum PivotStates{
        SPEAKER,
        AMP,
        MANUAL_UP,
        MANUAL_DOWN,
        OFF;
    }


    private PivotStates pivotState;

    public PivotSubsystem(){
        pivot = new TalonFX(Constants.PIVOT_MOTOR_CAN_ID);
        speakerLimitSwitch = new DigitalInput(0); //check these ports
        ampLimitSwitch = new DigitalInput(7); 
        
        init();
    }
    
    public void init(){
        setState(PivotStates.OFF);
        pivot.setNeutralMode(NeutralMode.Brake);
    }

    public void periodic(){//limit switches true, then false when pressed
        System.out.println("CURRENT PIVOT STATE: " + pivotState);
        System.out.println("top limit switch: " + speakerLimitSwitch.get());
        System.out.println("bottom limit switch: " + ampLimitSwitch.get());
        if((pivotState == PivotStates.SPEAKER) && speakerLimitSwitch.get()){
            pivot.set(ControlMode.PercentOutput, PIVOT_SPEED);
        }else if((pivotState == PivotStates.AMP) && ampLimitSwitch.get()){
            pivot.set(ControlMode.PercentOutput, -PIVOT_SPEED);
        }else if((pivotState == PivotStates.MANUAL_UP) && speakerLimitSwitch.get()){
            pivot.set(ControlMode.PercentOutput, MANUAL_SPEED);
        }else if((pivotState == PivotStates.MANUAL_DOWN) && ampLimitSwitch.get()){
            pivot.set(ControlMode.PercentOutput, -MANUAL_SPEED);
        }else if(pivotState == PivotStates.OFF){
            pivot.set(ControlMode.PercentOutput, 0);
        }else{
            System.out.println("=========UNRECOGNIZED PIVOT STATE: " + pivotState.toString() + "========");
            pivotState = PivotStates.OFF;
            pivot.set(ControlMode.PercentOutput, 0);
        }
    }
   
    public void manual() {
        // TODO: when we know the max rotation of the pivot motor we need to intergrate that here 
        if(OI.getTwoRightAxis() > 0.2) {
            pivot.set(ControlMode.PercentOutput, 0.2);    
        } else if(OI.getTwoRightAxis() < - 0.2) {
            pivot.set(ControlMode.PercentOutput, - 0.2);  
        } else {
            pivot.set(ControlMode.PercentOutput, 0);
        }
    }

    public void setState(PivotStates newState) {
        pivotState = newState;
    }

}