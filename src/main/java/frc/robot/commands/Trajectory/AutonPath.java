package frc.robot.commands.Trajectory;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import java.util.ArrayList;

public class AutonPath {
    public Trajectory trajectory;

    public void generateTrajectory() {
        var start = new Pose2d(0, 0, Rotation2d.fromDegrees(0));
        var end = new Pose2d(Units.feetToMeters(3.28084), 0, Rotation2d.fromDegrees(0));
        TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(5), Units.feetToMeters(5));
        config.setReversed(false);
        trajectory = TrajectoryGenerator.generateTrajectory(start, new ArrayList<>(), end, config);
    }
  
}
