package frc.robot.commands.Led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.StatusLED;

public class LEDStateCycler extends Command {
    private final StatusLED statusLED;
    private final Timer timer = new Timer();
    private int currentState = 0;

    public LEDStateCycler(StatusLED statusLED) {
        this.statusLED = statusLED;
        addRequirements(statusLED);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        currentState = 0;
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(2.0)) {
            cycleLEDState();
            timer.reset();
            currentState += 1;
            if(currentState == 3){currentState = 0;}
        }
    }

    private void cycleLEDState() {
        switch (currentState) {
            case 0:
                statusLED.setProcess();
                break;
            case 1:
                statusLED.setFocus(); 
                break;
            case 2:
                statusLED.setDefault();
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        statusLED.setDefault();
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
