package frc.robot;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

//ALL ROBOT SETTINGS ARE DEFINED HERE. IF YOU DON'T KNOW WHAT YOU ARE DOING, DON'T TOUCH ANYTHING!!
public final class Constants {
    public static final class Swerve {
        public static double WHEELBASE = 0.58;
        public static double TRACKWIDTH = 0.58;
        public static double MAX_SPEED_METERS_PER_SECOND = 5;
        public static double DRIVE_ENCODER_POSITION_CONVERSION = 0.0000237;
        public static double DRIVE_ENCODER_VELOCITY_CONVERSION = 0.000237;

        // Motor configuration
        public static PIDConstants TRANSLATION_PID = new PIDConstants(3.0, 0.0, 0.0); // TODO: Tune PID values
        public static PIDConstants ROTATION_PID = new PIDConstants(3.0, 0.0, 0.0); // TODO: Tune PID values
        public static double LIMELIGHT_ALIGN_KP = 0.5;
        public static boolean IS_FIELD_RELATIVE = false;

        // TODO Offsets Will Be Entered
        public static double FRONT_LEFT_MODULE_STEER_OFFSET = 0.0;
        public static double FRONT_RIGHT_MODULE_STEER_OFFSET = 0.0;
        public static double BACK_LEFT_MODULE_STEER_OFFSET = 0.0;
        public static double BACK_RIGHT_MODULE_STEER_OFFSET = 0.0;

        //Motor PID Configs
        public static double DRIVE_MOTOR_KP = 0.6;
        public static double DRIVE_MOTOR_KI = 0.0;
        public static double DRIVE_MOTOR_KD = 0.01;

        public static double STEER_MOTOR_KP = 0.6;
        public static double STEER_MOTOR_KI = 0.0;
        public static double STEER_MOTOR_KD = 0.01;
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
        public static int FRONT_LEFT_CANCODER = 9;
        public static int FRONT_RIGHT_CANCODER = 10;
        public static int BACK_LEFT_CANCODER = 11;
        public static int BACK_RIGHT_CANCODER = 12;
    }

    public static final class LedSubsystem {
        public static int LED_PWM_PORT = 0;
        public static int LED_LENGTH = 60; // Default
        public static LEDPattern RED_ALLIANCE_COLOR = LEDPattern.solid(Color.kRed);
        public static LEDPattern BLUE_ALLIANCE_COLOR = LEDPattern.solid(Color.kBlue);
        public static LEDPattern ELEVATOR_PROCESS_COLOR = LEDPattern.solid(Color.kPurple);
        public static LEDPattern TARGET_FOCUS_COLOR = LEDPattern.solid(Color.kGreen);
        public static LEDPattern INTAKE_COLOR = LEDPattern.solid(Color.kYellow);
        public static LEDPattern ALGEA_INTAKE_COLOR = LEDPattern.solid(Color.kAqua);
        public static LEDPattern BREATHE_COLOR = LEDPattern.solid(Color.kWhite);
    }

    public static final class Elevator {
        // TODO Encoder Values Wİll Be Updated
        public static double ELEVATOR_START_VALUE = 0; // Generally True
        public static double ELEVATOR_END_VALUE = 450;
        public static double ELEVATOR_SOURCE_VALUE = 100;
        public static double ELEVATOR_PROCESSOR_VALUE = 150;
        public static double ELEVATOR_REEFSCAPE_VALUE = 200;
        public static double ELEVATOR_L1_VALUE = 250;
        public static double ELEVATOR_L2_VALUE = 300;
        public static double ELEVATOR_L3_VALUE = 350;
        public static double ELEVATOR_L4_VALUE = 400;
        public static double ELEVATOR_NET_VALUE = 450;
        public static double ELEVATOR_ALGEA_VALUE_DOWN = 220;
        public static double ELEVATOR_ALGEA_VALUE_MIDDLE = 240;
        public static int ELEVATOR_LEADER_MOTOR_PORT = 0;
        public static int ELEVATOR_FOLLOWER_MOTOR_PORT = 1;
        public static double OCAL_PID_TOLERANCE_VALUE = 5;

    }

    public static class Intake {
        // Intake Motor Port
        public static int LEADER_MOTOR_PORT = 0;
        public static double INTAKE_SPEED = 0.5;
        public static double OUTTAKE_SPEED = 0.3;
    }

    public static class AlgeaIntake {
        // Algea Intake Motor Port
        public static int LEADER_MOTOR_PORT = 0; // Shoud Be Same With Intake
        public static double INTAKE_SPEED = 0.5;
        public static double OUTTAKE_SPEED = 0.3;
    }

    public static class IntakeMover {
        public static int LEADER_MOTOR_PORT = 0;
        public static double INTAKE_MOVER_SPEED = 0.5;
        public static double OCAL_PID_TOLERANCE_VALUE = 5;

        // TODO Encoder Values Will Be Updated
        public static double INTAKE_PROCESSOR_VALUE = 30;
        public static double INTAKE_SOURCE_VALUE = 30;
        public static double INTAKE_ALGEA_VALUE = 30;
        public static double INTAKE_REEFSCAPE_VALUE = 20;
        public static double INTAKE_CORAL_VALUE = 30;
        public static double INTAKE_L4_VALUE = 60;
        public static double INTAKE_NET_VALUE = 50;
    }

    public static class Colormatcher {
        public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;
        public static final Color CORAL_TARGET = new Color(224,223,221);
    }

    public static class Controlls {
        // For TEU
        public static final boolean PS5 = false;
        public static final boolean Xbox = true;

