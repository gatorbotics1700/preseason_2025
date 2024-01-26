package frc.robot.autonomous;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class AutonomousBase{

    public Pose2d startingCoordinate; //this is something that would be used if we were to resetPositionManager

    public AutonomousBase(Pose2d startingCoordinate){
        this.startingCoordinate = startingCoordinate;
    }

    public Pose2d getStartingPose(){
        return startingCoordinate; 
        
    }
    
    public void init(){

    }

    public void periodic(){

    }

    
    public double getStartingPoseX(){
        return startingCoordinate.getX();
    }

    public double getStartingPoseY(){
        return startingCoordinate.getY();
    }

    public Rotation2d getStartingPoseRotation(){
        return startingCoordinate.getRotation();
    }


}