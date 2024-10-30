package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.MechStop;


public class TurretSubsystem extends SubsystemBase {

  private final TalonFX turretMotor; //motor
  private double TURRET_SPEED = 0.1;

  private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    public TurretSubsystem(TalonFX turretMotor) {
      this.turretMotor = turretMotor;
      //setDefaultCommand(new MechStop(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // turretMotor.setControl(dutyCycleOut.withOutput(TURRET_SPEED));
    MechGo();
    
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



  public void turnToAngle(double desiredAngle){
    double difference = Math.abs(turretAngle()-desiredAngle);
    if(difference >= 20){
        MechGo();
    } else {
        MechStop();
    }
  }

  public void setTurretSpeed(double speed) {
    DutyCycleOut dutyCycleOut = new DutyCycleOut(speed); // Or whatever control method you use
    turretMotor.setControl(dutyCycleOut.withOutput(speed));
}

}
