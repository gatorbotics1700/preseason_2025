package frc.com.swervedrivespecialties.swervelib.ctre;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix6.StatusCode;

import edu.wpi.first.wpilibj.DriverStation;

public final class CtreUtils {
    private CtreUtils() {
    }

    public static boolean checkCtreError(StatusCode errorCode, String message) {
        if (errorCode != StatusCode.OK) {
            DriverStation.reportError(String.format("%s: %s", message, errorCode.toString()), false);
            if (errorCode == StatusCode.RxTimeout || errorCode == StatusCode.CanMessageStale){
                return true;
            }
        }
        return false;
    }

    public static boolean checkCtreError(ErrorCode errorCode, String message) {
        if (errorCode != ErrorCode.OK) {
            DriverStation.reportError(String.format("%s: %s", message, errorCode.toString()), false);
            if (errorCode == ErrorCode.CAN_MSG_NOT_FOUND || errorCode == ErrorCode.CAN_MSG_STALE){
                return true;
            }
        }
        return false;
    }
}