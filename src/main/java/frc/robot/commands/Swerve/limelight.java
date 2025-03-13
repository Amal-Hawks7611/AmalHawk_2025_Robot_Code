package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class limelight extends Command {
    public boolean finished;
    private final SwerveSubsystem swerveSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    public limelight(SwerveSubsystem swerveSubsystem, LimelightSubsystem limelightSubsystem ) {
        this.swerveSubsystem = swerveSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(swerveSubsystem);
        addRequirements(limelightSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        System.out.println("Hizalama Basladi");
    }

    @Override
    public void execute() {
        System.out.println(LimelightHelpers.getTY("limelight"));
        if(limelightSubsystem.checkFocus()){
            this.end(false);
        }
        else{
            swerveSubsystem.drive(new Translation2d(0,Constants.LIMELIGHT_KP), 0, false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Hizalama Bitti");
        swerveSubsystem.drive(new Translation2d(0,0),0,false);
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}