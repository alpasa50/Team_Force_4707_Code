package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
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
// 3 4 2 5 mirandolo de frente
public class Robot extends TimedRobot {
  
  private final WPI_TalonSRX m_frontRight = new WPI_TalonSRX(1);
  private final WPI_TalonSRX m_rearRight = new WPI_TalonSRX(3); //Hay que confirmar el ID
 
  private final MotorControllerGroup  m_rightDrive = new MotorControllerGroup(m_frontRight, m_rearRight);
 
  private final WPI_TalonSRX m_frontLeft = new WPI_TalonSRX(5); //Hay que confirmar el ID
  private final WPI_TalonSRX m_rearLeft = new WPI_TalonSRX(2);

  private final WPI_TalonSRX intaker = new WPI_TalonSRX(6);
  private final WPI_TalonSRX ShooterA = new WPI_TalonSRX(4);
  private final WPI_TalonSRX ShooterB = new WPI_TalonSRX(7);

  private final MotorControllerGroup  m_leftDrive = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);

  private final Joystick my_joystick = new Joystick(0);

 // Compressor airCompressor;
  //private final Timer m_timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    ShooterA.setNeutralMode(NeutralMode.Coast);
    ShooterB.setNeutralMode(NeutralMode.Coast);

    // ShooterB.follow(ShooterA);
    
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightDrive.setInverted(true);
   // ShooterB.follow(ShooterA);
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
   //m_robotDrive.arcadeDrive(my_joystick.getRawAxis(0), my_joystick.getRawAxis(1)); //Utilizando el joystick de la derecha
   
   
   //Conduccion Jorge
   m_robotDrive.arcadeDrive(-my_joystick.getRawAxis(1), my_joystick.getRawAxis(4));
  
    //m_robotDrive.tankDrive(my_joystick.getRawAxis(5), my_joystick.getRawAxis(1));

    //m_rearRight.set(ControlMode.PercentOutput, my_joystick.getRawAxis(5));
    //left_motor.set(ControlMode.PercentOutput, my_joystick.getRawAxis(1));
    //***RECOGER BOLAS */
    Intaker();
    //*** DISPARAR BOLAS */
    Shooter();

  }
  public void Intaker(){
    if(my_joystick.getRawButtonPressed(1) == true){
      intaker.set(ControlMode.PercentOutput, -0.50);
    }
    if(my_joystick.getRawButtonPressed(2) == true){
     intaker.set(ControlMode.PercentOutput, 0.0);
     }
  }
  public void Shooter(){
    if(my_joystick.getRawButton(3) == true){
      ShooterA.set(ControlMode.PercentOutput, 0.50);
      //ShooterB.set(ControlMode.PercentOutput, 0.50);
    }
    if(my_joystick.getRawButton(4) == true){
      ShooterA.set(ControlMode.PercentOutput, 0.0);
      //ShooterB.set(ControlMode.PercentOutput, 0.0);

    }
  }
  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
