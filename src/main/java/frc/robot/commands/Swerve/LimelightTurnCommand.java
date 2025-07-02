package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class LimelightTurnCommand extends Command {
    private final SwerveSubsystem swerveSubsystem;
    private final NetworkTableEntry tx;
    private boolean finished = false;

    public LimelightTurnCommand(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
    }

    @Override
    public void initialize() {
        finished = false;
        System.out.println("Yalnızca açıya yönelme başlıyor!");
    }

    @Override
    public void execute() {
        double angleError = tx.getDouble(0.0);
        double kP = 0.01; //TODO TUNE
        double rotationSpeed = kP * angleError;
        rotationSpeed = Math.max(Math.min(rotationSpeed, 0.3), -0.3);
        swerveSubsystem.drive(new Translation2d(0, 0), rotationSpeed, false);
        if (Math.abs(angleError) < 1.0) {
            finished = true;
        }
    }
    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.drive(new Translation2d(0, 0), 0, false);
        System.out.println("Açıya yönelme tamamlandı!");
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
