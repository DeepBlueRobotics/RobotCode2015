package org.usfirst.frc.team199.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team199.robot.commands.*;
import org.usfirst.frc.team199.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    public static Drivetrain drivetrain;
    public static Elevator elevator;
    public static Arm arm;
    public static Pneumatics pneumatics;
    public static Claw claw;
    public static CameraVision cameraVision;
    private SendableChooser chooser = new SendableChooser();


	public void robotInit() {
    	Preferences.init();
    	RobotMap.init();

        drivetrain = new Drivetrain();
        elevator = new Elevator();
        arm = new Arm();
        pneumatics = new Pneumatics();
        claw = new Claw();
        cameraVision = new CameraVision();
        oi = new OI();

        SmartDashboard.putData(Scheduler.getInstance());
        
        chooser.addDefault("No Auto", "none");
        chooser.addObject("Drive Forward", "drive");
        SmartDashboard.putData("AutoChooser", chooser);
    }

	public void disabledInit(){
		super.disabledInit();
    	Scheduler.getInstance().removeAll();
    	RobotMap.stopAllMotors();
    }

	public void disabledPeriodic() {
		super.disabledPeriodic();
    	RobotMap.stopAllMotors();
    }

	public void autonomousInit() {
    	Scheduler.getInstance().removeAll();
    	elevator.setOverride(false);
//    	int modeSelect = oi.selector();
//		switch (modeSelect) {
//			case 1:
//				new AutoDrive(Preferences.getDouble("AutoDriveForward_Distance"), 0.0, false).start();
//				break;
//			case 2:
//				new AutoMode_PullBinBack(1).start();
//				break;
//			case 3:
//				new AutoMode_PullBinBack(0).start();
//				break;
//			case 4:
//				new AutoMode_ThreeTotes(false).start();
//				break;
//			case 5:
//				new AutoMode_ThreeTotes(true).start();
//				break;
////			Arm Autonomi
//			case 6:
//				new AutoMode_TakeRecycling().start();
//				break;
////			case 6:
////				new AutoFollowLine(48).start();
////				break;
////			case 7:
////				new AutoVisionFollowLine(48).start();
////				break;
////			case 8:
////				new AutoModeSeven_TakeRecycling().start();
////				break;
////			case 9:
////				new AutoModeEight_TakeRecyclingWithLineReaders().start();
////				break;
////			case 10:
////				new AutoModeNine_TakeRecyclingWithVision().start();
////				break;
//			default:
////				new Immobilize().start();
//				break;
//		}
        new UpdateDashboard().start();
        if(chooser.getSelected()=="drive") {
        	new DeadReckoningAuto(0.8, 1.0).start();
        }
        drivetrain.shift(true);
    }

	public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        pneumatics.run();
    }

	public void teleopInit() {
    	Scheduler.getInstance().removeAll();
    	elevator.setOverride(false);
        new UpdateDashboard().start();
//        new AutoCalibrateElevator().start();
        drivetrain.shift(true);
    }

	public void teleopPeriodic() {
        Scheduler.getInstance().run();
        pneumatics.run();
        oi.updateDPad();
    }

	public void testPeriodic() {
        LiveWindow.run();
    }
}
