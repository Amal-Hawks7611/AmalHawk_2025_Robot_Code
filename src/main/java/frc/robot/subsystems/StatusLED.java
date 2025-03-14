package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.LedSubsystem;
import frc.robot.Constants.OI;
import edu.wpi.first.units.Units;

public class StatusLED extends SubsystemBase {
    public AddressableLED led;
    public AddressableLEDBuffer buffer;

    //NOTHING SPECIAL, JUST A LED CODE INTAGRATED WITH CONSTANTS
    public StatusLED() {
        try {
            if (EnabledParts.IS_LED_ENABLED) {
                led = new AddressableLED(LedSubsystem.LED_PWM_PORT);
                buffer = new AddressableLEDBuffer(LedSubsystem.LED_LENGTH);
                led.setLength(buffer.getLength());
                led.setData(buffer);
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize StatusLED: " + e.getMessage());
        }
    }

    public void setDefault() {
        if (led == null || buffer == null) {
            System.err.println("LED or buffer is not initialized!");
            return;
        }
        LEDPattern pattern = LedSubsystem.BREATHE_COLOR.breathe(Units.Seconds.of(LedSubsystem.BREATHE_MAGNITUDE));
        pattern.applyTo(buffer);
        led.setData(buffer);
        led.start();
    }

    public void setProcess() {
        if (led == null || buffer == null) {
            System.err.println("LED or buffer is not initialized!");
            return;
        }
        LedSubsystem.ELEVATOR_PROCESS_COLOR.applyTo(buffer);
        led.setData(buffer);
        led.start();
    }

    public void setFocus() {
        if (led == null || buffer == null) {
            System.err.println("LED or buffer is not initialized!");
            return;
        }
        LedSubsystem.TARGET_FOCUS_COLOR.applyTo(buffer);
        led.setData(buffer);
        led.start();
    }

    public void setAlgeaIntake() {
        if (led == null || buffer == null) {
            System.err.println("LED or buffer is not initialized!");
            return;
        }
        LedSubsystem.ALGEA_INTAKE_COLOR.applyTo(buffer);
        led.setData(buffer);
        led.start();
    }

    public void setIntake() {
        if (led == null || buffer == null) {
            System.err.println("LED or buffer is not initialized!");
            return;
        }
        LedSubsystem.INTAKE_COLOR.applyTo(buffer);
        led.setData(buffer);
        led.start();
    }

    public void checkForProcess() {
        if (!OI.IS_LED_CYCLING && !OI.IS_LED_MORSE_SHOWING) {
            if (OI.IS_PROCESSING || OI.IS_INTAKE_MOVING) {
                setProcess();
            } else if (OI.IS_SWERVE_FOCUSED) {
                setFocus();
            } else if (OI.IS_INTAKING) {
                setIntake();
            } else if(OI.IS_ALGEA_INTAKING){
                setAlgeaIntake();
            } else if(OI.IS_FOCUSED){
                setFocus();
            } else {
                setDefault();
            }
        }
    }

    @Override
    public void periodic() {
        checkForProcess();
    }
}