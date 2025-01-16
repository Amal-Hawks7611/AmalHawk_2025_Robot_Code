package frc.robot.subsystems;

import frc.robot.Constants.Elevator;
import frc.robot.Constants.OI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

public class ElevatorSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    public TalonFX followerMotor;

    private StatusSignal<Angle> leaderMotorPosition;
    private StatusSignal<Angle> followerMotorPosition;

    public ElevatorSubsystem() {
        leaderMotor = new TalonFX(Elevator.ELEVATOR_LEADER_MOTOR_PORT);
        followerMotor = new TalonFX(Elevator.ELEVATOR_FOLLOWER_MOTOR_PORT);
        leaderMotorPosition = leaderMotor.getPosition();
        followerMotorPosition = followerMotor.getPosition();

        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        return leaderMotorPosition.refresh().getValueAsDouble();
    }

    public double getFollowerMotorEncoder() {
        return followerMotorPosition.refresh().getValueAsDouble();
    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
        followerMotor.setPosition(0);
    }

    public void manualControl(double speed) {
        leaderMotor.set(speed);
        followerMotor.set(speed);
    }

    public void OcalPID(double speed, double setpoint) {
        if (!OI.IS_PID_ENDED) {
            double leaderPosition = getLeaderMotorEncoder();

            if (isWithinTolerance(leaderPosition, Elevator.ELEVATOR_END_VALUE) ||
                isWithinTolerance(leaderPosition, Elevator.ELEVATOR_START_VALUE) ||
                isWithinTolerance(leaderPosition, setpoint)) {

                stopMotors();
                OI.IS_PID_ENDED = true;
            } else {
                leaderMotor.set(speed);
                followerMotor.set(speed);
            }
        }
    }

    private boolean isWithinTolerance(double value, double target) {
        return value >= target - 0.02 && value <= target + 0.02;
    }

    private void stopMotors() {
        leaderMotor.stopMotor();
        followerMotor.stopMotor();
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("IsElevatorProcessing", OI.IS_PROCESSING);
        SmartDashboard.putNumber("Leader Motor Value", getLeaderMotorEncoder());
        SmartDashboard.putNumber("Follower Motor Encoder", getFollowerMotorEncoder());
    }
}
