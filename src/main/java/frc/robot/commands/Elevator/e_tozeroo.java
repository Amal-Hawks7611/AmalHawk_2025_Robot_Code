package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_tozeroo extends Command {
    public final ElevatorSubsystem elevatorSubsystem;
    public final boolean isalg;

    public e_tozeroo(ElevatorSubsystem elevatroSubsystem, boolean isalg) {
        this.elevatorSubsystem = elevatroSubsystem;
        this.isalg = isalg;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving To The Zero");
        OI.IS_PROCESSING = true;
        Elevator.ELEVATOR_END_VALUE += 100;
        Elevator.PROCESS_START_POSITION = elevatorSubsystem.getLeaderMotorEncoder();
        Elevator.CURRENT_DIRECTION = elevatorSubsystem.getLeaderMotorEncoder() < Elevator.ELEVATOR_TOZERO_VALUE;
    }

    @Override
    public void execute() {
        if (!OI.IS_PID_ENDED) {
            if (isalg) {
                elevatorSubsystem.OcalPID(0.3,Elevator.ELEVATOR_ALGZERO_VALUE);
            } else {
                elevatorSubsystem.OCAL_PID_PREMIUM(Elevator.ELEVATOR_TOZERO_VALUE);
            }
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
        Elevator.ELEVATOR_END_VALUE -= 100;
    }

    @Override
    public boolean isFinished() {
        return OI.IS_PID_ENDED;
    }
}
