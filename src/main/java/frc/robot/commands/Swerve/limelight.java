package frc.robot.commands.Swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.OI;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class limelight extends Command {
    public boolean finished;
    public double ty;
    public double tx;
    private final SwerveSubsystem swerveSubsystem;
    public limelight(SwerveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        System.out.println("Hizalama Basladi");
    }

    @Override
    public void execute() {
        System.out.println(LimelightHelpers.getTY("limelight"));
        if(OI.IS_FOCUSED){
            this.end(false);
        }
        else{
            swerveSubsystem.drive(new Translation2d(0,Constants.LIMELIGHT_KP), 0, false);
        }
    }

    public void rightAlign() {
        ty = LimelightHelpers.getTY("limelight");
        tx = LimelightHelpers.getTX("limelight");
        double ty_inRadians = ty * (Math.PI / 180.0);
        double tx_inRadians = tx * (Math.PI / 180.0);
 
        double distanceFromLMToTarget = (Constants.REEF_APRIL_TAG_HEIGHT - Constants.LIMELIGHT_HEIGHT) / Math.tan(tx_inRadians);
        
        double whereIsAprilTagLM = Math.tan(ty_inRadians) * distanceFromLMToTarget;
        double whereIsBranchLM = (whereIsAprilTagLM >= 0) 
            ? (whereIsAprilTagLM - Constants.APRILTAG_TO_BRANCH) 
            : (whereIsAprilTagLM + Constants.APRILTAG_TO_BRANCH); // sagdaysa neg, soldaysa pos

        double whereIsBranch_CENTER = (whereIsBranchLM - Constants.LIMELIGHT_TO_CENTER); // sagdaysa neg, soldaysa pos (cm)
        //swerveSubsystem.driveForward(whereIsBranch_CENTER / 100, Constants.LM_ALIGN_SPEED_MS);

        
        

        

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Hizalama Bitti");
        swerveSubsystem.drive(new Translation2d(0,0),0,false);
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}