package frc.robot.autonomous;

import frc.robot.Constants;
import frc.robot.autonomous.PDState.AutoStates;
import frc.robot.autonomous.MPState.StatesName;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;


public class Paths {
    public enum AUTO_OPTIONS{
        PD_TESTPATH,
        MP_TESTPATH, 
        THREE_PIECE_A, 
        THREE_PIECE_B, 
        THREE_PAMP,
        FOUR_PIECE_L,
        BREAD,
        NO_GO;
    }

    public static AutonomousBase constructAuto(AUTO_OPTIONS selectedAuto){
        if(selectedAuto == AUTO_OPTIONS.PD_TESTPATH){
            return new AutonomousBasePD(
                new Pose2d(100.0 * Constants.METERS_PER_INCH, 0.0, new Rotation2d(Math.toRadians(0.0))), 
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(40 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(45)))),
                    //new PDState(AutoStates.DRIVE, new Pose2d(40* Constants.METERS_PER_INCH, 0, new Rotation2d(0))),
                   // new PDState(AutoStates.DRIVE, new Pose2d(100 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0)))), 
                    //new PDState(AutoStates.DRIVE, new Pose2d(0, 30 * mpi, new Rotation2d(0))), 
                   // new PDState(AutoStates.DRIVE, new Pose2d(40 * mpi, 30 * mpi, new Rotation2d(0))), 
                   // new PDState(AutoStates.DRIVE, new Pose2d(0, 0, new Rotation2d(0))), 
                    //new PDState(AutoStates.DRIVE, new Pose2d(20 * mpi, 20 * mpi, new Rotation2d(0))),
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
        }else if(selectedAuto == AUTO_OPTIONS.THREE_PIECE_A){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), 
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-39.595)))),
                    //shoot 
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //TODO test to figure out how far back to move to pick up note
                    new PDState(AutoStates.DRIVE, new Pose2d(521*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //TODO test to figure out how far back to move to pick up note
                    //intake
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-39.595)))),
                    //shoot
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(521*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(521*Constants.METERS_PER_INCH, 225.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //TODO test to figure out how far back to move to pick up note
                    //intake
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 225.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 225.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-39.595)))),
                    //shoot
                    new PDState(AutoStates.STOP)
                }
            );
        }else if(selectedAuto == AUTO_OPTIONS.THREE_PIECE_B){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), 
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
         /*turn */  new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595)))),
                    //shoot 
      /*turn back*/ new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //TODO test to figure out how far back to move to pick up note
                    new PDState(AutoStates.DRIVE, new Pose2d(521*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //TODO test to figure out how far back to move to pick up note
                    // //intake
                    //new PDState(AutoStates.INTAKING, new Pose2d(536*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
          /*turn */ new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595)))), //TODO test to figure out how far back to move to pick up note
                    //shoot
    /*turn back */  new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(521*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(521*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                    //intake
                    //new PDState(AutoStates.INTAKING, new Pose2d(536*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
          /*turn */ new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-39.595)))), //TODO test to figure out how far back to move to pick up note
                    //shoot
                    new PDState(AutoStates.STOP)
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.THREE_PAMP){
            return new AutonomousBasePD(
                new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), 
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90)))), 
                    //shoot
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(554*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    //intake
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90)))), 
                    //shoot
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 254.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(534.5*Constants.METERS_PER_INCH, 254.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    //intake
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 254.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(573*Constants.METERS_PER_INCH, 310*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90)))), 
                    //shoot
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.FOUR_PIECE_L){
            return new AutonomousBasePD(
                new Pose2d(595.5*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), 
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    //shoot
                    new PDState(AutoStates.DRIVE, new Pose2d(523*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    //intake
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    //shoot
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 276.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(523*Constants.METERS_PER_INCH, 276.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    //intake
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 276.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 276.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-39.595)))), 
                    //shoot
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 276.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 297.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(320*Constants.METERS_PER_INCH, 297.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    //intake
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 297.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 276.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 276.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-39.595)))), 
                    //shoot

                }
            );
         }else if(selectedAuto == AUTO_OPTIONS.BREAD){
            return new AutonomousBasePD(
                new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))), 
                new PDState[]{
                    new PDState(AutoStates.FIRST),
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595)))), 
                    //shoot
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(326*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    new PDState(AutoStates.DRIVE, new Pose2d(326*Constants.METERS_PER_INCH, 37.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), 
                    //in pos to intake
                }
            );
        } else if(selectedAuto == AUTO_OPTIONS.MP_TESTPATH){
            return new AutonomousBaseMP(
                new Pose2d(0,0, new Rotation2d(0)),
                new MPState[]{
                    new MPState(StatesName.FIRST),
                    new MPState(StatesName.TRAJECTORY, Trajectories.uno),
                    new MPState(StatesName.STOP)
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

