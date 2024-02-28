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
        R_THREE_PIECE_1, //verified without mech
        B_THREE_PIECE_1, //verified without mech
        R_THREE_PIECE_2, //verified wo mech
        B_THREE_PIECE_2, //verified wo mech
        R_THREE_PAMP, //not using
        B_THREE_PAMP,// not using
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
        R_FALLBACK_1, //verified working without mech
        R_FALLBACK_2,//verified wo mech
        R_FALLBACK_3,
        B_FALLBACK_2, //verified wo mech
        B_FALLBACK_1,//verified wo mech
        B_FALLBACK_3,
        R_NO_GO,
        B_NO_GO;
    }

    //all left/right conventions are from robot perspective
    private static final Rotation2d RED_LEFT_TURN = new Rotation2d(Math.toRadians(31.185));
    private static final Rotation2d RED_RIGHT_TURN = new Rotation2d(Math.toRadians(-31.185));
    private static final Rotation2d BLUE_LEFT_TURN = new Rotation2d(Math.toRadians(211.185));
    private static final Rotation2d BLUE_RIGHT_TURN = new Rotation2d(Math.toRadians(148.815));
    private static final Rotation2d RED_DRIVERSTATION = new Rotation2d(Math.toRadians(0.0));
    private static final Rotation2d BLUE_DRIVERSTATION = new Rotation2d(Math.toRadians(180.0));
    private static final double R1_B2_STARTING_Y = 277.5*Constants.METERS_PER_INCH;
    private static final double R2_B1_STARTING_Y = 167.5*Constants.METERS_PER_INCH;
    private static final double R3_B3_STARTING_Y = 222.5*Constants.METERS_PER_INCH;
    private static final Pose2d RED_1_STARTING_POSE = new Pose2d(633*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, RED_DRIVERSTATION);
    private static final Pose2d BLUE_1_STARTING_POSE = new Pose2d(15*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_DRIVERSTATION);
    private static final Pose2d RED_2_STARTING_POSE = new Pose2d(633*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, RED_DRIVERSTATION);
    private static final Pose2d BLUE_2_STARTING_POSE = new Pose2d(15*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, BLUE_DRIVERSTATION);
    private static final Pose2d BLUE_3_STARTING_POSE = new Pose2d(55.5*Constants.METERS_PER_INCH, R3_B3_STARTING_Y, BLUE_DRIVERSTATION);
    private static final Pose2d RED_3_STARTING_POSE = new Pose2d(600*Constants.METERS_PER_INCH, R3_B3_STARTING_Y, RED_DRIVERSTATION);
    private static final double RED_X_OFFCENTER_SHOOTING = 555*Constants.METERS_PER_INCH;
    private static final double RED_X_CENTER_SHOOTING = 548*Constants.METERS_PER_INCH;
    private static final double BLUE_X_OFFCENTER_SHOOTING = 96*Constants.METERS_PER_INCH;
    private static final double BLUE_X_CENTER_SHOOTING = 103*Constants.METERS_PER_INCH;
    private static final double CENTER_Y = 220*Constants.METERS_PER_INCH;




    public static AutonomousBase constructAuto(AUTO_OPTIONS selectedAuto){
        if(selectedAuto == AUTO_OPTIONS.PD_TESTPATH){
            return new AutonomousBasePD(
                new Pose2d(100.0 * Constants.METERS_PER_INCH, 0.0, RED_DRIVERSTATION),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(45)))),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(140 * Constants.METERS_PER_INCH, 0, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(100 * Constants.METERS_PER_INCH, 0, RED_DRIVERSTATION)),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_NO_GO){
            return new AutonomousBasePD(
                new Pose2d(15, 56, BLUE_DRIVERSTATION),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_NO_GO){
            return new AutonomousBasePD(
                new Pose2d(634, 56, RED_DRIVERSTATION),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_1){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                RED_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_RIGHT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_RIGHT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_RIGHT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_1){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                BLUE_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_RIGHT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_RIGHT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_2){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                RED_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_DRIVERSTATION)), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_LEFT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_LEFT_TURN)), //warm up shooter
                    new PDState (AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_LEFT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_2){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                BLUE_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_LEFT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_LEFT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_LEFT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_3){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                RED_3_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_3){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
            BLUE_3_STARTING_POSE,
            new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_1){
            return new AutonomousBasePD(
                RED_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_RIGHT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_RIGHT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_RIGHT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_1_STARTING_POSE.getY(), RED_DRIVERSTATION)), // rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_CENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_CENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_2){
            return new AutonomousBasePD(
                BLUE_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_LEFT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_LEFT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_LEFT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_2_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)),// drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_CENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_CENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_2){
            return new AutonomousBasePD(
                RED_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_DRIVERSTATION)), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_LEFT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_LEFT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING,RED_2_STARTING_POSE.getY(), RED_LEFT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_2_STARTING_POSE.getY(), RED_DRIVERSTATION)),  //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_CENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_CENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_1){
            return new AutonomousBasePD(
                BLUE_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //move to intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_RIGHT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_RIGHT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_1_STARTING_POSE.getY(), BLUE_DRIVERSTATION)),  //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)),// drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_CENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_CENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_3){
            return new AutonomousBasePD(
                RED_3_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(558*Constants.METERS_PER_INCH, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(558*Constants.METERS_PER_INCH, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(558*Constants.METERS_PER_INCH, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //intake 1
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, RED_3_STARTING_POSE.getY(), RED_DRIVERSTATION)), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)),//rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, RED_DRIVERSTATION)),//move left, to center line
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move up, right of center piece, and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(345*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //move down
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //move right, near speaker to shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, new Rotation2d(Math.toRadians(31.85)))), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_3){  
            return new AutonomousBasePD(
            BLUE_3_STARTING_POSE, 
            new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(93*Constants.METERS_PER_INCH, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(93*Constants.METERS_PER_INCH, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(93*Constants.METERS_PER_INCH, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //intake 1
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, BLUE_3_STARTING_POSE.getY(), BLUE_DRIVERSTATION)), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //move left, to center line
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move up, right of center piece, and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(306*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //move down
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //move right, near speaker to shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_1){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, RED_DRIVERSTATION),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), // rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(548*Constants.METERS_PER_INCH, CENTER_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(548*Constants.METERS_PER_INCH, CENTER_Y, RED_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, CENTER_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_DRIVERSTATION)), //move down and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_RIGHT_TURN)), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_2){
            return new AutonomousBasePD(
                new Pose2d(15*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, BLUE_DRIVERSTATION),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)),// drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(103*Constants.METERS_PER_INCH, CENTER_Y, BLUE_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(103*Constants.METERS_PER_INCH, CENTER_Y, BLUE_DRIVERSTATION)), //3 shoot
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, CENTER_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_DRIVERSTATION)), //move down and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_LEFT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_LEFT_TURN)), //4 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FIVE_PIECE_2){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, RED_DRIVERSTATION),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_DRIVERSTATION)), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_RIGHT_TURN)), //rotate
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_RIGHT_TURN)), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_RIGHT_TURN)), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_DRIVERSTATION)), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move over to intake 2 position
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //intake 2
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move up to next note and intake 3
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, RED_LEFT_TURN)), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, RED_LEFT_TURN)), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move back to next note
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move up to next note and intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move back
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, RED_LEFT_TURN)), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, RED_LEFT_TURN)), //5 shoot
                new PDState(AutoStates.STOP)             
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FIVE_PIECE_1){
            return new AutonomousBasePD(
                new Pose2d(18*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_DRIVERSTATION),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_DRIVERSTATION)), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_LEFT_TURN)), //rotate
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_LEFT_TURN)), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_LEFT_TURN)), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_DRIVERSTATION)), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move over to intake 2 position
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //intake 2
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 224.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move up to next note and intake 3
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, BLUE_RIGHT_TURN)), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, BLUE_RIGHT_TURN)), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move back to next note
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(306*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move up to next note and intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(306*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move back
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, BLUE_RIGHT_TURN)), //rotate
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 281.5*Constants.METERS_PER_INCH, BLUE_RIGHT_TURN)), //5 shoot
                new PDState(AutoStates.STOP)             
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_ANAIKAS_DREAM_1){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, RED_DRIVERSTATION),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //rotate clockwise
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //rotate clockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_LEFT_TURN)), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, RED_DRIVERSTATION)), //back up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move up, right of note and intake 2
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 299.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //intake 3
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(RED_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //warm up speaker
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, 249.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move left
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(344*Constants.METERS_PER_INCH, 230*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 249.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //5 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_DRIVERSTATION)), //move down and intake 5
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_RIGHT_TURN)), //rotate anticlockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(RED_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, RED_RIGHT_TURN)), //6 shoot
                new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_ANAIKAS_DREAM_2){
            return new AutonomousBasePD(
                new Pose2d(18*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, BLUE_DRIVERSTATION),
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, new Rotation2d(Math.toRadians(180)))), //back up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //rotate clockwise
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //warm up shooter           
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //1 shoot preloaded
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //rotate back and intake 1
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //rotate clockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_RIGHT_TURN)), //2 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)),  //rotate back
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, R1_B2_STARTING_Y, BLUE_DRIVERSTATION)), //back up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move up, right of note, and intake 2
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 299.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //3 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //intake 3
                new PDState(AutoStates.HOLDING_TIMED, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //warm up shooter
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //4 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 249.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move up
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move left
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(307*Constants.METERS_PER_INCH, 230*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //intake 4
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(307*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move up
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 249.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move right
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //move down
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 220.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //5 shoot
                new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_DRIVERSTATION)), //move down and intake 5
                new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, R2_B1_STARTING_Y, BLUE_LEFT_TURN)), //rotate anticlockwise
                new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(BLUE_X_OFFCENTER_SHOOTING, 167*Constants.METERS_PER_INCH, BLUE_LEFT_TURN)), //6 shoot
                new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_BREAD){
            return new AutonomousBasePD(
                new Pose2d(591*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, RED_DRIVERSTATION),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(581*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, RED_DRIVERSTATION)), //drive back
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(581*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, new Rotation2d(Math.toRadians(39.595)))), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(581*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, new Rotation2d(Math.toRadians(39.595)))), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(581*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, new Rotation2d(Math.toRadians(39.595)))), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(581*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, RED_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(581*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), 
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(326*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //intake 1
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_BREAD){
            return new AutonomousBasePD(
                new Pose2d(61*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_DRIVERSTATION),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(70*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_DRIVERSTATION)), //drive back
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(70*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED, new Pose2d(70*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_RIGHT_TURN)), //warm up shooter          
                    new PDState(AutoStates.SHOOTING_SPEAKER, new Pose2d(70*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_RIGHT_TURN)), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(70*Constants.METERS_PER_INCH, R2_B1_STARTING_Y, BLUE_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(70*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(326*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //intake 1
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




    /* } else if(selectedAuto == AUTO_OPTIONS.R_THREE_PAMP){ //not using
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
        } else if(selectedAuto == AUTO_OPTIONS.B_THREE_PAMP){ //not using
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
        */
}


