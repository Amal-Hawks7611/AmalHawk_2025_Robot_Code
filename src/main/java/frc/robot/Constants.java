package frc.robot;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.LEDPattern.GradientType;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import swervelib.math.Matter;

//ALL ROBOT SETTINGS ARE DEFINED HERE. IF YOU DON'T KNOW WHAT YOU ARE DOING, DON'T TOUCH ANYTHING!!
public final class Constants {
    public static final double ROBOT_MASS = 104.72 * 0.453592;
    public static final Matter CHASSIS = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
    public static final double LOOP_TIME = 0.13; // s, 20ms + 110ms sprk max velocity lag
    public static final double MAX_SPEED = 3;
    public static final double LIMELIGHT_KP = 0.1;
    public static final double LIMELIGHT_TX = 12;

    public static Color setBrightness(Color currentColor, double brightness) {
        return new Color(currentColor.red * brightness, currentColor.green * brightness,
                currentColor.blue * brightness);
    }

    public static final class DrivebaseConstants {
        public static final double WHEEL_LOCK_TIME = 10;
        public static final PIDConstants transation = new PIDConstants(0.1, 0.0, 0.05);
        public static final PIDConstants angle = new PIDConstants(5, 0.0, 0.05);
    }

    public static class OperatorConstants {
        public static final double DEADBAND = 0.1;
        public static final double LEFT_Y_DEADBAND = 0.1;
        public static final double RIGHT_X_DEADBAND = 0.1;
        public static final double TURN_CONSTANT = 6;
    }

    public static final class LedSubsystem {
        public static int LED_PWM_PORT = 0;
        public static int LED_LENGTH = 180;
        public static int BREATHE_MAGNITUDE = 5;
        public static LEDPattern ELEVATOR_PROCESS_COLOR = LEDPattern.solid(setBrightness(Color.kPurple, 0.69));
        public static LEDPattern TARGET_FOCUS_COLOR = LEDPattern.solid(setBrightness(Color.kGreen, 0.69));
        public static LEDPattern INTAKE_COLOR = LEDPattern.solid(setBrightness(Color.kWhite, 0.69));
        public static LEDPattern ALGEA_INTAKE_COLOR = LEDPattern.solid(setBrightness(Color.kAqua, 0.69));
        public static LEDPattern BREATHE_COLOR = LEDPattern.gradient(GradientType.kDiscontinuous,
                setBrightness(Color.kOrangeRed, 0.69), setBrightness(Color.kRed, 0.69),
                setBrightness(Color.kDarkRed, 0.69));
    }

    public static final class RotarySwitch {
        public static int DT_CHANNEL = 3;
        public static int CLK_CHANNEL = 4;
        public static int SW_CHANNEL = 5;
    }

    public static final class Elevator {
        public static int ELEVATOR_LEADER_MOTOR_PORT = 1;
        public static int ELEVATOR_FOLLOWER_MOTOR_PORT = 2;
        public static double ELEVATOR_SPEED = 0.25;
        public static double ELEVATOR_DOWN_VOLTS = 0.05;
        public static double ELEVATOR_STATIC_VOLTS = 0.3;
        public static double OCAL_PID_TOLERANCE_VALUE = 0.15;
        public static double ELEVATOR_START_VALUE = 0; // Generally True
        public static double ELEVATOR_END_VALUE = 34;
        public static double ELEVATOR_SOURCE_VALUE = 0;
        public static double ELEVATOR_PROCESSOR_VALUE = 2;
        public static double ELEVATOR_TOZERO_VALUE = 0.2;
        public static double ELEVATOR_L1_VALUE = 4.25;
        public static double ELEVATOR_L2_VALUE = 8.20;
        public static double ELEVATOR_L3_VALUE = 20.31;
        public static double ELEVATOR_L4_VALUE = 34;
        public static double ELEVATOR_ALGEA_VALUE_DOWN = 2.5;
        public static double ELEVATOR_ALGEA_VALUE_MIDDLE = 4.5;

    }

    public static class Intake {
        // Intake Motor Port
        public static double INTAKE_REVERSE_SPEED = 0.3;
        public static int INTAKE_LEADER_MOTOR_PORT = 3;
        public static double INTAKE_SPEED = 0.13;
        public static double OUTTAKE_SPEED = 0.58;
        public static double INTAKE_TIME = 0.5;
        public static double OUTTAKE_TIME = 1;
    }

    public static class AlgeaIntake {
        // Algea Intake Motor Port
        public static int ALGEA_LEADER_MOTOR_PORT = 3; // Shoud Be Same With Intake
        public static double INTAKE_SPEED = 0.2;
        public static double OUTTAKE_SPEED = 0.4;
        public static double INTAKE_TIME = 0.5;
        public static double OUTTAKE_TIME = 1;
        public static double ALGEA_STATIC_VOLTAGES = 0.050;
    }

    public static class IntakeMover {
        public static int IM_LEADER_MOTOR_PORT = 4;
        public static double INTAKE_MOVER_SPEED = 0.2;
        public static double OCAL_PID_TOLERANCE_VALUE = 0.15;
        public static double IM_STATIC_VOLTAGE = 0.15;
        public static double IM_ALGEA_STATIC = 0.25;
        public static double IM_CORAL_STATIC = 0.05;

