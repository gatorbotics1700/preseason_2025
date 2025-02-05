package frc.com.swervedrivespecialties.swervelib.ctre;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.StatusSignal;
import edu.wpi.first.units.measure.Angle;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.hardware.CANcoder;
import frc.com.swervedrivespecialties.swervelib.AbsoluteEncoder;
import frc.com.swervedrivespecialties.swervelib.AbsoluteEncoderFactory;

public class CanCoderFactoryBuilder {
    private Direction direction = Direction.COUNTER_CLOCKWISE;
    private double buildTimeoutSeconds = 0.25;

    public CanCoderFactoryBuilder withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public AbsoluteEncoderFactory<CanCoderAbsoluteConfiguration> build() {
        return configuration -> {
            CANcoderConfiguration config = new CANcoderConfiguration()
                .withMagnetSensor(new MagnetSensorConfigs()
                    .withAbsoluteSensorDiscontinuityPoint(1) //unsigned 0-1
                    .withSensorDirection(direction == Direction.CLOCKWISE ? SensorDirectionValue.Clockwise_Positive : SensorDirectionValue.CounterClockwise_Positive)
                    .withMagnetOffset(configuration.getOffset() / (2 * Math.PI)));

            CANcoder encoder = new CANcoder(configuration.getId(), configuration.getCanbus());
            CtreUtils.checkCtreError(encoder.getConfigurator().apply(config, buildTimeoutSeconds), "Failed to configure CANCoder");

            CtreUtils.checkCtreError(encoder.getPosition().setUpdateFrequency(150, 0.25), "Failed to configure CANCoder update rate"); //was 1000.0 / periodMilliseconds

            return new EncoderImplementation(encoder);
        };
    }

    private static class EncoderImplementation implements AbsoluteEncoder {
        private final int ATTEMPTS = 3; // TODO: Allow changing number of tries for getting correct position

        private final CANcoder encoder;

        private EncoderImplementation(CANcoder encoder) {
            this.encoder = encoder;
        }

        @Override
        public double getAbsoluteAngle() {
            StatusSignal<Angle> angleCode = encoder.getPosition();

            for (int i = 0; i < ATTEMPTS; i++) {
                if (angleCode.getStatus() == StatusCode.OK) break;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) { }
                angleCode = encoder.getPosition();
            }

            CtreUtils.checkCtreError(angleCode.getStatus(), "Failed to retrieve CANcoder "+encoder.getDeviceID()+" absolute position after "+ATTEMPTS+" tries");

            double angle = angleCode.getValueAsDouble() * 2.0 * Math.PI;
            angle %= 2.0 * Math.PI;
            if (angle < 0.0) {
                angle += 2.0 * Math.PI;
            }

            return angle;
        }

        @Override
        public Object getInternal() {
            return this.encoder;
        }
    }

    public enum Direction {
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }
}
