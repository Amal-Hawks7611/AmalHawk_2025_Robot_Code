package frc.robot.subsystems;

import frc.robot.Constants.ObjectDetector;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class ObjectDetectorSubsystem extends SubsystemBase {
    public static DigitalInput m_objectSensor;

    public ObjectDetectorSubsystem() {
        m_objectSensor = new DigitalInput(ObjectDetector.SENSOR_DIO_PORT);
    }

    public boolean CheckObject() {
        return m_objectSensor.get();
    }
}
