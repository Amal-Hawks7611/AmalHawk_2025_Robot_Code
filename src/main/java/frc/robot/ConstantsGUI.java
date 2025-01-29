package frc.robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConstantsGUI {
    private JFrame frame;
    private JButton openEncoderMenuButton;
    private JButton openSettingsMenuButton;

    // Elevator Encoder Values
    private JTextField elevatorStartValueField;
    private JTextField elevatorEndValueField;
    private JTextField elevatorSourceValueField;
    private JTextField elevatorProcessorValueField;
    private JTextField elevatorReefscapeValueField;
    private JTextField elevatorL1ValueField;
    private JTextField elevatorL2ValueField;
    private JTextField elevatorL3ValueField;
    private JTextField elevatorL4ValueField;
    private JTextField elevatorNetValueField;
    private JTextField elevatorAlgeaValueDownField;
    private JTextField elevatorAlgeaValueMiddleField;

    // Intake Mover Values
    private JTextField intakeMoverSpeedField;
    private JTextField intakeProcessorValueField;
    private JTextField intakeSourceValueField;
    private JTextField intakeAlgeaValueField;
    private JTextField intakeReefscapeValueField;
    private JTextField intakeCoralValueField;
    private JTextField intakeL4ValueField;
    private JTextField intakeNetValueField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConstantsGUI window = new ConstantsGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ConstantsGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        // Use GridLayout for 2-column structure
        frame.getContentPane().setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns with 10px gap between components

        // Set the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set background color
        frame.getContentPane().setBackground(Color.decode("#f0f0f0"));

        // Create and configure the "Open Encoder Menu" button
        openEncoderMenuButton = new JButton("Open Encoder Menu");
        openEncoderMenuButton.setFont(new Font("Arial", Font.BOLD, 14));
        openEncoderMenuButton.setForeground(Color.WHITE);
        openEncoderMenuButton.setBackground(new Color(0, 123, 255)); // Blue background
        openEncoderMenuButton.setFocusPainted(false);
        openEncoderMenuButton.setPreferredSize(new Dimension(150, 40));

        openEncoderMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEncoderMenu(); // Open the Encoder Menu when clicked
            }
        });

        // Create and configure the "Open Settings Menu" button
        openSettingsMenuButton = new JButton("Open Settings Menu");
        openSettingsMenuButton.setFont(new Font("Arial", Font.BOLD, 14));
        openSettingsMenuButton.setForeground(Color.WHITE);
        openSettingsMenuButton.setBackground(new Color(0, 123, 255)); // Blue background
        openSettingsMenuButton.setFocusPainted(false);
        openSettingsMenuButton.setPreferredSize(new Dimension(150, 40));

        openSettingsMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettingsMenu(); // Open the Settings Menu when clicked
            }
        });

        frame.getContentPane().add(openEncoderMenuButton);
        frame.getContentPane().add(openSettingsMenuButton);
    }

    private void openEncoderMenu() {
        JFrame encoderFrame = new JFrame("Encoder Menu");
        encoderFrame.setBounds(100, 100, 600, 650); // Size of Encoder Menu
        encoderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        encoderFrame.getContentPane().setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns

        // Elevator Encoder Values (2 columns)
        elevatorStartValueField = addTextFieldWithLabel(encoderFrame, "Elevator Start Value:", String.valueOf(Constants.Elevator.ELEVATOR_START_VALUE));
        elevatorEndValueField = addTextFieldWithLabel(encoderFrame, "Elevator End Value:", String.valueOf(Constants.Elevator.ELEVATOR_END_VALUE));
        elevatorSourceValueField = addTextFieldWithLabel(encoderFrame, "Elevator Source Value:", String.valueOf(Constants.Elevator.ELEVATOR_SOURCE_VALUE));
        elevatorProcessorValueField = addTextFieldWithLabel(encoderFrame, "Elevator Processor Value:", String.valueOf(Constants.Elevator.ELEVATOR_PROCESSOR_VALUE));
        elevatorReefscapeValueField = addTextFieldWithLabel(encoderFrame, "Elevator Reefscape Value:", String.valueOf(Constants.Elevator.ELEVATOR_REEFSCAPE_VALUE));
        elevatorL1ValueField = addTextFieldWithLabel(encoderFrame, "Elevator L1 Value:", String.valueOf(Constants.Elevator.ELEVATOR_L1_VALUE));
        elevatorL2ValueField = addTextFieldWithLabel(encoderFrame, "Elevator L2 Value:", String.valueOf(Constants.Elevator.ELEVATOR_L2_VALUE));
        elevatorL3ValueField = addTextFieldWithLabel(encoderFrame, "Elevator L3 Value:", String.valueOf(Constants.Elevator.ELEVATOR_L3_VALUE));
        elevatorL4ValueField = addTextFieldWithLabel(encoderFrame, "Elevator L4 Value:", String.valueOf(Constants.Elevator.ELEVATOR_L4_VALUE));
        elevatorNetValueField = addTextFieldWithLabel(encoderFrame, "Elevator Net Value:", String.valueOf(Constants.Elevator.ELEVATOR_NET_VALUE));
        elevatorAlgeaValueDownField = addTextFieldWithLabel(encoderFrame, "Elevator Algea Down Value:", String.valueOf(Constants.Elevator.ELEVATOR_ALGEA_VALUE_DOWN));
        elevatorAlgeaValueMiddleField = addTextFieldWithLabel(encoderFrame, "Elevator Algea Middle Value:", String.valueOf(Constants.Elevator.ELEVATOR_ALGEA_VALUE_MIDDLE));

        // Intake Mover Values (2 columns)
        intakeMoverSpeedField = addTextFieldWithLabel(encoderFrame, "Intake Mover Speed:", String.valueOf(Constants.IntakeMover.INTAKE_MOVER_SPEED));
        intakeProcessorValueField = addTextFieldWithLabel(encoderFrame, "Intake Processor Value:", String.valueOf(Constants.IntakeMover.INTAKE_PROCESSOR_VALUE));
        intakeSourceValueField = addTextFieldWithLabel(encoderFrame, "Intake Source Value:", String.valueOf(Constants.IntakeMover.INTAKE_SOURCE_VALUE));
        intakeAlgeaValueField = addTextFieldWithLabel(encoderFrame, "Intake Algea Value:", String.valueOf(Constants.IntakeMover.INTAKE_ALGEA_VALUE));
        intakeReefscapeValueField = addTextFieldWithLabel(encoderFrame, "Intake Reefscape Value:", String.valueOf(Constants.IntakeMover.INTAKE_REEFSCAPE_VALUE));
        intakeCoralValueField = addTextFieldWithLabel(encoderFrame, "Intake Coral Value:", String.valueOf(Constants.IntakeMover.INTAKE_CORAL_VALUE));
        intakeL4ValueField = addTextFieldWithLabel(encoderFrame, "Intake L4 Value:", String.valueOf(Constants.IntakeMover.INTAKE_L4_VALUE));
        intakeNetValueField = addTextFieldWithLabel(encoderFrame, "Intake Net Value:", String.valueOf(Constants.IntakeMover.INTAKE_NET_VALUE));

        // Submit button to save values
        JButton submitButton = new JButton("Save Encoder Values");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(0, 123, 255)); // Blue background
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEncoderValues();
            }
        });

        encoderFrame.getContentPane().add(submitButton);
        encoderFrame.setVisible(true);
    }

    private void openSettingsMenu() {
        JFrame settingsFrame = new JFrame("Settings Menu");
        settingsFrame.setBounds(100, 100, 600, 1000); // Size of Settings Menu
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.getContentPane().setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns
    
        // Add settings parameters to the menu
        JTextField wheelbaseField = addTextFieldWithLabel(settingsFrame, "Wheelbase:", String.valueOf(Constants.Swerve.WHEELBASE));
        JTextField trackwidthField = addTextFieldWithLabel(settingsFrame, "Trackwidth:", String.valueOf(Constants.Swerve.TRACKWIDTH));
        JTextField maxSpeedField = addTextFieldWithLabel(settingsFrame, "Max Speed (m/s):", String.valueOf(Constants.Swerve.MAX_SPEED_METERS_PER_SECOND));
        JTextField driveEncoderPositionField = addTextFieldWithLabel(settingsFrame, "Drive Encoder Position Conversion:", String.valueOf(Constants.Swerve.DRIVE_ENCODER_POSITION_CONVERSION));
        JTextField driveEncoderVelocityField = addTextFieldWithLabel(settingsFrame, "Drive Encoder Velocity Conversion:", String.valueOf(Constants.Swerve.DRIVE_ENCODER_VELOCITY_CONVERSION));
        JTextField limelightKpField = addTextFieldWithLabel(settingsFrame, "Limelight Align KP:", String.valueOf(Constants.Swerve.LIMELIGHT_ALIGN_KP));
        JTextField isFieldRelativeField = addTextFieldWithLabel(settingsFrame, "Is Field Relative (true/false):", String.valueOf(Constants.Swerve.IS_FIELD_RELATIVE));
    
        JTextField frontLeftDriveMotorField = addTextFieldWithLabel(settingsFrame, "Front Left Drive Motor Port:", String.valueOf(Constants.SwervePorts.FRONT_LEFT_DRIVE_MOTOR));
        JTextField frontRightDriveMotorField = addTextFieldWithLabel(settingsFrame, "Front Right Drive Motor Port:", String.valueOf(Constants.SwervePorts.FRONT_RIGHT_DRIVE_MOTOR));
        JTextField backLeftDriveMotorField = addTextFieldWithLabel(settingsFrame, "Back Left Drive Motor Port:", String.valueOf(Constants.SwervePorts.BACK_LEFT_DRIVE_MOTOR));
        JTextField backRightDriveMotorField = addTextFieldWithLabel(settingsFrame, "Back Right Drive Motor Port:", String.valueOf(Constants.SwervePorts.BACK_RIGHT_DRIVE_MOTOR));
        JTextField frontLeftSteerMotorField = addTextFieldWithLabel(settingsFrame, "Front Left Steer Motor Port:", String.valueOf(Constants.SwervePorts.FRONT_LEFT_STEER_MOTOR));
        JTextField frontRightSteerMotorField = addTextFieldWithLabel(settingsFrame, "Front Right Steer Motor Port:", String.valueOf(Constants.SwervePorts.FRONT_RIGHT_STEER_MOTOR));
        JTextField backLeftSteerMotorField = addTextFieldWithLabel(settingsFrame, "Back Left Steer Motor Port:", String.valueOf(Constants.SwervePorts.BACK_LEFT_STEER_MOTOR));
        JTextField backRightSteerMotorField = addTextFieldWithLabel(settingsFrame, "Back Right Steer Motor Port:", String.valueOf(Constants.SwervePorts.BACK_RIGHT_STEER_MOTOR));
    
        JTextField frontLeftCANCoderField = addTextFieldWithLabel(settingsFrame, "Front Left CANCoder Port:", String.valueOf(Constants.SwervePorts.FRONT_LEFT_CANCODER));
        JTextField frontRightCANCoderField = addTextFieldWithLabel(settingsFrame, "Front Right CANCoder Port:", String.valueOf(Constants.SwervePorts.FRONT_RIGHT_CANCODER));
        JTextField backLeftCANCoderField = addTextFieldWithLabel(settingsFrame, "Back Left CANCoder Port:", String.valueOf(Constants.SwervePorts.BACK_LEFT_CANCODER));
        JTextField backRightCANCoderField = addTextFieldWithLabel(settingsFrame, "Back Right CANCoder Port:", String.valueOf(Constants.SwervePorts.BACK_RIGHT_CANCODER));
    
        JTextField ledPWMPortField = addTextFieldWithLabel(settingsFrame, "LED PWM Port:", String.valueOf(Constants.LedSubsystem.LED_PWM_PORT));
        JTextField ledLengthField = addTextFieldWithLabel(settingsFrame, "LED Length:", String.valueOf(Constants.LedSubsystem.LED_LENGTH));
    
        JTextField elevatorLeaderMotorPortField = addTextFieldWithLabel(settingsFrame, "Elevator Leader Motor Port:", String.valueOf(Constants.Elevator.ELEVATOR_LEADER_MOTOR_PORT));
        JTextField elevatorFollowerMotorPortField = addTextFieldWithLabel(settingsFrame, "Elevator Follower Motor Port:", String.valueOf(Constants.Elevator.ELEVATOR_FOLLOWER_MOTOR_PORT));
    
        JTextField intakeLeaderMotorPortField = addTextFieldWithLabel(settingsFrame, "Intake Leader Motor Port:", String.valueOf(Constants.IntakeMover.LEADER_MOTOR_PORT));
        JTextField intakeSpeedField = addTextFieldWithLabel(settingsFrame, "Intake Speed:", String.valueOf(Constants.Intake.INTAKE_SPEED));
        JTextField outtakeSpeedField = addTextFieldWithLabel(settingsFrame, "Outtake Speed:", String.valueOf(Constants.Intake.OUTTAKE_SPEED));
    
        JTextField intakeMoverSpeedField = addTextFieldWithLabel(settingsFrame, "Intake Mover Speed:", String.valueOf(Constants.IntakeMover.INTAKE_MOVER_SPEED));
    
        JTextField driverControllerPortField = addTextFieldWithLabel(settingsFrame, "Driver Controller Port:", String.valueOf(Constants.OI.DRIVER_CONTROLLER_PORT));
        JTextField elevatorSpeedField = addTextFieldWithLabel(settingsFrame, "Elevator Speed:", String.valueOf(Constants.OI.ELEVATOR_SPEED));
    
        // Offsets
        JTextField frontLeftModuleSteerOffsetField = addTextFieldWithLabel(settingsFrame, "Front Left Module Steer Offset:", String.valueOf(Constants.Swerve.FRONT_LEFT_MODULE_STEER_OFFSET));
        JTextField frontRightModuleSteerOffsetField = addTextFieldWithLabel(settingsFrame, "Front Right Module Steer Offset:", String.valueOf(Constants.Swerve.FRONT_RIGHT_MODULE_STEER_OFFSET));
        JTextField backLeftModuleSteerOffsetField = addTextFieldWithLabel(settingsFrame, "Back Left Module Steer Offset:", String.valueOf(Constants.Swerve.BACK_LEFT_MODULE_STEER_OFFSET));
        JTextField backRightModuleSteerOffsetField = addTextFieldWithLabel(settingsFrame, "Back Right Module Steer Offset:", String.valueOf(Constants.Swerve.BACK_RIGHT_MODULE_STEER_OFFSET));
    
        // Save button
        JButton saveSettingsButton = new JButton("Save Settings");
        saveSettingsButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveSettingsButton.setForeground(Color.WHITE);
        saveSettingsButton.setBackground(new Color(0, 123, 255)); // Blue background
        saveSettingsButton.setFocusPainted(false);
        saveSettingsButton.setPreferredSize(new Dimension(150, 90));
        saveSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettingsValues(wheelbaseField, trackwidthField, maxSpeedField, driveEncoderPositionField, 
                                   driveEncoderVelocityField, limelightKpField, isFieldRelativeField,
                                   frontLeftDriveMotorField, frontRightDriveMotorField, backLeftDriveMotorField, backRightDriveMotorField,
                                   frontLeftSteerMotorField, frontRightSteerMotorField, backLeftSteerMotorField, backRightSteerMotorField,
                                   frontLeftCANCoderField, frontRightCANCoderField, backLeftCANCoderField, backRightCANCoderField,
                                   ledPWMPortField, ledLengthField, elevatorLeaderMotorPortField, elevatorFollowerMotorPortField,
                                   intakeLeaderMotorPortField, intakeSpeedField, outtakeSpeedField, intakeMoverSpeedField,
                                   driverControllerPortField, elevatorSpeedField, frontLeftModuleSteerOffsetField, frontRightModuleSteerOffsetField, backLeftModuleSteerOffsetField,backRightModuleSteerOffsetField, backRightModuleSteerOffsetField, backRightModuleSteerOffsetField, backRightModuleSteerOffsetField, backRightModuleSteerOffsetField);
            }
        });
    
        settingsFrame.getContentPane().add(saveSettingsButton);
        settingsFrame.setVisible(true);
    }
    
    // Method to save the values entered in the settings menu
    private void saveSettingsValues(JTextField wheelbaseField, JTextField trackwidthField, JTextField maxSpeedField, 
                                    JTextField driveEncoderPositionField, JTextField driveEncoderVelocityField, 
                                    JTextField limelightKpField, JTextField isFieldRelativeField,
                                    JTextField frontLeftDriveMotorField, JTextField frontRightDriveMotorField, 
                                    JTextField backLeftDriveMotorField, JTextField backRightDriveMotorField,
                                    JTextField frontLeftSteerMotorField, JTextField frontRightSteerMotorField, 
                                    JTextField backLeftSteerMotorField, JTextField backRightSteerMotorField,
                                    JTextField frontLeftCANCoderField, JTextField frontRightCANCoderField, 
                                    JTextField backLeftCANCoderField, JTextField backRightCANCoderField,
                                    JTextField ledPWMPortField, JTextField ledLengthField, 
                                    JTextField elevatorLeaderMotorPortField, JTextField elevatorFollowerMotorPortField,
                                    JTextField intakeLeaderMotorPortField, JTextField intakeSpeedField, 
                                    JTextField outtakeSpeedField, JTextField intakeMoverSpeedField,
                                    JTextField driverControllerPortField, JTextField elevatorSpeedField,
                                    JTextField frontLeftModuleDriveOffsetField, JTextField frontLeftModuleSteerOffsetField,
                                    JTextField frontRightModuleDriveOffsetField, JTextField frontRightModuleSteerOffsetField,
                                    JTextField backLeftModuleDriveOffsetField, JTextField backLeftModuleSteerOffsetField,
                                    JTextField backRightModuleDriveOffsetField, JTextField backRightModuleSteerOffsetField) {
        try {
            // Read and update the values
            Constants.Swerve.WHEELBASE = Double.parseDouble(wheelbaseField.getText());
            Constants.Swerve.TRACKWIDTH = Double.parseDouble(trackwidthField.getText());
            Constants.Swerve.MAX_SPEED_METERS_PER_SECOND = Double.parseDouble(maxSpeedField.getText());
            Constants.Swerve.DRIVE_ENCODER_POSITION_CONVERSION = Double.parseDouble(driveEncoderPositionField.getText());
            Constants.Swerve.DRIVE_ENCODER_VELOCITY_CONVERSION = Double.parseDouble(driveEncoderVelocityField.getText());
            Constants.Swerve.LIMELIGHT_ALIGN_KP = Double.parseDouble(limelightKpField.getText());
            Constants.Swerve.IS_FIELD_RELATIVE = Boolean.parseBoolean(isFieldRelativeField.getText());
    
            // Update motor ports
            Constants.SwervePorts.FRONT_LEFT_DRIVE_MOTOR = Integer.parseInt(frontLeftDriveMotorField.getText());
            Constants.SwervePorts.FRONT_RIGHT_DRIVE_MOTOR = Integer.parseInt(frontRightDriveMotorField.getText());
            Constants.SwervePorts.BACK_LEFT_DRIVE_MOTOR = Integer.parseInt(backLeftDriveMotorField.getText());
            Constants.SwervePorts.BACK_RIGHT_DRIVE_MOTOR = Integer.parseInt(backRightDriveMotorField.getText());
            Constants.SwervePorts.FRONT_LEFT_STEER_MOTOR = Integer.parseInt(frontLeftSteerMotorField.getText());
            Constants.SwervePorts.FRONT_RIGHT_STEER_MOTOR = Integer.parseInt(frontRightSteerMotorField.getText());
            Constants.SwervePorts.BACK_LEFT_STEER_MOTOR = Integer.parseInt(backLeftSteerMotorField.getText());
            Constants.SwervePorts.BACK_RIGHT_STEER_MOTOR = Integer.parseInt(backRightSteerMotorField.getText());
    
            // Update CANCoder ports
            Constants.SwervePorts.FRONT_LEFT_CANCODER = Integer.parseInt(frontLeftCANCoderField.getText());
            Constants.SwervePorts.FRONT_RIGHT_CANCODER = Integer.parseInt(frontRightCANCoderField.getText());
            Constants.SwervePorts.BACK_LEFT_CANCODER = Integer.parseInt(backLeftCANCoderField.getText());
            Constants.SwervePorts.BACK_RIGHT_CANCODER = Integer.parseInt(backRightCANCoderField.getText());
    
            // Update LED settings
            Constants.LedSubsystem.LED_PWM_PORT = Integer.parseInt(ledPWMPortField.getText());
            Constants.LedSubsystem.LED_LENGTH = Integer.parseInt(ledLengthField.getText());
    
            // Update elevator motor ports
            Constants.Elevator.ELEVATOR_LEADER_MOTOR_PORT = Integer.parseInt(elevatorLeaderMotorPortField.getText());
            Constants.Elevator.ELEVATOR_FOLLOWER_MOTOR_PORT = Integer.parseInt(elevatorFollowerMotorPortField.getText());
    
            // Update intake motor ports and speeds
            Constants.IntakeMover.LEADER_MOTOR_PORT = Integer.parseInt(intakeLeaderMotorPortField.getText());
            Constants.Intake.INTAKE_SPEED = Double.parseDouble(intakeSpeedField.getText());
            Constants.Intake.OUTTAKE_SPEED = Double.parseDouble(outtakeSpeedField.getText());
            Constants.IntakeMover.INTAKE_MOVER_SPEED = Double.parseDouble(intakeMoverSpeedField.getText());
    
            // Update controller port and elevator speed
            Constants.OI.DRIVER_CONTROLLER_PORT = Integer.parseInt(driverControllerPortField.getText());
            Constants.OI.ELEVATOR_SPEED = Double.parseDouble(elevatorSpeedField.getText());
    
            // Update offsets
            Constants.Swerve.FRONT_LEFT_MODULE_STEER_OFFSET = Double.parseDouble(frontLeftModuleSteerOffsetField.getText());
            Constants.Swerve.FRONT_RIGHT_MODULE_STEER_OFFSET = Double.parseDouble(frontRightModuleSteerOffsetField.getText());
            Constants.Swerve.BACK_LEFT_MODULE_STEER_OFFSET = Double.parseDouble(backLeftModuleSteerOffsetField.getText());
            Constants.Swerve.BACK_RIGHT_MODULE_STEER_OFFSET = Double.parseDouble(backRightModuleSteerOffsetField.getText());
    
            JOptionPane.showMessageDialog(null, "Settings saved successfully!");
    
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values.");
        }
    }    

    private JTextField addTextFieldWithLabel(JFrame frame, String labelText, String defaultValue) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(Color.BLACK);
        frame.getContentPane().add(label);

        JTextField textField = new JTextField(defaultValue);
        textField.setPreferredSize(new Dimension(150, 30));
        frame.getContentPane().add(textField);

        return textField;
    }

    private void saveEncoderValues() {
        try {
            // Read values from the text fields and update Constants

            Constants.Elevator.ELEVATOR_START_VALUE = Double.parseDouble(elevatorStartValueField.getText());
            Constants.Elevator.ELEVATOR_END_VALUE = Double.parseDouble(elevatorEndValueField.getText());
            Constants.Elevator.ELEVATOR_SOURCE_VALUE = Double.parseDouble(elevatorSourceValueField.getText());
            Constants.Elevator.ELEVATOR_PROCESSOR_VALUE = Double.parseDouble(elevatorProcessorValueField.getText());
            Constants.Elevator.ELEVATOR_REEFSCAPE_VALUE = Double.parseDouble(elevatorReefscapeValueField.getText());
            Constants.Elevator.ELEVATOR_L1_VALUE = Double.parseDouble(elevatorL1ValueField.getText());
            Constants.Elevator.ELEVATOR_L2_VALUE = Double.parseDouble(elevatorL2ValueField.getText());
            Constants.Elevator.ELEVATOR_L3_VALUE = Double.parseDouble(elevatorL3ValueField.getText());
            Constants.Elevator.ELEVATOR_L4_VALUE = Double.parseDouble(elevatorL4ValueField.getText());
            Constants.Elevator.ELEVATOR_NET_VALUE = Double.parseDouble(elevatorNetValueField.getText());
            Constants.Elevator.ELEVATOR_ALGEA_VALUE_DOWN = Double.parseDouble(elevatorAlgeaValueDownField.getText());
            Constants.Elevator.ELEVATOR_ALGEA_VALUE_MIDDLE = Double.parseDouble(elevatorAlgeaValueMiddleField.getText());

            Constants.IntakeMover.INTAKE_MOVER_SPEED = Double.parseDouble(intakeMoverSpeedField.getText());
            Constants.IntakeMover.INTAKE_PROCESSOR_VALUE = Double.parseDouble(intakeProcessorValueField.getText());
            Constants.IntakeMover.INTAKE_SOURCE_VALUE = Double.parseDouble(intakeSourceValueField.getText());
            Constants.IntakeMover.INTAKE_ALGEA_VALUE = Double.parseDouble(intakeAlgeaValueField.getText());
            Constants.IntakeMover.INTAKE_REEFSCAPE_VALUE = Double.parseDouble(intakeReefscapeValueField.getText());
            Constants.IntakeMover.INTAKE_CORAL_VALUE = Double.parseDouble(intakeCoralValueField.getText());
            Constants.IntakeMover.INTAKE_L4_VALUE = Double.parseDouble(intakeL4ValueField.getText());
            Constants.IntakeMover.INTAKE_NET_VALUE = Double.parseDouble(intakeNetValueField.getText());

            JOptionPane.showMessageDialog(frame, "Encoder values saved successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers!");
        }
    }
}
