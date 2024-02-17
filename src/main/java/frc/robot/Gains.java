package frc.robot;
public class Gains { //Gain is a proportional value that shows the relationship between the magnitude of an input signal to the magnitude of an output signal at steady-state.
    public final double kP;
    public final double kI;
    public final double kD;
    public final int kIzone; //can be used to auto clear the integral accumulator if the sensor pos is too far from the target. This prevent unstable oscillation if the kI is too large. Value is in sensor units.
    public final double kPeakOutput; //Absolute max motor output during closed-loop control modes only. A value of ‘1’ represents full output in both directions.
    public Gains(double _kP, double _kI, double _kD, int _kIzone, double _kPeakOutput){
        kP = _kP;
        kI = _kI;
        kD = _kD;
        kIzone = _kIzone;
        kPeakOutput = _kPeakOutput;
    }
}




