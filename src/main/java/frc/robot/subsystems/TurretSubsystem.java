package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.Servo;


import frc.robot.Constants;

public class TurretSubsystem {
    private boolean isCW;
    
    private TalonFX turretMotor;

    private Servo turretServo; 

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
        turretServo = new Servo(Constants.TURRET_SERVO_CAN_ID); //not sure if this should be a can id bc wpilib says use channel 
        init();
    }

    public void init() {
       // turretMotor.configIntegratedSensorAbsoluteRange(AbsoluteSensorRangeValue(0.0,1.0));
        turretMotor.setInverted(false);
        turretMotor.setNeutralMode(NeutralModeValue.Brake);
        //setState(TurretStates.CW);

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
        return (((turretMotor.getPosition().getValue())/14)%1) *360;
    }

    public void setDirection(){
        isCW = false;
    }

    public void setTurretServoAngle(double servoAngle){
        turretServo.setAngle(servoAngle);
    }
    

    public void turnToAngle(double angle){
        double difference =  Math.abs(turretAngle()-angle);
       // System.out.println("DIFFERENCE: " + difference);
        if(difference>=20){
            System.out.println("DIFFERENCE: "+difference);
            setState(TurretStates.CW);
            difference =  Math.abs(turretAngle()-angle);
            
        //    if(isCW){
        //     setState(TurretStates.CW);
        //    }else{
        //     setState(TurretStates.CCW);
        //    }
        } else {
            setState(TurretStates.OFF);
            System.out.println("AT ANGLE");
        }    
    }

}
