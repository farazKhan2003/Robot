
import lejos.hardware.Battery;
import lejos.hardware.Button; 
import lejos.hardware.lcd.LCD; 
import lejos.hardware.motor.BaseRegulatedMotor; 
import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort; 
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
  
  
  public class driver {
	  final static float WHEEL_DIAMETER = 56; 
	  final static float AXLE_LENGTH = 140;
	  private static BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
	  private static  BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
	  private static EV3ColorSensor myColor = new EV3ColorSensor(SensorPort.S3);
	  private static Wheel wLeft = WheeledChassis.modelWheel(mLeft, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
	  private static Wheel wRight = WheeledChassis.modelWheel(mRight, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
	  private static Chassis chassis = new WheeledChassis((new Wheel[] {wRight,wLeft}),WheeledChassis.TYPE_DIFFERENTIAL);
	  private static MovePilot myPilot = new MovePilot(chassis);
  
  public static void main(String[] args) {
  

//if its shared put it here and pass it through a constructor as a param
//if its individual to the behaviour put it in the behviour constructor


  

 
  LCD.clear();
  LCD.drawString("WELCOME!",1,1); 
  LCD.drawString("Version: 0.9.1 ", 1, 2);
  LCD.drawString("Kayleigh Reid", 1, 3);
  LCD.drawString("Faraz Khan", 1, 4);
  LCD.drawString("Matthew Lousada", 1, 5);
  LCD.drawString("Ayaan Rahman", 1, 6);


  Button.ENTER.waitForPressAndRelease();

  
  FollowLine followline = new FollowLine(mLeft, mRight, myColor);
  Collision collision = new Collision(mLeft, mRight, myPilot);
  Cards cards = new Cards(mLeft, mRight, myColor);
  Honk honk = new Honk(mLeft, mRight, myColor);
  EmergencyStop emergencystop = new EmergencyStop();
  BatteryLvl batterylvl = new BatteryLvl(1000);
  SpeedClap speedclap = new SpeedClap(mLeft, mRight, myPilot);
  Calibrate calibrate = new Calibrate(myColor, mLeft, mRight);
  LCD.clear();
  LCD.drawString(":D", 5, 2);
  Arbitrator ab = new Arbitrator(new Behavior[] {	  
		  //arbitrator goes from least to most important	  
		  followline, speedclap, honk , cards, calibrate, collision, batterylvl, emergencystop
  } 
  );                                                                                                                                 
  
	

	 
  ab.go();   
  LCD.drawString(":D", 5, 2);
	 
  } 
  }
 