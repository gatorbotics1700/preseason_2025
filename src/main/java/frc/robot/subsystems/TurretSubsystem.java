package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants;

public class TurretSubsystem {
    private boolean isCW;
    
    private TalonFX turretMotor;

    private final double TURRET_SPEED = 0.1;
  
    private TurretStates turretState;

    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    public static enum TurretStates {
        OFF,
        CW,
        CCW;
    }
    
    public TurretSubsystem() {
        turretMotor = new TalonFX(Constants.TURRET_MOTOR_CAN_ID);
        init();
    }

    public void init() {
        turretMotor.setInverted(false);
        turretMotor.setNeutralMode(NeutralModeValue.Brake);
        setState(TurretStates.CW);
    }

    public void periodic() {
        if (turretState == TurretStates.OFF) {
            turretMotor.setControl(dutyCycleOut.withOutput(0));
            System.out.println("TURRET STATE IS OFF");
        } else if (turretState == TurretStates.CW) {
            turretMotor.setControl(dutyCycleOut.withOutput(TURRET_SPEED));
            System.out.println("TURRET STATE IS CLOCKWISE");
        } else if (turretState == TurretStates.CCW) {
            turretMotor.setControl(dutyCycleOut.withOutput(-TURRET_SPEED) );
            System.out.println("TURRET STATE IS COUNTERCLOCKWISE");
        } else {
            turretMotor.setControl(dutyCycleOut.withOutput(0));
            System.out.println("TURRET STATE UNRECOGNIZED!!");
        }
    }

    public void setState(TurretStates newState){
        turretState = newState;
    }

    public double turretAngle(){
        return turretMotor.getRotorPosition().getValue();
    }

    public void setDirection(){
        isCW = false;
    }

    public void turnToAngle(double angle){
        double difference =  Math.abs(turretMotor.getRotorPosition().getValue()-angle);
        
        if(turretMotor.getRotorPosition().getValue() > 180){
            isCW = true;
        }
        if(difference<=1){
            difference =  Math.abs(turretMotor.getRotorPosition().getValue()-angle);
           if(isCW){
            setState(TurretStates.CW);
           }else{
            setState(TurretStates.CCW);
           }
        } else {
            setState(TurretStates.OFF);
        }    
    }

}
