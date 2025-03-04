package frc.robot.commands.Vision;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class AlignWithAprilTagCommand extends Command {
    private final SwerveSubsystem swerve;
    private final int targetTagID;
    private final boolean useAllianceFlip;

    public AlignWithAprilTagCommand(SwerveSubsystem swerve, int targetTagID, boolean useAllianceFlip) {
        this.swerve = swerve;
        this.targetTagID = targetTagID;
        this.useAllianceFlip = useAllianceFlip;
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        // Reset odometry if using absolute positioning from AprilTag
        var alliance = DriverStation.getAlliance();
        if (useAllianceFlip && alliance.isPresent() && alliance.get() == DriverStation.Alliance.Red) {
            // Flip tag position for red alliance (example - implement your field layout)
            swerve.resetOdometry(getFlippedTagPose(targetTagID));
        }
    }

    @Override
    public void execute() {
        // Get AprilTag data from NetworkTables (C++ team's implementation)
        var visionTable = NetworkTableInstance.getDefault().getTable("Vision");
        double tagX = visionTable.getEntry("targetX").getDouble(0);
        double tagY = visionTable.getEntry("targetY").getDouble(0);
        
        // Calculate alignment angle to face the tag
        Pose2d currentPose = swerve.getPose();
        Rotation2d targetAngle = new Rotation2d(Math.atan2(
            tagY - currentPose.getY(),
            tagX - currentPose.getX()
        ));

        // Drive to align heading
        swerve.driveFieldOriented(new ChassisSpeeds(
            0,  // No X movement
            0,  // No Y movement
            swerve.getSwerveController().angleController.calculate(
                currentPose.getRotation().getRadians(),
                targetAngle.getRadians()
            )
        ));
    }

    @Override
    public boolean isFinished() {
        // Check if aligned within 3 degrees
        return Math.abs(swerve.getHeading().minus(targetAngle).getDegrees()) < 3.0;
    }

    private Pose2d getFlippedTagPose(int tagID) {
        // TODO: Implement field-specific AprilTag position flipping for red alliance
        // Example: return new Pose2d(16.54 - tagX, tagY, Rotation2d.fromDegrees(180 - tagAngle));
        return new Pose2d();
    }
}