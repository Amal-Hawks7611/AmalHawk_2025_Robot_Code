package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class Limelight extends Command {
    public boolean finished;
    private final SwerveSubsystem swerveSubsystem;
    public Limelight(SwerveSubsystem swerveSubsystem) {
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
        if(LimelightHelpers.getTY("limelight") >= Constants.LIMELIGHT_TX - 0.15 && LimelightHelpers.getTY("limelight") <= Constants.LIMELIGHT_TX + 0.15){
            this.end(false);
        }
        else{
        if(LimelightHelpers.getTY("limelight") != 0 && LimelightHelpers.getTY("limelight")>Constants.LIMELIGHT_TX){
            swerveSubsystem.drive(new Translation2d(0, Constants.LIMELIGHT_KP),0,false);
        }
        else if(LimelightHelpers.getTY("limelight") != 0){
            swerveSubsystem.drive(new Translation2d(0, -Constants.LIMELIGHT_KP),0,false);
        }
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Hizalama Bitti");
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}