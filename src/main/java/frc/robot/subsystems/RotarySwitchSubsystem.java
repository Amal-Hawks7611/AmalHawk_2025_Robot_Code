package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RotarySwitch;

//ROTARY SWITCH FOR AUTONOMOUS CHOOSING. HELL YEAH!!!
public class RotarySwitchSubsystem extends SubsystemBase {
    private final DigitalInput dtPin;
    private final DigitalInput clkPin;
    private final DigitalInput swPin;
    private int lastClockState;
    private int totalTurns;

    public RotarySwitchSubsystem() {
        dtPin = new DigitalInput(RotarySwitch.DT_CHANNEL);
        clkPin = new DigitalInput(RotarySwitch.CLK_CHANNEL);
        swPin = new DigitalInput(RotarySwitch.SW_CHANNEL);
        lastClockState = clkPin.get() ? 1 : 0;
        totalTurns = 0;
    }

    @Override
    public void periodic() {
        int clkState = clkPin.get() ? 1 : 0;
        int dtState = dtPin.get() ? 1 : 0;

        if (clkState != lastClockState) {
            if (dtState != clkState) {
                totalTurns++;
            } else {
                totalTurns--;
            }
            lastClockState = clkState;
        }

        SmartDashboard.putNumber("RotarySwitch", totalTurns);
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    public boolean isSwitchPressed() {
        return !swPin.get();
    }
}
