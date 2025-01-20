// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.BuildConstants;
import frc.robot.Constants.OI;

public class Robot extends LoggedRobot {
  private Command m_autonomousCommand;
  private final RobotContainer m_robotContainer;
  public static boolean doRejectUpdate; //Uzulerek reddediyoruz.

  public Robot() {
    // Storage... I will fill you up, break you apart, splay the logs of this robot's profane form
    // across your digital innards! I will fill you up until the very Sparks (MAX) cry for mercy!
    // My logs SHALL RELISH ENDING YOU... HERE AND NOW!
    Logger.recordOutput("Swerve Pose", DriverStation.getAlliance().map(alliance -> alliance.name()).orElse("Unknown"));
    Logger.recordMetadata("EventName", DriverStation.getEventName());
    Logger.recordMetadata("ProjectName", BuildConstants.MAVEN_NAME);
    Logger.recordMetadata("BuildDate", BuildConstants.BUILD_DATE);
    Logger.recordMetadata("GitSHA", BuildConstants.GIT_SHA);
    Logger.recordMetadata("GitDate", BuildConstants.GIT_DATE);
    Logger.recordMetadata("GitBranch", BuildConstants.GIT_BRANCH);

    switch (Constants.BuildConstants.currentMode) {
      case REAL:
        // Running on a real robot, log to a USB stick ("/U/logs")
        Logger.addDataReceiver(new WPILOGWriter());
        Logger.addDataReceiver(new NT4Publisher());
        break;

      case SIM:
        // Running a physics simulator, log to NT
        Logger.addDataReceiver(new NT4Publisher());
        break;

      case REPLAY:
        // Replaying a log, set up replay source
        setUseTiming(false); // Run as fast as possible
        String logPath = LogFileUtil.findReplayLog();
        Logger.setReplaySource(new WPILOGReader(logPath));
        Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim")));
        break;
    }

    m_robotContainer = new RobotContainer();
    doRejectUpdate = false;
    Logger.start();
  }

  @Override
  public void robotPeriodic() {
    // Not sure what this logs, but why not?
    Logger.recordOutput("Location of the Drivers", Integer.toString(DriverStation.getLocation().orElse(-1)).getBytes());
    Logger.getTimestamp();
    Logger.recordOutput("Time", Double.toString(DriverStation.getMatchTime()).getBytes());
    CommandScheduler.getInstance().run();

      /* int[] validIDs = {3,4};
      LimelightHelpers.SetFiducialIDFiltersOverride("limelight", validIDs);
      LimelightHelpers.SetRobotOrientation("limelight", m_robotContainer.swerveSubsystem.poseEstimator.getEstimatedPosition().getRotation().getDegrees(), 0, 0, 0, 0, 0);
      LimelightHelpers.PoseEstimate mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight");
      if(Math.abs(m_robotContainer.swerveSubsystem.gyro.getRate()) > 720)
      {
        doRejectUpdate = true;
      }
      if(mt2.tagCount == 0)
      {
        doRejectUpdate = true;
      }
      if(!doRejectUpdate)
      {
        m_robotContainer.swerveSubsystem.poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.7,.7,9999999));
        m_robotContainer.swerveSubsystem.poseEstimator.addVisionMeasurement(
            mt2.pose,
            mt2.timestampSeconds);
      } */
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    OI.IS_TEST = false;
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    OI.IS_TEST = true;
    OI.ELEVATOR_SPEED = 0.3;
  }

  @Override
  public void testPeriodic() {
    m_robotContainer.ledSubsystem.setDefaultCommand(m_robotContainer.led_cycle);
  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
