package frc.robot;

import frc.robot.subsystems.PivotSubsystem.PivotStates;
import frc.robot.subsystems.Mechanisms;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.Mechanisms.MechanismStates;

import com.ctre.phoenix6.mechanisms.MechanismState;

import frc.robot.subsystems.DrivetrainSubsystem;

public class Buttons {
    
  private DrivetrainSubsystem m_drivetrainSubsystem = Robot.m_drivetrainSubsystem;
  private Mechanisms m_mechanismSubsystem = Robot.m_mechanismSubsystem;
  
  public void buttonsPeriodic(){
    //DRIVER
      if (OI.driver.getLeftBumper()){
         m_drivetrainSubsystem.stopDrive(); 
      }

      if(OI.driver.getStartButton()){
        System.out.println("=======START BUTTON====REMAKING DRIVETRAIN=======");
        m_drivetrainSubsystem.resetOffsets();
        m_drivetrainSubsystem.onEnable();
      }

      if(OI.driver.getRightBumperPressed()){
        if(m_drivetrainSubsystem.getSlowDrive()){
          m_drivetrainSubsystem.setSlowDrive(false);
        }else{
          m_drivetrainSubsystem.setSlowDrive(true);
        }
      }
      

    //CODRIVER
      if (OI.codriver.getXButton()){
        m_mechanismSubsystem.setState(MechanismStates.UNDER_STAGE);
        System.out.println("===========X BUTTON======= UNDER STAGE =========");
      }

      if (OI.codriver.getYButton()){ //all off mech
        m_mechanismSubsystem.setState(MechanismStates.OFF);
        System.out.println("=======Y BUTTON====ALL OFF MECH=======");
      }

      if (OI.codriver.getAButton()){  //amp/speaker shooting
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.AMP_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_AMP);
          System.out.println("=====A BUTTON=====SHOOTING IN AMP!!");
        }else if(m_mechanismSubsystem.getMechanismState() == MechanismStates.SUBWOOFER_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_SUBWOOFER);
          System.out.println("=====A BUTTON=====SHOOTING IN SPEAKER!!");
        }else if(m_mechanismSubsystem.getMechanismState() == MechanismStates.PODIUM_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_PODIUM);
          System.out.println("=====A BUTTON=====SHOOTING IN SPEAKER!!");
        }else{
          System.out.println("=====A BUTTON=====ERROR NOT IN HOLDING CANNOT SHOOT !!!!!!!!!!!!!!!!!");
        }
      }
      
      if (OI.codriver.getBButtonPressed()){ //intaking toggle
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.INTAKING){
          m_mechanismSubsystem.setState(MechanismStates.OFF);
          System.out.println("=======B BUTTON====INTAKING OFF=======");
        } else {
          m_mechanismSubsystem.setState(MechanismStates.INTAKING);
          System.out.println("=======B BUTTON====INTAKING ON=======");
        }
      }

      if(OI.codriver.getLeftBumper()){
        // //sets state to off so the motors don't immediately go the other way from amp holding
        // //then warms up speaker motors (codriver has to click twice)
        ////this button works under the assumption that the driver will primarily go to amp
        System.out.println("=======LEFT BUMPER====OFF THEN SPEAKER HOLDING=======");
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.AMP_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.OFF);
        }else if(m_mechanismSubsystem.getMechanismState() == MechanismStates.OFF ||
               m_mechanismSubsystem.getMechanismState() == MechanismStates.INTAKING){
          m_mechanismSubsystem.setState(MechanismStates.SUBWOOFER_HOLDING);
        }
      }
      
      if(OI.codriver.getRightBumper()){
        m_mechanismSubsystem.setState(MechanismStates.AMP_HOLDING);
        System.out.println("=======RIGHT BUMPER====AMP HOLDING=======");
      }

      if(OI.codriver.getStartButtonPressed()){
        System.out.println("start button pressed");
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.MANUAL){
          m_mechanismSubsystem.setState(MechanismStates.OFF);
        } else {
          System.out.println("in manual pivot!");
          m_mechanismSubsystem.setState(MechanismStates.MANUAL);
        }
      }
      if(OI.codriver.getPOV() > 225 && OI.codriver.getPOV() < 315){
        if(m_mechanismSubsystem.sensorSubsystem.detectNote()){
          m_mechanismSubsystem.setState(MechanismStates.OFF);
        } else {
          System.out.println("========SWALLOWING NOTE BACK IN========");
          m_mechanismSubsystem.setState(MechanismStates.SWALLOWING);
        }
      }
      if(OI.codriver.getPOV() > 45 && OI.codriver.getPOV() < 115){
          m_mechanismSubsystem.setState(MechanismStates.FIXAMP);
      }
  }
}