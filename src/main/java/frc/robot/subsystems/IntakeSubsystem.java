package frc.robot.subsystems;

import frc.robot.RobotContainer;
import frc.robot.Constants.Controlls;
import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.Intake;
import frc.robot.Constants.OI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

//INTAKE SUBSYSTEM. YOU CAN INTAKE IF THERE IS NO CORAL AND YOU CAN OUTTAKE IF THERE IS CORAL. 
//CORAL FALL PROTECTION ENSURED WITH A TYPE OF STATIC VOLTAGE BUT MORE BRUTAL
public class IntakeSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    private StatusSignal<Angle> leaderMotorPosition;
    public RobotContainer container;

    public Timer timer = new Timer();

    public IntakeSubsystem(RobotContainer container) {
        leaderMotor = new TalonFX(Intake.INTAKE_LEADER_MOTOR_PORT, OI.RIO_CANBUS_STRING);
        leaderMotorPosition = leaderMotor.getPosition();
        this.container = container;
        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        return leaderMotorPosition.refresh().getValueAsDouble();
    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
    }

    public void Intake() {
        if (!EnabledParts.IS_OBJECT_SENSOR_ENABLED) {
            if (timer.hasElapsed(Intake.INTAKE_TIME)) {
                leaderMotor.stopMotor();
                OI.IS_INTAKING = false;
            } else {
                leaderMotor.set(-Intake.INTAKE_SPEED);
            }
        } else {
            if (!container.objectDetector.CheckObject()) {
                leaderMotor.set(-Intake.INTAKE_SPEED);
            } else {
                leaderMotor.stopMotor();
                OI.IS_INTAKING = false;
            }
        }
    }

    public void Shoot() {
        if (!EnabledParts.IS_OBJECT_SENSOR_ENABLED) {
            if (timer.hasElapsed(-Intake.OUTTAKE_TIME)) {
                leaderMotor.stopMotor();
                OI.IS_INTAKING = false;
            } else {
                leaderMotor.set(-Intake.OUTTAKE_SPEED);
            }
        } else {
            if (container.objectDetector.CheckObject()) {
                leaderMotor.set(-Intake.OUTTAKE_SPEED);
            } else {
                leaderMotor.stopMotor();
                OI.IS_INTAKING = false;
            }
        }

    }

    @Override
    public void periodic() {
        if (!OI.IS_INTAKING && container.objectDetector.CheckObject()) {
            leaderMotor.set(4.3);
        }
        if (container.objectDetector.CheckObject()) {
            Controlls.OPERATOR_CONTROLLER.setRumble(RumbleType.kBothRumble, 0.7);
        } else {
            Controlls.OPERATOR_CONTROLLER.setRumble(RumbleType.kBothRumble, 0);
        }
        SmartDashboard.putBoolean("IsCoralIntaking", OI.IS_INTAKING);
        SmartDashboard.putNumber("Intake Leader Motor Value", getLeaderMotorEncoder());
        SmartDashboard.putNumber("IntakeSPeed", leaderMotor.get());
    }
}
