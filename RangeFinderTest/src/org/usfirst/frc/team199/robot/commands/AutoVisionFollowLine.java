package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;
import org.usfirst.frc.team199.robot.subsystems.CameraVision;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Autonomously follow the line using vision.
 */
public class  AutoVisionFollowLine extends Command {
	
	private final double distance;

    public AutoVisionFollowLine(double distance) {
        requires(Robot.drivetrain);
        requires(Robot.cameraVision);
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cameraVision.initCamera();
//    	SlideWinder.cameraVision.setScanLine((int) SmartDashboard.getNumber("Scan Line", 100));
//    	SlideWinder.cameraVision.setLineReference((int) SmartDashboard.getNumber("Line Reference", 160));
//    	SlideWinder.cameraVision.setThreshold((int) SmartDashboard.getNumber("Threshold", 1000));
        Robot.drivetrain.setDistanceTarget(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SlideWinder.cameraVision.updateCamera();
    	if(CameraVision.cameraIsOpened){
    	Robot.drivetrain.visionFollowLine(Robot.cameraVision.getLineOffset());
    	}
    	Robot.cameraVision.updateDashboard();
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
