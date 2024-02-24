package frc.robot.autonomous;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants;
import frc.robot.autonomous.PDState.AutoStates;

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
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(45)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(100 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0)))),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_NO_GO){
            return new AutonomousBasePD(
                new Pose2d(15, 56, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_NO_GO){
            return new AutonomousBasePD(
                new Pose2d(634, 56, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_1){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_1){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                new Pose2d(15*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
      
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_2){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //warm up shooter
                    new PDState (AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_2){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                new Pose2d(15*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_3){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                new Pose2d(600*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), //start pose
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_3){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                new Pose2d(55.5*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))), //start pose
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_1){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), // rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(548*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), // intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(548*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_2){
            return new AutonomousBasePD(
                new Pose2d(15*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),// drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(103*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(103*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_2){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(548*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), // intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(548*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //3 shoot
//TODO:COME BACK TO THE NEXT THREE STEPS (di, di, dhs)
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_1){
            return new AutonomousBasePD(
                new Pose2d(15*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //1 shoot preloaded
                    //TODO: COME BACK TO THE NEXT THREE STEPS (di, di, dhs)
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),  //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),// drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(103*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(103*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_THREE_PAMP){ //TODO: changed values need to test
            return new AutonomousBasePD(
                new Pose2d(595.5*Constants.METERS_PER_INCH, 295*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270))),//start position
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(595.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //go forward, to AMP
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(580*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //move left, to center of amp to shoot
                    new PDState(AutoStates.SHOOTING_AMP, new Pose2d(580*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(537*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //move left, towards note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(537*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //TEST move down, above note, and intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(537*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //move up
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(580*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //move right, in front of AMP
                    new PDState(AutoStates.SHOOTING_AMP, new Pose2d(580*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //2 shoot
                     new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(537*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), // move left, towards piece 2
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(537*Constants.METERS_PER_INCH, 240*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))),// TEST move down, towards piece 2, andn intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(537*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //move up, towards AMP
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(580*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //move right, in front of AMP
                    new PDState(AutoStates.SHOOTING_AMP, new Pose2d(580*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_THREE_PAMP){ //TODO: changed values need to  test
            return new AutonomousBasePD(
                new Pose2d(62*Constants.METERS_PER_INCH, 295*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0))),//start position
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(62*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270)))), //go forward, to AMP
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(72*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //move left, to center of amp to shoot
                    new PDState(AutoStates.SHOOTING_AMP, new Pose2d(72*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(114*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //move left, towards note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(114*Constants.METERS_PER_INCH, 305*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //TEST move down, above note, and intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(114*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //move up
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(72*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //move right, in front of AMP
                    new PDState(AutoStates.SHOOTING_AMP, new Pose2d(72*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(114*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //move left, towards piece 2
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(114*Constants.METERS_PER_INCH, 240*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //TEST move down, towards piece 2, and intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(114*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //move up, towards AMP
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(72*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //move right, in front of AMP
                    new PDState(AutoStates.SHOOTING_AMP, new Pose2d(72*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(270.0)))), //3 shoot
                    new PDState(AutoStates.STOP)
                } 
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_3){
            return new AutonomousBasePD(
                new Pose2d(600*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),//start pose
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(558*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(558*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(558*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //intake 1
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move left, to center line
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move up, right of center piece, and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(345*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //move right, near speaker to shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.85)))), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_3){  //TODO: Angles are wrong pls test
            return new AutonomousBasePD(
                new Pose2d(51.25*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),//start pose
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(93*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(93*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(93*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 1
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move left, to center line
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move up, right of center piece, and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(306*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move down
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move right, near speaker to shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_1){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), // rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(548*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), // intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(548*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_2){
            return new AutonomousBasePD(
                new Pose2d(15*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),// drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(103*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(103*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //3 shoot
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move down and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FIVE_PIECE_2){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move over to intake 2 position
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //intake 2
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move up to next note and intake 3
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move back to next note
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move up to next note and intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move back
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //5 shoot
                new PDState(AutoStates.STOP)             
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FIVE_PIECE_1){
            return new AutonomousBasePD(
                new Pose2d(18*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //rotate
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move over to intake 2 position
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 2
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move up to next note and intake 3
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move back to next note
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move up to next note and intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(306*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move back
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //5 shoot
                new PDState(AutoStates.STOP)             
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_ANAIKAS_DREAM_1){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //back up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move up, right of note and intake 2
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //intake 3
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //warm up speaker
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move left
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 230*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //5 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down and intake 5
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate anticlockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //6 shoot
                new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_ANAIKAS_DREAM_2){
            return new AutonomousBasePD(
                new Pose2d(18*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180)))), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate clockwise
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //warm up shooter           
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate clockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),  //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //back up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move up, right of note, and intake 2
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 3
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move left
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 230*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(307*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //5 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //move down and intake 5
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //rotate anticlockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(96*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(211.185)))), //6 shoot
                new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_BREAD){
            return new AutonomousBasePD(
                new Pose2d(591*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //drive back
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595)))), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(581*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(326*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //intake 1
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_BREAD){
            return new AutonomousBasePD(
                new Pose2d(61*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(70*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //drive back
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(70*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(70*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //warm up shooter          
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(70*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(148.815)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(70*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(70*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(326*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(180.0)))), //intake 1
                    new PDState(AutoStates.STOP)
                }
            );
        }else{
            System.out.println("=============================UNRECOGNIZED AUTO WHAT IS WRONG WITH YOU?!?! --PATRICIA " + selectedAuto + "=============================");
            return new AutonomousBasePD(
                new Pose2d(),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.STOP)
                }
            );
        
        }
    }
}


