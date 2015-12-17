package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Limits the acceleration of the drivetrain to prevent totes from falling.
 */
public class GradualDrive extends Command {

	public GradualDrive() {
        requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.gradualDrive(Robot.oi.getLeftJoystick().getY(),
			Robot.oi.getLeftJoystick().getX(), Robot.oi.getRightJoystick().getX());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
