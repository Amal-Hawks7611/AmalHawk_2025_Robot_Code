package frc.robot.subsystems;

import frc.robot.Constants.Colormatcher;
import frc.robot.Constants.EnabledParts;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;

import com.revrobotics.ColorMatch;

//A CORAL GAMEPICE DETECTION SYSTEM. USED REV COLOR SENSOR V3
public class ColorMatcherSubsystem extends SubsystemBase {
    public static ColorSensorV3 m_colorSensor;
    public static ColorMatch m_colorMatcher;
    public static Color detectedColor = new Color();
    public static String colorString;
    public static ColorMatchResult match;

    public ColorMatcherSubsystem() {
        m_colorMatcher = new ColorMatch();
        m_colorSensor = new ColorSensorV3(Colormatcher.I2C_PORT);
        m_colorMatcher.addColorMatch(Colormatcher.CORAL_TARGET);
    }

    public static boolean CheckColor() {
        if (EnabledParts.IS_COLOR_SENSOR_ENABLED) {
            detectedColor = m_colorSensor.getColor();
            match = m_colorMatcher.matchClosestColor(detectedColor);
            if (match.color == Colormatcher.CORAL_TARGET) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
