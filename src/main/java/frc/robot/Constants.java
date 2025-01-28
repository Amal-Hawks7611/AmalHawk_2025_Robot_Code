package frc.robot;

import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public final class Constants {
    public static final class Swerve {
        public static final double WHEELBASE = 0.58;
        public static final double TRACKWIDTH = 0.58;
        public static final double MAX_SPEED_METERS_PER_SECOND = 3.0;
        public static final double DRIVE_ENCODER_POSITION_CONVERSION = 0.0001; // TODO: Verify conversion factor for
                                                                               // encoder position
        public static final double DRIVE_ENCODER_VELOCITY_CONVERSION = 0.0001; // TODO: Verify conversion factor for
                                                                               // encoder velocity

        // Motor configuration
        public static final Object TALON_CONFIG = new TalonFXConfiguration();
        @SuppressWarnings("removal")
        public static final Object CANCODER_CONFIG = new CANCoderConfiguration();

        public static final PIDConstants TRANSLATION_PID = new PIDConstants(4.0, 0.0, 0.0); // TODO: Tune PID values
        public static final PIDConstants ROTATION_PID = new PIDConstants(4.0, 0.0, 0.0); // TODO: Tune PID values
        public static final double LIMELIGHT_ALIGN_KP = 0.5;

        public static final boolean IS_FIELD_RELATIVE = true;

        // TODO Offsets Will Be Entered
        public static final double FRONT_LEFT_MODULE_DRIVE_OFFSET = 0.0;
        public static final double FRONT_LEFT_MODULE_STEER_OFFSET = 0.0;
        public static final double FRONT_RIGHT_MODULE_DRIVE_OFFSET = 0.0;
        public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = 0.0;
        public static final double BACK_LEFT_MODULE_DRIVE_OFFSET = 0.0;
        public static final double BACK_LEFT_MODULE_STEER_OFFSET = 0.0;
        public static final double BACK_RIGHT_MODULE_DRIVE_OFFSET = 0.0;
        public static final double BACK_RIGHT_MODULE_STEER_OFFSET = 0.0;
    }

    public static final class SwervePorts {
        // TODO Ports Will Be Entered
        public static final int FRONT_LEFT_DRIVE_MOTOR = 1;
        public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
        public static final int BACK_LEFT_DRIVE_MOTOR = 3;
        public static final int BACK_RIGHT_DRIVE_MOTOR = 4;

        public static final int FRONT_LEFT_STEER_MOTOR = 5;
        public static final int FRONT_RIGHT_STEER_MOTOR = 6;
        public static final int BACK_LEFT_STEER_MOTOR = 7;
        public static final int BACK_RIGHT_STEER_MOTOR = 8;

        public static final int FRONT_LEFT_CANCODER = 9;
        public static final int FRONT_RIGHT_CANCODER = 10;
        public static final int BACK_LEFT_CANCODER = 11;
        public static final int BACK_RIGHT_CANCODER = 12;
    }

    public static final class LedSubsystem {
        public static final int LED_PWM_PORT = 0;
        public static final int LED_LENGTH = 60; // Default
        public static final LEDPattern RED_ALLIANCE_COLOR = LEDPattern.solid(Color.kRed);
        public static final LEDPattern BLUE_ALLIANCE_COLOR = LEDPattern.solid(Color.kBlue);
        public static final LEDPattern ELEVATOR_PROCESS_COLOR = LEDPattern.solid(Color.kPurple);
        public static final LEDPattern TARGET_FOCUS_COLOR = LEDPattern.solid(Color.kGreen);
        public static final LEDPattern INTAKE_COLOR = LEDPattern.solid(Color.kYellow);
        public static final LEDPattern BREATHE_COLOR = LEDPattern.solid(Color.kWhite);
    }

    public static final class Elevator {
        // TODO Encoder Values Wİll Be Updated
        public static final double ELEVATOR_START_VALUE = 0; // Generally True
        public static final double ELEVATOR_END_VALUE = 450;
        public static final double ELEVATOR_SOURCE_VALUE = 100;
        public static final double ELEVATOR_PROCESSOR_VALUE = 150;
        public static final double ELEVATOR_REEFSCAPE_VALUE = 200;
        public static final double ELEVATOR_L1_VALUE = 250;
        public static final double ELEVATOR_L2_VALUE = 300;
        public static final double ELEVATOR_L3_VALUE = 350;
        public static final double ELEVATOR_L4_VALUE = 400;
        public static final double ELEVATOR_NET_VALUE = 450;
        public static final double ELEVATOR_ALGEA_VALUE_DOWN = 220;
        public static final double ELEVATOR_ALGEA_VALUE_MIDDLE = 240;

        public static final int ELEVATOR_LEADER_MOTOR_PORT = 0;
        public static final int ELEVATOR_FOLLOWER_MOTOR_PORT = 1;
        public static final double OCAL_PID_TOLERANCE_VALUE = 5;

    }

    public static class Intake {
        // Intake Motor Port
        public static final int LEADER_MOTOR_PORT = 0;
        public static final double INTAKE_SPEED = 0.5;
        public static final double OUTTAKE_SPEED = 0.3;
    }

    public static class AlgeaIntake {
        // Algea Intake Motor Port
        public static final int LEADER_MOTOR_PORT = 0; // Shoud Be Same With Intake
        public static final double INTAKE_SPEED = 0.5;
        public static final double OUTTAKE_SPEED = 0.3;
    }

    public static class IntakeMover {
        public static final int LEADER_MOTOR_PORT = 0;
        public static final double INTAKE_MOVER_SPEED = 0.5;
        public static final double OCAL_PID_TOLERANCE_VALUE = 5;

        // TODO Encoder Values Will Be Updated
        public static final double INTAKE_PROCESSOR_VALUE = 50;
        public static final double INTAKE_SOURCE_VALUE = 100;
        public static final double INTAKE_ALGEA_VALUE = 150;
        public static final double INTAKE_REEFSCAPE_VALUE = 100;
        public static final double INTAKE_CORAL_VALUE = 200;
        public static final double INTAKE_L4_VALUE = 350;
        public static final double INTAKE_NET_VALUE = 400;
    }

    public static class Colormatcher {
        // TODO Coral Color Will Be Entered
        public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;
        public static final Color CORAL_TARGET = new Color();
    }

    public static class Controlls {
        public static final CommandXboxController DRIVER_CONTROLLER = new CommandXboxController(
                OI.DRIVER_CONTROLLER_PORT);

        public static final Trigger ELEVATOR_MANUAL_UP = DRIVER_CONTROLLER.rightTrigger();
        public static final Trigger ELEVATOR_MANUAL_DOWN = DRIVER_CONTROLLER.leftTrigger();

        public static final Trigger INTAKE_MOVE_UP = DRIVER_CONTROLLER.rightBumper();
        public static final Trigger INTAKE_MOVE_DOWN = DRIVER_CONTROLLER.leftBumper();

        public static final Trigger ALGEA_PROCESSOR = DRIVER_CONTROLLER.a();
        public static final Trigger ALGEA_INTAKE_MIDDLE = DRIVER_CONTROLLER.x();
        public static final Trigger ALGEA_INTAKE_DOWN = DRIVER_CONTROLLER.b();
        public static final Trigger ALGEA_NET = DRIVER_CONTROLLER.y();

        public static final Trigger L1 = DRIVER_CONTROLLER.povDown();
        public static final Trigger L2 = DRIVER_CONTROLLER.povLeft();
        public static final Trigger L3 = DRIVER_CONTROLLER.povUp();
        public static final Trigger L4 = DRIVER_CONTROLLER.povRight();
    }

    public static class EnabledParts {
        // Hope Not To Use...
        public static final boolean IS_LED_ENABLED = true;
        public static final boolean IS_COLOR_SENSOR_ENABLED = true;
        public static final boolean IS_INTAKE_ENABLED = true;
        public static final boolean IS_ALGEA_INTAKE_ENABLED = true;
        public static final boolean IS_INTAKE_MOVER_ENABLED = true;
        public static final boolean IS_ELEVATOR_ENABLED = true;
        public static final boolean IS_SWERVE_ENABLED = true;
    }

    public static final class OI {
        // General Robot Constants
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static double ELEVATOR_SPEED = 0.5;
        public static boolean IS_TEST = false;
        public static boolean IS_PROCESSING = false;
        public static boolean IS_PID_ENDED = false;
        public static boolean IS_INTAKE_MOVING = false;
        public static boolean IS_SWERVE_FOCUSED = false;
        public static boolean IS_INTAKING = false;
        public static boolean IS_ALGEA_INTAKING = false;
    }
}
