package frc.robot.autonomous;

import edu.wpi.first.math.geometry.Pose2d;

public class PDState{

    public final AutoStates name;
    public final Pose2d coordinate;

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
        DRIVE_MECH_OFF,
        DRIVE_WITH_INTAKING, //move with intake and shooter going 
        DRIVE_WITH_HOLDING_SPEAKER, //move with speaker warming up
        HOLDING_TIMED, //hold while shooter warms up (only for preloaded)
        SHOOTING_AMP, //stay still while shooting happens 
        SHOOTING_SPEAKER, //stay still while shooting happens
        INTAKING_TIMED,
        STOP;
    }
}