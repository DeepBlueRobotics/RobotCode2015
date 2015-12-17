package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Slides sideways until the line is detected.
 */
public class AutoSlideToLine extends Command {

	public AutoSlideToLine() {
        requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.setSlideTarget(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.drivetrain.onLine()){
			Robot.drivetrain.drive(0, Robot.drivetrain.updateSlide(), 0);
		} else {
			Robot.drivetrain.drive(0, -1.0, 0);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.drivetrain.reachedSlideDistance();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setLineTrackLimits();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
