package frc.robot.commands.IntakeMover;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeMover;
import frc.robot.Constants.OI;
import frc.robot.subsystems.IntakeMoverSubsystem;

public class reefscape extends Command {
    public final IntakeMoverSubsystem intakeMoverSubsystem;

    public reefscape(IntakeMoverSubsystem intakeMoverSubsystem) {
        this.intakeMoverSubsystem = intakeMoverSubsystem;
        addRequirements(intakeMoverSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Intake Is Moving To The Reefscape");
        OI.IS_INTAKE_MOVING = true;
    }

    @Override
    public void execute() {
        if(!OI.IS_INTAKE_MOVING){
            intakeMoverSubsystem.MoveIT(IntakeMover.INTAKE_MOVER_SPEED, IntakeMover.INTAKE_REEFSCAPE_VALUE);
        }
        else{
            this.end(false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeMoverSubsystem.leaderMotor.stopMotor();
        intakeMoverSubsystem.followerMotor.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
