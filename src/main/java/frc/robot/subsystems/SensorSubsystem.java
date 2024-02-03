package frc.robot.subsystems;

//import frc.robot.subsystems.*;

import com.revrobotics.ColorSensorV3; 
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C; // TODO: check if this is right


public class SensorSubsystem { //TODO not done yet

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    public Mechanisms mechanismsSubsystem;

    private final Color noteColor = new Color(0, 0, 0); // TODO: make the right color based on testing with real robot
    public double colorThreshold = 0.03; //this feels really little lets test

    public SensorSubsystem(){
        init();
    }


    public void init(){
        System.out.println("sensor init");
        m_colorMatcher.addColorMatch(noteColor);
    }

    public void periodic(){//TODO code shooting from holding state
    }

    public boolean detectNote(){//TODO
        Color detectedColor = colorSensor.getColor();

        boolean redThreshold = (Math.abs(detectedColor.red-noteColor.red) <= colorThreshold);
        boolean greenThreshold = (Math.abs(detectedColor.green-noteColor.green) <= colorThreshold);
        boolean blueThreshold = (Math.abs(detectedColor.blue-noteColor.blue) <= colorThreshold);

        if(redThreshold && greenThreshold && blueThreshold) {
            System.out.println("WE'VE HIT THAT NOTE!!");
            return true; //off
        } else {
            return false; //on
        }
    }
}

