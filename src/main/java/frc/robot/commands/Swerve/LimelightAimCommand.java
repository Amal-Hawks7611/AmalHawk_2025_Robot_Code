package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.LimelightHelpers;

public class LimelightAimCommand extends Command {

    private final SwerveSubsystem swerveSubsystem;
    private static final double TOLERANCE = 0.5;

    public LimelightAimCommand(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Limelight Focus Başladı");
    }

    @Override
    public void execute() {
        double rotationSpeed = swerveSubsystem.CalculateLimelightAim();
        ChassisSpeeds speeds = new ChassisSpeeds(0, 0, rotationSpeed);
        swerveSubsystem.driveRobotRelative(speeds);
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.driveRobotRelative(new ChassisSpeeds(0.0, 0.0, 0.0));
        System.out.println("Limelight Focus Bitti");
    }

    @Override
    public boolean isFinished() {
        double tx = LimelightHelpers.getTX("limelight");
        return Math.abs(tx) < TOLERANCE;
    }
}
