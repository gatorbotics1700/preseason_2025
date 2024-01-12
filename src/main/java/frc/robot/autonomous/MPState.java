package frc.robot.autonomous;

import java.lang.Thread.State;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import frc.robot.autonomous.PDState.AutoStates;

public class MPState{

    public final StatesName name;
    public final Trajectory trajectory;

    public MPState(StatesName name, Trajectory trajectory){
        this.name = name;
        this.trajectory = trajectory;
    }
//this constructor is for states that don't require driving
    public MPState(StatesName name){
        this.name = name;
        this.trajectory = null; 
    }

    public static enum StatesName{
        TRAJECTORY, 
        STOP, 
        FIRST;
    } 




}
