package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.OI;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class PivotSubsystem{
    public TalonFX pivot;//public for testing
    public DigitalInput topLimitSwitch;
    public DigitalInput bottomLimitSwitch;


    private final double PIVOT_SPEED = 0.1;
    

    public static enum PivotStates{
        SPEAKER,
        AMP,
        OFF;
    }


    private PivotStates pivotState;

    public PivotSubsystem(){
        pivot = new TalonFX(Constants.PIVOT_MOTOR_CAN_ID);
        topLimitSwitch = new DigitalInput(0); //check these ports
        bottomLimitSwitch = new DigitalInput(7); 
    }
    
    public void init(){
        pivotState = PivotStates.AMP;
    }

    public void periodic(){//limit switches true, then false when pressed
        System.out.println("CURRENT PIVOT STATE: " + pivotState);
        if((pivotState == PivotStates.SPEAKER) && topLimitSwitch.get()){
            System.out.println("in periodic 1");
            pivot.set(ControlMode.PercentOutput, PIVOT_SPEED);
        }else if((pivotState == PivotStates.AMP) && bottomLimitSwitch.get()){
            pivot.set(ControlMode.PercentOutput, -PIVOT_SPEED);
            System.out.println("in periodic 2");
        }else{
            if(pivotState == PivotStates.AMP && !bottomLimitSwitch.get()){
                pivotState = PivotStates.SPEAKER;
            }
            
            if(pivotState == PivotStates.SPEAKER && !topLimitSwitch.get()){
                pivotState = PivotStates.AMP;
            }
            //pivotState = PivotStates.OFF;
            //pivot.set(ControlMode.PercentOutput, 0);
            System.out.println("in periodic 3");
            System.out.println("top limit switch: " + topLimitSwitch.get());
            System.out.println("bottom limit switch: " + bottomLimitSwitch.get());
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