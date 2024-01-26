package frc.robot.subsystems;

import frc.robot.subsystems.*;

import com.revrobotics.ColorSensorV3; 
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C; // TODO: check if this is right


public class SensorSubsystem {

    private SensorStates sensorState;
    private boolean seesNote;
    private final I2C.Port i2cPort = I2C.Port.kOnboard; // TODO: change port if needed
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    public Mechanisms mechanismsSubsystem;

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
        setState(SensorStates.ON);
        seesNote = false;
        m_colorMatcher.addColorMatch(noteColor);
    
    }

    public void periodic(){
        if (sensorState == SensorStates.ON){
            detectColor();
            if (seesNote){
                setState(SensorStates.OFF);
            }
        } else {
            seesNote = false;
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

    public void setState(SensorStates newState){
        sensorState = newState;
    }

    public boolean getSeesNote(){
        return seesNote;
    }
}  

