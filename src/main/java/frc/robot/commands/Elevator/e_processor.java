package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_processor extends Command {
    public final ElevatorSubsystem elevatorSubsystem;

    public e_processor(ElevatorSubsystem elevatroSubsystem) {
        this.elevatorSubsystem = elevatroSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving To The Processor");
        OI.IS_PROCESSING = true;
        Elevator.PROCESS_START_POSITION = elevatorSubsystem.getLeaderMotorEncoder();
        Elevator.CURRENT_DIRECTION = elevatorSubsystem.getLeaderMotorEncoder() < Elevator.ELEVATOR_PROCESSOR_VALUE;
    }

    @Override
    public void execute() {
        if (!OI.IS_PID_ENDED) {
            elevatorSubsystem.OCAL_PID_PREMIUM(Elevator.ELEVATOR_PROCESSOR_VALUE);
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
        System.out.println(OI.IS_PID_ENDED);
    }

    @Override
    public boolean isFinished() {
        return OI.IS_PID_ENDED;
    }
}
