package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses line readers to follow the line autonomously.
 */
public class AutoFollowLine extends Command {
	
	private final double distance;
	
	/**
	 * @param distance - The distance to travel in inches
	 */
    public AutoFollowLine(double distance) {
    	this.distance = distance;
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setDistanceTarget(distance);
    	Robot.drivetrain.setAngleTarget(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(Robot.drivetrain.updateDistance(), Robot.drivetrain.followLine(),
				Robot.drivetrain.updateAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.reachedDistance();
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
