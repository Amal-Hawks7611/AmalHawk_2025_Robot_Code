package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.OI;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class righttest extends Command {
    public boolean finished;
    private boolean focus;
    private final SwerveSubsystem swerveSubsystem;
    private final limelight kamera;
    private final Timer timer = new Timer();

    public righttest(SwerveSubsystem swerveSubsystem, limelight kamera) {
        this.swerveSubsystem = swerveSubsystem;
        this.kamera = kamera;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        focus = false;
        System.out.println("Sag Hizalama Basladi");
        timer.reset();
        timer.stop();
    }

    @Override
    public void execute() {
        if (OI.IS_FOCUSED) {
            focus = true;
            timer.start();
        } else if (!focus) {
            kamera.schedule();
        }
        if (focus) {
            swerveSubsystem.drive(new Translation2d(0, -Constants.LIMELIGHT_KP), 0, false);
            if (timer.hasElapsed(1.9533169)) {
                timer.stop();
                this.end(false);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Sag Hizalama Bitti");
        swerveSubsystem.drive(new Translation2d(0, 0), 0, false);
        finished = true;
        focus = false;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}