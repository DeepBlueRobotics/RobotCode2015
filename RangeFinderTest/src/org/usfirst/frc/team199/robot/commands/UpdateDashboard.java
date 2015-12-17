package org.usfirst.frc.team199.robot.commands;

import org.usfirst.frc.team199.robot.Robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Updates the SmartDashboard for all of the subsystems.
 */
public class UpdateDashboard extends Command {

	PowerDistributionPanel pdp = new PowerDistributionPanel();

	public UpdateDashboard() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putString("PDP/~TYPE~", "SubSystem");
		SmartDashboard.putString("Arm/~TYPE~", "SubSystem");
		SmartDashboard.putString("Claw/~TYPE~", "SubSystem");
		SmartDashboard.putString("Drivetrain/~TYPE~", "SubSystem");
		SmartDashboard.putString("Elevator/~TYPE~", "SubSystem");
		SmartDashboard.putString("CameraVision/~TYPE~", "SubSystem");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.arm.updateDashboard();
		Robot.claw.updateDashboard();
		Robot.drivetrain.updateDashboard();
		Robot.elevator.updateDashboard();
		Robot.cameraVision.updateDashboard();

		SmartDashboard.putNumber("PDP/PDP Voltage", pdp.getVoltage());
		for (int i = 0; i < 16; i++) {
			if(i<10){
				SmartDashboard.putNumber("PDP/PDP Port 0" + i, pdp.getCurrent(i));
			} else {
				SmartDashboard.putNumber("PDP/PDP Port " + i, pdp.getCurrent(i));
			}
		}
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
	}
}
