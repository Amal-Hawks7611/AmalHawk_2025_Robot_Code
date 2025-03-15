package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.OI;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class limelight extends Command {
    public boolean finished;
    private final SwerveSubsystem swerveSubsystem;

    public limelight(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        System.out.println("Hizalama Basladi");
    }

    @Override
    public void execute() {
        System.out.println(LimelightHelpers.getTY("limelight"));
        System.out.println(LimelightHelpers.getTX("limelight"));
        if (OI.IS_YFOCUSED) {
            this.end(false);
        } else {
            swerveSubsystem.drive(new Translation2d(0, Constants.LIMELIGHT_KP), 0, false);
        }
        if (OI.IS_XFOCUSED) {
            this.end(false);
        }else{
            swerveSubsystem.drive(new translation2d(Constants.LIMELIGHT_KP, 0), 0, false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Hizalama Bitti");
        swerveSubsystem.drive(new Translation2d(0, 0), 0, false);
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}