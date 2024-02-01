package frc.robot.subsystems;

//import frc.robot.subsystems.*;

import com.revrobotics.ColorSensorV3; 
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C; // TODO: check if this is right


public class SensorSubsystem { //TODO not done yet

    private SensorStates sensorState;
    private static boolean seesNote;
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    public Mechanisms mechanismsSubsystem;

    private final Color noteColor = new Color(0, 0, 0); // TODO: make the right color based on testing with real robot
    public double colorThreshold = 0.03; //this feels really little lets test

    public SensorSubsystem(){
        init();
    }

    public static enum SensorStates{//TODO do we need these states or can we just keep it on for the full match?
        OFF,
        ON;
    }

    public void init(){
        System.out.println("sensor init");
        setState(SensorStates.ON);
        seesNote = false;
        m_colorMatcher.addColorMatch(noteColor);
    
    }

    public void periodic(){//TODO code shooting from holding state
        if (sensorState == SensorStates.ON){
            detectColor();
            if (seesNote){
                //we stop transition motor in the shooter subsystem right now... is this very efficient??
                System.out.println("wooo note!!! low left motor stopped");
            }
        } else {
            seesNote = false;
            System.out.println("booo no note");
            //again, motor will move as normal in this case in shooter subsystem
        }
        System.out.println(colorSensor.getColor());
    }

    public void detectColor(){//TODO
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

    public static boolean getSeesNote(){
        return seesNote;
    }
}  