        public static double INTAKE_PROCESSOR_VALUE = 24.2;
        public static double INTAKE_SOURCE_VALUE = 0;
        public static double INTAKE_ALGEA_VALUE = 24.2;
        public static double INTAKE_REEFSCAPE_VALUE = 2.5;
        public static double INTAKE_CORAL_VALUE = 3.7;
        public static double INTAKE_L4_VALUE = 2.9;
    }

    public static class ObjectDetector {
        public static final int SENSOR_DIO_PORT = 1;
    }

    public static class Controlls {
        public static CommandXboxController DRIVER_CONTROLLER = new CommandXboxController(OI.DRIVER_CONTROLLER_PORT);

        // For TEU
        public static Trigger ELEVATOR_MANUAL_UP = DRIVER_CONTROLLER.button(8);
        public static Trigger ELEVATOR_MANUAL_DOWN = DRIVER_CONTROLLER.button(7);
        public static Trigger INTAKE_MOVE_UP = DRIVER_CONTROLLER.button(6);
        public static Trigger INTAKE_MOVE_DOWN = DRIVER_CONTROLLER.button(5);

        public static Trigger L1 = DRIVER_CONTROLLER.button(10);
        public static Trigger L2 = DRIVER_CONTROLLER.x();
        public static Trigger L3 = DRIVER_CONTROLLER.b();
        public static Trigger L4 = DRIVER_CONTROLLER.y();

        public static Trigger GET_SOURCE = DRIVER_CONTROLLER.a();
        public static Trigger LED_CYCLE = DRIVER_CONTROLLER.button(14);
        public static Trigger ELEVATOR_TOZERO = DRIVER_CONTROLLER.povDown();

        public static int SWERVE_RIGHT_X_AXIS = 2;
        public static Trigger RESET_GYRO = DRIVER_CONTROLLER.povUp();

        public static Trigger CORAL_INTAKE = DRIVER_CONTROLLER.povLeft();
        public static Trigger CORAL_GERIAL = DRIVER_CONTROLLER.povRight();

        public static Trigger LIMELIGHT_FOCUS = DRIVER_CONTROLLER.button(11);
        public static Trigger LIMELIGHT_STOP = DRIVER_CONTROLLER.button(12);
    }

    public static class Test_Controlls {
        public static Trigger T_ELEVATOR_MANUAL_UP = Controlls.DRIVER_CONTROLLER.button(8);
        public static Trigger T_ELEVATOR_MANUAL_DOWN = Controlls.DRIVER_CONTROLLER.button(7);
        public static Trigger T_INTAKE_MOVE_UP = Controlls.DRIVER_CONTROLLER.button(6);
        public static Trigger T_INTAKE_MOVE_DOWN = Controlls.DRIVER_CONTROLLER.button(5);
        public static Trigger T_ELEVATOR_ZERO = Controlls.DRIVER_CONTROLLER.button(2);
        public static Trigger T_ALGEA_INTAKE = Controlls.DRIVER_CONTROLLER.povLeft();
        public static Trigger T_ALGEA_OUTTAKE = Controlls.DRIVER_CONTROLLER.button(3);
        public static Trigger T_CORAL_INTAKE = Controlls.DRIVER_CONTROLLER.button(1);
        public static Trigger T_CORAL_OUTTAKE = Controlls.DRIVER_CONTROLLER.povRight();
        public static Trigger T_LED_CYCLE = Controlls.DRIVER_CONTROLLER.button(4);
        public static Trigger T_LED_MORSE = Controlls.DRIVER_CONTROLLER.button(9);
        public static Trigger T_INTAKE_MOVE_L1 = Controlls.DRIVER_CONTROLLER.povUp();
    }

    public static class EnabledParts {
        // Hope Not To Use...
        public static boolean IS_LED_ENABLED = true;
        public static boolean IS_OBJECT_SENSOR_ENABLED = true;
        public static boolean IS_ALGEA_INTAKE_ENABLED = true;
        public static boolean IS_INTAKE_MOVER_ENABLED = true;
        public static boolean IS_ELEVATOR_ENABLED = true;
        public static boolean IS_SWERVE_ENABLED = true;
        public static boolean IS_ROTARY_SWITCH_ENABLED = false;
        public static boolean IS_INTAKE_ENABLED = true;
    }

    public static final class OI {
        // General Robot Constants
        public static int DRIVER_CONTROLLER_PORT = 0;
        public static String SWERVE_CANBUS_STRING = "arch";
        public static String RIO_CANBUS_STRING = "rio";
        public static boolean IS_TEST = false;
        public static boolean IS_PROCESSING = false;
        public static boolean IS_PID_ENDED = false;
        public static boolean IS_INTAKE_MOVING = false;
        public static boolean IS_SWERVE_FOCUSED = false;
        public static boolean IS_INTAKING = false;
        public static boolean IS_ALGEA_INTAKING = false;
        public static boolean IS_LED_CYCLING = false;
        public static boolean IS_LED_MORSE_SHOWING = false;
        public static boolean IS_FOCUSED = false;
    }
}
