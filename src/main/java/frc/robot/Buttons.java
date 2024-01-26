package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.mechanisms.MechanismState;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Mechanism;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Mechanisms;
import frc.robot.subsystems.Mechanisms.MechanismStates;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterSubsystem.ShooterStates;

public class Buttons {
    
  private DrivetrainSubsystem m_drivetrainSubsystem = Robot.m_drivetrainSubsystem;
  private Mechanisms m_mechanismSubsystem = Robot.m_mechanismSubsystem;
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public double leftTrigger;
  public double rightTrigger;
  
  public void buttonsPeriodic(){
      //driver
      /*if (OI.m_controller.getBButton()){ //emergency stop EVERYTHING
        m_drivetrainSubsystem.stopDrive(); 
      }*/
      
      if (OI.m_controller.getLeftBumper()){ //emergency stop EVERYTHING
        m_drivetrainSubsystem.stopDrive(); 
      }
      if (OI.m_controller_two.getAButton()){ 
        //m_mechanismSubsystem.setState(MechanismStates.SHOOTING_AMP);
        shooterSubsystem.highLeft.set(ControlMode.PercentOutput, 0.4);
        shooterSubsystem.midRight.set(ControlMode.PercentOutput, 0.4);
        System.out.println("=====A BUTTON=====SETTING TO 0.4");
      }
      if (OI.m_controller_two.getBButton()){ 
<<<<<<< HEAD
        //m_mechanismSubsystem.setState(MechanismStates.SHOOTING_SPEAKER_R);
        shooterSubsystem.highLeft.set(ControlMode.PercentOutput, -0.4);
        shooterSubsystem.midRight.set(ControlMode.PercentOutput, 0.4);
        System.out.println("=======B BUTTON======SETTING ONE TO -0.4");
=======
        m_mechanismSubsystem.setState(MechanismStates.SHOOTING_SPEAKER);
>>>>>>> a6124eade76203d527e3201c81e931f371a1eae3
      }
      if (OI.m_controller_two.getRightBumper()){ 
        //m_mechanismSubsystem.setState(MechanismStates.OFF);
        shooterSubsystem.highLeft.set(ControlMode.PercentOutput, 0.0);
        shooterSubsystem.midRight.set(ControlMode.PercentOutput, 0.0);
        System.out.println("=======SHOOTER STOOOOPPPP=======");
      }
      if (OI.m_controller_two.getXButton()){
        if(IntakeSubsystem.intakeStates == IntakeSubsystem.IntakeStates.OFF){
          IntakeSubsystem.intakeStates = IntakeSubsystem.IntakeStates.INTAKING;
        } else {
          IntakeSubsystem.intakeStates = IntakeSubsystem.IntakeStates.OFF;
        }
        // TODO: for transition, we can use this button to turn both on and off at same time
      }


  }
}
