package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves elevator down to lower limit switch and sets that position to be the
 * zero value.
 */
public class AutoCalibrateElevator extends Command {

	boolean limitTrigger = false;

	public AutoCalibrateElevator() {
        requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		limitTrigger = false;
		Robot.elevator.setOverride(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.manualControl(-0.6);
		if (Robot.elevator.getLowerLimit()) {
			Robot.elevator.manualControl(0.2);
			limitTrigger = true;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !Robot.elevator.getLowerLimit() && limitTrigger;

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.activateBrake();
		Robot.elevator.zeroPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
