package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class zerogyro extends Command {

    private final SwerveSubsystem swerveSubsystem;
    public zerogyro(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("NavX Sıfırlanıyor");
    }

    @Override
    public void execute() {
        swerveSubsystem.zeroGyro();
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("NavX Sıfırlandı");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}