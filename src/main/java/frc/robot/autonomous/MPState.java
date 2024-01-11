package frc.robot.autonomous;

import java.lang.Thread.State;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import frc.robot.autonomous.PDState.AutoStates;

public class MPState{

    public final MPStateLabel stateLabel;
    public final Trajectory trajectory;

    public MPState(MPStateLabel stateLabel, Trajectory trajectory){
        this.stateLabel = stateLabel;
        this.trajectory = trajectory;
    }
//this constructor is for states that don't require driving
    public MPState(MPStateLabel stateLabel){
        this.stateLabel = stateLabel;
        this.trajectory = null; 
    }

    public static enum MPStateLabel{
        TRAJECTORY, 
        STOP, 
        FIRST;
    } 




}
