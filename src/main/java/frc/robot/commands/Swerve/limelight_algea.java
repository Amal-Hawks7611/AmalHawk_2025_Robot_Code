package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.Controlls;
import frc.robot.Constants.OI;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveInputStream;

public class limelight_algea extends Command {
    public boolean finished;
    private boolean focus;
    private final SwerveSubsystem swerveSubsystem;
    private final Timer timer = new Timer();
            SwerveInputStream driveAngularVelocityKeyboard;
    public limelight_algea(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        focus = false;
        driveAngularVelocityKeyboard = SwerveInputStream.of(swerveSubsystem.getSwerveDrive(),
                        () -> Controlls.DRIVER_CONTROLLER.getLeftY(),
                        () -> Controlls.DRIVER_CONTROLLER.getLeftX())
                        .withControllerRotationAxis(() -> -Controlls.DRIVER_CONTROLLER.getRawAxis(
                                        2))
                        .deadband(OperatorConstants.DEADBAND)
                        .scaleTranslation(0.8)
                        .allianceRelativeControl(true);
        System.out.println("Algea Hizalama Basladi");
        timer.reset();
        timer.stop();
    }

    @Override
    public void execute() {
        if (OI.IS_FOCUSED) {
            focus = true;
            timer.start();
        }
        if (focus) {
            swerveSubsystem.drive(new Translation2d(0, -Constants.LIMELIGHT_KP), 0, false);
            if (timer.hasElapsed(Constants.LIMELIGHT_ALGEA_TIME)) {
                timer.stop();
                this.end(false);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Algea Hizalandı");
        swerveSubsystem.drive(new Translation2d(0, 0), 0, false);
        swerveSubsystem.setDefaultCommand(swerveSubsystem.driveFieldOriented(driveAngularVelocityKeyboard));
        finished = true;
        focus = false;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}