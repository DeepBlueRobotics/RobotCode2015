package org.usfirst.frc100.SlideWinder.commands;

import org.usfirst.frc100.SlideWinder.OI;
import org.usfirst.frc100.SlideWinder.SlideWinder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RangefinderDrive extends Command {
	
	boolean targetAcquired;
	final double target;
	
    public RangefinderDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	target = 10;
    	requires(SlideWinder.drivetrain);
    }
    
    public RangefinderDrive(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.target = target;
    	requires(SlideWinder.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetAcquired = false; 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (SlideWinder.drivetrain.getRangefinderDistance() <= target);
    		targetAcquired = true;
    	
    	double speed = targetAcquired ? SlideWinder.oi.getLeftJoystick().getY() : 0;
    	SlideWinder.drivetrain.drive(speed, 0,
				 	 SlideWinder.oi.getRightJoystick().getX());
    	
    	SmartDashboard.putNumber("Rangefinder", SlideWinder.drivetrain.getRangefinderVoltage());
    	SmartDashboard.putNumber("Distance", SlideWinder.drivetrain.getRangefinderDistance());
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
    	end();
    }
}
