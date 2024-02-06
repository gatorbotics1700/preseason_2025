package frc.robot.subsystems;

//import frc.robot.subsystems.*;

import com.revrobotics.ColorSensorV3; 
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C; // TODO: check if this is right


public class SensorSubsystem { //TODO not done yet

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort); //TODO: change to private
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color NOTE_COLOR = new Color(98, 106, 50);
    private double colorThreshold = 0.06; //this feels really little lets test
    //tested 0.03 threshold with lights on and could get an inch away

    public SensorSubsystem(){
        init();
    }
    

    public void init(){
        System.out.println("sensor init");
        m_colorMatcher.addColorMatch(NOTE_COLOR);
        System.out.println(colorSensor.getColor());
    }

    public boolean detectNote(){//TODO
        Color detectedColor = colorSensor.getColor();
        System.out.println(detectedColor);

        boolean redThreshold = (Math.abs(detectedColor.red-NOTE_COLOR.red) <= colorThreshold);
        boolean greenThreshold = (Math.abs(detectedColor.green-NOTE_COLOR.green) <= colorThreshold);
        boolean blueThreshold = (Math.abs(detectedColor.blue-NOTE_COLOR.blue) <= colorThreshold);

        if(redThreshold && greenThreshold && blueThreshold) {
            System.out.println("WE'VE HIT THAT NOTE!!");
            return true; //off
        } else {
            System.out.println("We don't see the note");
            return false; //on
            
        }
    }
}