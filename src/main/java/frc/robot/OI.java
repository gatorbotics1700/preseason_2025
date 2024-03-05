package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
    public static final XboxController driver = new XboxController(0);//main driver/driving controller
    public static final XboxController codriver = new XboxController(1);//buttons/co-driver controller
    
    public static double getCodriverLeftAxis(){
        return codriver.getLeftY(); 
    }

    public static double getCodriverRightAxis(){
        return codriver.getRightY(); 
    }
}

