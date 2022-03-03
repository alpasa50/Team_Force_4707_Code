package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

// 3 4 2 5 mirandolo de frente
public class Robot extends TimedRobot {
  
  private final WPI_TalonSRX dt_frontRight = new WPI_TalonSRX(1);
  private final WPI_TalonSRX dt_rearRight = new WPI_TalonSRX(3);
 
  private final MotorControllerGroup  mc_rightDrive = new MotorControllerGroup(dt_frontRight, dt_rearRight);
 
  private final WPI_TalonSRX dt_frontLeft = new WPI_TalonSRX(5);
  private final WPI_TalonSRX dt_rearLeft = new WPI_TalonSRX(2);

  private final WPI_TalonSRX Intaker = new WPI_TalonSRX(6);
  private final WPI_TalonSRX ShooterA = new WPI_TalonSRX(4);
  private final WPI_TalonSRX ShooterB = new WPI_TalonSRX(7);

  private final MotorControllerGroup  mc_leftDrive = new MotorControllerGroup(dt_frontLeft, dt_rearLeft);

  private final DifferentialDrive robotDrive = new DifferentialDrive(mc_leftDrive, mc_rightDrive);

  private final Joystick jDriverIntaker = new Joystick(0);
  private final Joystick jDriverShooter = new Joystick(1);

  DoubleSolenoid door = new DoubleSolenoid(0,PneumaticsModuleType.CTREPCM, 0,1);

  DoubleSolenoid wheelie_bar = new DoubleSolenoid(0,PneumaticsModuleType.CTREPCM, 2,3);

  Compressor airCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);

  //private final Timer m_timer = new Timer();

  @Override
  public void robotInit() {
    ShooterA.setNeutralMode(NeutralMode.Coast);
    ShooterB.setNeutralMode(NeutralMode.Coast);

    //ShooterB.follow(ShooterA);
    
    mc_rightDrive.setInverted(true);
   // ShooterB.follow(ShooterA);

   airCompressor.enableDigital();
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
   //robotDrive.arcadeDrive(jDriverIntaker.getRawAxis(0), jDriverIntaker.getRawAxis(1)); //Utilizando el joystick de la derecha
   
   
   //Conduccion Jorge
   //robotDrive.arcadeDrive(-jDriverIntaker.getRawAxis(1), jDriverIntaker.getRawAxis(4));
  
    robotDrive.tankDrive(-jDriverIntaker.getRawAxis(1), -jDriverIntaker.getRawAxis(5));

    //dt_rearRight.set(ControlMode.PercentOutput, jDriverIntaker.getRawAxis(5));
    //left_motor.set(ControlMode.PercentOutput, jDriverIntaker.getRawAxis(1));

    //***RECOGER BOLAS */
    Intaker();
    //*** DISPARAR BOLAS */
    Shooter();

  }
  public void Intaker(){
    double RTrigger = jDriverIntaker.getRawAxis(3);
    double LTrigger = jDriverIntaker.getRawAxis(2);

    //Green Button (A)
    /* Activate intakers motors*/
    if(jDriverIntaker.getRawButtonPressed(1) == true){
      Intaker.set(ControlMode.PercentOutput, -0.70);
    }
    //Red Button (B)
    /* Stop intakers motors*/
    if(jDriverIntaker.getRawButtonPressed(2) == true){
     Intaker.set(ControlMode.PercentOutput, 0.0);
     }
     // Yellow Button (Y)
     /* Reverse intakers motors*/
     if(jDriverIntaker.getRawButtonPressed(4) == true){
      Intaker.set(ControlMode.PercentOutput, 0.70);
     }

    // if(Math.abs(RTrigger) > 0.25){
    //   Intaker.set(ControlMode.PercentOutput, 0.70);
    // }else{
    //    Intaker.set(ControlMode.PercentOutput, 0.0);
    // }

    // if(Math.abs(LTrigger) > 0.25){
    //   Intaker.set(ControlMode.PercentOutput, 0.70);
    // }else{
    //   Intaker.set(ControlMode.PercentOutput, 0.0);
    // }


  }

  public void Shooter(){
    // Blue Button (X)
    /* Activate shooter motors and doors*/
    if(jDriverShooter.getRawButtonPressed(3) == true){
      door.set(DoubleSolenoid.Value.kForward);
      ShooterA.set(ControlMode.PercentOutput, 0.61);
      ShooterB.set(ControlMode.PercentOutput, 0.61);
    }
    // Yellow Button (Y)
    /* Activate shooter motors and doors */
    if(jDriverShooter.getRawButtonPressed(4) == true){
      door.set(DoubleSolenoid.Value.kReverse);
      ShooterA.set(ControlMode.PercentOutput, 0.0);
      ShooterB.set(ControlMode.PercentOutput, 0.0);   
    }
     //doors
      if(jDriverShooter.getRawButtonPressed(7)){
      door.set(DoubleSolenoid.Value.kReverse);
     } 
    if(jDriverShooter.getRawButtonPressed(8) == true){ 
      door.set(DoubleSolenoid.Value.kForward);
     }
    }
//distancia de 5ft 0.60 de velocidad 
//roza el 8ft
  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
