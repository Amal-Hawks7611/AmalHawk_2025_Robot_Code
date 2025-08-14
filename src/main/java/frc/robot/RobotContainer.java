// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.AlgeaIntakeSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeMoverSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ObjectDetectorSubsystem;
import frc.robot.subsystems.RotarySwitchSubsystem;
import frc.robot.subsystems.StatusLED;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Controlls;
import frc.robot.Constants.Test_Controlls;
import frc.robot.Constants.LedSubsystem;
import frc.robot.Constants.OI;
import frc.robot.commands.AlgeaIntake.AlgeaIntake;
import frc.robot.commands.AlgeaIntake.AlgeaOuttake;
import frc.robot.commands.Elevator.*;
import frc.robot.commands.Intake.Intake;
import frc.robot.commands.Intake.Outtake;
import frc.robot.commands.Intake.Gerial;
import frc.robot.commands.IntakeMover.*;
import frc.robot.commands.Led.LEDMorseScroller;
import frc.robot.commands.Led.LEDStateCycler;
import frc.robot.commands.Swerve.*;
import frc.robot.commands.Trajectory.AutonPath;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.Constants.OperatorConstants;
import java.io.File;
import swervelib.SwerveInputStream;

public class RobotContainer {

        private final SendableChooser<Command> autoChooser;
        public final SwerveSubsystem drivebase = new SwerveSubsystem(
                        new File(Filesystem.getDeployDirectory(), "swerve"));
        public final LimelightSubsystem limelight = new LimelightSubsystem();
        private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
        public final ObjectDetectorSubsystem objectDetector = new ObjectDetectorSubsystem();
        public final IntakeMoverSubsystem intakeMoverSubsystem = new IntakeMoverSubsystem(this);
        public final IntakeSubsystem intakeSubsystem = new IntakeSubsystem(this);
        public final AlgeaIntakeSubsystem algeaIntakeSubsystem = new AlgeaIntakeSubsystem();
        public final StatusLED ledSubsystem = new StatusLED();
        public final RotarySwitchSubsystem rotarySwitchSubsystem = new RotarySwitchSubsystem();
        public final AutonPath otonom_path = new AutonPath();
        public CommandPS5Controller driverPs5 = Controlls.DRIVER_CONTROLLER;
        public final e_movedown elevator_down;
        public final e_moveup elevator_up;
        public final e_tozeroo e_tozero;
        public final e_level1 e_l1;
        public final e_level2 e_l2;
        public final e_level3 e_l3;
        public final e_source e_source;
        public final e_processor e_processor;
        public final e_level4 e_l4;
        public final e_algea e_algea_middle;
        public final e_algea e_algea_down;

        public final source im_source;
        public final processor im_processor;
        public final reefscape im_reefscape;
        public final algea im_algea;
        public final korel im_coral;
        public final l4 im_l4;
        public final movedown im_movedown;
        public final moveup im_moveup;

        public final AlgeaIntake a_intake;
        public final AlgeaOuttake a_outtake;

        public final Command zerogyro;
        public final Command admin;

        public final Intake c_intake;
        public final Outtake c_outtake;
        public final Gerial c_Gerial;

        public final LEDStateCycler led_cycle;
        public final miracsurpriz miracsurpriz;
        public final LEDMorseScroller led_morse;

        public final Command intakeAlgeaMiddle;
        public final Command intakeAlgeaDown;
        public final Command getSource;
        public final Command AlgeaProcessor;
        public final Command Coral_l1;
        public final Command Coral_l2;
        public final Command Coral_l3;
        public final Command Coral_l4;

        public final InstantCommand limelight_stop;
        public final limelight limelight_align;
        public final limelight2 limelight_2;
        public final limelight_withtruewheels limelight_go;
        public final LimelightTurnCommand limelight_turn;
        public final Command fully_align;
        public final Command fully_align_premium;
        public final Command algea_align;
        public final InstantCommand zero_all;

