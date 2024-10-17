package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable;

    public LimelightSubsystem() {
        super();
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void turnOnLED() {
        limelightTable.getEntry("ledMode").setNumber(3); // 3 means force on
    }

    public void turnOffLED() {
        limelightTable.getEntry("ledMode").setNumber(1); // 1 means force off
    }

    public boolean hasValidTarget() {
        return limelightTable.getEntry("tv").getDouble(0.0) == 1.0;
    }

    public double getHorizontalOffset() {
        return limelightTable.getEntry("tx").getDouble(0.0);
    }

    public double getVerticalOffset() {
        return limelightTable.getEntry("ty").getDouble(1.0);
    }

    public double getTargetArea() {
        return limelightTable.getEntry("ta").getDouble(0.0);
    }

    public double getSkew() {
        return limelightTable.getEntry("ts").getDouble(0.0);
    }

    public double getLatency() {
        return limelightTable.getEntry("tl").getDouble(0.0);
    }

    //public void printVerticalOffset(){
    //    System.out.println(getVerticalOffset());
    //}

    @Override
    public void periodic() {
        // Called once per scheduler run. You can add diagno4stic info or telemetry here if needed.
        //System.out.println("AAAAAAAA");
        System.out.println(getTargetArea());
    }

}
