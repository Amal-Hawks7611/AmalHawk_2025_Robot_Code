package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;
import frc.robot.subsystems.ElevatorSubsystem;

public class e_algea extends Command {
    public final ElevatorSubsystem elevatorSubsystem;
    public final boolean ismiddle;
    public e_algea(ElevatorSubsystem elevatroSubsystem, boolean ismiddle) {
        this.elevatorSubsystem = elevatroSubsystem;
        this.ismiddle = ismiddle;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Elevator Is Moving To The Algea");
        OI.IS_PROCESSING = true;
    }

    @Override
    public void execute() {
        if(!OI.IS_PID_ENDED){
            if(ismiddle){
                elevatorSubsystem.OcalPID(Elevator.ELEVATOR_SPEED, Elevator.ELEVATOR_ALGEA_VALUE_MIDDLE);
            }else{elevatorSubsystem.OcalPID(Elevator.ELEVATOR_SPEED, Elevator.ELEVATOR_ALGEA_VALUE_DOWN);}
        }
        else{
            this.end(false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.leaderMotor.stopMotor();
        elevatorSubsystem.followerMotor.stopMotor();
        OI.IS_PID_ENDED = true;
        OI.IS_PROCESSING = false;
    }

    @Override
    public boolean isFinished() {
        return OI.IS_PID_ENDED;
    }
}
