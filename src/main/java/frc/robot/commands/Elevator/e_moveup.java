package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_moveup extends Command {
    public final ElevatorSubsystem elevatorSubsystem;

    public e_moveup(ElevatorSubsystem elevatroSubsystem) {
        this.elevatorSubsystem = elevatroSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving Up Manually");
        OI.IS_PROCESSING = true;
    }

    @Override
    public void execute() {
        if(elevatorSubsystem.leaderMotorPosition.refresh().getValueAsDouble() < Elevator.ELEVATOR_END_VALUE){
            elevatorSubsystem.manualControl(Elevator.ELEVATOR_SPEED);
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
