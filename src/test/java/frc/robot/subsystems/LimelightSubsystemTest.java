package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightSubsystemTest {
    private final LimelightSubsystem limelight = new LimelightSubsystem();
    
    //@Test
    // public void testConvertToFieldSpaceAtOrigin() {
    //     final Pose2d robotPose = new Pose2d(0, 0, new Rotation2d(0));
    //     final double[] cameraSpacePose = {0.31, 0, 0.87,0,0,0}; //array in field relative axis labels would be y, z, x, something, -yaw, something
    //     final Pose2d result = limelight.aprilTagPoseInFieldSpace(robotPose, cameraSpacePose);
        
    //     assertAll("Robot at origin, facing positive X",
    //         () -> assertEquals(0.73, result.getX(), 0.1, "X coordinate should match"),
    //         () -> assertEquals(0.0, result.getY(), 0.1, "Y coordinate should match"),
    //         () -> assertEquals(0, result.getRotation().getRadians(), 0.01, "Rotation should match")
    //     );
    // }

    // @Test
    // public void testTagPoseRotated45() {
    //     final Pose2d robotPose = new Pose2d(0, 0, new Rotation2d(Math.toRadians(45)));
    //     final double[] cameraSpacePose = {1.01, 0, 1.99, 0, 45,0}; //array in field relative axis labels would be y, z, x, something, -yaw, something
    //     final Pose2d result = limelight.aprilTagPoseInFieldSpace(robotPose, cameraSpacePose);
        
    //     assertAll("Robot at origin, facing positive X",
    //         () -> assertEquals(0.61, result.getX(), 0.1, "X coordinate should match"),
    //         () -> assertEquals(2, result.getY(), 0.1, "Y coordinate should match"),
    //         () -> assertEquals(0, result.getRotation().getRadians(), 0.01, "Rotation should match")
    //     );
    // }

    // @Test
    // public void testTagPoseRotated160() {
    //     final Pose2d robotPose = new Pose2d(3, 0, new Rotation2d(Math.toRadians(-160)));
    //     final double[] cameraSpacePose = {-0.55, 0, 3.37, 0, 160,0}; //array in field relative axis labels would be y, z, x, something, -yaw, something
    //     final Pose2d botSpace = limelight.convertCameraSpaceToRobotSpace(limelight.arrayToPose(cameraSpacePose));
    //     final Pose2d fieldSpace = limelight.convertToFieldSpace(botSpace, robotPose);
    //     final Pose2d result = limelight.offsetToFrontCenter(fieldSpace);
    //     // assertAll("Robot at origin, facing positive X",
    //     //     () -> assertEquals(3.5, botSpace.getX(), 0.5, "X coordinate should match"),
    //     //     () -> assertEquals(0.8, botSpace.getY(), 0.5, "Y coordinate should match"),
    //     //     () -> assertEquals(Math.toRadians(-160), botSpace.getRotation().getRadians(), 0.01, "Rotation should match")
    //     // );

    //     assertAll("Robot at origin, facing positive X",
    //     () -> assertEquals(0, fieldSpace.getX(), 0.1, "X coordinate should match"),
    //     () -> assertEquals(2, fieldSpace.getY(), 0.1, "Y coordinate should match"),
    //     () -> assertEquals(180, fieldSpace.getRotation().getRadians(), 0.01, "Rotation should match")
    // );
        
    //     // assertAll("Robot at origin, facing positive X",
    //     //     () -> assertEquals(2.61, result.getX(), 0.1, "X coordinate should match"),
    //     //     () -> assertEquals(2, result.getY(), 0.1, "Y coordinate should match"),
    //     //     () -> assertEquals(180, result.getRotation().getRadians(), 0.01, "Rotation should match")
    //     // );
    // }

    @Test
    public void testConvertToField1() {
        final Pose2d robotPoseInFieldSpace = new Pose2d(0, 0, new Rotation2d(Math.toRadians(0)));
        final Pose2d aprilTagBotSpace = new Pose2d(2,0,new Rotation2d(0)); //array in field relative axis labels would be y, z, x, something, -yaw, something
        final Pose2d result = limelight.convertToFieldSpace(robotPoseInFieldSpace, aprilTagBotSpace);
        
        assertAll("Robot at origin, facing positive X",
            () -> assertEquals(2, result.getX(), 0.1, "X coordinate should match"),
            () -> assertEquals(0, result.getY(), 0.1, "Y coordinate should match"),
            () -> assertEquals(0, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }

    @Test
    public void testConvertToField2() {
        final Pose2d robotPoseInFieldSpace = new Pose2d(0, 0, new Rotation2d(Math.toRadians(45)));
        final Pose2d aprilTagBotSpace = new Pose2d(1.41, -1.41,new Rotation2d(Math.toRadians(-45))); //array in field relative axis labels would be y, z, x, something, -yaw, something
        final Pose2d result = limelight.convertToFieldSpace(robotPoseInFieldSpace, aprilTagBotSpace);
        
        assertAll("Robot at origin, facing positive X",
            () -> assertEquals(2, result.getX(), 0.1, "X coordinate should match"),
            () -> assertEquals(0, result.getY(), 0.1, "Y coordinate should match"),
            () -> assertEquals(0, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }

    @Test
    public void testConvertToField3() {
        final Pose2d robotPoseInFieldSpace = new Pose2d(0, 0, new Rotation2d(Math.toRadians(70)));
        final Pose2d aprilTagBotSpace = new Pose2d(2.56, -1.20, new Rotation2d(Math.toRadians(-70))); //array in field relative axis labels would be y, z, x, something, -yaw, something
        final Pose2d result = limelight.convertToFieldSpace(robotPoseInFieldSpace, aprilTagBotSpace);
        
        assertAll("Robot at origin, facing positive X",
            () -> assertEquals(2, result.getX(), 0.1, "X coordinate should match"),
            () -> assertEquals(2, result.getY(), 0.1, "Y coordinate should match"),
            () -> assertEquals(0, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }

    @Test
    public void testConvertToFieldWithOffset4() {
        final Pose2d robotPoseInFieldSpace = new Pose2d(2, 4, new Rotation2d(Math.toRadians(-100)));
        final Pose2d aprilTagBotSpace = new Pose2d(2.14, -0.64, new Rotation2d(Math.toRadians(10))); //array in field relative axis labels would be y, z, x, something, -yaw, something
        final Pose2d fieldSpace = limelight.convertToFieldSpace(robotPoseInFieldSpace, aprilTagBotSpace);
        final Pose2d result = limelight.offsetToFrontCenter(fieldSpace);
        
        assertAll("Robot at origin, facing positive X",
            () -> assertEquals(1, result.getX(), 0.1, "X coordinate should match"),
            () -> assertEquals(2.3937, result.getY(), 0.1, "Y coordinate should match"),
            () -> assertEquals(-Math.PI/2, result.getRotation().getRadians(), 0.01, "Rotation should match")
        );
    }

    // @Test
    // public void testConvertToFieldSpaceRotated45() {
    //     final Pose2d robotPose = new Pose2d(0, 0, new Rotation2d(Math.toRadians(-45)));
    //     final Pose2d robotSpacePose = new Pose2d(1.41, 0, new Rotation2d(0));
    //     final Pose2d result = limelight.convertToFieldSpace(robotSpacePose, robotPose);
        
    //     assertAll("Robot at origin, facing positive X",
    //         () -> assertEquals(0.73, result.getX(), 0.1, "X coordinate should match"),
    //         () -> assertEquals(0.0, result.getY(), 0.1, "Y coordinate should match"),
    //         () -> assertEquals(Math.toRadians(54.1), result.getRotation().getRadians(), 0.01, "Rotation should match")
    //     );
    // }

    // @Test
    // public void testConvertToBotSpaceRotated45() {
    //     final Pose2d robotPose = new Pose2d(0, 0, new Rotation2d(-45));
    //     final double[] cameraSpacePose = {0.30, 0, 1.28,0,-45,0};
    //     final Pose2d result = limelight.convertCameraSpaceToRobotSpace(limelight.arrayToPose(cameraSpacePose));
        
    //     assertAll("Robot at origin, facing positive X",
    //         () -> assertEquals(1.41, result.getX(), 0.1, "X coordinate should match"),
    //         () -> assertEquals(0.0, result.getY(), 0.1, "Y coordinate should match"),
    //         () -> assertEquals(Math.PI/4, result.getRotation().getRadians(), 0.01, "Rotation should match")
    //     );
    // }

    // @Test
    // public void testConvertToFieldSpaceFacingNegativeX() {
    //     final Pose2d robotPose = new Pose2d(-1, 2, new Rotation2d(Math.PI));
    //     final Pose2d cameraSpacePose = new Pose2d(1, 0, new Rotation2d(Math.PI/6));
    //     final Pose2d result = limelight.convertToFieldSpace(cameraSpacePose, robotPose);
        
    //     assertAll("Robot at (-1,2) facing negative X",
    //         () -> assertEquals(-2.0, result.getX(), 0.01, "X coordinate should match"),
    //         () -> assertEquals(2.0, result.getY(), 0.01, "Y coordinate should match"),
    //         () -> assertEquals(-5*Math.PI/6, result.getRotation().getRadians(), 0.01, "Rotation should match")
    //     );
    // }

    // @Test
    // public void testConvertToFieldSpaceDiagonal() {
    //     final Pose2d robotPose = new Pose2d(0, 0, new Rotation2d(Math.PI/4));
    //     final Pose2d cameraSpacePose = new Pose2d(Math.sqrt(2), 0, new Rotation2d(-Math.PI/4));
    //     final Pose2d result = limelight.convertToFieldSpace(cameraSpacePose, robotPose);
        
    //     assertAll("Robot at origin, 45° with diagonal target",
    //         () -> assertEquals(1.0, result.getX(), 0.01, "X coordinate should match"),
    //         () -> assertEquals(1.0, result.getY(), 0.01, "Y coordinate should match"),
    //         () -> assertEquals(0.0, result.getRotation().getRadians(), 0.01, "Rotation should match")
    //     );
    // }
} 