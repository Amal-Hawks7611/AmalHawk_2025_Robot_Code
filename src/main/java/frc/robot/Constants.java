package frc.robot;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import swervelib.math.Matter;

//ALL ROBOT SETTINGS ARE DEFINED HERE. IF YOU DON'T KNOW WHAT YOU ARE DOING, DON'T TOUCH ANYTHING!!
public final class Constants {
    public static final double ROBOT_MASS = 104.72 * 0.453592; // 32lbs * kg per pound
    public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
    public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
    public static final double MAX_SPEED  = Units.feetToMeters(14.5);
    
    public static final class DrivebaseConstants
    {
      public static final double WHEEL_LOCK_TIME = 10;
      public static final PIDConstants transation = new PIDConstants(5.0,0.0,0.0);
      public static final PIDConstants angle = new PIDConstants(5.0,0.0,0.0);
    }
  
    public static class OperatorConstants
    {
      public static final double DEADBAND        = 0.1;
      public static final double LEFT_Y_DEADBAND = 0.1;
      public static final double RIGHT_X_DEADBAND = 0.1;
      public static final double TURN_CONSTANT    = 6;
    }

    public static final class Swerve {
        public static double WHEELBASE = 0.58;
        public static double TRACKWIDTH = 0.58;
        public static double MAX_SPEED_METERS_PER_SECOND = 1;
        public static double DRIVE_ENCODER_POSITION_CONVERSION = 0.0000237;
        public static double DRIVE_ENCODER_VELOCITY_CONVERSION = 0.000237;

        // Motor configuration
        public static PIDConstants TRANSLATION_PID = new PIDConstants(3.0, 0.0, 0.0); // TODO: Tune PID values
        public static PIDConstants ROTATION_PID = new PIDConstants(0, 0.0, 0.0); // TODO: Tune PID values
        public static double LIMELIGHT_ALIGN_KP = 0.5;
        public static boolean IS_FIELD_RELATIVE = false;

        // TODO Offsets Will Be Entered
        public static double FRONT_LEFT_MODULE_STEER_OFFSET = 0.0;
        public static double FRONT_RIGHT_MODULE_STEER_OFFSET = 0.0;
        public static double BACK_LEFT_MODULE_STEER_OFFSET = 0.0;
        public static double BACK_RIGHT_MODULE_STEER_OFFSET = 0.0;

        //Motor PID Configs
        public static double DRIVE_MOTOR_KP = 0.3;
        public static double DRIVE_MOTOR_KI = 0.0;
        public static double DRIVE_MOTOR_KD = 0.01;

        public static double STEER_MOTOR_KP = 0;
        public static double STEER_MOTOR_KI = 0.0;
        public static double STEER_MOTOR_KD = 0;
    }

    public static final class SwervePorts {
        // TODO Ports Will Be Entered
        public static int FRONT_LEFT_DRIVE_MOTOR = 1;
        public static int FRONT_RIGHT_DRIVE_MOTOR = 2;
        public static int BACK_LEFT_DRIVE_MOTOR = 3;
        public static int BACK_RIGHT_DRIVE_MOTOR = 4;
        public static int FRONT_LEFT_STEER_MOTOR = 5;
        public static int FRONT_RIGHT_STEER_MOTOR = 6;
        public static int BACK_LEFT_STEER_MOTOR = 7;
        public static int BACK_RIGHT_STEER_MOTOR = 8;
        public static int FRONT_LEFT_CANCODER = 5;
        public static int FRONT_RIGHT_CANCODER = 6;
        public static int BACK_LEFT_CANCODER = 7;
        public static int BACK_RIGHT_CANCODER = 8;
    }

    public static final class LedSubsystem {
        public static int LED_PWM_PORT = 0;
        public static int LED_LENGTH = 60;
        public static int BREATHE_MAGNITUDE = 5;
        public static LEDPattern RED_ALLIANCE_COLOR = LEDPattern.solid(Color.kRed);
        public static LEDPattern BLUE_ALLIANCE_COLOR = LEDPattern.solid(Color.kBlue);
        public static LEDPattern ELEVATOR_PROCESS_COLOR = LEDPattern.solid(Color.kPurple);
        public static LEDPattern TARGET_FOCUS_COLOR = LEDPattern.solid(Color.kGreen);
        public static LEDPattern INTAKE_COLOR = LEDPattern.solid(Color.kYellow);
        public static LEDPattern ALGEA_INTAKE_COLOR = LEDPattern.solid(Color.kAqua);
        public static LEDPattern BREATHE_COLOR = LEDPattern.solid(Color.kWhite);
    }

    // TODO: Update the Rotary Ports
    public static final class RotarySwitch {
        public static int DT_CHANNEL = 3;
        public static int CLK_CHANNEL = 4;
        public static int SW_CHANNEL = 5;
    }

    public static final class Elevator {
        public static int ELEVATOR_LEADER_MOTOR_PORT = 1;
        public static int ELEVATOR_FOLLOWER_MOTOR_PORT = 2;
        public static double ELEVATOR_SPEED = 0.2;
        public static double ELEVATOR_STATIC_VOLTS = 0.3;
        public static double OCAL_PID_TOLERANCE_VALUE = 0.15;
        // TODO Encoder Values Will Be Updated
        public static double ELEVATOR_START_VALUE = 0; // Generally True
        public static double ELEVATOR_END_VALUE = 32.8;
        public static double ELEVATOR_SOURCE_VALUE = 0;
        public static double ELEVATOR_PROCESSOR_VALUE = 2;
        public static double ELEVATOR_TOZERO_VALUE = 6;
        public static double ELEVATOR_L1_VALUE = 3;
        public static double ELEVATOR_L2_VALUE = 4;
        public static double ELEVATOR_L3_VALUE = 5;
        public static double ELEVATOR_L4_VALUE = 6;
        public static double ELEVATOR_ALGEA_VALUE_DOWN = 2.5;
        public static double ELEVATOR_ALGEA_VALUE_MIDDLE = 3.5; //Elevator Ticks Per Meters
 
    }

