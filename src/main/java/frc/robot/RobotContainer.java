package frc.robot;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StatusLED;
import frc.robot.subsystems.SwerveSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OI;

import frc.robot.commands.Elevator.e_level1;
import frc.robot.commands.Elevator.e_level2;
import frc.robot.commands.Elevator.e_level3;
import frc.robot.commands.Elevator.e_movedown;
import frc.robot.commands.Elevator.e_moveup;
import frc.robot.commands.Elevator.e_reefscape;
import frc.robot.commands.Led.LEDStateCycler;
import frc.robot.commands.Swerve.LimelightAimCommand;
import frc.robot.commands.Trajectory.AutonPath;
import frc.robot.commands.Trajectory.FollowTrajectoryCommand;

public class RobotContainer {
    private final SendableChooser<Command> autoChooser;
    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
    public final StatusLED ledSubsystem = new StatusLED();

    public final CommandXboxController driverController = new CommandXboxController(OI.DRIVER_CONTROLLER_PORT);

    public final AutonPath otonom_path = new AutonPath();
    public final FollowTrajectoryCommand otonom = new FollowTrajectoryCommand(swerveSubsystem);

    public final e_movedown elevator_down;
    public final e_moveup elevator_up;
    public final e_reefscape reefscape;
    public final e_level1 l1;
    public final e_level2 l2;
    public final e_level3 l3;

    public LEDStateCycler led_cycle;

    public final LimelightAimCommand limelight_focus;

    public RobotContainer() {

        elevator_down = new e_movedown(elevatorSubsystem);
        elevator_up = new e_moveup(elevatorSubsystem);
        reefscape = new e_reefscape(elevatorSubsystem);
        l1 = new e_level1(elevatorSubsystem);
        l2 = new e_level2(elevatorSubsystem);
        l3 = new e_level3(elevatorSubsystem);

        led_cycle = new LEDStateCycler(ledSubsystem);
        limelight_focus = new LimelightAimCommand(swerveSubsystem);

        configureButtonBindings();

        Command drive_command = swerveSubsystem.drive(
        driverController.getLeftY(),
        driverController.getLeftX(),
        driverController.getRawAxis(2),
        false);
        swerveSubsystem.setDefaultCommand(drive_command);

        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    private void configureButtonBindings() {
        driverController.leftTrigger().whileTrue(elevator_down);
        driverController.rightTrigger().whileTrue(elevator_up);
        driverController.a().onTrue(reefscape);
        driverController.x().onTrue(l1);
        driverController.b().onTrue(l2);
        driverController.y().onTrue(l3);
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}