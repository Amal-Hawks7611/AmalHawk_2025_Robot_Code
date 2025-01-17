package frc.robot.subsystems;

import frc.robot.Constants.AlgeaIntake;
import frc.robot.Constants.OI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

public class AlgeaIntakeSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    private StatusSignal<Angle> leaderMotorPosition;

    public Timer timer = new Timer();

    public AlgeaIntakeSubsystem() {
        leaderMotor = new TalonFX(AlgeaIntake.LEADER_MOTOR_PORT);
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

    public void Intake(boolean inverted) {
        if(timer.hasElapsed(AlgeaIntake.INTAKE_TIME)){
            leaderMotor.stopMotor();
            OI.IS_ALGEA_INTAKING = false;
        }
        else{
            if(inverted){leaderMotor.set(-AlgeaIntake.INTAKE_SPEED);}
            else{leaderMotor.set(AlgeaIntake.INTAKE_SPEED);}
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("IsIntaking", OI.IS_ALGEA_INTAKING);
        SmartDashboard.putNumber("Intake Leader Motor Value", getLeaderMotorEncoder());
    }
}