    public static class Intake {
        // Intake Motor Port
        public static int INTAKE_LEADER_MOTOR_PORT = 3;
        public static double INTAKE_SPEED = 0.4;
        public static double OUTTAKE_SPEED = 0.4;
        public static double INTAKE_TIME = 0.5;
        public static double OUTTAKE_TIME = 1;
    }

    public static class AlgeaIntake {
        // Algea Intake Motor Port
        public static int ALGEA_LEADER_MOTOR_PORT = 3; // Shoud Be Same With Intake
        public static double INTAKE_SPEED = 0.4;
        public static double OUTTAKE_SPEED = 0.4;
        public static double INTAKE_TIME = 0.5;
        public static double OUTTAKE_TIME = 1;
    }

    public static class IntakeMover {
        public static int IM_LEADER_MOTOR_PORT = 4;
        public static double INTAKE_MOVER_SPEED = 0.2;
        public static double OCAL_PID_TOLERANCE_VALUE = 0.15;
        public static double IM_STATIC_VOLTAGE = 0.08;

        // TODO Encoder Values Will Be Updated
        public static double INTAKE_PROCESSOR_VALUE = 24.2;
        public static double INTAKE_SOURCE_VALUE = 0;
        public static double INTAKE_ALGEA_VALUE = 24.2;
        public static double INTAKE_REEFSCAPE_VALUE = 2.5;
        public static double INTAKE_CORAL_VALUE = 3.7;
        public static double INTAKE_L4_VALUE = 4.3;
    }

    public static class ObjectDetector {
        public static final int SENSOR_DIO_PORT = 1;
    }

    public static class Controlls {
        public static CommandXboxController DRIVER_CONTROLLER = new CommandXboxController(OI.DRIVER_CONTROLLER_PORT);
              
        //For TEU
        public static Trigger ELEVATOR_MANUAL_UP = DRIVER_CONTROLLER.button(8);
        public static Trigger ELEVATOR_MANUAL_DOWN = DRIVER_CONTROLLER.button(7);
        public static Trigger INTAKE_MOVE_UP = DRIVER_CONTROLLER.button(10);
        public static Trigger INTAKE_MOVE_DOWN = DRIVER_CONTROLLER.button(5);
        public static Trigger ALGEA_PROCESSOR = DRIVER_CONTROLLER.button(2);
        public static Trigger ALGEA_INTAKE_MIDDLE = DRIVER_CONTROLLER.button(1);
        public static Trigger ALGEA_INTAKE_DOWN = DRIVER_CONTROLLER.button(3);
        public static Trigger L1 = DRIVER_CONTROLLER.povDown();
        public static Trigger L2 = DRIVER_CONTROLLER.povLeft();
        public static Trigger L3 = DRIVER_CONTROLLER.povUp();
        public static Trigger L4 = DRIVER_CONTROLLER.povRight();
        public static Trigger GET_SOURCE = DRIVER_CONTROLLER.button(5);
        public static Trigger LED_CYCLE = DRIVER_CONTROLLER.button(9);
        public static int SWERVE_RIGHT_X_AXIS = 2;
    }

    public static class Test_Controlls {
        public static Trigger T_ELEVATOR_MANUAL_UP = Controlls.DRIVER_CONTROLLER.button(8);
        public static Trigger T_ELEVATOR_MANUAL_DOWN = Controlls.DRIVER_CONTROLLER.button(7);
        public static Trigger T_INTAKE_MOVE_UP = Controlls.DRIVER_CONTROLLER.button(10);
        public static Trigger T_INTAKE_MOVE_DOWN = Controlls.DRIVER_CONTROLLER.button(5);
        public static Trigger T_ELEVATOR_ZERO = Controlls.DRIVER_CONTROLLER.button(2);
        public static Trigger T_ALGEA_INTAKE = Controlls.DRIVER_CONTROLLER.button(1);
        public static Trigger T_ALGEA_OUTTAKE = Controlls.DRIVER_CONTROLLER.button(3);
        public static Trigger T_CORAL_INTAKE = Controlls.DRIVER_CONTROLLER.povLeft();
        public static Trigger T_CORAL_OUTTAKE = Controlls.DRIVER_CONTROLLER.povRight();
        public static Trigger T_LED_CYCLE = Controlls.DRIVER_CONTROLLER.button(4);
        public static Trigger T_LED_MORSE = Controlls.DRIVER_CONTROLLER.button(9);
    }

    public static class EnabledParts {
        // Hope Not To Use...
        public static boolean IS_LED_ENABLED = true;
        public static boolean IS_OBJECT_SENSOR_ENABLED = false;
        public static boolean IS_INTAKE_ENABLED = true;
        public static boolean IS_ALGEA_INTAKE_ENABLED = true;
        public static boolean IS_INTAKE_MOVER_ENABLED = true;
        public static boolean IS_ELEVATOR_ENABLED = true;
        public static boolean IS_SWERVE_ENABLED = true;
        public static boolean IS_ROTARY_SWITCH_ENABLED = false;
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
    }
}
