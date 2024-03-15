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
        R_THREE_PIECE_3_TOP,
        R_THREE_PIECE_3_BOTTOM,
        B_THREE_PIECE_3_TOP,
        B_THREE_PIECE_3_BOTTOM,
        R_FOUR_PIECE_3,
        B_FOUR_PIECE_3,
        R_FOUR_PIECE_1,
        R_FOUR_PIECE_2,
        B_FOUR_PIECE_1, 
        B_FOUR_PIECE_2,
        R_BREAD,
        B_BREAD,
        R_FALLBACK_1, //verified working without mech
        R_FALLBACK_2,//verified wo mech
        R_FALLBACK_3,
        B_FALLBACK_2, //verified wo mech
        B_FALLBACK_1,//verified wo mech
        B_FALLBACK_3,
        R_NO_GO,
        B_NO_GO,
        R_THREE_PIECE_1_ALT,
        B_THREE_PIECE_1_ALT,
        R_THREE_PIECE_2_ALT,
        B_THREE_PIECE_2_ALT,
        R_ONE_PIECE_AMP,
        B_ONE_PIECE_AMP,
        R_TWO_AMP,
        B_TWO_AMP,
        B_VOMIT,
        R_VOMIT,
        B_TWO_AMP_FAR,
        R_TWO_AMP_FAR,
        B_TWO_AMP_FARTHER,
        R_TWO_AMP_FARTHER,
        R_LEAVE,
        B_LEAVE;
    }

    //all left/right conventions are from robot perspective
    private static final Rotation2d RED_LEFT_TURN = new Rotation2d(Math.toRadians(38.185)); //TODO: INCREASE MORE (AND OTHER ANGLES)
    private static final Rotation2d RED_RIGHT_TURN = new Rotation2d(Math.toRadians(-38.185));
    private static final Rotation2d BLUE_LEFT_TURN = new Rotation2d(Math.toRadians(216.185)); //changed
    private static final Rotation2d BLUE_RIGHT_TURN = new Rotation2d(Math.toRadians(143.815)); //changed
    private static final Rotation2d RED_DRIVERSTATION = new Rotation2d(Math.toRadians(0));
    private static final Rotation2d BLUE_DRIVERSTATION = new Rotation2d(Math.toRadians(180));
    private static final Rotation2d AMP_ANGLE = new Rotation2d(Math.toRadians(270)); //TODO: CHECK

    private static final double R_SHOOTING_X = 564 * Constants.METERS_PER_INCH;
    private static final double R_INTAKING_X = 532 * Constants.METERS_PER_INCH;
    
    private static final double B_SHOOTING_X = 87.25 * Constants.METERS_PER_INCH;
    private static final double B_INTAKING_X = 119 * Constants.METERS_PER_INCH;

    private static final double TOP_NOTE_Y = 277.5 * Constants.METERS_PER_INCH;
    private static final double MID_NOTE_Y = 220.5 * Constants.METERS_PER_INCH;
    private static final double BOTTOM_NOTE_Y = 167.5 * Constants.METERS_PER_INCH;

    private static final double FARTHEST_NOTE_Y = 300 * Constants.METERS_PER_INCH;
    private static final double FARTHEST_INTAKING_X = 325 * Constants.METERS_PER_INCH;
    private static final double ABOVE_STAGE_Y = 250 * Constants.METERS_PER_INCH;
    private static final double SECOND_FARTHEST_NOTE_Y = 234 * Constants.METERS_PER_INCH;
    private static final double SECOND_FARTHEST_NOTE_INTAKING_X = 322*Constants.METERS_PER_INCH;


    private static final double R_STAGE_BUFFER_X = FARTHEST_INTAKING_X + (15 * Constants.METERS_PER_INCH);
    private static final double B_STAGE_BUFFER_X = FARTHEST_INTAKING_X - (15 * Constants.METERS_PER_INCH);

    private static final double BREAD_FARTHEST_Y = 30 * Constants.METERS_PER_INCH;
    private static final double R_BREAD_STARTING_X = 593 * Constants.METERS_PER_INCH;
    private static final double B_BREAD_STARTING_X = 56 * Constants.METERS_PER_INCH;

    private static final double AMP_STARTING_Y = 283.5 * Constants.METERS_PER_INCH; //up against amp safe zone
    private static final double AMP_ENDING_Y = 301.25 * Constants.METERS_PER_INCH;

    private static final double R_AMP_STARTING_X = 591.93 * Constants.METERS_PER_INCH;
    private static final double B_AMP_STARTING_X = 59.07 * Constants.METERS_PER_INCH; //a little askew from white line 
    private static final double R_AMP_X = 576.43 * Constants.METERS_PER_INCH; 
    private static final double B_AMP_X = 74.57 * Constants.METERS_PER_INCH;
    private static final double R_AMP_ENDING_X = 545.43 * Constants.METERS_PER_INCH;
    private static final double B_AMP_ENDING_X = 105.57 * Constants.METERS_PER_INCH;

    private static final double LEAVE_STARTING_Y = 78 * Constants.METERS_PER_INCH;
    private static final double R_LEAVE_ENDING_X = 516 * Constants.METERS_PER_INCH;
    private static final double B_LEAVE_ENDING_X = 135 * Constants.METERS_PER_INCH;
    
    private static final Pose2d RED_1_STARTING_POSE = new Pose2d(633*Constants.METERS_PER_INCH, TOP_NOTE_Y, RED_DRIVERSTATION);
    private static final Pose2d BLUE_1_STARTING_POSE = new Pose2d(15*Constants.METERS_PER_INCH, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION);
    private static final Pose2d RED_2_STARTING_POSE = new Pose2d(633*Constants.METERS_PER_INCH, BOTTOM_NOTE_Y, RED_DRIVERSTATION);
    private static final Pose2d BLUE_2_STARTING_POSE = new Pose2d(15*Constants.METERS_PER_INCH, TOP_NOTE_Y, BLUE_DRIVERSTATION);
    private static final Pose2d BLUE_3_STARTING_POSE = new Pose2d(55.5*Constants.METERS_PER_INCH, MID_NOTE_Y, BLUE_DRIVERSTATION);
    private static final Pose2d RED_3_STARTING_POSE = new Pose2d(600*Constants.METERS_PER_INCH, MID_NOTE_Y, RED_DRIVERSTATION);
    private static final Pose2d RED_BREAD_STARTING_POSE = new Pose2d(R_BREAD_STARTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION);
    private static final Pose2d BLUE_BREAD_STARTING_POSE = new Pose2d(B_BREAD_STARTING_X, BOTTOM_NOTE_Y,BLUE_DRIVERSTATION);
    private static final Pose2d BLUE_AMP_STARTING_POSE = new Pose2d(B_AMP_STARTING_X, AMP_STARTING_Y, AMP_ANGLE); //robot at edge of amp zone centered (ish) on the white tape line 
    private static final Pose2d RED_AMP_STARTING_POSE = new Pose2d(R_AMP_STARTING_X, AMP_STARTING_Y, AMP_ANGLE); 
    private static final Pose2d RED_LEAVE_STARTING_POSE = new Pose2d(633*Constants.METERS_PER_INCH, LEAVE_STARTING_Y, RED_DRIVERSTATION);
    private static final Pose2d BLUE_LEAVE_STARTING_POSE = new Pose2d(16*Constants.METERS_PER_INCH, LEAVE_STARTING_Y, BLUE_DRIVERSTATION);

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
                new Pose2d(15, 56, new Rotation2d(180)),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_NO_GO){
            return new AutonomousBasePD(
                new Pose2d(634, 56, new Rotation2d(0)),
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
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_1){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                BLUE_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_2){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                RED_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //move towards stage
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)), //turn towards source
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_2){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                BLUE_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FALLBACK_3){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
                RED_3_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FALLBACK_3){ // 1 shoot preloaded + get past starting zone (for auto points)
            return new AutonomousBasePD(
            BLUE_3_STARTING_POSE,
            new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_1){
            return new AutonomousBasePD(
                RED_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), // rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), // intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move toward speaker
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move away from amp
                    new PDState(AutoStates.SHOOTING_SPEAKER), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move towards speaker
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_2){
            return new AutonomousBasePD(
                BLUE_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),// intake note 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), 
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //move towards mid note shooting location
                    new PDState(AutoStates.SHOOTING_SPEAKER), //shoot 2 
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), 
                    new PDState(AutoStates.SHOOTING_SPEAKER), //shoot 3
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_2){
            return new AutonomousBasePD(
                RED_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //move towards stage
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)), //turn towards source
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //turn towards driver station
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //move towards note, intake
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),  //move towards stage
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move towards source
                    new PDState(AutoStates.SHOOTING_SPEAKER), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move towards stage
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );

        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_1){
            return new AutonomousBasePD(
                BLUE_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),  //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),//intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //drive to mid note
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //move to shooting position
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_3_TOP){
            return new AutonomousBasePD(
                RED_3_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_3_BOTTOM){
            return new AutonomousBasePD(
                RED_3_STARTING_POSE, 
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_3_TOP){
            return new AutonomousBasePD(
                BLUE_3_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_3_BOTTOM){
            return new AutonomousBasePD(
                BLUE_3_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_3){
            return new AutonomousBasePD(
                RED_3_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //rotate
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),//rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, FARTHEST_NOTE_Y, RED_DRIVERSTATION)),//move left, to center line
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(FARTHEST_INTAKING_X, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //move up, right of center piece, and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(FARTHEST_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move down
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move right, near speaker to shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER), //4 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_3){  
            return new AutonomousBasePD(
            BLUE_3_STARTING_POSE, 
            new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //back up
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //rotate
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //move left, to center line
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(FARTHEST_INTAKING_X, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //move up, right of center piece, and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(FARTHEST_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //move down
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //move right, near speaker to shoot
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)), //rotate
                    new PDState(AutoStates.SHOOTING_SPEAKER), //4 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_1){
            return new AutonomousBasePD(
                RED_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), // rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //drive towards center note
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), 
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //move down and intake 3
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //4 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.R_FOUR_PIECE_2){
            return new AutonomousBasePD(
                RED_2_STARTING_POSE,  
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)),
                    new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_1){
            return new AutonomousBasePD(
                BLUE_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_FOUR_PIECE_2){
            return new AutonomousBasePD(
                BLUE_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),// drive towards center note
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),// drive towards center note
                    new PDState(AutoStates.SHOOTING_SPEAKER), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)), //move down and intake 3
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)), 
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //4 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_BREAD){
            return new AutonomousBasePD(
                RED_BREAD_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //drive back
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BREAD_FARTHEST_Y, RED_DRIVERSTATION)), 
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(FARTHEST_INTAKING_X, BREAD_FARTHEST_Y, RED_DRIVERSTATION)), //intake 1
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_BREAD){
            return new AutonomousBasePD(
                BLUE_BREAD_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)), //drive back
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter          
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)), //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BREAD_FARTHEST_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(FARTHEST_INTAKING_X, BREAD_FARTHEST_Y, BLUE_DRIVERSTATION)), //intake 1
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_1_ALT){
            return new AutonomousBasePD(
                RED_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), // rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), // intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //move toward speaker
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_RIGHT_TURN)),
                    new PDState(AutoStates.HOLDING_TIMED), //2 seconds
                    new PDState(AutoStates.SHOOTING_SPEAKER), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, TOP_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move away from amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move towards speaker
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_1_ALT){
            return new AutonomousBasePD(
                BLUE_1_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)), //rotate
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),  //rotate back
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),//intake 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_RIGHT_TURN)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, BOTTOM_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //drive to mid note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //move to shooting position
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER),
                    new PDState(AutoStates.STOP)
                }
            );
         }else if(selectedAuto == AUTO_OPTIONS.R_THREE_PIECE_2_ALT){
            return new AutonomousBasePD(
                RED_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //move towards stage
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)), //turn towards source
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //turn towards driver station
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)), //move towards note, intake
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),  //move towards stage
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_LEFT_TURN)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //2 shoot
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, BOTTOM_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move towards source
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, MID_NOTE_Y, RED_DRIVERSTATION)), // intake 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(R_SHOOTING_X, MID_NOTE_Y, RED_DRIVERSTATION)), //move towards stage
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //3 shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_THREE_PIECE_2_ALT){
            return new AutonomousBasePD(
                BLUE_2_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //move to shoot 1
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)), //rotate clockwise
                    new PDState(AutoStates.HOLDING_TIMED), //warm up shooter
                    new PDState(AutoStates.SHOOTING_SPEAKER), //1 shoot preloaded
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //rotate back, intake 1
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),// intake note 2
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), 
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_LEFT_TURN)),
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //shoot 2
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), //move towards mid note shooting location
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_SPEAKER, new Pose2d(B_SHOOTING_X, MID_NOTE_Y, BLUE_DRIVERSTATION)), 
                    new PDState(AutoStates.HOLDING_TIMED),
                    new PDState(AutoStates.SHOOTING_SPEAKER), //shoot 3
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_ONE_PIECE_AMP){
            return new AutonomousBasePD(
                RED_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), //shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_ENDING_X, AMP_ENDING_Y, AMP_ANGLE)), //moves away from amp
                    new PDState(AutoStates.STOP) 
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_ONE_PIECE_AMP){
            return new AutonomousBasePD(
                BLUE_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), //shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_ENDING_X, AMP_ENDING_Y, AMP_ANGLE)), //moves away from amp
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_LEAVE){ //leaving the starting zone only
            return new AutonomousBasePD(
                RED_LEAVE_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_LEAVE_ENDING_X, LEAVE_STARTING_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.STOP)
                }
            );

        }else if(selectedAuto == AUTO_OPTIONS.B_LEAVE){ //leaving the starting zone only
            return new AutonomousBasePD(
                BLUE_LEAVE_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_LEAVE_ENDING_X, LEAVE_STARTING_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_TWO_AMP){ 
            return new AutonomousBasePD(
                RED_AMP_STARTING_POSE,   
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), //shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, AMP_ENDING_Y, RED_DRIVERSTATION)), //moves away from amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //moves down to line up with the top note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, RED_DRIVERSTATION)), //INTAKE
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_INTAKING_X, TOP_NOTE_Y, AMP_ANGLE)), //turn
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, TOP_NOTE_Y, AMP_ANGLE)), //move x
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //move y
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_TWO_AMP){
            return new AutonomousBasePD(
                BLUE_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), //shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, AMP_ENDING_Y, BLUE_DRIVERSTATION)), // moves away from amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)),  //moves down to line up with the top note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, BLUE_DRIVERSTATION)), //INTAKE
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_INTAKING_X, TOP_NOTE_Y, AMP_ANGLE)), //turn
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, TOP_NOTE_Y, AMP_ANGLE)), //move x
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //move y
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_TWO_AMP_FAR){ //TODO: CHECK THIS PATH AND ANY AFTER IT
            return new AutonomousBasePD(
                BLUE_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), // shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, AMP_ENDING_Y, BLUE_DRIVERSTATION)),  //moves away from amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //moves down to line up with the fart top note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //INTAKE
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, AMP_ANGLE)), //turn
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, FARTHEST_NOTE_Y, AMP_ANGLE)), //move x
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //move y
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_TWO_AMP_FAR){
            return new AutonomousBasePD(
                RED_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), // shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, AMP_ENDING_Y, RED_DRIVERSTATION)), //moves away from amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //moves down to line up with the far top note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //INTAKE
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, AMP_ANGLE)), //turn
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, FARTHEST_NOTE_Y, AMP_ANGLE)), //move x
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //move y
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_TWO_AMP_FARTHER){
            return new AutonomousBasePD(
                BLUE_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), // shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, AMP_ENDING_Y, BLUE_DRIVERSTATION)), //moves away from amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //moves down to line up with the farthest (below the farther) note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(261*Constants.METERS_PER_INCH, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //intake
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(261*Constants.METERS_PER_INCH, SECOND_FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, SECOND_FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //intake
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, SECOND_FARTHEST_NOTE_Y, AMP_ANGLE)), //turn
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, AMP_ANGLE)), 
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, FARTHEST_NOTE_Y, AMP_ANGLE)), //move x
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //move y
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_TWO_AMP_FARTHER){
            return new AutonomousBasePD(
                RED_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //moves up to amp
                    new PDState(AutoStates.SHOOTING_AMP), //shoots preloaded piece into amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, AMP_ENDING_Y, RED_DRIVERSTATION)), //moves away from amp
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //moves down to line up with the farthest (below the farther) note
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(379*Constants.METERS_PER_INCH, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), 
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(379*Constants.METERS_PER_INCH, SECOND_FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, SECOND_FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //intake
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, SECOND_FARTHEST_NOTE_Y, AMP_ANGLE)), //turn
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, AMP_ANGLE)), //TURN
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, FARTHEST_NOTE_Y, AMP_ANGLE)), //move x
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //move y
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.B_VOMIT){ //TODO: IF THESE ARE SUPPOSED TO THROW PIECES TOWARDS OUR SIDE OF THE FIELD, THEY CURRENTLY DONT
            return new AutonomousBasePD(
                BLUE_AMP_STARTING_POSE,
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(B_AMP_X, AMP_ENDING_Y, AMP_ANGLE)), //
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, AMP_ENDING_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(B_AMP_X, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //INTAKE
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(214*Constants.METERS_PER_INCH, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(261*Constants.METERS_PER_INCH, FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(261*Constants.METERS_PER_INCH, SECOND_FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, SECOND_FARTHEST_NOTE_Y, BLUE_DRIVERSTATION)), // intake
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, 260*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(214*Constants.METERS_PER_INCH, 260*Constants.METERS_PER_INCH, BLUE_DRIVERSTATION)), // 
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.R_VOMIT){
            return new AutonomousBasePD(
                new Pose2d(B_AMP_STARTING_X, AMP_STARTING_Y, new Rotation2d(Math.toRadians(270))),
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_STARTING_X, AMP_ENDING_Y, AMP_ANGLE)), //basically where the amp is - our length
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(R_AMP_X, AMP_ENDING_Y, AMP_ANGLE)),
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, AMP_ENDING_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(R_AMP_X, FARTHEST_NOTE_Y, RED_DRIVERSTATION)),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(SECOND_FARTHEST_NOTE_INTAKING_X, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //INTAKE
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(475*Constants.METERS_PER_INCH, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //
                    new PDState(AutoStates.SHOOTING_AMP),
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(393*Constants.METERS_PER_INCH, FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(393*Constants.METERS_PER_INCH, SECOND_FARTHEST_NOTE_Y, RED_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_INTAKING, new Pose2d(330*Constants.METERS_PER_INCH, SECOND_FARTHEST_NOTE_Y, RED_DRIVERSTATION)), // intake
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(330*Constants.METERS_PER_INCH, 260*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), //
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(475*Constants.METERS_PER_INCH, 260*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), // 
                    new PDState(AutoStates.DRIVE_WITH_HOLDING_AMP, new Pose2d(475*Constants.METERS_PER_INCH, 260*Constants.METERS_PER_INCH, RED_DRIVERSTATION)), // 
                    new PDState(AutoStates.SHOOTING_AMP),
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