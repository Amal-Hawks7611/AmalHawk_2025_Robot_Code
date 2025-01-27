package frc.robot.commands.Trajectory;
import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.Trajectory.State;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class FollowTrajectoryCommand extends Command {
    public Trajectory trajectory;
    public final SwerveSubsystem swerveSubsystem;
    private int currentSegmentIndex = 0;

    public FollowTrajectoryCommand(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        var start = new Pose2d(0, 0, Rotation2d.fromDegrees(0));
        var end = new Pose2d(Units.feetToMeters(3.28084), 0, Rotation2d.fromDegrees(0));
        TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(5), Units.feetToMeters(5));
        config.setReversed(false);
        trajectory = TrajectoryGenerator.generateTrajectory(start, new ArrayList<>(), end, config);
        swerveSubsystem.resetOdometry(trajectory.getInitialPose()); 
    }

    @Override
    public void execute() {
        State currentState = trajectory.sample(swerveSubsystem.getPose().getTranslation().getX());

        double xSpeed = currentState.velocityMetersPerSecond;
        double ySpeed = 0; 
        double rot = 0;

        swerveSubsystem.drive(xSpeed, ySpeed, rot, true); 
        if (currentSegmentIndex >= trajectory.getStates().size()) {
           end(false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.drive(0, 0, 0, false); 
    }

    @Override
    public boolean isFinished() {
        return currentSegmentIndex >= trajectory.getStates().size();
    }
}
