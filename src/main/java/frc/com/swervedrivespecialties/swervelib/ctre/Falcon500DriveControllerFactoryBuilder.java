package frc.com.swervedrivespecialties.swervelib.ctre;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.MotorOutputConfigs;

import frc.com.swervedrivespecialties.swervelib.DriveController;
import frc.com.swervedrivespecialties.swervelib.DriveControllerFactory;
import frc.com.swervedrivespecialties.swervelib.ModuleConfiguration;
import frc.robot.Constants;

public final class Falcon500DriveControllerFactoryBuilder {
    private static final double TICKS_PER_ROTATION = 2048.0;

    private static final double CAN_TIMEOUT_SC = 0.750;
    private static final int STATUS_FRAME_GENERAL_PERIOD_MS = 250;
    private static final double FREQUENCY = 1000/STATUS_FRAME_GENERAL_PERIOD_MS;


    private double nominalVoltage = Double.NaN;
    private double currentLimit = Double.NaN;

    public Falcon500DriveControllerFactoryBuilder withVoltageCompensation(double nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
        return this;
    }

    public boolean hasVoltageCompensation() {
        return Double.isFinite(nominalVoltage);
    }

    public DriveControllerFactory<ControllerImplementation, Integer> build() {
        return new FactoryImplementation();
    }

    public Falcon500DriveControllerFactoryBuilder withCurrentLimit(double currentLimit) {
        this.currentLimit = currentLimit;
        return this;
    }

    public boolean hasCurrentLimit() {
        return Double.isFinite(currentLimit);
    }

    private class FactoryImplementation implements DriveControllerFactory<ControllerImplementation, Integer> {

        @Override
        public ControllerImplementation create(Integer driveConfiguration, ModuleConfiguration moduleConfiguration) {
            TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();
            TalonFX motor = new TalonFX(driveConfiguration);
            

            MotorOutputConfigs motorOutputConfigs= new MotorOutputConfigs();
            TalonFXConfigurator talonFXConfigurator = motor.getConfigurator();


            double sensorPositionCoefficient = Math.PI * moduleConfiguration.getWheelDiameter() * moduleConfiguration.getDriveReduction() / TICKS_PER_ROTATION;
            double sensorVelocityCoefficient = sensorPositionCoefficient * 10.0;

            if (hasVoltageCompensation()) {
                motorConfiguration.Voltage.PeakForwardVoltage = nominalVoltage;
            }

            if (hasCurrentLimit()) {
                motorConfiguration.CurrentLimits.withSupplyCurrentLimit(currentLimit);
                motorConfiguration.CurrentLimits.withSupplyCurrentLimitEnable(true);
            }

            boolean haveError = CtreUtils.checkCtreError(motor.getConfigurator().apply(motorConfiguration), "Failed to configure Falcon 500"); //added can timeout on 03/03
            for(int i = 0; i < 5; i++){
                haveError =  CtreUtils.checkCtreError(motor.getConfigurator().apply(motorConfiguration), "Failed to configure Falcon 500"); //added can timeout on 03/03
                if(!haveError){
                    break;
                }
            }

            motor.setNeutralMode(NeutralModeValue.Brake);

            if(moduleConfiguration.isDriveInverted() == true){

                motorOutputConfigs.Inverted = InvertedValue.Clockwise_Positive;
                System.out.println("-------------------------------------------------------------");
                for(int i = 0; i < 5; i++){
                    haveError =  CtreUtils.checkCtreError(talonFXConfigurator.apply(motorOutputConfigs), "Failed to configure Falcon 500"); //added can timeout on 03/03
                    if(!haveError){
                        break;
                    }
                }
                motor.setNeutralMode(NeutralModeValue.Brake);

            }else{
                motorOutputConfigs.Inverted = InvertedValue.CounterClockwise_Positive;
                System.out.println("*****************************************************************************");
                for(int i = 0; i < 5; i++){
                    haveError =  CtreUtils.checkCtreError(talonFXConfigurator.apply(motorOutputConfigs), "Failed to configure Falcon 500"); //added can timeout on 03/03
                    if(!haveError){
                        break;
                    }
                }               
                motor.setNeutralMode(NeutralModeValue.Brake);

            }
            motor.optimizeBusUtilization();
        
            // Reduce CAN status frame rates
            CtreUtils.checkCtreError(
                    motor.getPosition().setUpdateFrequency(
                            FREQUENCY,
                            CAN_TIMEOUT_SC
                    ),
                    "Failed to configure drive Falcon status frame period"
            );

            return new ControllerImplementation(motor, sensorVelocityCoefficient);
        }
    }

    private class ControllerImplementation implements DriveController {
        private final TalonFX motor;
        private final double sensorVelocityCoefficient;
        private final double nominalVoltage = hasVoltageCompensation() ? Falcon500DriveControllerFactoryBuilder.this.nominalVoltage : 12.0;

        private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

        private ControllerImplementation(TalonFX motor, double sensorVelocityCoefficient) {
            this.motor = motor;
            this.sensorVelocityCoefficient = sensorVelocityCoefficient;
        }

        @Override
        public void setReferenceVoltage(double voltage) {
            motor.setControl(dutyCycleOut.withOutput(voltage / nominalVoltage));
        }

        @Override
        public double getStateVelocity() {
            return (motor.getRotorVelocity().getValue()*Constants.TICKS_PER_REV)/Constants.SWERVE_TICKS_PER_METER; 
            
        }

        @Override
        public double getPosition(){
            return (motor.getRotorPosition().getValue()*Constants.TICKS_PER_REV);
        }
    }
}