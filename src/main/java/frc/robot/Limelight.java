package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Limelight {
    public NetworkTable networkTable = NetworkTableInstance.getDefault().getTable("limelight");
    private final double aprilTagHeight = 37; // top speaker april tag
    private final double limelightHeight = 11; 
    private final double limelightAngle = 0;
    private final double pivotToSpeakerHeight = 68.39; //inches

    private PIDController turnController;
    private static final double turnKP= 0.0; 
    private static final double turnKI= 0.0; 
    private static final double turnKD= 0.0; 

    private static final double TURN_DEADBAND = 3;
    private static final double MAX_PIVOT_ANGLE = 98.0; // relative to the limelight angle (zero) the amp is 98 degrees

    private DrivetrainSubsystem drivetrainSubsystem;

    public void init() {
        networkTable.getEntry("pipeline").setNumber(0.0);
        turnController = new PIDController(turnKP, turnKI, turnKD); 
        turnController.reset();
    }

    public double getTv(){
        return networkTable.getEntry("tv").getDouble(0);
    }

    public double getTx(){ //in degrees
        return networkTable.getEntry("tx").getDouble(0);
    }

    public double getTy(){
        return networkTable.getEntry("ty").getDouble(0);
    }

    public double getDistance(){
        return (aprilTagHeight - limelightHeight)/ Math.tan(Math.toRadians(limelightAngle + getTy())); //tan needs radians
    }

    private boolean turnAtSetpoint(){
        return Math.abs(turnController.getPositionError()) <= TURN_DEADBAND;
    }

    public void driveToLocation(double rotation){      
        turnController.setSetpoint(drivetrainSubsystem.getPoseDegrees() + rotation);
        double speedRotate = turnController.calculate(drivetrainSubsystem.getPoseDegrees(), drivetrainSubsystem.getPoseDegrees() + rotation);
        
        if(turnAtSetpoint()){
            speedRotate = 0;
            System.out.println("At rotational setpoint");
        }

        drivetrainSubsystem.setSpeed(ChassisSpeeds.fromFieldRelativeSpeeds(0, 0, speedRotate, drivetrainSubsystem.getPoseRotation()));  
    } 

    public double getDesiredPivotAngle(){
        return -(MAX_PIVOT_ANGLE - Math.toDegrees(Math.atan(pivotToSpeakerHeight/(getDistance()+6))));
    }

    public void printTy(){
        System.out.println(getTy());
    }
}
