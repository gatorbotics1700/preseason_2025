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
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.signals.NeutralModeValue;

import com.revrobotics.ColorSensorV3; 
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C; // TODO: check if this is right

import frc.robot.Constants;


public class SensorSubsystem {

    private SensorStates sensorState;
    public boolean seesNote;
    private final I2C.Port i2cPort = I2C.Port.kOnboard; // TODO: change port if needed
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color noteColor = new Color(0, 0, 0); // TODO: make the right color
    public double colorThreshold = 0.03;

    public SensorSubsystem(){
        init();
    }

    public static enum SensorStates{
        OFF,
        ON;
    }

    public void init(){
        System.out.println("sensor init");
        setState(SensorStates.OFF);
        seesNote = false;
        m_colorMatcher.addColorMatch(noteColor);
    
    }

    public void periodic(){
        detectColor();

        if(seesNote == false){
            setState(SensorStates.ON);
        }
        else if (seesNote){
            setState(SensorStates.OFF);
        }
        else{
            setState(SensorStates.OFF);
        }
   
    }

    public void detectColor(){
        Color detectedColor = colorSensor.getColor();

        boolean redThreshold = (Math.abs(detectedColor.red-noteColor.red) <= colorThreshold);
        boolean greenThreshold = (Math.abs(detectedColor.green-noteColor.green) <= colorThreshold);
        boolean blueThreshold = (Math.abs(detectedColor.blue-noteColor.blue) <= colorThreshold);

        if(redThreshold && greenThreshold && blueThreshold) {
            System.out.println("WE'VE HIT THAT NOTE!!");
            seesNote = true; //off
        } else {
            seesNote = false; //on
        }
    }

    private void setState(SensorStates sensorState){
        this.sensorState = sensorState;
    }
}  

