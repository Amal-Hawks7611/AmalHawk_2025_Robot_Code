package frc.robot.subsystems;


import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.core.CoreCANcoder;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import com.studica.frc.AHRS;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Swerve;
import frc.robot.Constants.SwervePorts;
import frc.robot.LimelightHelpers;


public class SwerveSubsystem extends SubsystemBase {

    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule backLeft;
    private final SwerveModule backRight;
    public AHRS gyro;
    public static final SwerveDriveKinematics kinematics;
    private final SwerveDriveOdometry odometry;
    public final SwerveDrivePoseEstimator poseEstimator;

    //TODO Kinematics Wİll Be Entered
    static {
        kinematics = new SwerveDriveKinematics(
            new Translation2d(0.3, 0.3), 
            new Translation2d(0.3, -0.3),
            new Translation2d(-0.3, 0.3), 
            new Translation2d(-0.3, -0.3) 
        );
    }

    public SwerveSubsystem() {
        gyro = new AHRS(AHRS.NavXComType.kMXP_SPI);

        frontLeft = new SwerveModule(
            SwervePorts.FRONT_LEFT_DRIVE_MOTOR, SwervePorts.FRONT_LEFT_STEER_MOTOR,
            SwervePorts.FRONT_LEFT_CANCODER,
            Swerve.FRONT_LEFT_MODULE_DRIVE_OFFSET, Swerve.FRONT_LEFT_MODULE_STEER_OFFSET
        );
        frontRight = new SwerveModule(
            SwervePorts.FRONT_RIGHT_DRIVE_MOTOR, SwervePorts.FRONT_RIGHT_STEER_MOTOR,
            SwervePorts.FRONT_RIGHT_CANCODER,
            Swerve.FRONT_RIGHT_MODULE_DRIVE_OFFSET, Swerve.FRONT_RIGHT_MODULE_STEER_OFFSET
        );
        backLeft = new SwerveModule(
            SwervePorts.BACK_LEFT_DRIVE_MOTOR, SwervePorts.BACK_LEFT_STEER_MOTOR,
            SwervePorts.BACK_LEFT_CANCODER,
            Swerve.BACK_LEFT_MODULE_DRIVE_OFFSET, Swerve.BACK_LEFT_MODULE_STEER_OFFSET
        );
        backRight = new SwerveModule(
            SwervePorts.BACK_RIGHT_DRIVE_MOTOR, SwervePorts.BACK_RIGHT_STEER_MOTOR,
            SwervePorts.BACK_RIGHT_CANCODER,
            Swerve.BACK_RIGHT_MODULE_DRIVE_OFFSET, Swerve.BACK_RIGHT_MODULE_STEER_OFFSET
        );

        odometry = new SwerveDriveOdometry(
            kinematics, 
            Rotation2d.fromDegrees(gyro.getYaw()), 
            new SwerveModulePosition[] {
                frontLeft.getPosition(),
                frontRight.getPosition(),
                backLeft.getPosition(),
                backRight.getPosition()
            }
        );

        poseEstimator = new SwerveDrivePoseEstimator(kinematics, gyro.getRotation2d(), new SwerveModulePosition[]{frontLeft.getPosition(), frontRight.getPosition(), backLeft.getPosition(), backRight.getPosition()}, getPose());

        //TODO Pathplanner Update
        RobotConfig config;
        try{
            config = RobotConfig.fromGUISettings();

        } catch (Exception e) {
            e.printStackTrace();
            config = null;
        }

        AutoBuilder.configure(
            this::getPose, 
            this::resetOdometry, 
            this::getRobotRelativeSpeeds, 
            (speeds, feedforwards) -> driveRobotRelative(speeds), 
            new PPHolonomicDriveController(
                    Swerve.TRANSLATION_PID, 
                    Swerve.ROTATION_PID 
            ),
            config, 
            () -> {
              var alliance = DriverStation.getAlliance();
              if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
              }
              return false;
            },
            this
            );
    }

    //TODO Not Sure
    public void driveRobotRelative(ChassisSpeeds speeds) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(speeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, 3.0);

        frontLeft.setDesiredState(applySecondOrderKinematics(swerveModuleStates[0]));
        frontRight.setDesiredState(applySecondOrderKinematics(swerveModuleStates[1]));
        backLeft.setDesiredState(applySecondOrderKinematics(swerveModuleStates[2]));
        backRight.setDesiredState(applySecondOrderKinematics(swerveModuleStates[3]));

        SmartDashboard.putString("FL Desired State", swerveModuleStates[0].toString());
        SmartDashboard.putString("FR Desired State", swerveModuleStates[1].toString());
        SmartDashboard.putString("BL Desired State", swerveModuleStates[2].toString());
        SmartDashboard.putString("BR Desired State", swerveModuleStates[3].toString());
    }

    private ChassisSpeeds getRobotRelativeSpeeds() {
        SwerveModuleState frontLeftState = frontLeft.getState();
        SwerveModuleState frontRightState = frontRight.getState();
        SwerveModuleState backLeftState = backLeft.getState();
        SwerveModuleState backRightState = backRight.getState();
        return kinematics.toChassisSpeeds(
            frontLeftState,
            frontRightState,
            backLeftState,
            backRightState
        );
    }

    public Command drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        return run(() -> {
            double currentYaw = -gyro.getYaw();

            SmartDashboard.putNumber("Drive/X Speed", xSpeed);
            SmartDashboard.putNumber("Drive/Y Speed", ySpeed);
            SmartDashboard.putNumber("Drive/Rotation Speed", rot);
            SmartDashboard.putBoolean("Drive/Field Relative", fieldRelative);
            SmartDashboard.putNumber("Drive/Gyro Yaw", currentYaw);

            ChassisSpeeds speeds = fieldRelative
                ? ChassisSpeeds.fromFieldRelativeSpeeds(
                      xSpeed, ySpeed, rot, Rotation2d.fromDegrees(currentYaw))
                : new ChassisSpeeds(xSpeed, ySpeed, rot);

            var swerveModuleStates = kinematics.toSwerveModuleStates(speeds);
            SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, 3.0);

            frontLeft.setDesiredState(applySecondOrderKinematics(swerveModuleStates[0]));
            frontRight.setDesiredState(applySecondOrderKinematics(swerveModuleStates[1]));
            backLeft.setDesiredState(applySecondOrderKinematics(swerveModuleStates[2]));
            backRight.setDesiredState(applySecondOrderKinematics(swerveModuleStates[3]));

            SmartDashboard.putString("FL Desired State", swerveModuleStates[0].toString());
            SmartDashboard.putString("FR Desired State", swerveModuleStates[1].toString());
            SmartDashboard.putString("BL Desired State", swerveModuleStates[2].toString());
            SmartDashboard.putString("BR Desired State", swerveModuleStates[3].toString());
        });

    }

    public double CalculateLimelightAim(){
        double kMaxAngularSpeed = Swerve.MAX_SPEED_METERS_PER_SECOND / Math.hypot(0.3, 0.3);//TODO Kinematics Will Be Entered
        double targetingAngularVelocity = LimelightHelpers.getTX("limelight") * Swerve.LIMELIGHT_ALIGN_KP;
        targetingAngularVelocity *= kMaxAngularSpeed;
        targetingAngularVelocity *= -1.0;
        return targetingAngularVelocity;
    }

    //THIS SHIT TOOK MY WHOLE NIGHT
    private SwerveModuleState applySecondOrderKinematics(SwerveModuleState state) {
        double vmx = state.speedMetersPerSecond * Math.cos(state.angle.getRadians());
        double vmy = state.speedMetersPerSecond * Math.sin(state.angle.getRadians());

        double correctedSpeed = Math.sqrt(vmx * vmx + vmy * vmy);
        double correctedAngle = Math.atan2(vmy, vmx);

        return new SwerveModuleState(correctedSpeed, new Rotation2d(correctedAngle));
    }

    @Override
    public void periodic() {
        double currentYaw = -gyro.getYaw();
        poseEstimator.update(gyro.getRotation2d(), new SwerveModulePosition[]{frontLeft.getPosition(), frontRight.getPosition(), backLeft.getPosition(), backRight.getPosition()});
        odometry.update(
            Rotation2d.fromDegrees(currentYaw),
            new SwerveModulePosition[] {
                frontLeft.getPosition(),
                frontRight.getPosition(),
                backLeft.getPosition(),
                backRight.getPosition()
            }
        );

        SmartDashboard.putString("Odometry Pose", odometry.getPoseMeters().toString());
        SmartDashboard.putNumber("Gyro Yaw", currentYaw);
        SmartDashboard.putString("FL Position", frontLeft.getPosition().toString());
        SmartDashboard.putString("FR Position", frontRight.getPosition().toString());
        SmartDashboard.putString("BL Position", backLeft.getPosition().toString());
        SmartDashboard.putString("BR Position", backRight.getPosition().toString());
    }



    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }



    public void resetOdometry(Pose2d pose) {
        odometry.resetPosition(
            Rotation2d.fromDegrees(-gyro.getYaw()), 
            new SwerveModulePosition[] {
                frontLeft.getPosition(),
                frontRight.getPosition(),
                backLeft.getPosition(),
                backRight.getPosition()
            },
            pose
        );

    }

}



