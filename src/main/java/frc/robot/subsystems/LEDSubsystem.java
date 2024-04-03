package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;
//code came from this chief delphi post: https://www.chiefdelphi.com/t/rev-blinkin-example-code/452871/3

public class LEDSubsystem {
    public enum BlinkinPattern {
        RAINBOW_PARTY_PALETTE(-0.97),
        RED_ORANGE(+0.62), //good
        LIME(+0.73), //good
        PURPLE(+0.91); //test
        public final double value;
        private BlinkinPattern(double value) {
          this.value = value;
        }  
    }

  private LEDSubsystem m_controller = null;
  private Spark m_blinkin;

  public LEDSubsystem() {
    m_blinkin = new Spark(Constants.LED_PORT);

  }

  public void setPattern(BlinkinPattern pattern) {
    m_blinkin.set(pattern.value);
  }

  public LEDSubsystem getInstance() {
    if (m_controller == null) m_controller = new LEDSubsystem();
    return m_controller;
  }
     
}
