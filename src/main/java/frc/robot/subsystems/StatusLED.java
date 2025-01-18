package frc.robot.subsystems;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LedSubsystem;
import frc.robot.Constants.OI;

//Disco lights for the robot.
// Cool AF.

public class StatusLED extends SubsystemBase {
    private AddressableLED led;
    private AddressableLEDBuffer buffer;

    public StatusLED() {
        try {
            led = new AddressableLED(LedSubsystem.LED_PWM_PORT); 
            buffer = new AddressableLEDBuffer(LedSubsystem.LED_LENGTH);
            led.setLength(buffer.getLength());
            led.setData(buffer);
        } catch (Exception e) {
            System.err.println("Failed to initialize StatusLED: " + e.getMessage());
        }
    }

    public void setDefault() {
        if (led == null || buffer == null) {
            System.err.println("LED or buffer is not initialized!");
            return;
        }
        LedSubsystem.BREATHE_COLOR.applyTo(buffer);
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

    public void checkForProcess() {
		if(!OI.IS_TEST){
			if (OI.IS_PROCESSING) {
				setProcess();
			} else if (OI.IS_SWERVE_FOCUSED) {
				setFocus();
			} else {
				setDefault();
			}
		}
    }

    @Override
    public void periodic() {
        Logger.getGlobal().info("StatusLED periodic");
        Logger.getGlobal().info("IS_PROCESSING: " + OI.IS_PROCESSING);
        Logger.getGlobal().info("IS_SWERVE_FOCUSED: " + OI.IS_SWERVE_FOCUSED);
        checkForProcess();
    }
}
