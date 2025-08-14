package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_movedown extends Command {
    public final ElevatorSubsystem elevatorSubsystem;

    public e_movedown(ElevatorSubsystem elevatroSubsystem) {
        this.elevatorSubsystem = elevatroSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving Down Manually");
        OI.IS_PROCESSING = true;
    }

    @Override
    public void execute() {
        elevatorSubsystem.leaderMotor.set(-Elevator.ELEVATOR_DOWN_VOLTS);
        elevatorSubsystem.followerMotor.set(-Elevator.ELEVATOR_DOWN_VOLTS);
        Elevator.ELEVATOR_STATIC_VOLTS = 0;
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.leaderMotor.stopMotor();
        elevatorSubsystem.followerMotor.stopMotor();
        OI.IS_PID_ENDED = false;
        OI.IS_PROCESSING = false;
        Elevator.ELEVATOR_STATIC_VOLTS = 0.3;
    }

    @Override
    public boolean isFinished() {
        return OI.IS_PID_ENDED;
    }
}
