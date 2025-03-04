package frc.robot.commands.Vision;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class VisionAlignCommand extends Command {
    private final SwerveSubsystem swerve;
    private final PIDController xController, yController, thetaController;
    private final NetworkTable visionTable;
    private Pose2d targetPose;

    public VisionAlignCommand(SwerveSubsystem swerve) {
        this.swerve = swerve;
        xController = new PIDController(Constants.DrivebaseConstants.TRANSLATION_KP, Constants.DrivebaseConstants.TRANSLATION_KI, Constants.DrivebaseConstants.TRANSLATION_KD); // kP, kI, kD
        yController = new PIDController(Constants.DrivebaseConstants.ANGLE_KP, Constants.DrivebaseConstants.ANGLE_KI, Constants.DrivebaseConstants.ANGLE_KD);
        thetaController = new PIDController(1.5, 0, 0);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        xController.setTolerance(0.01);
        yController.setTolerance(0.01);
        thetaController.setTolerance(Units.degreesToRadians(2.0));

        visionTable = NetworkTableInstance.getDefault().getTable("Vision");
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        xController.reset();
        yController.reset();
        thetaController.reset();
    }

    @Override
    public void execute() {
        if (!visionTable.getEntry("hasTarget").getBoolean(false)) return;

        double targetX = visionTable.getEntry("targetX").getDouble(0.0);
        double targetY = visionTable.getEntry("targetY").getDouble(0.0);
        double targetTheta = visionTable.getEntry("targetTheta").getDouble(0.0);
        targetPose = new Pose2d(targetX, targetY, new Rotation2d(targetTheta));

        Pose2d currentPose = swerve.getPose();

        double vx = xController.calculate(currentPose.getX(), targetX);
        double vy = yController.calculate(currentPose.getY(), targetY);
        double omega = thetaController.calculate(
            currentPose.getRotation().getRadians(), targetTheta
        );

        ChassisSpeeds speeds = new ChassisSpeeds(vx, vy, omega);
        swerve.driveFieldOriented(speeds);
    }

    @Override
    public boolean isFinished() {
        return xController.atSetpoint() && yController.atSetpoint() && thetaController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        swerve.drive(new ChassisSpeeds(0, 0, 0));
    }
}