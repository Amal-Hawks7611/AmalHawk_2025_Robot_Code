package frc.robot;

import frc.robot.subsystems.AlgeaIntakeSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeMoverSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StatusLED;
import frc.robot.subsystems.SwerveSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OI;
import frc.robot.commands.AlgeaIntake.AlgeaIntake;
import frc.robot.commands.AlgeaIntake.AlgeaOuttake;
import frc.robot.commands.Elevator.*;
import frc.robot.commands.Intake.Intake;
import frc.robot.commands.Intake.Outtake;
import frc.robot.commands.IntakeMover.*;
import frc.robot.commands.Led.LEDStateCycler;
import frc.robot.commands.Swerve.LimelightAimCommand;
import frc.robot.commands.Trajectory.AutonPath;
import frc.robot.commands.Trajectory.FollowTrajectoryCommand;

public class RobotContainer {
    private final SendableChooser<Command> autoChooser;
    public final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
    public final IntakeMoverSubsystem intakeMoverSubsystem = new IntakeMoverSubsystem();
    public final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    public final AlgeaIntakeSubsystem algeaIntakeSubsystem = new AlgeaIntakeSubsystem();
    public final StatusLED ledSubsystem = new StatusLED();

    public final CommandXboxController driverController = new CommandXboxController(OI.DRIVER_CONTROLLER_PORT);

    public final AutonPath otonom_path = new AutonPath();
    public final FollowTrajectoryCommand otonom = new FollowTrajectoryCommand(swerveSubsystem);

    public final e_movedown elevator_down;
    public final e_moveup elevator_up;
    public final e_reefscape e_reefscape;
    public final e_level1 e_l1;
    public final e_level2 e_l2;
    public final e_level3 e_l3;
    public final e_source e_source;
    public final e_processor e_processor;
    public final e_net e_net;
    public final e_level4 e_l4;
    public final e_algea e_algea_middle;
    public final e_algea e_algea_down;

    public final source im_source;
    public final processor im_processor;
    public final reefscape im_reefscape;
    public final algea im_algea;
    public final korel im_coral;
    public final l4 im_l4;
    public final net im_net;
    public final movedown im_movedown;
    public final moveup im_moveup;

    public final AlgeaIntake a_intake;
    public final AlgeaOuttake a_outtake;

    public final Intake c_intake;
    public final Outtake c_outtake;

    public final LEDStateCycler led_cycle;
    public final LimelightAimCommand limelight_focus;

    public final Command intakeAlgeaMiddle;
    public final Command intakeAlgeaDown;
    public final Command getSource;
    public final Command AlgeaProcessor;
    public final Command AlgeaNet;
    public final Command Coral_reefscape;
    public final Command Coral_l1;
    public final Command Coral_l2;
    public final Command Coral_l3;
    public final Command Coral_l4;

    public RobotContainer() {

        elevator_down = new e_movedown(elevatorSubsystem);
        elevator_up = new e_moveup(elevatorSubsystem);
        e_reefscape = new e_reefscape(elevatorSubsystem);
        e_processor = new e_processor(elevatorSubsystem);
        e_algea_middle = new e_algea(elevatorSubsystem,true);
        e_algea_down = new e_algea(elevatorSubsystem,true);
        e_source = new e_source(elevatorSubsystem);
        e_net = new e_net(elevatorSubsystem);
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
        im_net = new net(intakeMoverSubsystem);
        im_moveup = new moveup(intakeMoverSubsystem);
        im_movedown = new movedown(intakeMoverSubsystem);

        a_intake = new AlgeaIntake(algeaIntakeSubsystem);
        a_outtake = new AlgeaOuttake(algeaIntakeSubsystem);

        c_intake = new Intake(intakeSubsystem);
        c_outtake = new Outtake(intakeSubsystem);

        led_cycle = new LEDStateCycler(ledSubsystem);
        limelight_focus = new LimelightAimCommand(swerveSubsystem);

        //TODO ALL THE TORTURE ENDS HERE
        intakeAlgeaMiddle = new SequentialCommandGroup(new e_algea(elevatorSubsystem,true), new algea(intakeMoverSubsystem), new AlgeaIntake(algeaIntakeSubsystem));
        intakeAlgeaDown = new SequentialCommandGroup(new e_algea(elevatorSubsystem,false), new algea(intakeMoverSubsystem), new AlgeaIntake(algeaIntakeSubsystem));
        getSource = new SequentialCommandGroup(new e_source(elevatorSubsystem), new source(intakeMoverSubsystem), new Intake(intakeSubsystem));
    
        AlgeaNet = new SequentialCommandGroup(new e_net(elevatorSubsystem), new net(intakeMoverSubsystem), new AlgeaOuttake(algeaIntakeSubsystem));
        AlgeaProcessor = new SequentialCommandGroup(new e_processor(elevatorSubsystem), new processor(intakeMoverSubsystem), new AlgeaOuttake(algeaIntakeSubsystem));
    
        Coral_l1 = new SequentialCommandGroup(new e_level1(elevatorSubsystem), new korel(intakeMoverSubsystem), new Outtake(intakeSubsystem));
        Coral_l2 = new SequentialCommandGroup(new e_level2(elevatorSubsystem), new korel(intakeMoverSubsystem), new Outtake(intakeSubsystem));
        Coral_l3 = new SequentialCommandGroup(new e_level3(elevatorSubsystem), new korel(intakeMoverSubsystem), new Outtake(intakeSubsystem));
        Coral_l4 = new SequentialCommandGroup(new e_level4(elevatorSubsystem), new l4(intakeMoverSubsystem), new Outtake(intakeSubsystem));
        Coral_reefscape = new SequentialCommandGroup(new e_reefscape(elevatorSubsystem), new reefscape(intakeMoverSubsystem), new Outtake(intakeSubsystem));

        configureButtonBindings();

        Command drive_command = swerveSubsystem.drive(
        driverController.getLeftY(),
        driverController.getLeftX(),
        driverController.getRawAxis(2),
        false);
        swerveSubsystem.setDefaultCommand(drive_command);

        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);

        //Add Commands to the PathPlanner
        NamedCommands.registerCommand("intakeAlgeaMiddle", intakeAlgeaMiddle);
        NamedCommands.registerCommand("intakeAlgeaDown", intakeAlgeaDown);
        NamedCommands.registerCommand("getSource", getSource);

        NamedCommands.registerCommand("AlgeaNet", AlgeaNet);
        NamedCommands.registerCommand("AlgeaProcessor", AlgeaProcessor);

        NamedCommands.registerCommand("Coral_reefscape", Coral_reefscape);
        NamedCommands.registerCommand("Coral_l1", Coral_l1);
        NamedCommands.registerCommand("Coral_l2", Coral_l2);
        NamedCommands.registerCommand("Coral_l3", Coral_l3);
        NamedCommands.registerCommand("Coral_l4", Coral_l4);

    }

    private void configureButtonBindings() {
        driverController.leftTrigger().whileTrue(elevator_down);
        driverController.rightTrigger().whileTrue(elevator_up);
        driverController.leftBumper().whileTrue(im_movedown);
        driverController.rightBumper().whileTrue(im_moveup);
        driverController.a().onTrue(Coral_l1);
        driverController.x().onTrue(Coral_l2);
        driverController.b().onTrue(Coral_l3);
        driverController.y().onTrue(Coral_l4);
        driverController.povDown().onTrue(Coral_reefscape);
        driverController.povLeft().onTrue(getSource);
        driverController.povRight().onTrue(intakeAlgeaMiddle);
        driverController.povUp().onTrue(AlgeaNet);
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}
