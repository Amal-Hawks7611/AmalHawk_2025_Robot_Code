package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OI;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class limelight_withtruewheels extends Command {
    public boolean finished;
    private final SwerveSubsystem swerveSubsystem;
    public Pose2d targetPose;

    public limelight_withtruewheels(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        System.out.println("Apriltage Gitme Başlıyor!");
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double ty = table.getEntry("ty").getDouble(0.0);

        double mountAngle = -0; // TODO WRITE
        double limelightHeight = 26.0; // TODO WRITE
        double targetHeight = 30.81;
        double totalAngle = mountAngle + ty;
        double distance = (targetHeight - limelightHeight) / Math.tan(Math.toRadians(totalAngle));

        Pose2d current = swerveSubsystem.getPose();
        targetPose = current.plus(new Transform2d(0, distance / 100.0, new Rotation2d()));
    }

    @Override
    public void execute() {
        if (OI.IS_GONE) {
            end(false);
        } else {
            System.out.println(targetPose);
            swerveSubsystem.driveToPose(targetPose);
            double distance = swerveSubsystem.getPose().getTranslation().getDistance(targetPose.getTranslation());
            if (distance < 0.1)
                OI.IS_GONE = true;
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