package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;

public class OI {
    public static final XboxController driver = new XboxController(0);//main driver/driving controller
    public static final XboxController codriver = new XboxController(1);//buttons/co-driver controller
    
    public static double getTwoLeftAxis(){
        return codriver.getLeftY(); 
    }

    public static double getTwoRightAxis(){
        return codriver.getRightY(); 
    }
}

