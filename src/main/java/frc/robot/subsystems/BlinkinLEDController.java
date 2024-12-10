package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;
import edu.wpi.first.hal.can.CANJNI;
import edu.wpi.first.hal.can.CANStatus;

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

  private BlinkinLEDController m_controller = null;
  private Spark m_blinkin;
  private BlinkinPattern m_currentPattern;
  private CANStatus m_status;

  public BlinkinLEDController() {
    m_blinkin = new Spark(Constants.LED_PORT);
  }

  public void setPattern(BlinkinPattern pattern) {
    m_currentPattern = pattern;
    m_blinkin.set(m_currentPattern.value);
  }

  public BlinkinLEDController getInstance() {
    if (m_controller == null) m_controller = new BlinkinLEDController();
    return m_controller;
  }

  public static CANStatus getCANStatus() {
    CANStatus status = new CANStatus();
    CANJNI.getCANStatus(status);
    return status;
  }

}
