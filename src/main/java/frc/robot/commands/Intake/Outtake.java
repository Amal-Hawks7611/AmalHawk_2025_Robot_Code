package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OI;
import frc.robot.subsystems.IntakeSubsystem;

public class Outtake extends Command {
    public final IntakeSubsystem intakeSubsystem;

    public Outtake(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Coral Outtaking Ininialized");
        intakeSubsystem.timer.reset();
        intakeSubsystem.timer.start();
        OI.IS_INTAKING = true;
    }

    @Override
    public void execute() {
        intakeSubsystem.Shoot();
        if(!OI.IS_INTAKING){this.end(false);}
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.leaderMotor.stopMotor();
        intakeSubsystem.timer.stop();
        OI.IS_INTAKING = false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
