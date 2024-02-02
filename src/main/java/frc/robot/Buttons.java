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
  private IntakeSubsystem m_intakeSubsystem = Robot.m_mechanismSubsystem.intakeSubsystem;
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
        m_mechanismSubsystem.setState(MechanismStates.SHOOTING_AMP);
        shooterSubsystem.high.set(ControlMode.PercentOutput, 0.35);
        shooterSubsystem.mid.set(ControlMode.PercentOutput, -0.45);
        System.out.println("=====A BUTTON=====SETTING TO 0.4");
      }
      if (OI.m_controller_two.getBButton()){ 
        m_mechanismSubsystem.setState(MechanismStates.SHOOTING_SPEAKER);
        shooterSubsystem.high.set(ControlMode.PercentOutput, -0.1);
        shooterSubsystem.mid.set(ControlMode.PercentOutput, 0.1);
        System.out.println("=======B BUTTON======SETTING ONE TO -0.4");
      }
      if (OI.m_controller_two.getRightBumper()){ 
        m_mechanismSubsystem.setState(MechanismStates.OFF);
        shooterSubsystem.high.set(ControlMode.PercentOutput, 0.0);
        shooterSubsystem.mid.set(ControlMode.PercentOutput, 0.0);
        System.out.println("=======SHOOTER STOOOOPPPP=======");
      }
      if (OI.m_controller_two.getXButton()){
        if(m_intakeSubsystem.getCurrentIntakeState() == IntakeSubsystem.IntakeStates.OFF){
          m_intakeSubsystem.setIntakeStates(IntakeSubsystem.IntakeStates.INTAKING);
        } else {
          m_intakeSubsystem.setIntakeStates(IntakeSubsystem.IntakeStates.OFF);
        }
        // TODO: for transition, we can use this button to turn both on and off at same time
      }
  }
}
