package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.Intake;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_level3 extends Command {
    public final ElevatorSubsystem elevatorSubsystem;

    public e_level3(ElevatorSubsystem elevatroSubsystem) {
        this.elevatorSubsystem = elevatroSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving To The Level3");
        OI.IS_PROCESSING = true;
        Intake.OUTTAKE_SPEED = 0.40;
        Elevator.PROCESS_START_POSITION = elevatorSubsystem.getLeaderMotorEncoder();
        Elevator.CURRENT_DIRECTION = elevatorSubsystem.getLeaderMotorEncoder() < Elevator.ELEVATOR_L3_VALUE;
    }

    @Override
    public void execute() {
        if (!OI.IS_PID_ENDED) {
            elevatorSubsystem.OCAL_PID_PREMIUM(Elevator.ELEVATOR_L3_VALUE);
        } else {
            this.end(false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.leaderMotor.stopMotor();
        elevatorSubsystem.followerMotor.stopMotor();
        OI.IS_PID_ENDED = false;
        OI.IS_PROCESSING = false;
    }

    @Override
    public boolean isFinished() {
        return OI.IS_PID_ENDED;
    }
}
