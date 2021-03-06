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
	// This variable target is the stop distance away from the 
	// object in front
	final double target;
	
    public RangefinderDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	target = 12;
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
    	Robot.drivetrain.setRangefinderTarget(target);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (Robot.drivetrain.getRangefinderDistance() <= target){
//    		Robot.drivetrain.drive(0, 0, Robot.oi.rightJoystick.getX());
//    	}else{
    		Robot.drivetrain.updateRangefinder(Robot.oi.rightJoystick.getX());
//    	}
    	SmartDashboard.putNumber("Rangefinder", Robot.drivetrain.getRangefinderVoltage());
    	SmartDashboard.putNumber("Distance", Robot.drivetrain.getRangefinderDistance());
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
