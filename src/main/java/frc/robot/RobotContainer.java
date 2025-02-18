package frc.robot;

import frc.robot.subsystems.AlgeaIntakeSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeMoverSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RotarySwitchSubsystem;
import frc.robot.subsystems.StatusLED;
import frc.robot.subsystems.SwerveSubsystem;

import java.util.Map;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Controlls;
import frc.robot.Constants.Test_Controlls;
import frc.robot.Constants.EnabledParts;
import frc.robot.Constants.OI;
import frc.robot.commands.AlgeaIntake.AlgeaIntake;
import frc.robot.commands.AlgeaIntake.AlgeaOuttake;
import frc.robot.commands.Elevator.*;
import frc.robot.commands.Intake.Intake;
import frc.robot.commands.Intake.Outtake;
import frc.robot.commands.IntakeMover.*;
import frc.robot.commands.Led.LEDMorseScroller;
import frc.robot.commands.Led.LEDStateCycler;
import frc.robot.commands.Swerve.LimelightAimCommand;
import frc.robot.commands.Trajectory.AutonPath;
import frc.robot.commands.Trajectory.FollowTrajectoryCommand;

//A COOL ROBOTCONTAINER THAT CONTAINS NAMEDCOMMANDS FOR PATHPLANNER AND COMMAND GROUPS FOR TEU
public class RobotContainer {
        private final SendableChooser<Command> autoChooser;
        public final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
        private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
        public final IntakeMoverSubsystem intakeMoverSubsystem = new IntakeMoverSubsystem();
        public final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
        public final AlgeaIntakeSubsystem algeaIntakeSubsystem = new AlgeaIntakeSubsystem();
        public final StatusLED ledSubsystem = new StatusLED();
        public final RotarySwitchSubsystem rotarySwitchSubsystem = new RotarySwitchSubsystem();

        public final AutonPath otonom_path = new AutonPath();
        public final FollowTrajectoryCommand otonom = new FollowTrajectoryCommand(swerveSubsystem);

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

        public final Intake c_intake;
        public final Outtake c_outtake;

        public final LEDStateCycler led_cycle;
        public final LEDMorseScroller led_morse;

        public final LimelightAimCommand limelight_focus;

        public final Command intakeAlgeaMiddle;
        public final Command intakeAlgeaDown;
        public final Command getSource;
        public final Command AlgeaProcessor;
        public final Command Coral_l1;
        public final Command Coral_l2;
        public final Command Coral_l3;
        public final Command Coral_l4;

        public RobotContainer() {
                // Add Commands to the PathPlanner
                NamedCommands.registerCommand("eProcessor", new e_processor(elevatorSubsystem));
                NamedCommands.registerCommand("eAlgeaMiddle", new e_algea(elevatorSubsystem, true));
                NamedCommands.registerCommand("eAlgeaDown", new e_algea(elevatorSubsystem, false));
                NamedCommands.registerCommand("eSource", new e_source(elevatorSubsystem));
                NamedCommands.registerCommand("eToZero", new e_tozeroo(elevatorSubsystem));
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

                //Definate Commands
                elevator_down = new e_movedown(elevatorSubsystem);
                elevator_up = new e_moveup(elevatorSubsystem);
                e_processor = new e_processor(elevatorSubsystem);
                e_algea_middle = new e_algea(elevatorSubsystem, true);
                e_algea_down = new e_algea(elevatorSubsystem, false);
                e_source = new e_source(elevatorSubsystem);
                e_tozero = new e_tozeroo(elevatorSubsystem);
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

                c_intake = new Intake(intakeSubsystem);
                c_outtake = new Outtake(intakeSubsystem);

                led_cycle = new LEDStateCycler(ledSubsystem);
                led_morse = new LEDMorseScroller(ledSubsystem, 60, "AMAL HAWKS IS THE BEST");

                limelight_focus = new LimelightAimCommand(swerveSubsystem);

                //Commands are fully autonomous for driver comfort and easy autonomous integration
                intakeAlgeaMiddle = new SequentialCommandGroup(
                                new e_algea(elevatorSubsystem, true),
                                new algea(intakeMoverSubsystem), 
                                new AlgeaIntake(algeaIntakeSubsystem), new e_tozeroo(elevatorSubsystem));
                intakeAlgeaDown = new SequentialCommandGroup(
                                new e_algea(elevatorSubsystem, false),
                                new algea(intakeMoverSubsystem), 
                                new AlgeaIntake(algeaIntakeSubsystem), new e_tozeroo(elevatorSubsystem));
                getSource = new SequentialCommandGroup(
                                new e_source(elevatorSubsystem),
                                new source(intakeMoverSubsystem),
                                new Intake(intakeSubsystem), new e_tozeroo(elevatorSubsystem));

                AlgeaProcessor = new SequentialCommandGroup(
                                new e_processor(elevatorSubsystem),
                                new processor(intakeMoverSubsystem), 
                                new AlgeaOuttake(algeaIntakeSubsystem), new e_tozeroo(elevatorSubsystem));

                Coral_l1 = new SequentialCommandGroup(
                                new e_level1(elevatorSubsystem),
                                new reefscape(intakeMoverSubsystem),
                                new Outtake(intakeSubsystem), new e_tozeroo(elevatorSubsystem));
                Coral_l2 = new SequentialCommandGroup(
                                new e_level2(elevatorSubsystem), 
                                new korel(intakeMoverSubsystem),
                                new Outtake(intakeSubsystem), new e_tozeroo(elevatorSubsystem));
                Coral_l3 = new SequentialCommandGroup(
                                new e_level3(elevatorSubsystem), 
                                new korel(intakeMoverSubsystem),
                                new Outtake(intakeSubsystem), new e_tozeroo(elevatorSubsystem));
                Coral_l4 = new SequentialCommandGroup(
                                new e_level4(elevatorSubsystem), 
                                new l4(intakeMoverSubsystem),
                                new Outtake(intakeSubsystem), new e_tozeroo(elevatorSubsystem));

                //Set Swerve Tele-Op Drive
                if (EnabledParts.IS_SWERVE_ENABLED) {
                        Command drive_command = swerveSubsystem.driveteleop();
                        swerveSubsystem.setDefaultCommand(drive_command);
                }
                //PathPlanner Autonomous Chooser
                autoChooser = AutoBuilder.buildAutoChooser();
                SmartDashboard.putData("Auto Chooser", autoChooser);
        }

