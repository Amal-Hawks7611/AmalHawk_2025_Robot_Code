package frc.robot.subsystems;

import frc.robot.Constants.ObjectDetector;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//SUBSYSTEM FOR MZ80 SENSOR.
public class ObjectDetectorSubsystem extends SubsystemBase {
    public static AnalogInput m_objectSensor;

    public ObjectDetectorSubsystem() {
        m_objectSensor = new AnalogInput(ObjectDetector.SENSOR_DIO_PORT);
    }

    public boolean CheckObject() {
        if (m_objectSensor.getValue() < 1000) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("ObjectSENSOR", CheckObject());
    }
}
