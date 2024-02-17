package frc.robot.autonomous;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants;
import frc.robot.autonomous.PDState.AutoStates;
import frc.robot.subsystems.Mechanisms;
import frc.robot.subsystems.Mechanisms.MechanismStates;;

//651 is entire field

public class Paths {
  public enum AUTO_OPTIONS{
      PD_TESTPATH,
      //R + B = red alliance vs blue alliance
      //1 + 2 + 3 = starting position 1 vs. 2
      //1 = right of the speaker if at driverstation
      //2 = opposite (left of the speaker)
      //3 = middle of speaker
      R_THREE_PIECE_1,
      B_THREE_PIECE_1,
      R_THREE_PIECE_2,
      B_THREE_PIECE_2,
      R_THREE_PAMP,
      B_THREE_PAMP,
      R_FOUR_PIECE_3,
      B_FOUR_PIECE_3,
      R_FOUR_PIECE_1,
      B_FOUR_PIECE_2,
      R_FIVE_PIECE_2,
      B_FIVE_PIECE_1,
      R_ANAIKAS_DREAM_1,
      B_ANAIKAS_DREAM_2,
      R_BREAD,
      B_BREAD,
      R_FALLBACK_1,
      R_FALLBACK_2,
      R_FALLBACK_3,
      B_FALLBACK_2,
      B_FALLBACK_1,
      B_FALLBACK_3,
      R_NO_GO,
      B_NO_GO;
  }

