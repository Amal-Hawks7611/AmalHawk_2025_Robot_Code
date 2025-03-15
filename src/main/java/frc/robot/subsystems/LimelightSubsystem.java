package frc.robot.subsystems;

import frc.robot.LimelightHelpers;
import frc.robot.Constants.OI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//INTAKE SUBSYSTEM. YOU CAN INTAKE IF THERE IS NO CORAL AND YOU CAN OUTTAKE IF THERE IS CORAL. BASIC LOGIC!
public class LimelightSubsystem extends SubsystemBase {
    public static double ty;
    public static double tx;
    public Timer timer = new Timer();

    public LimelightSubsystem() {
    }
    public boolean checkFocusY(){
        ty = LimelightHelpers.getTY("limelight");
        if(ty != 0 && ty < 9 && ty>4){
            return true;
        }else{
            return false;
        }
    }
    // TODO esitsizlik Degerlerini duzenle
    public boolean checkFocusX(){
        tx = LimelightHelpers.getTX("limelight");
        if(tx != 0 && tx < 9 && tx > 4){
            return true;
        }else{
            return false;
        } 
    }
    @Override
    public void periodic(){
        OI.IS_YFOCUSED = checkFocusY();
        OI.IS_XFOCUSED = checkFocusX();
        SmartDashboard.putBoolean("IsYFocused", OI.IS_YFOCUSED);
        SmartDashboard.putBoolean("IsXFocused", OI.IS_XFOCUSED);
    }
}
