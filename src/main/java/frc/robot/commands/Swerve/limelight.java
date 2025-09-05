package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.OI;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class limelight extends Command {
    public boolean finished;
    private final SwerveSubsystem swerveSubsystem;
    private final RobotContainer robotContainer;

    public limelight(SwerveSubsystem swerveSubsystem, RobotContainer robotContainer) {
        this.swerveSubsystem = swerveSubsystem;
        this.robotContainer = robotContainer;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        System.out.println("Hizalama Basladi");
        if (OI.IS_FOCUSED) {
            this.end(false);
            robotContainer.limelight_right_branch.schedule();
        }
    }

    @Override
    public void execute() {
        if(OI.IS_FOCUSED){
            this.end(false);
        } else {
            swerveSubsystem.drive(new Translation2d(0, Constants.LIMELIGHT_KP), 0, false);
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