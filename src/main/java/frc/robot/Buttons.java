package frc.robot;

import frc.robot.subsystems.DrivetrainSubsystem;

public class Buttons {
    
  private DrivetrainSubsystem m_drivetrainSubsystem = Robot.m_drivetrainSubsystem;
  
  public void buttonsPeriodic(){

      //driver
      if (OI.m_controller.getBButton()){ //emergency stop EVERYTHING
        m_drivetrainSubsystem.stopDrive(); 
      }
  }
}
