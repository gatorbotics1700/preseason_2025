package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import frc.robot.autonomous.PDState;
// import frc.robot.autonomous.Paths;
// import frc.robot.autonomous.PDState.AutoStates;
import frc.robot.autonomous.AutonomousBasePD;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.Robot;
import frc.robot.Constants;

public class LEDSubsystem {
    public AddressableLED m_led;

    public AddressableLEDBuffer m_ledBuffer;
    AutonomousBasePD autonomousBasePD;
    LEDStates state = LEDStates.NEUTRAL;

    //getting class to draw getStateSequence
    public LEDSubsystem(){ //AutonomousBasePD auto
        m_led = new AddressableLED(Constants.LED_PORT); // port will change when actually plugged in
        m_ledBuffer = new AddressableLEDBuffer(Constants.NUMLED); // # of LEDS
        m_led.setLength(m_ledBuffer.getLength()); // gets length
    }

    public static enum LEDStates {
        HAS_NOTE,//orange
        OOP, // turns yellow
        NEUTRAL; // turns green
    }

    public void init(){ //was onEnable
        m_led.start();//TODO: 2/13/2024 originally was after set data, but switched to before. check if correct
        //sets data
        m_led.setData(m_ledBuffer);
    }

    public void periodic(){
        if (state == LEDStates.HAS_NOTE){
            for(int i = 0; i < m_ledBuffer.getLength(); i++){
                m_ledBuffer.setRGB(i, 255,136,0);//orange
            }
        } else if (state == LEDStates.NEUTRAL){
            for(int i = 0; i < m_ledBuffer.getLength(); i++){
                m_ledBuffer.setRGB(i, 0,255,0);//green
            }
        } else if (state == LEDStates.OOP){
            for(int i = 0; i < m_ledBuffer.getLength(); i++){
                m_ledBuffer.setRGB(i, 0,255,0);//green
            }
        } else {
            for(int i = 0; i < m_ledBuffer.getLength(); i++){
                m_ledBuffer.setRGB(i, 255,235,0);//yellow
            }
        }
    }  

    public void setState(LEDStates state) {
        this.state = state;
    }
   
}
