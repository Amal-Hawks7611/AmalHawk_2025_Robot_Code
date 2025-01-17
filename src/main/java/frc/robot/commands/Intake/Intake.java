package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OI;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends Command {
    public final IntakeSubsystem intakeSubsystem;
    public Intake(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Coral Intaking Ininialized");
        intakeSubsystem.timer.reset();
        intakeSubsystem.timer.start();
        OI.IS_INTAKING = true;

    }

    @Override
    public void execute() {
        intakeSubsystem.Move(false);
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
