package frc.robot.subsystems;

import frc.robot.Constants.Intake;
import frc.robot.Constants.OI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

//INTAKE SUBSYSTEM. YOU CAN INTAKE IF THERE IS NO CORAL AND YOU CAN OUTTAKE IF THERE IS CORAL. BASIC LOGIC!
public class IntakeSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    private StatusSignal<Angle> leaderMotorPosition;

    public Timer timer = new Timer();

    public IntakeSubsystem() {
        leaderMotor = new TalonFX(Intake.INTAKE_LEADER_MOTOR_PORT);
        leaderMotorPosition = leaderMotor.getPosition();

        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        return leaderMotorPosition.refresh().getValueAsDouble();
    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
    }

    public void Move() {
        if(timer.hasElapsed(1.5)){
            leaderMotor.stopMotor();
            OI.IS_INTAKING = false;
        }
        else{
            leaderMotor.set(Intake.INTAKE_SPEED);   
        }}
    
    public void Shoot(){
        if(timer.hasElapsed(1.5)){
            leaderMotor.stopMotor();
            OI.IS_INTAKING = false;
        }
        else{
            leaderMotor.set(Intake.OUTTAKE_SPEED);
        }
    }
    @Override
    public void periodic(){
        SmartDashboard.putBoolean("IsCoralIntaking", OI.IS_INTAKING);
        SmartDashboard.putNumber("Intake Leader Motor Value", getLeaderMotorEncoder());
    }
}
