// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.OI;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private final RobotContainer m_robotContainer;
  public static boolean doRejectUpdate;
  public static boolean isErrorWritten;
  public Robot() {
    m_robotContainer = new RobotContainer();
    doRejectUpdate = false;
    isErrorWritten = false;
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    try {
      int[] validIDs = {3,4};
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
      }
    } catch (Exception e) {
      if(!isErrorWritten){
        System.out.println("Limelight Bulunamadı!");
        isErrorWritten = true;
      }

    }
  }
  @Override
  public void robotInit() 
  {
    for (int port = 5800; port <= 5809; port++) {
      PortForwarder.add(port, "limelight.local",port);
    }
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
