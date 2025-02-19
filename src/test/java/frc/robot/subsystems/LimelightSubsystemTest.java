package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightSubsystemTest {
    private final LimelightSubsystem limelight = new LimelightSubsystem();
    
    @Test
    public void testConvertToFieldSpaceAtOrigin() {
        final Pose2d robotPose = new Pose2d(0, 0, new Rotation2d(0));
        final double[] cameraSpacePose = {0,0,0,0,0,0};
        final Pose2d result = limelight.aprilTagPoseInFieldSpace(robotPose, cameraSpacePose);
        
        assertAll("Robot at origin, facing positive X",
            () -> assertEquals(2.0, result.getX(), 0.01, "X coordinate should match"),
            () -> assertEquals(1.0, result.getY(), 0.01, "Y coordinate should match"),
            () -> assertEquals(Math.PI/4, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }
    
    @Test
    public void testConvertToFieldSpaceRotated90() {
        final Pose2d robotPose = new Pose2d(1, 1, new Rotation2d(Math.PI/2));
        final Pose2d cameraSpacePose = new Pose2d(2, 1, new Rotation2d(0));
        final Pose2d result = limelight.convertToFieldSpace(cameraSpacePose, robotPose);
        
        assertAll("Robot rotated 90° CCW at (1,1)",
            () -> assertEquals(0.0, result.getX(), 0.01, "X coordinate should match"),
            () -> assertEquals(3.0, result.getY(), 0.01, "Y coordinate should match"),
            () -> assertEquals(Math.PI/2, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }

    @Test
    public void testConvertToFieldSpaceFacingNegativeX() {
        final Pose2d robotPose = new Pose2d(-1, 2, new Rotation2d(Math.PI));
        final Pose2d cameraSpacePose = new Pose2d(1, 0, new Rotation2d(Math.PI/6));
        final Pose2d result = limelight.convertToFieldSpace(cameraSpacePose, robotPose);
        
        assertAll("Robot at (-1,2) facing negative X",
            () -> assertEquals(-2.0, result.getX(), 0.01, "X coordinate should match"),
            () -> assertEquals(2.0, result.getY(), 0.01, "Y coordinate should match"),
            () -> assertEquals(-5*Math.PI/6, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }

    @Test
    public void testConvertToFieldSpaceDiagonal() {
        final Pose2d robotPose = new Pose2d(0, 0, new Rotation2d(Math.PI/4));
        final Pose2d cameraSpacePose = new Pose2d(Math.sqrt(2), 0, new Rotation2d(-Math.PI/4));
        final Pose2d result = limelight.convertToFieldSpace(cameraSpacePose, robotPose);
        
        assertAll("Robot at origin, 45° with diagonal target",
            () -> assertEquals(1.0, result.getX(), 0.01, "X coordinate should match"),
            () -> assertEquals(1.0, result.getY(), 0.01, "Y coordinate should match"),
            () -> assertEquals(0.0, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }
} 