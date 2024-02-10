package frc.robot.subsystems;

import static frc.robot.Constants.TICKS_PER_REV;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.subsystems.IntakeSubsystem.IntakeStates;
import frc.robot.subsystems.Mechanisms.MechanismStates;
import frc.robot.Constants;
import frc.robot.OI;

import edu.wpi.first.wpilibj.DigitalInput; 


public class ShooterSubsystem {
    
    private TalonFX high; 
    private TalonFX mid;
    private TalonFX low;
    private TalonFX pivot;
    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch; 

    private final double AMP_SPEED = 0.2;
    private final double HIGH_SPEAKER_SPEED = 0.8;
    private final double MID_SPEAKER_SPEED = 0.7;
    private final double AMP_ANGLE = 85.0; // TODO: this is a placeholder value, please test
    private final double SPEAKER_ANGLE = 45.0; // TODO: this is a placeholder value, please test
    private final double PIVOT_TICKS_PER_DEGREE = (Constants.TICKS_PER_REV * Constants.REVS_PER_ROTATION)/180.0;
    private final double PIVOT_DEADBAND_TICKS = 5000;



    public static enum ShooterStates {
        OFF,
        INTAKING,
        AMP_HOLDING,
        SPEAKER_HOLDING,
        AMP,
        SPEAKER; 
    }

    public static enum LimitSwitchStates{
        TOO_HIGH, TOO_LOW, CORRECT_ANGLE; 
    }

    private ShooterStates currentShooterState; //REVIEW: previously initialized to ShooterStates.AMP
    public LimitSwitchStates limitSwitchState; 

    
    public ShooterSubsystem() {
        high = new TalonFX(Constants.SHOOTER_HIGH_CAN_ID);
        mid = new TalonFX(Constants.SHOOTER_MID_CAN_ID);
        low = new TalonFX(Constants.AMP_MOTOR_CAN_ID);
        pivot = new TalonFX(Constants.PIVOT_MOTOR_CAN_ID);
        topLimitSwitch = new DigitalInput(0); //check these ports
        bottomLimitSwitch = new DigitalInput(1); 
        init();
    }

    public void init(){
        currentShooterState = ShooterStates.OFF;
    }

    public void periodic(){
        System.out.println("CURRENT SHOOTER STATE: " + currentShooterState);
        angleLimit();
        System.out.println("CURRENT ANGLE STATE: " + limitSwitchState);
        if (currentShooterState == ShooterStates.INTAKING){
            low.set(ControlMode.PercentOutput, AMP_SPEED);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);

        }else if (currentShooterState == ShooterStates.AMP_HOLDING) {
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);
            double desiredTicks = determineRightTicks(AMP_ANGLE);
            setPivot(desiredTicks);

        } else if(currentShooterState == ShooterStates.SPEAKER_HOLDING){
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);
            double desiredTicks = determineRightTicks(SPEAKER_ANGLE);
            setPivot(desiredTicks);

        }else if(currentShooterState == ShooterStates.AMP){//check negative signs here
            low.set(ControlMode.PercentOutput, AMP_SPEED);
            high.set(ControlMode.PercentOutput, -AMP_SPEED);
            mid.set(ControlMode.PercentOutput, AMP_SPEED);

        }else if(currentShooterState == ShooterStates.SPEAKER){//check negative signs here
            low.set(ControlMode.PercentOutput, MID_SPEAKER_SPEED);
            high.set(ControlMode.PercentOutput, HIGH_SPEAKER_SPEED);
            mid.set(ControlMode.PercentOutput, -MID_SPEAKER_SPEED);

        }else if(currentShooterState == ShooterStates.OFF){
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);

        }else{
            low.set(ControlMode.PercentOutput, 0);
            high.set(ControlMode.PercentOutput, 0);
            mid.set(ControlMode.PercentOutput, 0);
            System.out.println("====UNRECOGNIZED SHOOTER STATE!!!!!==== current shooter state: " + currentShooterState);
        }

    }

    public void angleLimit(){
        if (topLimitSwitch.get()){
            limitSwitchState = LimitSwitchStates.TOO_HIGH;
        } else if (bottomLimitSwitch.get()){
            limitSwitchState = LimitSwitchStates.TOO_LOW;
        } else{
            limitSwitchState = LimitSwitchStates.CORRECT_ANGLE;
        }
    }



    private double determineRightTicks(double desiredDegrees){
        return desiredDegrees * PIVOT_TICKS_PER_DEGREE;
    }

    private void setPivot(double desiredTicks){
         if(Math.abs(desiredTicks - pivot.getSelectedSensorPosition()) > PIVOT_DEADBAND_TICKS){
            if(limitSwitchState == LimitSwitchStates.TOO_HIGH){
                if(desiredTicks >= pivot.getSelectedSensorPosition()){
                    currentShooterState = ShooterStates.OFF;
                }else{
                    pivot.set(ControlMode.Position, desiredTicks);
                }
            }else if(limitSwitchState == LimitSwitchStates.TOO_LOW){
                if(desiredTicks <= pivot.getSelectedSensorPosition()){
                    currentShooterState = ShooterStates.OFF;
                }else{
                    pivot.set(ControlMode.Position, desiredTicks);
                }
            }else{
                pivot.set(ControlMode.Position, desiredTicks);
            }
        }else{
            currentShooterState = ShooterStates.OFF;
        }
    }

    public void adjusting() {
        // TODO: when we know the max rotation of the pivot motor we need to intergrate that here 
        if(OI.getTwoRightAxis() > 0.2) {
            pivot.set(ControlMode.PercentOutput, 0.2);    
        } else if(OI.getTwoRightAxis() < - 0.2) {
            pivot.set(ControlMode.PercentOutput, - 0.2);  
        } else {
            pivot.set(ControlMode.PercentOutput, 0);
        }
    }

    public void setState(ShooterStates newState) {
        currentShooterState = newState;
    }
}
