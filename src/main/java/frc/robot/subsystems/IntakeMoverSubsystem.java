package frc.robot.subsystems;

import frc.robot.RobotContainer;
import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.IntakeMover;
import frc.robot.Constants.OI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

//INTAKE MOVER SUBSYSTEM INTAGRATED WITH OCALPID, IT WILL BE JUST GONE TO THE SPECIFIC ENCODER VALUE OR MOVED MANUALLY.
//STATIC VOLTAGE IS HERE TOO. IT INCREASES WHEN IT DETECTS CORAL!
public class IntakeMoverSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    private StatusSignal<Angle> leaderMotorPosition;
    public RobotContainer container;
    public double simEncoder;

    public IntakeMoverSubsystem(RobotContainer container) {
        leaderMotor = new TalonFX(IntakeMover.IM_LEADER_MOTOR_PORT, OI.RIO_CANBUS_STRING);
        leaderMotorPosition = leaderMotor.getPosition();
        simEncoder = 0;
        this.container = container;
        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        if (RobotBase.isSimulation()) {
            return simEncoder;
        } else {
            return leaderMotorPosition.refresh().getValueAsDouble();
        }

    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
    }

    public void manualControl(double speed) {
        leaderMotor.set(speed);

        double maxRPM = 6000;
        double loopTimeSeconds = 0.02;

        double revolutionsPerSecond = (maxRPM / 60.0) * Math.abs(speed);
        double revolutionsPerLoop = revolutionsPerSecond * loopTimeSeconds;

        simEncoder += speed > 0 ? revolutionsPerLoop : -revolutionsPerLoop;
    }

    // OCALPID
    public void MoveIT(double speed, double setpoint) {
        if (EnabledParts.IS_INTAKE_MOVER_ENABLED) {
            if (OI.IS_INTAKE_MOVING) {
                double leaderPosition = getLeaderMotorEncoder();
                if (isWithinTolerance(leaderPosition, setpoint)) {
                    stopMotors();
                    OI.IS_INTAKE_MOVING = false;
                } else {
                    double maxRPM = 6000;
                    double loopTimeSeconds = 0.02;
                    double revolutionsPerSecond = (maxRPM / 60.0) * Math.abs(speed);
                    double revolutionsPerLoop = revolutionsPerSecond * loopTimeSeconds;

                    if (leaderPosition > setpoint) {
                        leaderMotor.set(-speed);
                        simEncoder -= revolutionsPerLoop;
                    } else {
                        leaderMotor.set(speed);
                        simEncoder += revolutionsPerLoop;
                    }
                }
            }
        } else {
            OI.IS_INTAKE_MOVING = false;
        }
    }

    private boolean isWithinTolerance(double value, double target) {
        return value >= target - IntakeMover.OCAL_PID_TOLERANCE_VALUE
                && value <= target + IntakeMover.OCAL_PID_TOLERANCE_VALUE;
    }

    private void stopMotors() {
        leaderMotor.stopMotor();
    }

    @Override
    public void periodic() {
        if (!OI.IS_ALGEA_INTAKING && !OI.IS_INTAKING && !OI.IS_INTAKE_MOVING
                && leaderMotorPosition.refresh().getValueAsDouble() > 5) {
            leaderMotor.setVoltage(-IntakeMover.IM_STATIC_VOLTAGE);
        }
        if (!OI.IS_ALGEA_INTAKING && !OI.IS_INTAKING && !OI.IS_INTAKE_MOVING
                && leaderMotorPosition.refresh().getValueAsDouble() < 5) {
            leaderMotor.setVoltage(IntakeMover.IM_STATIC_VOLTAGE);
        }
        if (!OI.IS_INTAKE_MOVING && OI.IS_ALGEA_INTAKING) {
            leaderMotor.setVoltage(-IntakeMover.IM_ALGEA_STATIC);
        }
        if (!OI.IS_INTAKING && !isWithinTolerance(getLeaderMotorEncoder(), 1)
                && container.objectDetector.CheckObject()) {
            leaderMotor.setVoltage(-IntakeMover.IM_CORAL_STATIC);
        }
        SmartDashboard.putBoolean("IsIntakeMoving", OI.IS_INTAKE_MOVING);
        SmartDashboard.putNumber("Intake Mover Leader Motor Value", getLeaderMotorEncoder());
    }
}
