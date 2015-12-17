package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Preferences;
import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses vision and PID to line up with a tote.
 */
public class AlignToTote extends Command {
	
    public AlignToTote() {
        requires(Robot.drivetrain);
        requires(Robot.cameraVision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setAutoTarget(0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angleError = Robot.cameraVision.getToteAngleError();
    	double positionError = Robot.cameraVision.getTotePositionError();
    	positionError *= Preferences.getDouble("CameraPID_Ratio");
    	Robot.drivetrain.updateAuto(0.0, positionError, angleError, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.autoReachedTarget();
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
