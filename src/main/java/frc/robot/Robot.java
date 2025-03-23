// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.LEDPattern.GradientType;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.Controlls;
import frc.robot.Constants.Elevator;
import frc.robot.Constants.LedSubsystem;
import frc.robot.Constants.OI;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private final RobotContainer m_robotContainer;
  public static boolean doRejectUpdate;
  public static boolean isErrorWritten;
  public Timer disabledTimer;

  public Robot() {
    m_robotContainer = new RobotContainer();
    doRejectUpdate = false;
    isErrorWritten = false;
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    //LED BREATHE CHANGES BY SWERVE SPEED
    if (Controlls.DRIVER_CONTROLLER.getLeftY() <= -0.3 && Controlls.DRIVER_CONTROLLER.getLeftY() >= -0.6)
      LedSubsystem.BREATHE_MAGNITUDE = 3;
    if (Controlls.DRIVER_CONTROLLER.getLeftY() <= -0.6 && Controlls.DRIVER_CONTROLLER.getLeftY() >= -0.9) {
      LedSubsystem.BREATHE_MAGNITUDE = 2;
    }
    if (Controlls.DRIVER_CONTROLLER.getLeftY() <= -0.9) {
      LedSubsystem.BREATHE_MAGNITUDE = 1;
    }
  }

  @Override
  public void robotInit() {
    m_robotContainer.drivebase.zeroGyro();

    //LED COLOR CHANGES BY ALLIANCE
    if (!RobotBase.isSimulation()) {
      if (DriverStation.getAlliance().get() == DriverStation.Alliance.Blue) {
        LedSubsystem.BREATHE_COLOR = LEDPattern.gradient(GradientType.kDiscontinuous,
            m_robotContainer.ledSubsystem.setBrightness(Color.kBlue, 0.69*0.8),
            m_robotContainer.ledSubsystem.setBrightness(Color.kDarkBlue, 0.69*0.8),
            m_robotContainer.ledSubsystem.setBrightness(Color.kPurple, 0.69*0.8));
      }
    }
    // Limelight Local Port Brute Because Of FMS Connections
    for (int port = 5800; port <= 5809; port++) {
      PortForwarder.add(port, "limelight.local", port);
    }
    disabledTimer = new Timer();
  }

  @Override
  public void disabledInit() {
    m_robotContainer.setMotorBrake(true);
    disabledTimer.reset();
    disabledTimer.start();
  }

  @Override
  public void disabledPeriodic() {
    if (disabledTimer.hasElapsed(Constants.DrivebaseConstants.WHEEL_LOCK_TIME)) {
      m_robotContainer.setMotorBrake(false);
      disabledTimer.stop();
      disabledTimer.reset();
    }
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    m_robotContainer.setMotorBrake(true);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
      m_robotContainer.led_morse.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    OI.IS_TEST = false;
    m_robotContainer.configureButtonBindings();
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    OI.IS_TEST = true;
    Elevator.ELEVATOR_SPEED = 0.2;
    m_robotContainer.configureButtonBindings();
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
    DriverStation.silenceJoystickConnectionWarning(true);
  }

  @Override
  public void simulationPeriodic() {
  }
}
