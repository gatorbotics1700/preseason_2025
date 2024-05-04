package frc.robot;

import frc.robot.subsystems.PivotSubsystem.PivotStates;
import frc.robot.subsystems.Mechanisms;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.Mechanisms.MechanismStates;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Buttons {
    
  private DrivetrainSubsystem m_drivetrainSubsystem = Robot.m_drivetrainSubsystem;
  private Mechanisms m_mechanismSubsystem = Robot.m_mechanismSubsystem;
  
  public void buttonsPeriodic(){
    //DRIVER
      if (OI.driver.getYButtonPressed()){
         m_drivetrainSubsystem.stopDrive(); 
      }

      if(OI.driver.getLeftBumperPressed()){
        m_mechanismSubsystem.setState(MechanismStates.VOMIT);
        System.out.println("=======LEFT BUMPER====VOMIT=======");
      }

      if(OI.driver.getStartButtonPressed()){ //should be PRESSED
        System.out.println("=======START BUTTON====REMAKING DRIVETRAIN + setting posmanager=======");
        m_drivetrainSubsystem.resetOffsets();
        m_drivetrainSubsystem.trueNorth180();
        m_drivetrainSubsystem.onEnable();
      }

      if(OI.driver.getBButtonPressed()){
        m_drivetrainSubsystem.flippedDrive = !m_drivetrainSubsystem.flippedDrive;
        System.out.println("=========B BUTTON====FLIPPED DRIVE========");
        //m_drivetrainSubsystem.getPositionManager().resetPosition(m_drivetrainSubsystem.getGyroscopeRotation(), m_drivetrainSubsystem.getModulePositionArray(), new Pose2d(0, 0, new Rotation2d(Math.toRadians(0))));
        //System.out.println("set position manager to:" + m_drivetrainSubsystem.getPositionManager().getEstimatedPosition());

      }

      if(OI.driver.getAButtonPressed()){ //
        System.out.println("=======A BUTTON====REMAKING DRIVETRAIN=======");
        m_drivetrainSubsystem.resetOffsets();
        m_drivetrainSubsystem.onEnable();
      }

      // if(OI.driver.getXButtonPressed()){
      //   m_drivetrainSubsystem.getPositionManager().resetPosition(m_drivetrainSubsystem.getGyroscopeRotation(), m_drivetrainSubsystem.getModulePositionArray(), new Pose2d(0, 0, new Rotation2d(Math.toRadians(180))));
      //   System.out.println("set position manager to:" + m_drivetrainSubsystem.getPositionManager().getEstimatedPosition());

      // }

      if(OI.driver.getAButtonPressed()){
        m_drivetrainSubsystem.getPositionManager().resetPosition(m_drivetrainSubsystem.getGyroscopeRotation(), m_drivetrainSubsystem.getModulePositionArray(), new Pose2d(0, 0, new Rotation2d(Math.toRadians(270))));
        System.out.println("set position manager to:" + m_drivetrainSubsystem.getPositionManager().getEstimatedPosition());

      }

      if(OI.driver.getRightBumperPressed()){
        if(m_drivetrainSubsystem.getSlowDrive()){
          m_drivetrainSubsystem.setSlowDrive(false);
        }else{
          m_drivetrainSubsystem.setSlowDrive(true);
        }
      }
      

    //CODRIVER
      if (OI.codriver.getXButtonPressed()){
        m_mechanismSubsystem.setState(MechanismStates.UNDER_STAGE);
        System.out.println("===========X BUTTON======= UNDER STAGE =========");
      }

      if (OI.codriver.getYButtonPressed()){ //all off mech
        m_mechanismSubsystem.setState(MechanismStates.OFF);
        System.out.println("=======Y BUTTON====ALL OFF MECH=======");
      }

      if (OI.codriver.getAButtonPressed()){  //amp/speaker shooting
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.AMP_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_AMP);
          System.out.println("=====A BUTTON=====SHOOTING IN AMP!!");
        }else if(m_mechanismSubsystem.getMechanismState() == MechanismStates.SUBWOOFER_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_SUBWOOFER);
          System.out.println("=====A BUTTON=====SHOOTING IN SPEAKER!!");
        // }else if(m_mechanismSubsystem.getMechanismState() == MechanismStates.PODIUM_HOLDING){
        //   m_mechanismSubsystem.setState(MechanismStates.SHOOTING_PODIUM);
        //   System.out.println("=====A BUTTON=====SHOOTING IN SPEAKER!!");
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

      if(OI.codriver.getLeftBumperPressed()){
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
      
      if(OI.codriver.getRightBumperPressed()){
        //TODO: determine whether we want to apply the same logic as getLeftBumperPressed
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
  }
}