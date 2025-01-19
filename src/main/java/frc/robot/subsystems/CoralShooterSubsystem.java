package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;

public class CoralShooterSubsystem extends SubsystemBase{
    public final TalonFX coralShooterMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    //private final DigitalInput beamBreakSensor;
    private static Servo servo;
    private static final double MIN_ANGLE = 0;
    private static final double MAX_ANGLE = 90;
    
    public CoralShooterSubsystem(){
        coralShooterMotor = new TalonFX(Constants.CORAL_MOTOR_CAN_ID);//TODO: enter in can id later
        //beamBreakSensor = new DigitalInput(0); //TODO: replace with beambreak receiver 
        servo = new Servo(Constants.SERVO_PWM_PORT);
        setAngle(0.0, false);
    }

    @Override
    public void periodic(){

    }

    public void setSpeed(double speed){
        DutyCycleOut dutyCycleOut = new DutyCycleOut(speed);
        coralShooterMotor.setControl(dutyCycleOut.withOutput(speed));
    }

    // public boolean isBeamBroken() {
    //     return beamBreakSensor.get();
    // }

    public void setAngle(double angle, boolean invert) {
        if(angle > MAX_ANGLE) {
            angle = MAX_ANGLE;
        } else if(angle < MIN_ANGLE){
            angle = MIN_ANGLE;
        }
        if(invert) {
            angle = MAX_ANGLE - angle;
        }
        servo.setAngle(angle);
    }

    public double getAngle(boolean inverted) {
        double angle = servo.getAngle();
        if(inverted) {
            angle = -angle + MAX_ANGLE;
        }
        return angle;
    } 
}
