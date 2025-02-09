package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LedSubsystem;
import frc.robot.Constants.OI;

public class StatusLED extends SubsystemBase {
    public PWM RED;
    public PWM BLUE;
    public PWM GREEN;
    public DigitalOutput POWER;

    //NOTHING SPECIAL, BUT COOL AF.
    public StatusLED() {
        RED = new PWM(LedSubsystem.PWM_RED);
        BLUE = new PWM(LedSubsystem.PWM_BLUE);
        GREEN = new PWM(LedSubsystem.PWM_GREEN);
        POWER = new DigitalOutput(LedSubsystem.POWER_PORT);
    }

    public void setDefault() {
        setColor(4096, 4096, 4096);
    }

    public void setProcess() {
        setColor(4096, 0, 0);
    }

    public void setFocus() {
        setColor(0, 0, 4096);
    }

    public void setAlgeaIntake() {
        setColor(0, 4096, 4096);
    }

    public void setIntake() {
        setColor(4096, 0, 4096);
    }

    public void setColor(int cRED,int cBLUE, int cGREEN){
        RED.setPulseTimeMicroseconds(cRED);
        BLUE.setPulseTimeMicroseconds(cBLUE);
        GREEN.setPulseTimeMicroseconds(cGREEN);
    }

    public void checkForProcess() {
        if (!OI.IS_TEST) {
            if (OI.IS_PROCESSING || OI.IS_INTAKE_MOVING) {
                setProcess();
            } else if (OI.IS_SWERVE_FOCUSED) {
                setFocus();
            } else if (OI.IS_INTAKING) {
                setIntake();
            } else if(OI.IS_ALGEA_INTAKING){
                setAlgeaIntake();
            } else {
                setDefault();
            }
        }
    }

    @Override
    public void periodic() {
        checkForProcess();
        POWER.set(true);
    }
}