  public static AutonomousBase constructAuto(AUTO_OPTIONS selectedAuto){
      if(selectedAuto == AUTO_OPTIONS.PD_TESTPATH){
          return new AutonomousBasePD(
              new Pose2d(100.0 * Constants.METERS_PER_INCH, 0.0, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(45))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(100 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
      } else if (selectedAuto == AUTO_OPTIONS.B_NO_GO){
          return new AutonomousBasePD(
              new Pose2d(60.0, 0.0, new Rotation2d(Math.toRadians(180.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST),
                  new PDState(AutoStates.STOP)
              }
          );
      } else if (selectedAuto == AUTO_OPTIONS.R_NO_GO){
          return new AutonomousBasePD(
              new Pose2d(591, 0.0, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST),
                  new PDState(AutoStates.STOP)
              }
          );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_1){
          return new AutonomousBasePD(
              new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to shoot 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
                  // 1 shoot preloaded + get past starting zone (for auto points)
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_2){
          return new AutonomousBasePD(
              new Pose2d(18*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to shoot 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
                  // 1 shoot preloaded + get past starting zone (for auto points)
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), 
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_2){
          return new AutonomousBasePD(
              new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 1 shoot preloaded + get past starting zone (for auto points)
                  new PDState (AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), 
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_1){
          return new AutonomousBasePD(
              new Pose2d(18*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 1 shoot preloaded + get past starting zone (for auto points)
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_3){
          return new AutonomousBasePD(
              new Pose2d(595.5*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),//start pose
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//back up
                  // 1 shoot preloaded + get past starting zone (for auto points)
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_3){
          return new AutonomousBasePD(
              new Pose2d(55.5*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),//start pose
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//back up
                  // 1 shoot preloaded + get past starting zone (for auto points)
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_1){
          return new AutonomousBasePD(
              new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to shoot 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
                  // 1 shoot preloaded
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  //intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING), // rotate back
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.OFF), //rotate back
                  //intake 2
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING), //move to intake 2
                  // 3 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_2){
          return new AutonomousBasePD(
              new Pose2d(18*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to shoot 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
                  // 1 shoot preloaded
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  //intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING), // rotate back
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF), //rotate back
                  //intake 2
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING), //move to intake 2
                  // 3 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
      }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_2){
          return new AutonomousBasePD(
              new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 1 shoot preloaded
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),  //rotate back
                  //intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(536*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),//rotate back
                  //intake 2
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING), //move to intake 2 position
                  //warming up shooter
                  new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  // 3 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_1){
          return new AutonomousBasePD(
              new Pose2d(18*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move to intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 1 shoot preloaded
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),  //rotate back
                  //intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(115*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),//rotate back
                  //intake 2
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING), //move to intake 2 position
                  //warming up shooter
                  new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  // 3 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
      } else if(selectedAuto == AUTO_OPTIONS.R_THREE_PAMP){ //TODO: changed values need to test
          return new AutonomousBasePD(
              new Pose2d(585.5*Constants.METERS_PER_INCH, 295*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))),//start position
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.AMP_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(585.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.AMP_HOLDING),//go forward, to AMP
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.AMP_HOLDING),//move left, to center of amp to shoot 
                  //1 shoot preloaded
                   new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.SHOOTING_AMP),//move left, to center of amp to shoot 
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90))), Mechanisms.MechanismStates.OFF), //move left, towards note
                  //1 intake
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(535*Constants.METERS_PER_INCH, 305*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.INTAKING), // TEST move down, above note
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.AMP_HOLDING),//move up 
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.AMP_HOLDING),//move right, in front of AMP
                  //2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.SHOOTING_AMP),//move right, in front of AMP
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.OFF),// move left, towards piece 2
                  //2 intake 
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(535*Constants.METERS_PER_INCH, 245*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.INTAKING),// TEST move down, towards piece 2                  
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90))), Mechanisms.MechanismStates.AMP_HOLDING), //move up, towards AMP
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.AMP_HOLDING), //move right, in front of AMP
                  //3 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))), Mechanisms.MechanismStates.SHOOTING_AMP), //move right, in front of AMP
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
      } else if(selectedAuto == AUTO_OPTIONS.B_THREE_PAMP){ //TODO: changed values need to  test
          return new AutonomousBasePD(
              new Pose2d(65.5*Constants.METERS_PER_INCH, 295*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))),//start position
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.AMP_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(65.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING),//go forward, to AMP
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(75.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING),//move left, to center of amp to shoot 
                  //1 shoot preloaded
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(75.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING),//move left, to center of amp to shoot 
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(116*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.OFF), //move left, towards note
                  //1 intake                 
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(116*Constants.METERS_PER_INCH, 305*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.INTAKING), // TEST move down, above note
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(116*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING),//move up 
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(75.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING),//move right, in front of AMP
                  //2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(75.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.SHOOTING_AMP),//move right, in front of AMP
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(116*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.OFF),// move left, towards piece 2
                  //2 intake
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(116*Constants.METERS_PER_INCH, 245*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.INTAKING),// TEST move down, towards piece 2
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(116*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING), //move up, towards AMP
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(75.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING), //move right, in front of AMP
                  //3 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(75.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))), Mechanisms.MechanismStates.AMP_HOLDING), //move right, in front of AMP
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
      } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_3){ 
          return new AutonomousBasePD(
              new Pose2d(595.5*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),//start pose
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//back up
                  // 1 shoot preloaded
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),//back up
                  // intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),//back up
                  // 2 shoot
                  new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  //intake 2
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),//rotate back
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),//move left, to center line
                  //3 intake
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),//move up, right of center piece
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move down 
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move right, near speaker to shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//rotate
                  //4 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.OFF),// rotate back
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        } else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_3){  //TODO: Angles are wrong pls test
          return new AutonomousBasePD(
              new Pose2d(55.5*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),//start pose
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//back up
                  // 1 shoot preloaded
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  // intake 1
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),
                  // 2 shoot
                  new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  //intake 2
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),//rotate back
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),//move left, to center line
                  //intake 3
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),//move up, right of center piece
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move down
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //move right, near speaker to shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//rotate
                  //4 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),// rotate back
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
       } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_1){
           return new AutonomousBasePD(
              new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
              new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //back up
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
              //1 shoot preloaded 
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
              // intake
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING), //rotate back 
              // 2 shoot
              new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), 
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.OFF), //rotate
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF), //rotate back
              // intake
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING), //move down, in front of speaker
              // 3 shoot
              new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
              //intake
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING), //move down 
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
              //4 shoot
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF), //rotate back
              new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
           );
        } else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_2){
           return new AutonomousBasePD(
              new Pose2d(18*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
              new PDState[]{
              new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //back up
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
              //1 shoot preloaded 
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
              //intake
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING), //rotate back 
              // 2 shoot
              new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.OFF), //rotate
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF), //rotate back
              //intake
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING), //move down, in front of speaker
              // 3 shoot
              new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), 
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), 
              //intake
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING), //move down 
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
              //4 shoot
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
              new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF), //rotate back
              new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
           );
        } else if(selectedAuto == AUTO_OPTIONS.R_FIVE_PIECE_2){
            return new AutonomousBasePD(
               new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
               new PDState[]{
               new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //back up 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
               // 1 shoot preloaded
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
               //intake 1
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
               // 2 shoot 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
               // intake 2
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),
               // 3 shoot
               new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
               //intake 3
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),  //move up to next note
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
               //4 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),  //move back to next note
               //intake 4
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),  //move up to next note
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),  //move right
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),  //move back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING),  //rotate
               //5 shoot 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),  //rotate
               new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)              
               }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FIVE_PIECE_1){
            return new AutonomousBasePD(
               new Pose2d(18*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
               new PDState[]{
               new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //back up 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
               // 1 shoot preloaded
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),
               //intake 1
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
               // 2 shoot 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
               // intake 2
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING), 
               // 3 shoot
               new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), 
               //intake 3
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),  //move up to next note
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
               //4 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),  //move back to next note
               //intake 4
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),  //move up to next note
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),  //move right
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),  //move back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING),  //rotate
               //5 shoot 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),  //rotate
               new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)              
               }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_ANAIKAS_DREAM_1){
            return new AutonomousBasePD(
               new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
               new PDState[]{
               new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //back up
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
               // 1 shoot preloaded
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate clockwise
               //intake 1
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
               // 2 shoot 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate clockwise
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),//back up
               //intake 2
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),//move up, right of note
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move right
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move down
               // 3 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),//move down
               // intake 3
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),//move down
               // 4 shoot
               new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move down
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),//move down
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),//move up
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.OFF),//move left
               //intake 4
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 230*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move up
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move right
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),// move down
               // 5 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),// move down
               //intake 5
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), Mechanisms.MechanismStates.INTAKING),//move down
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//rotate anticlockwise
               // 6 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),//rotate anticlockwise
               new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
               }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_ANAIKAS_DREAM_2){
            return new AutonomousBasePD(
               new Pose2d(18*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
               new PDState[]{
               new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //back up
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
               // 1 shoot preloaded
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate clockwise
               //intake 1
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate clockwise
               // 2 shoot 
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate clockwise
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),  //rotate back
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),//back up
               //intake 2
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),//move up, right of note
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move right
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move down
               // 3 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),//move down
               // intake 3
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),//move down
               // 4 shoot
               new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move down
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),//move down
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),//move up
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),//move left
               //intake 4
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 230*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move up
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//move right
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),// move down
               // 5 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),// move down
               //intake 5
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),//move down
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SPEAKER_HOLDING),//rotate anticlockwise
               // 6 shoot
               new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185))), Mechanisms.MechanismStates.SHOOTING_SPEAKER),//rotate anticlockwise
               new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
               }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_BREAD){
          return new AutonomousBasePD(
              new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 1 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.OFF), //rotate back
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(533*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.OFF),
                  //intake
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(326*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0))), Mechanisms.MechanismStates.OFF),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        }else if(selectedAuto == AUTO_OPTIONS.B_BREAD){
          return new AutonomousBasePD(
              new Pose2d(70*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(118*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.SPEAKER_HOLDING),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(118*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(140.405))), Mechanisms.MechanismStates.SPEAKER_HOLDING), //rotate
                  // 1 shoot
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(118*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(140.405))), Mechanisms.MechanismStates.SHOOTING_SPEAKER), //rotate
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(118*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF), //rotate back
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(118*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(325*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.OFF),
                  //intake
                  new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(325*Constants.METERS_PER_INCH, 37.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), Mechanisms.MechanismStates.INTAKING),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
      }else{
          System.out.println("=============================UNRECOGNIZED AUTO WHAT IS WRONG WITH YOU?!?! --PATRICIA " + selectedAuto + "=============================");
          return new AutonomousBasePD(
              new Pose2d(),
              new PDState[]{
                  new PDState(AutoStates.FIRST, Mechanisms.MechanismStates.OFF),
                  new PDState(AutoStates.STOP, Mechanisms.MechanismStates.OFF)
              }
          );
        
      }
  }
}