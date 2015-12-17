package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Extends or retracts the autonomous arm, depending on the boolean value
 * specified in the constructor.
 */
public class DeployArm extends Command {

	private boolean extend;

	/**
	 * @param extend - Extend or retract the arm
	 */
    public DeployArm(boolean extend) {
        requires(Robot.arm);
        this.extend = extend;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.deploy(extend);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
