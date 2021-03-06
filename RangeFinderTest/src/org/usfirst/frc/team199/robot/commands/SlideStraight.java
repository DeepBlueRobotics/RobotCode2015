package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses angle PID to slide in a straight line.
 */
public class SlideStraight extends Command {

	Joystick leftStick;
	Joystick rightStick;

	public SlideStraight() {
        requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		leftStick = Robot.oi.getLeftJoystick();
		rightStick = Robot.oi.getRightJoystick();
		Robot.drivetrain.setAngleTarget(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.drive(leftStick.getY(), leftStick.getX(),
				Robot.drivetrain.updateAngle());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.drive(0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
