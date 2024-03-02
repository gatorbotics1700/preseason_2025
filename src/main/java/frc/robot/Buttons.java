package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.mechanisms.MechanismState;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
//import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.Mechanisms;
// import frc.robot.subsystems.Mechanisms.MechanismStates;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.PivotSubsystem.PivotStates;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;
import frc.robot.subsystems.Mechanisms;
import frc.robot.subsystems.Mechanisms.MechanismStates;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Buttons {
    
  private DrivetrainSubsystem m_drivetrainSubsystem = Robot.m_drivetrainSubsystem;
  private Mechanisms m_mechanismSubsystem = Robot.m_mechanismSubsystem;
  
  public void buttonsPeriodic(){
    //DRIVER
      if (OI.driver.getLeftBumper()){ //emergency stop EVERYTHING
         m_drivetrainSubsystem.stopDrive(); 
      }

      if(OI.driver.getStartButton()){
        System.out.println("=======START BUTTON====REMAKING DRIVETRAIN=======");
        m_drivetrainSubsystem.resetOffsets();
        m_drivetrainSubsystem.onEnable();
      }
      

    //CODRIVER
      if (OI.codriver.getXButton()){ //manual
        m_mechanismSubsystem.setState(MechanismStates.AMP_HOLDING);
        //m_mechanismSubsystem.pivotSubsystem.setState(PivotStates.AMP);
        System.out.println("x button");

        /*if(m_mechanismSubsystem.pivotSubsystem.getState() == PivotStates.MANUAL){
          m_mechanismSubsystem.pivotSubsystem.setState(PivotStates.OFF);
          System.out.println("=======x BUTTON====PIVOT OFF=======");
        } else {
          m_mechanismSubsystem.pivotSubsystem.setState(PivotStates.MANUAL);
          System.out.println("=======x BUTTON====PIVOT IN MANUAL=======");
        }*/
      }

      if (OI.codriver.getYButton()){ //all off mech
        m_mechanismSubsystem.setState(MechanismStates.OFF);
        System.out.println("=======Y BUTTON====MECHANISMS STOP=======");
        //m_mechanismSubsystem.pivotSubsystem.setState(PivotStates.SPEAKER);
      }

      if (OI.codriver.getAButton()){  //amp/speaker shooting
         if(m_mechanismSubsystem.getMechanismState() == MechanismStates.AMP_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_AMP);
          System.out.println("=====A BUTTON=====SHOOTING IN AMP!!");
        }else if(m_mechanismSubsystem.getMechanismState() == MechanismStates.SPEAKER_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_SPEAKER);
          System.out.println("=====A BUTTON=====SHOOTING IN SPEAKER!!");
        }else{
          System.out.println("=====A BUTTON=====ERROR NOT IN HOLDING CANNOT SHOOT !!!!!!!!!!!!!!!!!");
        }

        //m_mechanismSubsystem.pivotSubsystem.setState(PivotStates.MANUAL);
      }
      
      if (OI.codriver.getBButtonPressed()){ //intaking toggle
        // m_mechanismSubsystem.setState(MechanismStates.SPEAKER_HOLDING);
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.INTAKING){
          m_mechanismSubsystem.setState(MechanismStates.OFF);
          System.out.println("=======B BUTTON====INTAKING OFF=======");
        } else {
          m_mechanismSubsystem.setState(MechanismStates.INTAKING);
          System.out.println("=======B BUTTON====INTAKING ON=======");
        }
        
      }
      if(OI.codriver.getLeftBumper()){
        m_mechanismSubsystem.setState(MechanismStates.SPEAKER_HOLDING);
        System.out.println("=======RIGHT BUMPER====SPEAKER HOLDING=======");
      }
      
      if(OI.codriver.getRightBumper()){
        m_mechanismSubsystem.setState(MechanismStates.AMP_HOLDING);
        System.out.println("=======LEFT BUMPER====AMP HOLDING=======");
      }  

      //TODO reagan wants dpad for pivot on/manual then buttons for speaker,amp
      if(OI.getTwoLeftAxis() > 0.2) {
        // m_mechanismSubsystem.pivotSubsystem.setState(PivotStates.SPEAKER); 
      }
      if(OI.getTwoLeftAxis() < - 0.2) {
       //   m_mechanismSubsystem.pivotSubsystem.setState(PivotStates.AMP);
      }

     /* if(OI.codriver.getPOV() > 45 && OI.codriver.getPOV() < 135){
        System.out.println("dpad");
      }

      if(OI.codriver.getPOV() > 225 && OI.codriver.getPOV() < 315){
        System.out.println("dpad");
      }*/



  }
}