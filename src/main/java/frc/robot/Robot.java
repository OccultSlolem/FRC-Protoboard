// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private static Joystick joy;
    private static CANSparkMax sparkOne, sparkTwo;
    private static double counter = 0;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private String now() {
        return dtf.format(LocalDateTime.now());
    }

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        System.out.println("Robot powered on at " + now());

        joy = new Joystick(Constants.joystickUSBOrder);
        sparkOne = new CANSparkMax(Constants.sparkMax1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        sparkTwo = new CANSparkMax(Constants.sparkMax2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    @Override
    public void robotPeriodic() {}

    @Override
    public void autonomousInit() {
        System.out.println("No autonomous available; please switch to teleop.");
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        if (counter >= 15) {
            System.out.println("No autonomous available; please switch to teleop.");
            counter = 0;
        }
        counter++;
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
        System.out.println("Teleop enabled at " + now());
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        // TODO: Evaluate controls
        sparkOne.set(joy.getRawAxis(1));
        if (joy.getRawButton(3)) {
            sparkOne.set(joy.getY());
            sparkTwo.set(joy.getY());
            return;
        }

        if (joy.getTrigger()) {
            sparkOne.set(joy.getY());
        } else {
            sparkTwo.set(joy.getY());
        }
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
        System.out.println("Robot disabled at " + now());
        counter = 0;
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
        System.out.println("No test mode available; please switch to teleop");
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
        if (counter >= 15) {
            System.out.println("No test mode available; please switch to teleop.");
            counter = 0;
        }
        counter++;
    }
}
