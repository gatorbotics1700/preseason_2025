package frc.robot;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;

public class Buttons {
    
  private DrivetrainSubsystem m_drivetrainSubsystem = Robot.m_drivetrainSubsystem;
  private ShooterSubsystem m_shooterSubsystem = Robot.m_shooterSubsystem;
  
  public void buttonsPeriodic(){

      //driver
      /*if (OI.m_controller.getBButton()){ //emergency stop EVERYTHING
        m_drivetrainSubsystem.stopDrive(); 
      }*/
      if (OI.m_controller.getLeftBumper()){ //emergency stop EVERYTHING
        m_drivetrainSubsystem.stopDrive(); 
      }
      if (OI.m_controller.getAButton()){ 
        m_shooterSubsystem.setState(ShooterStates.AMP);
      }
      if (OI.m_controller.getYButton()){ 
        m_shooterSubsystem.setState(ShooterStates.SPEAKER_BLUE);
      }
      if (OI.m_controller.getBButton()){ 
        m_shooterSubsystem.setState(ShooterStates.SPEAKER_RED);
      }
      if (OI.m_controller.getRightBumper()){ 
        m_shooterSubsystem.setState(ShooterStates.OFF);
      }
      if (OI.m_controller_two.getXButton()){
        if(IntakeSubsystem.intakeState == IntakeSubsystem.IntakeStates.OFF){
          IntakeSubsystem.intakeState = IntakeSubsystem.IntakeStates.INTAKING;
        } else {
          IntakeSubsystem.intakeState = IntakeSubsystem.IntakeStates.OFF;
        }
        // TODO: for transition, we can use this button to turn both on and off at same time
      }
  }
}
