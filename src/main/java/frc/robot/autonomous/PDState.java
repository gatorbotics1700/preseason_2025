package frc.robot.autonomous;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.subsystems.Mechanisms.MechanismStates;

public class PDState{

    public final AutoStates name;
    public final Pose2d coordinate;
    public final MechanismStates mechState;

    //TODO: we can think about adding a MechanismStates parameter to the PDState constructor
    //that would eliminate the need for DRIVE_HOLDING
    public PDState(AutoStates name, Pose2d endCoordinate, MechanismStates mechState){
        this.name = name;
        this.coordinate = endCoordinate;
        this.mechState = mechState;
    }

    public PDState(AutoStates name, MechanismStates mechState){
        this.name = name;
        this.coordinate = null;
        this.mechState = mechState;
    }

    public static enum AutoStates{
        FIRST,
        DRIVE,
        HOLDING_TIMED,
        STOP,
    }
}