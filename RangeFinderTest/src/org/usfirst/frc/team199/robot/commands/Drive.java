package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Preferences;
import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Operates the drivetrain during teleoperated period. Left Y is forward/back,
 * left X is strafe left/right, and right X is turn clockwise/counterclockwise.
 */
public class Drive extends Command {

	public Drive() {
        requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double leftJoystickX = Robot.oi.getLeftJoystick().getX();
		if (Math.abs(leftJoystickX) < Preferences.getDouble("SlideJoystickDeadband")) {
			leftJoystickX = 0;
		}
		// Joystick Y-axis values are flipped, so send a negative Y-value
		Robot.drivetrain.drive(-Robot.oi.getLeftJoystick().getY(),
				leftJoystickX, Robot.oi.getRightJoystick().getX());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
