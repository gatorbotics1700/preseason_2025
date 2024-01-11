package frc.robot.autonomous;
import frc.robot.Constants;
import frc.robot.autonomous.*;
import frc.robot.autonomous.PDState.AutoStates;
import frc.robot.autonomous.MPState.MPStateLabel;

import javax.swing.plaf.nimbus.State;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;


public class Paths {
    private static double mpi = Constants.METERS_PER_INCH;
    private static final double STARTING_X = 68.95; //WHAT ARE  THESE
    //frequently used midpoints
    private static final double HB_Y_B = 200.046;
    private static final double HD_Y_B = 54.69;
    private static final double HB_Y_R = 200.046;
    private static final double HD_Y_R = 54.69;

    public enum AUTO_OPTIONS{
        TESTPATH,
        NOGO,
        HDLEAVEB,
        HBLEAVEB,
        LOWHDPLACELEAVEB,
        LOWHBPLACELEAVEB,
        MIDHDPLACELEAVEB,
        MIDHBPLACELEAVEB,
        LOW_OVER_ENGAGE,
        MID_OVER_ENGAGE,
        TIMED,
        MIDTIMEDENGAGED,
        LOWTIMEDENGAGED,
        DRIVETIMEDENGAGED,
        MP, 
        MP_HD3SCORER, 
        MP_TESTPATH,
        MP_HDLEAVEB, 
        MP_HBLEAVEB, 
        MP_LOWHDPLACELEAVEB, 
        MP_LOWHBPLACELEAVEB, 
        MP_MIDHDPLACELEAVEB, 
        MP_MIDHBPLACELEAVEB,
        MP_LOW_OVER_ENGAGE, 
        MP_MID_OVER_ENGAGE; 
    }

    public static AutonomousBase constructAuto(AUTO_OPTIONS selectedAuto){
        if(selectedAuto == AUTO_OPTIONS.TESTPATH){
            return new AutonomousBasePD(
                new Pose2d(0.0, 0.0, new Rotation2d(Math.toRadians(180.0))), 
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.DRIVE, new Pose2d(5 * mpi, -40 * mpi, new Rotation2d(Math.toRadians(180)))), 
                new PDState(AutoStates.DRIVE, new Pose2d(40 * mpi, 0, new Rotation2d(0))), 
                new PDState(AutoStates.DRIVE, new Pose2d(0, 30 * mpi, new Rotation2d(0))), 
                new PDState(AutoStates.DRIVE, new Pose2d(40 * mpi, 30 * mpi, new Rotation2d(0))), 
                new PDState(AutoStates.DRIVE, new Pose2d(0, 0, new Rotation2d(0))), 
                new PDState(AutoStates.DRIVE, new Pose2d(20 * mpi, 20 * mpi, new Rotation2d(0))),
                new PDState(AutoStates.STOP)
                }
            );
        } else if (selectedAuto == AUTO_OPTIONS.NOGO){
            return new AutonomousBasePD(
                new Pose2d(STARTING_X, 0.0, new Rotation2d(Math.toRadians(180.0))), 
                new PDState[]{
                new PDState(AutoStates.FIRST),
                new PDState(AutoStates.STOP)
                }
            );

        }else if(selectedAuto == AUTO_OPTIONS.MP_TESTPATH){
            return new AutonomousBaseMP(
                new MPState[]{
                    new MPState(MPStateLabel.FIRST),
                    new MPState(MPStateLabel.TRAJECTORY, Trajectories.uno),
                    new MPState(MPStateLabel.STOP)
                }
            );
        }
    }

}

