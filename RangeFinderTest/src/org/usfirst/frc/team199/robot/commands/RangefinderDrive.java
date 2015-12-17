package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.OI;
import org.usfirst.frc.team199.robot.Robot;

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
    	requires(Robot.drivetrain);
    }
    
    public RangefinderDrive(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.target = target;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetAcquired = false; 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.drivetrain.getRangefinderDistance() <= target);
    		targetAcquired = true;
    	
    	double speed = targetAcquired ? Robot.oi.getLeftJoystick().getY() : 0;
    	Robot.drivetrain.drive(speed, 0,
				 	 Robot.oi.getRightJoystick().getX());
    	
    	SmartDashboard.putNumber("Rangefinder", Robot.drivetrain.getRangefinderVoltage());
    	SmartDashboard.putNumber("Distance", Robot.drivetrain.getRangefinderDistance());
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
