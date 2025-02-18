package frc.robot.commands.Led;


import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OI;
import frc.robot.subsystems.StatusLED;


//SMOOT AF
public class LEDStateCycler extends Command {
    private final StatusLED statusLED;
    private final Timer timer = new Timer();
    private final LEDPattern m_rainbow = LEDPattern.rainbow(255, 128);
    private static final Distance kLedSpacing = Units.Meters.of(1 / 120.0);
    private final LEDPattern m_scrollingRainbow = m_rainbow.scrollAtAbsoluteSpeed(Units.MetersPerSecond.of(1), kLedSpacing);


    public LEDStateCycler(StatusLED statusLED) {
        this.statusLED = statusLED; 
        addRequirements(statusLED);
    }

    @Override
    public void initialize() {
        OI.IS_LED_CYCLING = true;
    }

    @Override
    public void execute() {
        m_scrollingRainbow.applyTo(statusLED.buffer);
        statusLED.led.setData(statusLED.buffer);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        statusLED.setDefault();
        OI.IS_LED_CYCLING = false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
