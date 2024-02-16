package frc.robot.autonomous;

import edu.wpi.first.math.geometry.Pose2d;

public class PDState{

    public final AutoStates name;
    public final Pose2d coordinate;

    //TODO: we can think about adding a MechanismStates parameter to the PDState constructor
    //that would eliminate the need for DRIVE_HOLDING
    public PDState(AutoStates name, Pose2d endCoordinate){
        this.name = name;
        this.coordinate = endCoordinate;
    }

    public PDState(AutoStates name){
        this.name = name;
        this.coordinate = null;
    }

    public static enum AutoStates{
        FIRST,
        DRIVE,
        DRIVE_HOLDING,
        HOLDING_TIMED,
        STOP,
        INTAKING,
        OUTTAKING; 
    }
}