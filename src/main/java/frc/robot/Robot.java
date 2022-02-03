// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;  

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX m_frontRight = new WPI_TalonSRX(1); //Declarar el motor derecho en frente
  private final WPI_TalonSRX m_rearRight = new WPI_TalonSRX(2); //Declarar el motor derecho detras
 
  private final MotorControllerGroup  m_rightDrive = new MotorControllerGroup(m_frontRight, m_rearRight); //Declaramos el grupo de motores derecho
 
  private final WPI_TalonSRX m_frontLeft = new WPI_TalonSRX(4); //Declarar el motor derecho en frente
  private final WPI_TalonSRX m_rearLeft = new WPI_TalonSRX(3);  //Declarar el motor derecho detras

  private final MotorControllerGroup  m_leftDrive = new MotorControllerGroup(m_frontLeft, m_rearLeft); //Declaramos el grupo de motores izquierdo

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive); //Declaramos el robotDrive utilizando los dos grupos de motores

  private final Joystick my_joystick = new Joystick(0); //Creamos el joystick
  //private final Timer m_timer = new Timer();

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightDrive.setInverted(true); //Invertimos los motores
    //read values periodically
    
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);


    SmartDashboard.putNumber("Limelight-tv", tv);
    SmartDashboard.putNumber("Limelight-tx", tx);
    SmartDashboard.putNumber("Limelight-ty", ty);
    SmartDashboard.putNumber("Limelight-ta", ta);

    //m_frontRight.set(ControlMode.PercentOutput, my_joystick.getRawAxis(5));
    //codigo del robot funcional arcade
    m_robotDrive.arcadeDrive(-my_joystick.getRawAxis(5), my_joystick.getRawAxis(4)); //Utilizando el joystick de la derecha
    
    
    /*Conduccion Jorge
    m_robotDrive.arcadeDrive(-my_joystick.getRawAxis(1), my_joystick.getRawAxis(4));
    */

    //codigo del robot funcional tanque
   //m_robotDrive.tankDrive(-my_joystick.getRawAxis(1), -my_joystick.getRawAxis(5));


    // right_motor.set(ControlMode.PercentOutput, my_joystick.getRawAxis(5));
    // left_motor.set(ControlMode.PercentOutput, my_joystick.getRawAxis(1)); */
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
