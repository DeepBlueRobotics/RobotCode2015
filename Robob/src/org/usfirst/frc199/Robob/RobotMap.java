// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc199.Robob;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveTrainRightM;
    public static SpeedController driveTrainLeftM;
    public static RobotDrive driveTrainRobotDrive21;
    public static Encoder driveTrainLeftE;
    public static Encoder driveTrainRightE;
    public static Gyro driveTrainG;
    public static SpeedController elevatorM;
    public static DigitalInput elevatorUpperLim;
    public static DigitalInput elevatorLowerLim;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainRightM = new Talon(1);
        LiveWindow.addActuator("DriveTrain", "RightM", (Talon) driveTrainRightM);
        
        driveTrainLeftM = new Talon(0);
        LiveWindow.addActuator("DriveTrain", "LeftM", (Talon) driveTrainLeftM);
        
        driveTrainRobotDrive21 = new RobotDrive(driveTrainLeftM, driveTrainRightM);
        
        driveTrainRobotDrive21.setSafetyEnabled(true);
        driveTrainRobotDrive21.setExpiration(0.1);
        driveTrainRobotDrive21.setSensitivity(0.5);
        driveTrainRobotDrive21.setMaxOutput(1.0);
        

        driveTrainLeftE = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftE", driveTrainLeftE);
        driveTrainLeftE.setDistancePerPulse(1.0);
        driveTrainLeftE.setPIDSourceParameter(PIDSourceParameter.kRate);
        driveTrainRightE = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightE", driveTrainRightE);
        driveTrainRightE.setDistancePerPulse(1.0);
        driveTrainRightE.setPIDSourceParameter(PIDSourceParameter.kRate);
        driveTrainG = new Gyro(0);
        LiveWindow.addSensor("DriveTrain", "G", driveTrainG);
        driveTrainG.setSensitivity(0.007);
        elevatorM = new Talon(3);
        LiveWindow.addActuator("Elevator", "M", (Talon) elevatorM);
        
        elevatorUpperLim = new DigitalInput(8);
        LiveWindow.addSensor("Elevator", "UpperLim", elevatorUpperLim);
        
        elevatorLowerLim = new DigitalInput(9);
        LiveWindow.addSensor("Elevator", "LowerLim", elevatorLowerLim);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
