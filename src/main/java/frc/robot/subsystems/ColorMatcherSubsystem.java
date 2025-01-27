package frc.robot.subsystems;

import frc.robot.Constants.Colormatcher;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;

import com.revrobotics.ColorMatch;

public class ColorMatcherSubsystem extends SubsystemBase {
    public static ColorSensorV3 m_colorSensor;
    public static ColorMatch m_colorMatcher;
    public static Color detectedColor = new Color();
    public static String colorString;
    public static ColorMatchResult match;
    public ColorMatcherSubsystem(){
        m_colorMatcher = new ColorMatch();
        m_colorSensor = new ColorSensorV3(Colormatcher.i2cport);
        m_colorMatcher.addColorMatch(Colormatcher.kCoralTarget);
    }
    public static boolean CheckColor(){
        detectedColor = m_colorSensor.getColor();
        match = m_colorMatcher.matchClosestColor(detectedColor);
        if(match.color == Colormatcher.kCoralTarget){return true;}else{return false;}
    }
}
