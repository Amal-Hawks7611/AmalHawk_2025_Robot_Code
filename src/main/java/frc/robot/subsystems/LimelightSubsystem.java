package frc.robot.subsystems;

import frc.robot.LimelightHelpers;
import frc.robot.Constants.OI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//LIMELIGHT SUBSYSTEM USED FOR ALIGNS
public class LimelightSubsystem extends SubsystemBase {
    public static double ty;
    public Timer timer = new Timer();

    public LimelightSubsystem() {
    }

    public boolean checkFocus() {
        ty = LimelightHelpers.getTY("limelight");
        if (ty != 0 && ty < 11 && ty > 6) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void periodic() {
        OI.IS_FOCUSED = checkFocus();
        SmartDashboard.putBoolean("IsFocused", OI.IS_FOCUSED);
    }
}
