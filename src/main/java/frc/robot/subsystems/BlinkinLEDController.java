package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;

public class BlinkinLEDController extends SubsystemBase{
    public enum BlinkinPattern {
        RAINBOW_PARTY_PALETTE(-0.97),
        RED_ORANGE(+0.62), //good
        LIME(+0.73), //good
        PURPLE(+0.92); //test
        public final double value;
        private BlinkinPattern(double value) {
          this.value = value;
        }
  };

  private static BlinkinLEDController m_controller = null;
  private Spark m_blinkin;
  private BlinkinPattern m_currentPattern;

  public BlinkinLEDController() {
    System.out.println("trying to make an led controller");
    m_blinkin = new Spark(Constants.LED_PORT);
  }

  public void setPattern(BlinkinPattern pattern) {
    m_currentPattern = pattern;
    System.out.println("lights should be working!!");
    m_blinkin.set(m_currentPattern.value);
  }

  public static BlinkinLEDController getInstance() {
    if (m_controller == null) m_controller = new BlinkinLEDController();
    return m_controller;
  }
     
}