        /**
         * Converts driver input into a field-relative ChassisSpeeds that is controlled
         * by angular velocity.
         */
        SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
                        () -> driverPs5.getLeftY() * -1 * 0.8,
                        () -> driverPs5.getLeftX() * -1 * 0.8)
                        .withControllerRotationAxis(driverPs5::getRightX)
                        .deadband(OperatorConstants.DEADBAND)
                        .scaleTranslation(0.8)
                        .allianceRelativeControl(true);

        /**
         * Clone's the angular velocity input stream and converts it to a fieldRelative
         * input stream.
         */
        SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(driverPs5::getRightX,
                        driverPs5::getRightY)
                        .headingWhile(true);

        /**
         * Clone's the angular velocity input stream and converts it to a robotRelative
         * input stream.
         */
        SwerveInputStream driveRobotOriented = driveAngularVelocity.copy().robotRelative(true)
                        .allianceRelativeControl(false);

        SwerveInputStream driveAngularVelocityKeyboard = SwerveInputStream.of(drivebase.getSwerveDrive(),
                        () -> driverPs5.getLeftY(),
                        () -> driverPs5.getLeftX())
                        .withControllerRotationAxis(() -> -driverPs5.getRawAxis(
                                        2))
                        .deadband(OperatorConstants.DEADBAND)
                        .scaleTranslation(0.8)
                        .allianceRelativeControl(true);
        // Derive the heading axis with math!
        SwerveInputStream driveDirectAngleKeyboard = driveAngularVelocityKeyboard.copy()
                        .withControllerHeadingAxis(() -> Math.sin(
                                        driverPs5.getRawAxis(
                                                        2) *
                                                        Math.PI)
                                        *
                                        (Math.PI *
                                                        2),
                                        () -> Math.cos(
                                                        driverPs5.getRawAxis(
                                                                        2) *
                                                                        Math.PI)
                                                        *
                                                        (Math.PI *
                                                                        2))
                        .headingWhile(true);

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the trigger bindings
                configureBindings();
                DriverStation.silenceJoystickConnectionWarning(true);

                // Add Commands to the PathPlanner
                NamedCommands.registerCommand("eProcessor", new e_processor(elevatorSubsystem));
                NamedCommands.registerCommand("eAlgeaMiddle", new e_algea(elevatorSubsystem, true));
                NamedCommands.registerCommand("eAlgeaDown", new e_algea(elevatorSubsystem, false));
                NamedCommands.registerCommand("eSource", new e_source(elevatorSubsystem));
                NamedCommands.registerCommand("eToZero", new e_tozeroo(elevatorSubsystem, false));
                NamedCommands.registerCommand("eL1", new e_level1(elevatorSubsystem));
                NamedCommands.registerCommand("eL2", new e_level2(elevatorSubsystem));
                NamedCommands.registerCommand("eL3", new e_level3(elevatorSubsystem));
                NamedCommands.registerCommand("eL4", new e_level4(elevatorSubsystem));

                NamedCommands.registerCommand("imProcessor", new processor(intakeMoverSubsystem));
                NamedCommands.registerCommand("imSource", new source(intakeMoverSubsystem));
                NamedCommands.registerCommand("imReefscape", new reefscape(intakeMoverSubsystem));
                NamedCommands.registerCommand("imAlgea", new algea(intakeMoverSubsystem));
                NamedCommands.registerCommand("imCoral", new korel(intakeMoverSubsystem));
                NamedCommands.registerCommand("imL4", new l4(intakeMoverSubsystem));

                NamedCommands.registerCommand("aIntake", new AlgeaIntake(algeaIntakeSubsystem));
                NamedCommands.registerCommand("aOuttake", new AlgeaOuttake(algeaIntakeSubsystem));

                NamedCommands.registerCommand("cIntake", new Intake(intakeSubsystem));
                NamedCommands.registerCommand("cOuttake", new Outtake(intakeSubsystem));

                NamedCommands.registerCommand("limelight", new limelight(drivebase));
                NamedCommands.registerCommand("limelight2", new limelight2(drivebase, new limelight(drivebase)));

                // Definate Commands
                zero_all = new InstantCommand(() -> {
                        elevatorSubsystem.resetEncoders();
                        intakeMoverSubsystem.resetEncoders();
                });
                elevator_down = new e_movedown(elevatorSubsystem);
                elevator_up = new e_moveup(elevatorSubsystem);
                e_processor = new e_processor(elevatorSubsystem);
                e_algea_middle = new e_algea(elevatorSubsystem, true);
                e_algea_down = new e_algea(elevatorSubsystem, false);
                e_source = new e_source(elevatorSubsystem);
                e_tozero = new e_tozeroo(elevatorSubsystem, false);
                e_l1 = new e_level1(elevatorSubsystem);
                e_l2 = new e_level2(elevatorSubsystem);
                e_l3 = new e_level3(elevatorSubsystem);
                e_l4 = new e_level4(elevatorSubsystem);

                im_processor = new processor(intakeMoverSubsystem);
                im_source = new source(intakeMoverSubsystem);
                im_reefscape = new reefscape(intakeMoverSubsystem);
                im_algea = new algea(intakeMoverSubsystem);
                im_coral = new korel(intakeMoverSubsystem);
                im_l4 = new l4(intakeMoverSubsystem);
                im_moveup = new moveup(intakeMoverSubsystem);
                im_movedown = new movedown(intakeMoverSubsystem);

                a_intake = new AlgeaIntake(algeaIntakeSubsystem);
                a_outtake = new AlgeaOuttake(algeaIntakeSubsystem);
                c_Gerial = new Gerial(intakeSubsystem);

                zerogyro = new InstantCommand(() -> drivebase.zeroGyro());
                admin = new InstantCommand(() -> CommandScheduler.getInstance().cancelAll());

                c_intake = new Intake(intakeSubsystem);
                c_outtake = new Outtake(intakeSubsystem);

                led_cycle = new LEDStateCycler(ledSubsystem);
                miracsurpriz = new miracsurpriz(led_cycle);
                led_morse = new LEDMorseScroller(ledSubsystem, LedSubsystem.LED_LENGTH, "    AMAL HAWKS ZWABOBUM");

                limelight_align = new limelight(drivebase);
                limelight_2 = new limelight2(drivebase, limelight_align);
                limelight_go = new limelight_withtruewheels(drivebase);
                limelight_turn = new LimelightTurnCommand(drivebase);
                limelight_stop = new InstantCommand(() -> {
                        OI.IS_FOCUSED = true;
                        limelight_2.end(false);
                });

                // Commands are fully autonomous for driver comfort and easy autonomous
                // integration

                fully_align = new SequentialCommandGroup(
                                new limelight(drivebase));

                algea_align = new SequentialCommandGroup(
                                new limelight(drivebase),
                                new limelight_algea(drivebase));
                fully_align_premium = new SequentialCommandGroup(
                                new LimelightTurnCommand(drivebase),
                                new limelight(drivebase),
                                new limelight_withtruewheels(drivebase));

                intakeAlgeaMiddle = new SequentialCommandGroup(
                                new limelight(drivebase),
                                new limelight_algea(drivebase),
                                new e_tozeroo(elevatorSubsystem, true),
                                new algea(intakeMoverSubsystem),
                                new ParallelCommandGroup(
                                                new e_algea(elevatorSubsystem, true),
                                                new AlgeaIntake(algeaIntakeSubsystem)));

                intakeAlgeaDown = new SequentialCommandGroup(
                                new algea(intakeMoverSubsystem),
                                new e_algea(elevatorSubsystem, false),
                                new AlgeaIntake(algeaIntakeSubsystem));

                getSource = new SequentialCommandGroup(
                                new e_source(elevatorSubsystem),
                                new source(intakeMoverSubsystem),
                                new Intake(intakeSubsystem));

                AlgeaProcessor = new ParallelCommandGroup(
                                new AlgeaIntake(algeaIntakeSubsystem),
                                new processor(intakeMoverSubsystem),
                                new e_processor(elevatorSubsystem));

                Coral_l1 = new SequentialCommandGroup(
                                new reefscape(intakeMoverSubsystem),
                                new e_level1(elevatorSubsystem),
                                new Outtake(intakeSubsystem));
                Coral_l2 = new SequentialCommandGroup(
                                new korel(intakeMoverSubsystem),
                                new e_level2(elevatorSubsystem),
                                new Outtake(intakeSubsystem));
                Coral_l3 = new SequentialCommandGroup(
                                new korel(intakeMoverSubsystem),
                                new e_level3(elevatorSubsystem),
                                new Outtake(intakeSubsystem));
                Coral_l4 = new SequentialCommandGroup(
                                new l4(intakeMoverSubsystem),
                                new e_level4(elevatorSubsystem));

                // PathPlanner Autonomous Chooser
                autoChooser = AutoBuilder.buildAutoChooser();
                SmartDashboard.putData("Auto Chooser", autoChooser);
                configureBindings();
        }

        private void configureBindings() {
                Command driveFieldOrientedCommand = drivebase.driveFieldOriented(driveAngularVelocityKeyboard);
                drivebase.setDefaultCommand(driveFieldOrientedCommand);
        }

        public void configureButtonBindings() {
                // Configure Button Bindings For Test And TeleOp Modes
                if (OI.IS_TEST) {
                        Test_Controlls.T_ALGEA_INTAKE.onTrue(a_intake);
                        Test_Controlls.T_ALGEA_OUTTAKE.onTrue(a_outtake);

                        Test_Controlls.T_CORAL_INTAKE.onTrue(getSource);
                        Test_Controlls.T_CORAL_OUTTAKE.onTrue(c_outtake);

                        Test_Controlls.T_ELEVATOR_MANUAL_DOWN.whileTrue(elevator_down);
                        Test_Controlls.T_ELEVATOR_MANUAL_UP.whileTrue(elevator_up);

                        Test_Controlls.T_INTAKE_MOVE_DOWN.whileTrue(im_movedown);
                        Test_Controlls.T_INTAKE_MOVE_UP.whileTrue(im_moveup);

                        Test_Controlls.T_INTAKE_MOVE_L1.onTrue(im_algea);
                        Test_Controlls.T_ELEVATOR_ZERO.onTrue(Coral_l4);

                        Test_Controlls.T_LED_CYCLE.whileTrue(led_cycle);
                        Test_Controlls.T_LED_MORSE.onTrue(led_morse);
                } else {
                        Controlls.ELEVATOR_MANUAL_DOWN.whileTrue(elevator_down);
                        Controlls.ELEVATOR_MANUAL_UP.whileTrue(elevator_up);
                        Controlls.INTAKE_MOVE_DOWN.whileTrue(im_movedown);
                        Controlls.INTAKE_MOVE_UP.whileTrue(im_moveup);

                        Controlls.ELEVATOR_TOZERO.onTrue(e_tozero);
                        Controlls.GET_SOURCE.onTrue(getSource);

                        Controlls.RESET_GYRO.onChange(zerogyro);
                        Controlls.ADMIN.whileTrue(admin);
                        Controlls.LIMELIGHT_PREMIUM.onTrue(fully_align_premium);

                        Controlls.L4.onTrue(Coral_l4);
                        Controlls.L3.onTrue(Coral_l3);
                        Controlls.L2.onTrue(Coral_l2);
                        Controlls.L1.onTrue(Coral_l1);

                        Controlls.LED_CYCLE.toggleOnTrue(led_cycle);

                        Controlls.CORAL_INTAKE.onTrue(c_intake);
                        Controlls.CORAL_GERIAL.onTrue(c_Gerial);

                        Controlls.LIMELIGHT_FOCUS.onTrue(fully_align);
                        Controlls.LIMELIGHT_STOP.whileTrue(limelight_stop);

                        Controlls.ALGEA_INTAKE.onTrue(a_intake);
                        Controlls.ALGEA_OUTTAKE.onTrue(a_outtake);
                        Controlls.ALGEA_PROCESSOR.onTrue(AlgeaProcessor);
                        Controlls.ALGEA_REMOVAL.onTrue(intakeAlgeaMiddle);

                        Controlls.ZERO_ALL.onChange(zero_all);
                }
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                // An example command will be run in autonomous
                return autoChooser.getSelected();
        }

        public void setMotorBrake(boolean brake) {
                drivebase.setMotorBrake(brake);
        }
}
