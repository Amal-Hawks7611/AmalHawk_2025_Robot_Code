package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class DriveAfterFocus extends Command {

    private final SwerveSubsystem swerveSubsystem;
    private static final double TOLERANCE = 0.5;
    public Timer timer;
    public DriveAfterFocus(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        System.out.println("FocusBitti. Mercana Gidiliyor.");
    }

    @Override
    public void execute() {
        if(timer.hasElapsed(Constants.LIMELIGHT_DRIVE_TIME)){
            timer.stop();
            this.end(false);
        }else{            
            ChassisSpeeds speeds = new ChassisSpeeds(0.2, 0, 0);
            swerveSubsystem.drive(speeds);  }
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
        System.out.println("Limelight Focus Bitti");
    }

    @Override
    public boolean isFinished() {
        double tx = LimelightHelpers.getTX("limelight");
        return Math.abs(tx) < TOLERANCE;
    }
}