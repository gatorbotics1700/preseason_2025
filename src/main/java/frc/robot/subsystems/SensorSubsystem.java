package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3; 
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
//import edu.wpi.first.wpilibj.DigitalInput;


public class SensorSubsystem {

    
    private ColorSensorV3 colorSensor;
    //private DigitalInput beambreak;
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color NOTE_COLOR = new Color(94, 106, 55);//(94, 106, 55);//98, 106, 50 //98, 106, 50 //champs: 
    private final double COLOR_THRESHOLD = 0.15;//0.12; //CALIBRATE AT COMPS 
    //Hex value converted to a fraction between 0 and 1; it's essentially the percentage of the color spectrum that we are allowing 
    //TODO: confirm COLOR_THRESHOLD
    //tested 0.03 threshold with lights on and could get an inch away

    public SensorSubsystem(){
        I2C.Port i2cPort = I2C.Port.kOnboard;
        colorSensor = new ColorSensorV3(i2cPort);
        //beambreak = new DigitalInput(Constants.BEAMBREAK_PORT);
        init();
    }

    public void init(){
        //System.out.println("sensor init"); //comment ouot prints post calibration 
        m_colorMatcher.addColorMatch(NOTE_COLOR);
        //System.out.println(colorSensor.getColor());
    }

    public boolean detectNote(){
        Color detectedColor = colorSensor.getColor();
        //System.out.println("detectedColor: " + detectedColor);

        boolean redThreshold = (Math.abs(detectedColor.red-NOTE_COLOR.red) <= COLOR_THRESHOLD);
        boolean greenThreshold = (Math.abs(detectedColor.green-NOTE_COLOR.green) <= COLOR_THRESHOLD);
        boolean blueThreshold = (Math.abs(detectedColor.blue-NOTE_COLOR.blue) <= COLOR_THRESHOLD);

        if(redThreshold && greenThreshold && blueThreshold) {
           // System.out.println("WE'VE HIT THAT NOTE!!");
            return true;
        } else {
          //  System.out.println("We don't see the note");
            return false;
        }

        /*if((redThreshold && greenThreshold && blueThreshold) || beambreak.get()) {
            //System.out.println("WE'VE HIT THAT NOTE!!");
            return true;
        } else {
            //System.out.println("We don't see the note");
            return false;
        }*/
    }
}