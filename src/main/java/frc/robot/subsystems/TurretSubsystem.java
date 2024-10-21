package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode; 
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {

    private WPI_TalonFX turretMotor;
    private final double slowRotationSpeed = 0.2; 

    public TurretSubsystem() {
        turretMotor = new WPI_TalonFX(5);
        turretMotor.configFactoryDefault();
        
    }

    public void rotateTurretSlow() {
        turretMotor.set(TalonFXControlMode.PercentOutput, slowRotationSpeed);
    }

    public void stopTurret() {

        turretMotor.set(TalonFXControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        
        rotateTurretSlow(); 
    }
}