        public void configureButtonBindings() {
                //Configure Button Bindings For Test And TeleOp Modes
                if(OI.IS_TEST){
                        Test_Controlls.T_ALGEA_INTAKE.onTrue(a_intake);
                        Test_Controlls.T_ALGEA_OUTTAKE.onTrue(a_outtake);

                        Test_Controlls.T_CORAL_INTAKE.onTrue(c_intake);
                        Test_Controlls.T_CORAL_OUTTAKE.onTrue(c_outtake);

                        Test_Controlls.T_ELEVATOR_MANUAL_DOWN.whileTrue(elevator_down);
                        Test_Controlls.T_ELEVATOR_MANUAL_UP.whileTrue(elevator_up);

                        Test_Controlls.T_INTAKE_MOVE_DOWN.whileTrue(im_movedown);
                        Test_Controlls.T_INTAKE_MOVE_UP.whileTrue(im_moveup);

                        Test_Controlls.T_ELEVATOR_ZERO.onTrue(e_tozero);

                        Test_Controlls.T_LED_CYCLE.whileTrue(led_cycle);
                        Test_Controlls.T_LED_MORSE.onTrue(led_morse);
                }else{                
                        Controlls.ELEVATOR_MANUAL_DOWN.whileTrue(elevator_down);
                        Controlls.ELEVATOR_MANUAL_UP.whileTrue(elevator_up);
                        Controlls.INTAKE_MOVE_DOWN.whileTrue(im_movedown);
                        Controlls.INTAKE_MOVE_UP.whileTrue(im_moveup);

                        Controlls.ALGEA_PROCESSOR.onTrue(AlgeaProcessor);
                        Controlls.ALGEA_INTAKE_MIDDLE.onTrue(intakeAlgeaMiddle);
                        Controlls.ALGEA_INTAKE_DOWN.onTrue(intakeAlgeaDown);

                        Controlls.GET_SOURCE.onTrue(getSource);

                        Controlls.L4.onTrue(Coral_l4);
                        Controlls.L3.onTrue(Coral_l3);
                        Controlls.L2.onTrue(Coral_l2);
                        Controlls.L1.onTrue(Coral_l1);

                        Controlls.LED_CYCLE.whileTrue(led_cycle);
                }
        }

        public Command getAutonomousCommand() {
          if (EnabledParts.IS_ROTARY_SWITCH_ENABLED) {
                        Map<Integer, String> autoModes = Map.of(
                                1, "ALG",
                                2, "ALG+Reef",
                                3, "ALG+IkiCoralAtis",
                                4, "ALG+UcCoralAtis"
                        );

                        String autoName = autoModes.getOrDefault(rotarySwitchSubsystem.getTotalTurns(), "None");
                        return new PathPlannerAuto(autoName);
                }else{
                        return autoChooser.getSelected();
                }
        }
}
