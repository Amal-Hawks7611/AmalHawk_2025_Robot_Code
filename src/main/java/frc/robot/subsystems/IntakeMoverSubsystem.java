package frc.robot.subsystems;

import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.IntakeMover;
import frc.robot.Constants.OI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeMoverSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    private StatusSignal<Angle> leaderMotorPosition;

    public IntakeMoverSubsystem() {
        leaderMotor = new TalonFX(IntakeMover.LEADER_MOTOR_PORT);
        leaderMotorPosition = leaderMotor.getPosition();

        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        return leaderMotorPosition.refresh().getValueAsDouble();
    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
    }

    public void manualControl(double speed) {
        leaderMotor.set(speed);
    }

    public void MoveIT(double speed, double setpoint) {
        if(EnabledParts.IS_INTAKE_MOVER_ENABLED){
            if (OI.IS_INTAKE_MOVING) {
                double leaderPosition = getLeaderMotorEncoder();
                if (isWithinTolerance(leaderPosition, setpoint)) {
                    stopMotors();
                    OI.IS_INTAKE_MOVING = false;
                } else {
                    if(leaderPosition > setpoint){
                        leaderMotor.set(-speed);
                    }
                    else{
                        leaderMotor.set(speed);
                    }
                }
            }
        }
        else{
            OI.IS_INTAKE_MOVING = false;
        }
    }

    private boolean isWithinTolerance(double value, double target) {
        return value >= target - IntakeMover.OCAL_PID_TOLERANCE_VALUE && value <= target + IntakeMover.OCAL_PID_TOLERANCE_VALUE;
    }

    private void stopMotors() {
        leaderMotor.stopMotor();
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("IsIntakeMoving", OI.IS_INTAKE_MOVING);
        SmartDashboard.putNumber("Intake Mover Leader Motor Value", getLeaderMotorEncoder());
    }
}
