package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Led.LEDStateCycler;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix6.hardware.TalonFX;

public class miracsurpriz extends Command {
    public final TalonFX motor;
    public final Timer timer = new Timer();
    public int currentNoteIndex = 0;
    public double noteStartTime = 0;
    public LEDStateCycler led_cycle;

    // Not bilgilerini tutmak için iç sınıf
    private static class Note {
        public final double frequency; // Hertz (Hz) cinsinden nota frekansı
        public final double duration; // Notanın süresi (saniye cinsinden)

        public Note(double frequency, double duration) {
            this.frequency = frequency;
            this.duration = duration;
        }
    }

    // "Happy Birthday" melodisinin basitleştirilmiş versiyonu
    // Örnek notalar (frekans değerleri Hz cinsindendir)
    // G4 = 392Hz, A4 = 440Hz, C5 = 523Hz, B4 = 494Hz, D5 = 587Hz
    private final Note[] notes = new Note[] {
            new Note(392, 0.4), // G4
            new Note(392, 0.4), // G4
            new Note(440, 0.8), // A4
            new Note(392, 0.8), // G4
            new Note(523, 0.8), // C5
            new Note(494, 1.6), // B4

            new Note(392, 0.4), // G4
            new Note(392, 0.4), // G4
            new Note(440, 0.8), // A4
            new Note(392, 0.8), // G4
            new Note(587, 0.8), // D5
            new Note(523, 1.6) // C5
    };

    public miracsurpriz(LEDStateCycler led_cycle) {
        this.led_cycle = led_cycle;
        this.motor = new TalonFX(4, "rio");
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        currentNoteIndex = 0;
        noteStartTime = timer.get();
        led_cycle.schedule();
    }

    @Override
    public void execute() {
        if (currentNoteIndex >= notes.length) {
            return;
        }
        Note currentNote = notes[currentNoteIndex];
        double elapsed = timer.get() - noteStartTime;
        if (elapsed > currentNote.duration) {
            currentNoteIndex++;
            noteStartTime = timer.get();
            motor.setControl(new com.ctre.phoenix6.controls.DutyCycleOut(0));
            return;
        }

        double period = 1.0 / currentNote.frequency;
        double halfPeriod = period / 2.0;
        double modTime = elapsed % period;
        if (modTime < halfPeriod) {
            motor.setControl(new com.ctre.phoenix6.controls.DutyCycleOut(0.05));
        } else {
            motor.setControl(new com.ctre.phoenix6.controls.DutyCycleOut(-0.05));
        }
    }

    @Override
    public void end(boolean interrupted) {
        motor.setControl(new com.ctre.phoenix6.controls.DutyCycleOut(0));
        led_cycle.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return currentNoteIndex >= notes.length;
    }
}