        public static Trigger ELEVATOR_MANUAL_UP;
        public static Trigger ELEVATOR_MANUAL_DOWN;
        public static Trigger INTAKE_MOVE_UP;
        public static Trigger INTAKE_MOVE_DOWN;
        public static Trigger ALGEA_PROCESSOR;
        public static Trigger ALGEA_INTAKE_MIDDLE;
        public static Trigger ALGEA_INTAKE_DOWN;
        public static Trigger ALGEA_NET;
        public static Trigger L1;
        public static Trigger L2;
        public static Trigger L3;
        public static Trigger L4;
        public static Trigger GET_SOURCE;

        public CommandGenericHID DRIVER_CONTROLLER;

        public Controlls() {
            if (!OI.DRIVER_CONTROLLER_TYPE) {
                final CommandPS5Controller DRIVER_CONTROLLER = new CommandPS5Controller(OI.DRIVER_CONTROLLER_PORT);
                this.DRIVER_CONTROLLER = DRIVER_CONTROLLER;

                ELEVATOR_MANUAL_UP = DRIVER_CONTROLLER.R2();
                ELEVATOR_MANUAL_DOWN = DRIVER_CONTROLLER.L2();
                INTAKE_MOVE_UP = DRIVER_CONTROLLER.R1();
                INTAKE_MOVE_DOWN = DRIVER_CONTROLLER.L1();
                ALGEA_PROCESSOR = DRIVER_CONTROLLER.cross();
                ALGEA_INTAKE_MIDDLE = DRIVER_CONTROLLER.square();
                ALGEA_INTAKE_DOWN = DRIVER_CONTROLLER.circle();
                ALGEA_NET = DRIVER_CONTROLLER.triangle();
                L1 = DRIVER_CONTROLLER.povDown();
                L2 = DRIVER_CONTROLLER.povLeft();
                L3 = DRIVER_CONTROLLER.povUp();
                L4 = DRIVER_CONTROLLER.povRight();
                GET_SOURCE = DRIVER_CONTROLLER.button(10);
            }
            if (OI.DRIVER_CONTROLLER_TYPE) {
                final CommandXboxController DRIVER_CONTROLLER = new CommandXboxController(OI.DRIVER_CONTROLLER_PORT);
                this.DRIVER_CONTROLLER = DRIVER_CONTROLLER;

                ELEVATOR_MANUAL_UP = DRIVER_CONTROLLER.rightTrigger();
                ELEVATOR_MANUAL_DOWN = DRIVER_CONTROLLER.leftTrigger();
                INTAKE_MOVE_UP = DRIVER_CONTROLLER.rightBumper();
                INTAKE_MOVE_DOWN = DRIVER_CONTROLLER.leftBumper();
                ALGEA_PROCESSOR = DRIVER_CONTROLLER.a();
                ALGEA_INTAKE_MIDDLE = DRIVER_CONTROLLER.x();
                ALGEA_INTAKE_DOWN = DRIVER_CONTROLLER.b();
                ALGEA_NET = DRIVER_CONTROLLER.y();
                L1 = DRIVER_CONTROLLER.povDown();
                L2 = DRIVER_CONTROLLER.povLeft();
                L3 = DRIVER_CONTROLLER.povUp();
                L4 = DRIVER_CONTROLLER.povRight();
                GET_SOURCE = DRIVER_CONTROLLER.button(10);
            }
        }
        
        public double getLeftX() {
            if (DRIVER_CONTROLLER instanceof CommandXboxController) {
                return ((CommandXboxController)DRIVER_CONTROLLER).getLeftX();
            } else {
                return ((CommandPS5Controller)DRIVER_CONTROLLER).getLeftX();
            }
        }

        public double getLeftY() {
            if (DRIVER_CONTROLLER instanceof CommandXboxController) {
                return ((CommandXboxController)DRIVER_CONTROLLER).getLeftY();
            } else {
                return ((CommandPS5Controller)DRIVER_CONTROLLER).getLeftY();
            }
        }

        public double getRightX() {
            if (DRIVER_CONTROLLER instanceof CommandXboxController) {
                return ((CommandXboxController)DRIVER_CONTROLLER).getRightX();
            } else {
                return ((CommandPS5Controller)DRIVER_CONTROLLER).getRightX();
            }
        }

        public double getRightY() {
            if (DRIVER_CONTROLLER instanceof CommandXboxController) {
                return ((CommandXboxController)DRIVER_CONTROLLER).getRightY();
            } else {
                return ((CommandPS5Controller)DRIVER_CONTROLLER).getRightY();
            }
        }

        public double getRawAxis(int axis) {
            if (DRIVER_CONTROLLER instanceof CommandXboxController) {
                return ((CommandXboxController)DRIVER_CONTROLLER).getRawAxis(axis);
            } else {
                return ((CommandPS5Controller)DRIVER_CONTROLLER).getRawAxis(axis);
            }
        }
    }

    public static class EnabledParts {
        // Hope Not To Use...
        public static boolean IS_LED_ENABLED = true;
        public static boolean IS_COLOR_SENSOR_ENABLED = true;
        public static boolean IS_INTAKE_ENABLED = true;
        public static boolean IS_ALGEA_INTAKE_ENABLED = true;
        public static boolean IS_INTAKE_MOVER_ENABLED = true;
        public static boolean IS_ELEVATOR_ENABLED = true;
        public static boolean IS_SWERVE_ENABLED = true;
    }

    public static final class OI {
        // General Robot Constants
        public static int DRIVER_CONTROLLER_PORT = 0;
        public static boolean DRIVER_CONTROLLER_TYPE = Controlls.PS5;
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
