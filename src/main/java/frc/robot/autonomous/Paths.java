package frc.robot.autonomous;




import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants;
import frc.robot.autonomous.MPState.StatesName;
import frc.robot.autonomous.PDState.AutoStates;
import frc.robot.subsystems.Mechanisms.MechanismStates;;



public class Paths {
  public enum AUTO_OPTIONS{
      PD_TESTPATH,
      MP_TESTPATH,
      
      THREE_PIECE_A,
      THREE_PIECE_B,
      THREE_PAMP,
      FOUR_PIECE_A,
      FOUR_PIECE_B,
      FIVE_PIECE,
      ANAIKAS_DREAM,
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
                  new PDState(AutoStates.DRIVE, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(45)))),
                  new PDState(AutoStates.DRIVE, new Pose2d(140 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0)))),
                  new PDState(AutoStates.DRIVE, new Pose2d(100 * Constants.METERS_PER_INCH, 0, new Rotation2d(Math.toRadians(0)))),

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
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to shoot 1
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
                  // 1 shoot preloaded
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), // rotate back
                  //intake 1
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //rotate back
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 220*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to intake 2
                  //intake 2
                  // 3 shoot
                  new PDState(AutoStates.STOP)
              }
          );
      }else if(selectedAuto == AUTO_OPTIONS.THREE_PIECE_B){
          return new AutonomousBasePD(
              new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST),
                  new PDState(AutoStates.DRIVE_HOLDING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to intake 1
                  new PDState(AutoStates.DRIVE_HOLDING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
                  // 1 shoot preloaded
                  new PDState(AutoStates.OUTTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))),
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //rotate back
                  //intake 1
                  new PDState(AutoStates.INTAKING, new Pose2d(536*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                  new PDState(AutoStates.DRIVE_HOLDING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
                  // 2 shoot
                  new PDState(AutoStates.OUTTAKING, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))),
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//rotate back
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move to intake 2 position
                  //intake 2
                  new PDState(AutoStates.INTAKING, new Pose2d(536*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                  //warming up shooter
                  new PDState(AutoStates.HOLDING_TIMED, new Pose2d(536*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                  // 3 shoot
                  new PDState(AutoStates.OUTTAKING, new Pose2d(536*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                  new PDState(AutoStates.STOP)
              }
          );
      } else if(selectedAuto == AUTO_OPTIONS.THREE_PAMP){ //TODO: changed values need to  test
          return new AutonomousBasePD(
              new Pose2d(585.5*Constants.METERS_PER_INCH, 295*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0))),//start position
              new PDState[]{
                  new PDState(AutoStates.FIRST),
                  new PDState(AutoStates.DRIVE, new Pose2d(585.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))),//go forward, to AMP
                  new PDState(AutoStates.DRIVE, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))),//move left, to center of amp to shoot 
                  //1 shoot preloaded
                  new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90)))), //move left, towards note
                  new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 305*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))), // TEST move down, above note
                  //1 intake
                  new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))),//move up 
                  new PDState(AutoStates.DRIVE, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))),//move right, in front of AMP
                  //2 shoot
                  new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))),// move left, towards piece 2
                  new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 245*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))),// TEST move down, towards piece 2
                  //2 intake 
                  new PDState(AutoStates.DRIVE, new Pose2d(535*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90)))), //move up, towards AMP
                
                  new PDState(AutoStates.DRIVE, new Pose2d(575.5*Constants.METERS_PER_INCH, 315*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(90.0)))), //move right, in front of AMP
                  //3 shoot
                  new PDState(AutoStates.STOP)
              }
          );
      } else if(selectedAuto == AUTO_OPTIONS.FOUR_PIECE_A){ 
          return new AutonomousBasePD(
              new Pose2d(595.5*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),//start pose
              new PDState[]{
                  new PDState(AutoStates.FIRST),
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 222.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//back up
                  // 1 shoot preloaded
                  // intake 1
                  // 2 shoot
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
                  //intake 2
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))),//rotate
                  // 2 shoot
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//rotate back
                  new PDState(AutoStates.DRIVE, new Pose2d(345*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move left, to center line
                    new PDState(AutoStates.DRIVE, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move up, right of center piece
                  //3 intake 
                  new PDState(AutoStates.DRIVE, new Pose2d(345*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down
                  
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //move right, near speaker to shoot
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))),//rotate
                  //4 shoot
                  new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),// rotate back
                  new PDState(AutoStates.STOP)
              }
          );
       } else if(selectedAuto == AUTO_OPTIONS.FOUR_PIECE_B){
           return new AutonomousBasePD(
              new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
              new PDState(AutoStates.FIRST),
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //back up
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate
              //1 shoot preloaded 
              new PDState(AutoStates.OUTTAKING),
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back 
              // i ntake
              new PDState(AutoStates.INTAKING),
              // 2 shoot
              new PDState(AutoStates.OUTTAKING),
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down, in front of speaker
              // intake
              new PDState(AutoStates.INTAKING),
              // 3 shoot
              new PDState(AutoStates.OUTTAKING),
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //move down 
              //intake
              new PDState(AutoStates.INTAKING),
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
              //4 shoot
              new PDState(AutoStates.OUTTAKING),
              new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate back
              new PDState(AutoStates.STOP)
              }
           );
        } else if(selectedAuto == AUTO_OPTIONS.FIVE_PIECE){
            return new AutonomousBasePD(
               new Pose2d(633*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
               new PDState[]{
               new PDState(AutoStates.FIRST),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //back up 
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))), //rotate
               // 1 shoot preloaded
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //rotate back
               //intake 1
               new PDState(AutoStates.INTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 224.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))), //rotate
               // 2 shoot 
               new PDState(AutoStates.OUTTAKING),
               // intake 2
               new PDState(AutoStates.INTAKING),
               // 3 shoot
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //move up to next note
               //intake 3
               new PDState(AutoStates.INTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate
               //4 shoot
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //rotate back
               new PDState(AutoStates.DRIVE, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //move back to next note
               new PDState(AutoStates.DRIVE, new Pose2d(345*Constants.METERS_PER_INCH, 300*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //move up to next note
               //intake 4
               new PDState(AutoStates.INTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(345*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //move right
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //move back
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 281.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))),  //rotate
               //5 shoot 
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.STOP)              
               }
            );
        } else if(selectedAuto == AUTO_OPTIONS.ANAIKAS_DREAM){
            return new AutonomousBasePD(
               new Pose2d(633*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
               new PDState[]{
               new PDState(AutoStates.FIRST),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //back up
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
               // 1 shoot preloaded
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //rotate back
               //intake 1
               new PDState(AutoStates.INTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(31.185)))), //rotate clockwise
               // 2 shoot 
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),  //rotate back
               new PDState(AutoStates.DRIVE, new Pose2d(344*Constants.METERS_PER_INCH, 277.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//back up
               new PDState(AutoStates.DRIVE, new Pose2d(344*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move up, right of note
               //intake 2
               new PDState(AutoStates.INTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 299.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move right
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move down
               // 3 shoot
               new PDState(AutoStates.OUTTAKING),
               // intake 3
               new PDState(AutoStates.INTAKING),
               // 4 shoot
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move up
               new PDState(AutoStates.DRIVE, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move left
               new PDState(AutoStates.DRIVE, new Pose2d(344*Constants.METERS_PER_INCH, 230*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),
               // intake 4
               new PDState(AutoStates.INTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(344*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move up
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 249.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move right
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 220.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),// move down
               // 5 shoot
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0)))),//move down
               // intake 5
               new PDState(AutoStates.INTAKING),
               new PDState(AutoStates.DRIVE, new Pose2d(555*Constants.METERS_PER_INCH, 167*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(-31.185)))),//rotate anticlockwise
               // 6 shoot
               new PDState(AutoStates.OUTTAKING),
               new PDState(AutoStates.STOP)
               }
            );
        }else if(selectedAuto == AUTO_OPTIONS.BREAD){
          return new AutonomousBasePD(
              new Pose2d(581*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0.0))),
              new PDState[]{
                  new PDState(AutoStates.FIRST),
                  new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),
                  new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(39.595)))), //rotate
                  // 1 shoot
                  new PDState(AutoStates.OUTTAKING),
                  new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 167.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))), //rotate back
                  new PDState(AutoStates.DRIVE, new Pose2d(533*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),
                  new PDState(AutoStates.DRIVE, new Pose2d(326*Constants.METERS_PER_INCH, 53.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),
                  new PDState(AutoStates.DRIVE, new Pose2d(326*Constants.METERS_PER_INCH, 37.5*Constants.METERS_PER_INCH, new Rotation2d(Math.toRadians(0)))),
                  //in pos to intake
                  new PDState(AutoStates.INTAKING),
                  new PDState(AutoStates.STOP)
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