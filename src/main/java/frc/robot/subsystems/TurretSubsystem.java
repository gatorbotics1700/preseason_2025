package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.compound.Diff_DutyCycleOut_Position;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.MechStop;
import edu.wpi.first.math.controller.PIDController;


public class TurretSubsystem extends SubsystemBase {

  public final TalonFX turretMotor; //motor
  private double TURRET_SPEED = 0.05;
  private final double TURRET_OFFSET = 0;
  private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
  private final PIDController pidController;
  private final double kP = 0.1;
  private final double kI = 0.0;
  private final double kD = 0.0;

  public TurretSubsystem() {
    turretMotor = new TalonFX(Constants.TURRET_MOTOR_CAN_ID);
    pidController = new PIDController(kP, kI, kD);
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
  
  public double turretAngle(){
    return(((turretMotor.getPosition().getValue())/14)%1)*360;
  }

  public void turnToAngle(double desiredAngle) {
    double currentAngle = getTurretAngle();
    double output = pidController.calculate(currentAngle, desiredAngle);
    setTurretSpeed(output);
  }

  public void setTurretSpeed(double speed) {
    DutyCycleOut dutyCycleOut = new DutyCycleOut(speed); 
    turretMotor.setControl(dutyCycleOut.withOutput(speed));
  }

  public double getTurretAngle() {
    return ((turretMotor.getPosition().getValueAsDouble() / 9.2) * 360) % 360 + TURRET_OFFSET;
  }

}