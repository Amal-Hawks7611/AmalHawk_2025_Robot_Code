package frc.robot.subsystems;

import frc.robot.Constants.Elevator;
import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.OI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
//ELEVATOR CODE. USED OCALPID INSTEAD OF WPILIB CONTROLL SYSTEMS(FEETFORWARD,CLOSE-LOOP) THAT MAKES NON-SENSE.
public class ElevatorSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    public TalonFX followerMotor;
    public double simulationEncoder;
    public StatusSignal<Angle> leaderMotorPosition;
    private StatusSignal<Angle> followerMotorPosition;

    public ElevatorSubsystem() {
        leaderMotor = new TalonFX(Elevator.ELEVATOR_LEADER_MOTOR_PORT, OI.RIO_CANBUS_STRING);
        followerMotor = new TalonFX(Elevator.ELEVATOR_FOLLOWER_MOTOR_PORT, OI.RIO_CANBUS_STRING);
        leaderMotorPosition = leaderMotor.getPosition();
        followerMotorPosition = followerMotor.getPosition();
        simulationEncoder = 0;
        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        if(RobotBase.isSimulation()){return simulationEncoder;}
        else{return leaderMotorPosition.refresh().getValueAsDouble();}
    }

    public double getFollowerMotorEncoder() {
        if(RobotBase.isSimulation()){return simulationEncoder;}
        else{return followerMotorPosition.refresh().getValueAsDouble();}
        
    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
        followerMotor.setPosition(0);
    }

    public void manualControl(double speed) {
        leaderMotor.set(speed);
        followerMotor.set(speed);
        simulationEncoder+=4355;
    }

    //Different to the pid, ocalpid controlls distance instead of speed. Because TalonFX already controlls speed
    public void OcalPID(double speed, double setpoint) {
        if (EnabledParts.IS_ELEVATOR_ENABLED) {
            if (!OI.IS_PID_ENDED) {
                double leaderPosition = getLeaderMotorEncoder();
                if (isWithinTolerance(leaderPosition, Elevator.ELEVATOR_END_VALUE)
                        || isWithinTolerance(leaderPosition, setpoint)) {
                        stopMotors();
                        OI.IS_PID_ENDED = true;
                } else {
                    if (leaderPosition > setpoint) {
                        if(setpoint <= 1){
                            leaderMotor.set(-0.1);
                            followerMotor.set(-0.1);
                        }
                        else{
                            leaderMotor.set(-speed*0.7);
                            followerMotor.set(-speed*0.7);
                        }
                        simulationEncoder -= 4355;
                    } else {
                        leaderMotor.set(speed);
                        followerMotor.set(speed);
                        simulationEncoder += 4355;
                    }
                }
            }else{
                Elevator.ELEVATOR_SPEED = 0.2;
            }
        } else {
            OI.IS_PID_ENDED = true;
        }
    }

    public boolean isWithinTolerance(double value, double target) {
        return value >= target - Elevator.OCAL_PID_TOLERANCE_VALUE && value <= target + Elevator.OCAL_PID_TOLERANCE_VALUE;
    }

    private void stopMotors() {
        leaderMotor.stopMotor();
        followerMotor.stopMotor();
    }

    @Override
    public void periodic() {
        if(OI.IS_PID_ENDED || !OI.IS_PROCESSING){
            leaderMotor.setVoltage(Elevator.ELEVATOR_STATIC_VOLTS);
            followerMotor.setVoltage(Elevator.ELEVATOR_STATIC_VOLTS);}
        SmartDashboard.putNumber("Elevator Speed",Elevator.ELEVATOR_SPEED);
        SmartDashboard.putBoolean("IsElevatorProcessing", OI.IS_PROCESSING);
        SmartDashboard.putNumber("Elevator Leader Motor Value", getLeaderMotorEncoder());
        SmartDashboard.putNumber("Elevator Follower Motor Encoder", getFollowerMotorEncoder());
    }
}
