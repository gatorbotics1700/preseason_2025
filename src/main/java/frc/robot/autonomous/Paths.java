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

    public enum AUTO_OPTIONS{
        PD_TESTPATH,
        MP_TESTPATH, 
        NO_GO;
    }

    public static AutonomousBase constructAuto(AUTO_OPTIONS selectedAuto){
        if(selectedAuto == AUTO_OPTIONS.PD_TESTPATH){
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
        } else if (selectedAuto == AUTO_OPTIONS.NO_GO){
            return new AutonomousBasePD(
                new Pose2d(60.0, 0.0, new Rotation2d(Math.toRadians(180.0))), 
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

