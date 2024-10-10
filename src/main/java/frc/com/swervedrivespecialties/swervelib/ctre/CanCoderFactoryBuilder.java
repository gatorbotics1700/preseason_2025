package frc.com.swervedrivespecialties.swervelib.ctre;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.StatusSignal;

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
            CANcoderConfiguration config = new CANcoderConfiguration()
            .withMagnetSensor(new MagnetSensorConfigs()
                .withAbsoluteSensorRange(AbsoluteSensorRangeValue.Unsigned_0To1)
                .withSensorDirection(direction == Direction.CLOCKWISE ? SensorDirectionValue.Clockwise_Positive : SensorDirectionValue.CounterClockwise_Positive)
                .withMagnetOffset(configuration.getOffset() / (2 * Math.PI))
            );
            
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
            StatusSignal<Double> angleCode = encoder.getPosition();

            CtreUtils.checkCtreError(angleCode.getStatus(), "Failed to retrieve CANCoder " + encoder.getDeviceID() + " absolute position");

            double angle = Math.toRadians(angleCode.getValue());
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
