package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LedSubsystem;
import frc.robot.Constants.OI;

public class StatusLED extends SubsystemBase {
    private final PWM red;
    private final PWM green;
    private final PWM blue;

    private final DigitalOutput common;

    //Not Just a regular RGB LED. It's Cool AF!!
    public StatusLED() {
        red = new PWM(LedSubsystem.PWM_RED);
        green = new PWM(LedSubsystem.PWM_GREEN);
        blue = new PWM(LedSubsystem.PWM_BLUE);

        common = new DigitalOutput(LedSubsystem.POWER_PORT);
    }

    //MATH MOTHAFUKA
    public void setColor(int rValue, int gValue, int bValue) {
        double rDuty = 1.0 - (rValue / 255.0);
        double gDuty = 1.0 - (gValue / 255.0);
        double bDuty = 1.0 - (bValue / 255.0);

        red.setSpeed(rDuty);
        green.setSpeed(gDuty);
        blue.setSpeed(bDuty);
    }

    public void setDefault() {
        setColor(255, 255, 255);
    }

    public void setProcess() {
        setColor(255, 0, 0);
    }

    public void setFocus() {
        setColor(0, 0, 255);
    }

    public void setAlgeaIntake() {
        setColor(0, 255, 255);
    }

    public void setIntake() {
        setColor(255, 0, 255);
    }

    public void checkForProcess() {
        if (!OI.IS_TEST) {
            if (OI.IS_PROCESSING || OI.IS_INTAKE_MOVING) {
                setProcess();
            } else if (OI.IS_SWERVE_FOCUSED) {
                setFocus();
            } else if (OI.IS_INTAKING) {
                setIntake();
            } else if (OI.IS_ALGEA_INTAKING) {
                setAlgeaIntake();
            } else {
                setDefault();
            }
        }
    }

    @Override
    public void periodic() {
        checkForProcess();
        common.set(false);
    }
}
