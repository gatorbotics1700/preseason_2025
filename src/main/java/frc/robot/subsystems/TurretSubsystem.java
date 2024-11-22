package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.compound.Diff_DutyCycleOut_Position;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.MechStop;


public class TurretSubsystem extends SubsystemBase {

  public final TalonFX turretMotor; //motor
  private double TURRET_SPEED = 0.05;
  private double turretOffset = 0;
  private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

  public TurretSubsystem() {
    turretMotor = new TalonFX(Constants.TURRET_MOTOR_CAN_ID);
    //setDefaultCommand(new MechStop(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // turretMotor.setControl(dutyCycleOut.withOutput(TURRET_SPEED));
   System.out.println("TURRET ANGLE: " + getTurretAngle());
    
  }

  public void MechFaster() {
    TURRET_SPEED = Math.min(1.0, TURRET_SPEED + 0.1);
  }
  public void MechSlower() {
    TURRET_SPEED = Math.max(-1.0, TURRET_SPEED - 0.1);
  }
  public void MechStop() {
    turretMotor.setControl(dutyCycleOut.withOutput(0));

  }
  public void MechGo() {
    turretMotor.setControl(dutyCycleOut.withOutput(TURRET_SPEED));

  }

  public void turnToAngle(double desiredAngle, double speed) {
    double currentAngle = getTurretAngle();
    double error = desiredAngle - currentAngle;
    
    // Normalize the error to -180 to 180 degrees
    if (error > 180) {
        error -= 360;
    } else if (error < -180) {
        error += 360;
    }
    
    if (Math.abs(error) >= 5) {
        // Set direction based on shortest path
        double direction = Math.signum(error);
        setTurretSpeed(speed * direction);
    } else {
        setTurretSpeed(0);
    }
  }

  public void setTurretSpeed(double speed) {
    DutyCycleOut dutyCycleOut = new DutyCycleOut(speed); 
    turretMotor.setControl(dutyCycleOut.withOutput(speed));
  }

  public void zeroTurret(){
    turretOffset = turretMotor.getPosition().getValueAsDouble(); 
    System.out.println("Zeroing the turret");
    System.out.println("turret offset: " + turretOffset);
  }

  public double getTurretAngle() {
    return (((turretMotor.getPosition().getValueAsDouble() - turretOffset) / 9.2) * 360) % 360; //converts the turret angle rotations to degrees
  }

}
