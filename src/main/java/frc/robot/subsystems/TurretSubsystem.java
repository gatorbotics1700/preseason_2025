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
  private double TURRET_SPEED = 0.01;
  private double turretOffset = 0;
  private static final double TURNING_SPEED = 0.02;
  private static final double TOLERANCE = 2.0;
  private static final double kP = 0.2;
  private static final double kI = 0.0;
  private static final double kD = 0.0;
  private static final boolean USE_PID = true;
  public final TalonFX turretMotor; //motor
  private final PIDController pidController;
  private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

  private LimelightSubsystem limelightSubsystem;

  public TurretSubsystem() {
    turretMotor = new TalonFX(Constants.TURRET_MOTOR_CAN_ID);
    //setDefaultCommand(new MechStop(this));
    this.pidController = new PIDController(kP, kI, kD);
    pidController.setTolerance(TOLERANCE);
    pidController.setSetpoint(0.0);
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

  public void turnToAngle() {
    // double currentAngle = getTurretAngle();
    // double error = limelightSubsystem.getHorizontalOffset() - currentAngle;
    
    //if(USE_PID){
    if (limelightSubsystem.hasValidTarget()) {
      double targetAngle = limelightSubsystem.getHorizontalOffset(); // assuming it is zeroed
      System.out.println("TARGET ANGLE: " + targetAngle);
      double turnSpeed;
      
      // if (USE_PID) {

      turnSpeed = -pidController.calculate(targetAngle);
      turnSpeed = Math.max(-TURNING_SPEED, Math.min(TURNING_SPEED, turnSpeed));

      System.out.println("Turn Speed: " + turnSpeed);
      // } else {
      //     // turnSpeed = 0;
      //     // if (Math.abs(targetAngle) > TOLERANCE) {
      //     //     turnSpeed = Math.signum(targetAngle) * TURNING_SPEED;
      //     // }
      // }
    
      setTurretSpeed(turnSpeed);
        //turnToAngle(targetAngle,0.1);
      
    } else {
      System.out.println("NO APRILTAG FOUND");
        //if (USE_PID) {
      pidController.reset();
        //}
      setTurretSpeed(0);
    }


    
    // } else {
    //   // Normalize the error to -180 to 180 degrees
    //   if (error > 180) {
    //     error -= 360;
    //   } else if (error < -180) {
    //     error += 360;
    //   }
    
    //   if (Math.abs(error) >= 5) {
    //     // Set direction based on shortest path
    //     double direction = Math.signum(error);
    //     setTurretSpeed(speed * direction);
    //   } else {
    //     setTurretSpeed(0);
    //   }
    // }
  }

  public boolean isAtTarget(){
    double currentAngle = getTurretAngle();
    double offset = Math.abs(limelightSubsystem.getHorizontalOffset());
    boolean isAligned = Math.abs(currentAngle-offset) < TOLERANCE;
    if(isAligned){
      System.out.println("Turret is aligned!");
      return true;
    } else {
      System.out.println("Target Aligned! Offset: " + offset);
      return false;
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
