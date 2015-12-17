package org.usfirst.frc100.SlideWinder.commands;

import org.usfirst.frc100.SlideWinder.SlideWinder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeadReckoningAuto extends Command {
	private Timer timer = new Timer();
	private double speed, time;
	
    public DeadReckoningAuto(double speed, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(SlideWinder.drivetrain);
    	this.speed = speed;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SlideWinder.drivetrain.drive(speed, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SlideWinder.drivetrain.drive(0, 0, 0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
