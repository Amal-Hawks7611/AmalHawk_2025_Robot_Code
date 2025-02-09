package frc.robot.commands.Led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.StatusLED;
import java.awt.Color;


//SMOOT AF
public class LEDStateCycler extends Command {
    private final StatusLED statusLED;
    private final Timer timer = new Timer();
    private final double cycleDuration = 10.0;

    public LEDStateCycler(StatusLED statusLED) {
        this.statusLED = statusLED;
        addRequirements(statusLED);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        double t = timer.get();
        double hue = (t % cycleDuration) / cycleDuration;
        int rgb = Color.HSBtoRGB((float) hue, 1.0f, 1.0f);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;

        statusLED.setColor(r, g, b);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        statusLED.setDefault();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
