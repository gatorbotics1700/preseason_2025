package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import com.revrobotics.ColorSensorV3; 
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C; // TODO: check if this is rigu8ht

import frc.robot.Constants;

public class IntakeSubsystem {

    public TalonFX intakeMotor;

    private final I2C.Port i2cPort = I2C.Port.kOnboard; // TODO: change port if needed
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color noteColor = new Color(0, 0, 0); // TODO: make the right color
    public double colorThreshold = 0.03;

    public static enum IntakeStates {
        INTAKING,
        OFF;
    }

    public static IntakeStates intakeState = IntakeStates.OFF;

    public IntakeSubsystem() {

    }

    public void init() {
        intakeMotor = new TalonFX(Constants.INTAKE_MOTOR_CAN_ID);
        
        m_colorMatcher.addColorMatch(noteColor);

    }

    public void periodic() {
        detectColor();
        if(intakeState == IntakeStates.INTAKING) {
            intakeMotor.set(0.2);
        } else {
            intakeMotor.set(0);
        }
    }

    public void detectColor(){ //TODO: this should be in the transition and should stop the transition
        Color detectedColor = colorSensor.getColor();

        boolean redThreshold = (Math.abs(detectedColor.red-noteColor.red) <= colorThreshold);
        boolean greenThreshold = (Math.abs(detectedColor.green-noteColor.green) <= colorThreshold);
        boolean blueThreshold = (Math.abs(detectedColor.blue-noteColor.blue) <= colorThreshold);

        if(redThreshold && greenThreshold && blueThreshold) {
            System.out.println("WE'VE HIT THAT NOTE!!");
            intakeState = IntakeStates.INTAKING;
        } else {
            intakeState = IntakeStates.OFF;
        }
    }

}
