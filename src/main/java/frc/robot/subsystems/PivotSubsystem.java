package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.OI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class PivotSubsystem{
    private TalonFX pivot;//public for testing
    // limit switches are true when not pressed (motor should run) and false when pressed (motor should stop)
    private DigitalInput speakerLimitSwitch;
    private DigitalInput ampLimitSwitch;

    private final double PIVOT_SPEED = 0.12;
    private final double MANUAL_SPEED = 0.1;
    
    public static enum PivotStates{
        FIRST,
        SPEAKER,
        AMP,
        MANUAL,
        OFF;
    }


    private PivotStates pivotState;

    public PivotSubsystem(){
        pivot = new TalonFX(Constants.PIVOT_MOTOR_CAN_ID);
        speakerLimitSwitch = new DigitalInput(8);
        ampLimitSwitch = new DigitalInput(9); 
        init();
    }
    
    public void init(){
        setState(PivotStates.OFF);
        pivot.setNeutralMode(NeutralMode.Brake);
    }

    public void periodic(){//limit switches finally swapped to true when pressed, false when not
        System.out.println("CURRENT PIVOT STATE: " + pivotState);
        if((pivotState == PivotStates.SPEAKER) && !speakerLimitSwitch.get()){
            pivot.set(ControlMode.PercentOutput, PIVOT_SPEED);
        }else if((pivotState == PivotStates.AMP) && !ampLimitSwitch.get()){
            pivot.set(ControlMode.PercentOutput, -PIVOT_SPEED);
        }else if(pivotState == PivotStates.MANUAL){ //can be used to climb
            manual();
        }else if(pivotState == PivotStates.OFF){
            pivot.set(ControlMode.PercentOutput, 0);  
        }else{
            System.out.println("=========UNRECOGNIZED PIVOT STATE: " + pivotState.toString() + "========");
            pivotState = PivotStates.OFF;
            pivot.set(ControlMode.PercentOutput, 0);
        }
        //System.out.println("SPEAKER LIMIT SWITCH: " + speakerLimitSwitch.get());
        //System.out.println("AMP LIMIT SWITCH: " + ampLimitSwitch.get());
    }
   
    public void manual() {
        //System.out.println("+++++++++++IN MANUAL++++++++++");
        if((OI.getCodriverRightAxis() > 0.2) && !speakerLimitSwitch.get()) {
            //System.out.println("TOWARDS SPEAKER");
            pivot.set(ControlMode.PercentOutput, MANUAL_SPEED);    
        } else if((OI.getCodriverRightAxis() < - 0.2) && !ampLimitSwitch.get()) {
            //System.out.println("TOWARDS AMP");
            pivot.set(ControlMode.PercentOutput, -MANUAL_SPEED);  
        } else {
            pivot.set(ControlMode.PercentOutput, 0); 
        }
    }

    public void setState(PivotStates newState) {
        pivotState = newState;
    }

    public PivotStates getState(){
        return pivotState;
    }

    public boolean getSpeakerLimitSwitch(){ // false when NOT pressed, true when pressed
        return speakerLimitSwitch.get();
    }

    public boolean getAmpLimitSwitch(){ // false when NOT pressed, true when pressed
        return ampLimitSwitch.get();
    }

}