package frc.robot.commands.Vision;

import com.pathplanner.lib.path.PathConstraints;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class DriveToAprilTagCommand extends Command {
    private final SwerveSubsystem swerve;
    private final int targetTagID;
    private NetworkTable visionTable;

    public DriveToAprilTagCommand(SwerveSubsystem swerve, int targetTagID) {
        this.swerve = swerve;
        this.targetTagID = targetTagID;
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        // TODO: Configure pathfinding constraints based on coral payload
        PathConstraints constraints = new PathConstraints(
            3.0,  // Max speed (m/s)
            4.0,  // Max acceleration (m/s²)
            Units.degreesToRadians(540),  // Max angular speed
            Units.degreesToRadians(720)   // Max angular acceleration
        );
        
        visionTable = NetworkTableInstance.getDefault().getTable("Vision");
        var visionTable = NetworkTableInstance.getDefault().getTable("Vision");
        Pose2d targetPose = new Pose2d(
            visionTable.getEntry("targetX").getDouble(0),
            visionTable.getEntry("targetY").getDouble(0),
            swerve.getHeading()
        );

        // Start pathfinding
        swerve.driveToPose(targetPose).schedule();
    }

    @Override
    public boolean isFinished() {
        return swerve.getPose().getTranslation().getDistance(
            new Translation2d(
                visionTable.getEntry("targetX").getDouble(0),
                visionTable.getEntry("targetY").getDouble(0)
            )
        ) < 0.2;
    }
}