package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Causes the PID code for a specific subsystem to attempt to reach a value
 * specified on the SmartDashboard.
 */
public class TestPID extends Command {

	private final System system;
	private double target = 0;
	private boolean armDone = false;

	// The various PID loops of the robot
	public enum System {
		DRIVEDISTANCE, DRIVEANGLE, DRIVESLIDE, ELEVATOR, ARM;
	}

	/**
	 * @param system - The PID system to be tested
	 */
	public TestPID(System system) {
		this.system = system;
		if (system == System.ELEVATOR) {
			requires(Robot.elevator);
//		} else if (system == System.ARM) {
//			requires(SlideWinder.arm);
		} else {
			requires(Robot.drivetrain);
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (system == System.DRIVEDISTANCE) {
			target = SmartDashboard.getNumber("PID/DriveDistance TestTarget");
			Robot.drivetrain.setAutoTarget(target, 0, 0);
		} else if (system == System.DRIVESLIDE) {
			target = SmartDashboard.getNumber("PID/DriveSlide TestTarget");
			Robot.drivetrain.setAutoTarget(0, target, 0);
		} else if (system == System.DRIVEANGLE) {
			target = SmartDashboard.getNumber("PID/DriveAngle TestTarget");
			Robot.drivetrain.setAutoTarget(0, 0, target);
		} else if (system == System.ELEVATOR) {
			target = SmartDashboard.getNumber("PID/Elevator TestTarget");
			Robot.elevator.setAutoTarget(target);
//		} else if (system == System.ARM) {
//			target = SmartDashboard.getNumber("PID/Arm TestTarget");
//			SlideWinder.arm.setArmHeight(target);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (system == System.ELEVATOR) {
			Robot.elevator.updatePID();
//		} else if (system == System.ARM) {
//			armDone = SlideWinder.arm.updateArm();
		} else {
			Robot.drivetrain.updateAuto(false);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (system == System.ELEVATOR) {
			return Robot.elevator.isInPosition();
		} else if (system == System.ARM) {
			return armDone;
		} else {
			return Robot.drivetrain.autoReachedTarget();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		if (system == System.ELEVATOR) {
			Robot.elevator.activateBrake();
//		} else if (system == System.ARM) {
//			SlideWinder.arm.manualControl(0.0);
		} else {
			Robot.drivetrain.drive(0, 0, 0);
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
