package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import frc.com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import frc.com.swervedrivespecialties.swervelib.SwerveModule;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import java.util.function.DoubleSupplier;

import frc.robot.OI;
import frc.robot.subsystems.*;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class TransitionSubsystem {

    public TalonFX transitionMotor; 
    private TransitionStates transitionState;
    private boolean ifShooterReady; //gets shots
    private boolean ifIntakingDone; 
    private boolean ifIntaking; //from floor to held in transition
    private SensorSubsystem sensorSubsystem;

    public TransitionSubsystem(){
        transitionMotor = new TalonFX(Constants.TRANSITION_CAN_ID);
        init();
    }

    public static enum TransitionStates{
        OFF,
        ON;
    }

    public void init(){
        System.out.println("transition init");
        transitionMotor.setInverted(false); //sets it to default sending a piece up (counterclockwise)
        transitionMotor.setNeutralMode(NeutralModeValue.Brake); //brake mode so nothing slips = locks in place when not getting power
        setState(TransitionStates.OFF);
    }

    public void periodic(){
        if (sensorSubsystem.seesNote == false){ 
            setState(TransitionStates.ON); //going into transition
            transitionMotor.set(0.2);
        }
        else if (sensorSubsystem.seesNote == true){ 
            setState(TransitionStates.OFF); //inside transition
            transitionMotor.set(0);
        }
        else {
            setState(TransitionStates.OFF);
            transitionMotor.set(0);
        }
    }

    private void setState(TransitionStates transitionState){
        this.transitionState = transitionState;
    }
}
