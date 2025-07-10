package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.Intake;
import frc.robot.Constants.OI;
import frc.robot.subsystems.IntakeSubsystem;

public class Gerial extends Command {
    public final IntakeSubsystem intakeSubsystem;

    public Gerial(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.timer.reset();
        intakeSubsystem.timer.start();
        System.out.println("Coral Drain Ininialized");
        OI.IS_INTAKING = true;
        Intake.OUTTAKE_SPEED = -0.35;
    }

    @Override
    public void execute() {
        if (EnabledParts.IS_INTAKE_ENABLED) {
            intakeSubsystem.Shoot();
            if (!OI.IS_INTAKING) {
                this.end(false);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.timer.stop();
        intakeSubsystem.timer.reset();
        intakeSubsystem.leaderMotor.stopMotor();
        OI.IS_INTAKING = false;
        Intake.OUTTAKE_SPEED = 0.65;
    }

    @Override
    public boolean isFinished() {
        return !OI.IS_INTAKING;
    }
}