class SwerveModule {
    private final TalonFX driveMotor;
    private final TalonFX steerMotor;
    private final CoreCANcoder steerEncoder;
    @SuppressWarnings("unused")
    private final double driveEncoderOffset;
    private final double steerEncoderOffset;


    public SwerveModule(int driveMotorPort, int steerMotorPort, int steerEncoderPort, double driveEncoderOffset, double steerEncoderOffset) {
        driveMotor = new TalonFX(driveMotorPort);
        steerMotor = new TalonFX(steerMotorPort);
        steerEncoder = new CoreCANcoder(steerEncoderPort);

        TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();
        TalonFXConfiguration steerMotorConfig = new TalonFXConfiguration();
        CANcoderConfiguration steerEncoderConfig = new CANcoderConfiguration();

        driveMotor.getConfigurator().apply(driveMotorConfig);
        steerMotor.getConfigurator().apply(steerMotorConfig);
        steerEncoder.getConfigurator().apply(steerEncoderConfig);

        this.driveEncoderOffset = driveEncoderOffset;
        this.steerEncoderOffset = steerEncoderOffset;
    }



    public SwerveModuleState getState() {
        double adjustedDriveRate = driveMotor.getVelocity().getValueAsDouble() * Swerve.DRIVE_ENCODER_VELOCITY_CONVERSION;
        double adjustedSteerAngle = steerEncoder.getPosition().getValueAsDouble() - steerEncoderOffset;

        return new SwerveModuleState(
            adjustedDriveRate,
            Rotation2d.fromDegrees(adjustedSteerAngle)
        );

    }



    public SwerveModulePosition getPosition() {
        double adjustedDriveDistance = driveMotor.getPosition().getValueAsDouble() * Swerve.DRIVE_ENCODER_POSITION_CONVERSION;
        double adjustedSteerAngle = steerEncoder.getPosition().getValueAsDouble() - steerEncoderOffset;

        return new SwerveModulePosition(
            adjustedDriveDistance,
            Rotation2d.fromDegrees(adjustedSteerAngle)
        );

    }



    public void setDesiredState(SwerveModuleState desiredState) {
        Rotation2d currentAngle = getState().angle;
        double deltaAngle = desiredState.angle.minus(currentAngle).getRadians();

        if (Math.abs(deltaAngle) > Math.PI / 2) {
            desiredState = new SwerveModuleState(
                -desiredState.speedMetersPerSecond,
                desiredState.angle.rotateBy(Rotation2d.fromDegrees(180))
            );
        }

        driveMotor.set(desiredState.speedMetersPerSecond / Swerve.MAX_SPEED_METERS_PER_SECOND);
        steerMotor.set(desiredState.angle.getDegrees() + steerEncoderOffset);
    }

}
