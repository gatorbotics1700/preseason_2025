package frc.robot.autonomous;

import java.lang.Thread.State;

import edu.wpi.first.math.geometry.Pose2d;

public class PDState{

    public final AutoStates name;
    public final Pose2d coordinate;

    public PDState(AutoStates name, Pose2d coordinate){
        this.name = name;
        this.coordinate = coordinate;
    }

    public PDState(AutoStates name){
        this.name = name;
        this.coordinate = null;
    }

    public static enum AutoStates{
        FIRST,
        DRIVE,
        STOP,
        INTAKING,
        OUTTAKING,
        FASTDRIVE; 
    } 




}

