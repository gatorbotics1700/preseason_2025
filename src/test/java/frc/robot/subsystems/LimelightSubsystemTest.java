// package frc.robot.subsystems;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import frc.robot.subsystems.LimelightSubsystem;

// public class LimelightSubsystemTest {
//     private final LimelightSubsystem limelight = new LimelightSubsystem();
    
//     @Test
//     public void testConvertToRobotSpace1() {
//         final double[] cameraSpacePose = {0.31, 0, 2.87,0,0,0}; //array in field relative axis labels would be y, z, x, something, -yaw, something
//         final Pose2d result = limelight.convertCameraSpaceToRobotSpace(limelight.arrayToPose(cameraSpacePose));
        
//         assertAll("Robot at origin, facing positive X",
//             () -> assertEquals(3.00, result.getX(), 0.1, "X coordinate should match"),
//             () -> assertEquals(0, result.getY(), 0.1, "Y coordinate should match"),
//             () -> assertEquals(0, result.getRotation().getRadians(), 0.01, "Rotation should match")
//         );
//     }

//     @Test
//     public void testConvertToRobotSpace2() {
//         final double[] cameraSpacePose = {2.25, 0, 3.26,0,10,0}; //array in field relative axis labels would be y, z, x, something, -yaw, something
//         final Pose2d result = limelight.convertCameraSpaceToRobotSpace(limelight.arrayToPose(cameraSpacePose));
        
//         assertAll("Robot at origin, facing positive X",
//             () -> assertEquals(3.39, result.getX(), 0.1, "X coordinate should match"),
//             () -> assertEquals(-1.94, result.getY(), 0.1, "Y coordinate should match"),
//             () -> assertEquals(Math.toRadians(-10), result.getRotation().getRadians(), 0.01, "Rotation should match")
//         );
//     }
// } 