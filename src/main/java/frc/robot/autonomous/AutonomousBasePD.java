package frc.robot.autonomous;

import javax.swing.SwingWorker.StateValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants;
import frc.robot.autonomous.PDState.AutoStates;
/* 
import frc.robot.autonomous.StateWithCoordinate;
import frc.robot.autonomous.StateWithCoordinate.AutoStates;
import frc.robot.subsystems.PneumaticIntakeSubsystem;
import frc.robot.subsystems.Mechanisms.MechanismStates;
import frc.robot.subsystems.PneumaticIntakeSubsystem.PneumaticIntakeStates;
import frc.robot.subsystems.Mechanisms;
*/

public class AutonomousBasePD extends AutonomousBase{
    private static final double turnKP= 0.0001; //increased slight *** not tested
    private static final double turnKI= 0.0; 
    private static final double turnKD= 0.0;
    private static final double driveKP= 0.75; //Robot.kP.getDouble(0.00006);//0.00006;
    private static final double driveKI= 0.0; //Robot.kI.getDouble(0.0);//0.0;
    private static final double driveKD= 0.0; //Robot.kD.getDouble(0.0);//0.0;
    private static final double DRIVE_DEADBAND = 6*Constants.METERS_PER_INCH; //meters - previously 3 inches
    private static final double TURN_DEADBAND = 6.0; 

    private Pose2d startingCoordinate;
    private PDState[] stateSequence;
    private int i;
    private Boolean isFirst;
    private double startTime;
    
    private DrivetrainSubsystem drivetrainSubsystem;
    public AutoStates states;

    //pids
    private PIDController turnController;
    private PIDController xController;
    private PIDController yController;

    public AutonomousBasePD(Pose2d startingCoordinate, PDState[] stateSequence){
        this.startingCoordinate = startingCoordinate;
        this.stateSequence =  stateSequence;
        init();
    }

    @Override
    public void init(){
        System.out.println("AUTONOMOUS INIT!\nINIT!\nINIT!");
        drivetrainSubsystem = Robot.m_drivetrainSubsystem;
        drivetrainSubsystem.resetOdometry(startingCoordinate);
        turnController = new PIDController(turnKP, turnKI, turnKD); 
        xController = new PIDController(driveKP, driveKI, driveKD);
        yController = new PIDController(driveKP, driveKI, driveKD);
        xController.reset(); //Avery question: function of resetting?
        yController.reset();
        turnController.reset();
        turnController.enableContinuousInput(0, 360);
        i = 0;
        isFirst = true;
        startTime = 0.0;
    }

    @Override
    public void periodic()
    {
        states = stateSequence[i].state;
        System.out.println("state: " + states);
        if (states == AutoStates.FIRST){
            turnController.setTolerance(TURN_DEADBAND); 
            xController.setTolerance(DRIVE_DEADBAND);
            yController.setTolerance(DRIVE_DEADBAND);
            xController.setSetpoint(0); //translation not pose component
            yController.setSetpoint(0);
            turnController.setSetpoint(0);
            i++;  
            System.out.println("moving on to " + stateSequence[i]);
            return;
        }
        if (states == AutoStates.DRIVE){
            driveDesiredDistance(stateSequence[i].coordinate);
            if(xController.atSetpoint() && yController.atSetpoint() && turnController.atSetpoint()){
                i++;  
                System.out.println("moving on to " + stateSequence[i]);
            }
        } else if(states == AutoStates.INTAKING){
            if(isFirst){
                startTime = System.currentTimeMillis(); 
                isFirst = false;
            }
            if(System.currentTimeMillis()-startTime>=500){
                i++;
                isFirst = true; //Avery note: interested in why we are negating the boolean
            }
            //insert the code to intake things here. 
        }else if(states == AutoStates.FASTDRIVE){
            if(Math.abs(stateSequence[i].coordinate.getX() - drivetrainSubsystem.getMPoseX())>DRIVE_DEADBAND){
                drivetrainSubsystem.setSpeed(ChassisSpeeds.fromFieldRelativeSpeeds(1,0,0, drivetrainSubsystem.getPoseRotation()));
            }else{
                drivetrainSubsystem.setSpeed(ChassisSpeeds.fromFieldRelativeSpeeds(0,0,0, drivetrainSubsystem.getPoseRotation()));
                i++;
            }
        } else {
            drivetrainSubsystem.stopDrive();
        } 
        drivetrainSubsystem.drive(); //Avery note: is it driving just on it's own? 
    }

    /** 
    @param dPose is desired pose
    */
    @Override
    public void driveDesiredDistance(Pose2d dPose){      
        double speedX = xController.calculate(drivetrainSubsystem.getMPoseX(), dPose.getX());
        double speedY = yController.calculate(drivetrainSubsystem.getMPoseY(), dPose.getY());
        System.out.println("m_pose deg: " + drivetrainSubsystem.getMPoseDegrees() % 360);
        System.out.println("d_pose deg: " + dPose.getRotation().getDegrees() % 360);

        double speedRotate = turnController.calculate(drivetrainSubsystem.getMPoseDegrees(), dPose.getRotation().getDegrees());
        
        if(xAtSetpoint()){ //NOTE: This may be removed when the automatic at setpoint thing with vision works 
            speedX = 0; 
        } else {
            speedX = Math.signum(speedX)*Math.max(drivetrainSubsystem.MIN_VOLTAGE, Math.min(drivetrainSubsystem.MAX_VOLTAGE, Math.abs(speedX)));  
            //Avery Note: how is this setting the speed relative to the PID? 
        }
 
        if(yAtSetpoint()){
            speedY = 0; 
        } else {
            speedY = Math.signum(speedY)*Math.max(drivetrainSubsystem.MIN_VOLTAGE, Math.min(drivetrainSubsystem.MAX_VOLTAGE, Math.abs(speedY)));
        }

        if(turnAtSetpoint()){
            speedRotate = 0;
            System.out.println("At rotational setpoint");
        } else {
            //System.out.println("Position error: " + turnController.getPositionError());
            speedRotate = Math.signum(speedRotate)*Math.max(drivetrainSubsystem.MIN_VOLTAGE, Math.min(drivetrainSubsystem.MAX_VOLTAGE, Math.abs(speedRotate)));
        }

        drivetrainSubsystem.setSpeed(ChassisSpeeds.fromFieldRelativeSpeeds(speedX, speedY, speedRotate, drivetrainSubsystem.getPoseRotation()));  
        double errorX = (dPose.getX() - drivetrainSubsystem.getMPoseX());
        double errorY = (dPose.getY() - drivetrainSubsystem.getMPoseY());
        double errorRotate = turnController.getPositionError();
        System.out.println("Speed X: " + speedX + " Speed Y: " + speedY + " Speed R: " + speedRotate);
        System.out.println("error:" + errorX + ", " + errorY + ", " + errorRotate);
    }

    public boolean xAtSetpoint(){
        return Math.abs(xController.getPositionError()) <= DRIVE_DEADBAND;
    }

    public boolean yAtSetpoint(){
        return Math.abs(yController.getPositionError()) <= DRIVE_DEADBAND;
    }

    public boolean turnAtSetpoint(){
        return Math.abs(turnController.getPositionError()) <= TURN_DEADBAND;
    }

    public void setState(PDState.AutoStates newAutoState){
        states = newAutoState;
    }
}
