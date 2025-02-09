package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LedSubsystem;
import frc.robot.Constants.OI;

public class StatusLED extends SubsystemBase {
    public PWM RED;
    public PWM BLUE;
    public PWM GREEN;

    //NOTHING SPECIAL, JUST A LED CODE INTAGRATED WITH CONSTANTS
    public StatusLED() {
        RED = new PWM(LedSubsystem.PWM_RED);
        BLUE = new PWM(LedSubsystem.PWM_BLUE);
        GREEN = new PWM(LedSubsystem.PWM_GREEN);
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

    public void setColor(int cRED,int cBLUE, int cGREEN){
        RED.setRaw(cRED);
        BLUE.setRaw(cBLUE);
        GREEN.setRaw(cGREEN);
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
    }
}
