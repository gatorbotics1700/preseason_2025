package frc.com.swervedrivespecialties.swervelib.ctre;

// import com.ctre.phoenix.sensors.AbsoluteSensorRange;
// import com.ctre.phoenix.sensors.CANCoder;
// import com.ctre.phoenix.sensors.CANCoderConfiguration;
// import com.ctre.phoenix.sensors.CANCoderStatusFrame;
// import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.ctre.phoenix6.configs.CANcoderConfiguration;

import frc.com.swervedrivespecialties.swervelib.AbsoluteEncoder;
import frc.com.swervedrivespecialties.swervelib.AbsoluteEncoderFactory;

public class CanCoderFactoryBuilder {
    private Direction direction = Direction.COUNTER_CLOCKWISE;
    private int periodMilliseconds = 10;
    private int FREQUENCY = 1000 / periodMilliseconds;

    public CanCoderFactoryBuilder withReadingUpdatePeriod(int periodMilliseconds) {
        this.periodMilliseconds = periodMilliseconds;
        return this;
    }

    public CanCoderFactoryBuilder withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public AbsoluteEncoderFactory<CanCoderAbsoluteConfiguration> build() {
        return configuration -> {
            CANcoderConfiguration config = new CANcoderConfiguration();
            //config.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition; //TODO: THIS SHOULD AUTOMATICALLY BOOT TO ABSOLUTE WITH V6
            config.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Unsigned_0To1;
            config.MagnetSensor.MagnetOffset = Math.toDegrees(configuration.getOffset()) / 360; //TODO: CHECK THIS -- SEEMS LIKE THE MAGNET OFFSET WANTS ROTATION FROM -1 TO 1 SO DOES IT MAKE SENSE TO JUST DIVIDE THE DEGREES BY 360?
            config.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
            
            CANcoder encoder = new CANcoder(configuration.getId());
            boolean haveError = CtreUtils.checkCtreError(encoder.getConfigurator().apply(config, 0.25), "Failed to configure CANCoder");
            for(int i = 0; i < 10; i++){
                haveError =  CtreUtils.checkCtreError(encoder.getConfigurator().apply(config, 0.25), "Failed to configure CANCoder");
                if(!haveError){
                    break;
                }
            }
            CtreUtils.checkCtreError(encoder.getPosition().setUpdateFrequency(FREQUENCY, 0.25), "Failed to configure CANCoder update rate"); //TODO: CHECK THIS -- GOT RID OF SENSOR DATA BUT IT DOESNT EXIST IN V6

            return new EncoderImplementation(encoder);
        };
    }

    private static class EncoderImplementation implements AbsoluteEncoder {
        private final CANcoder encoder;

        private EncoderImplementation(CANcoder encoder) {
            this.encoder = encoder;
        }

        @Override 
        public CANcoder getCANCoderFB() {
            return encoder;
        }

        @Override
        public double getAbsoluteAngle() {
            double angle = Math.toRadians(encoder.getAbsolutePosition().getValue()); 
            angle %= 2.0 * Math.PI;
            if (angle < 0.0) {
                angle += 2.0 * Math.PI;
            }

            return angle;
        }
    }

    public enum Direction {
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }
}
