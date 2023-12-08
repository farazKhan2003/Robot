import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Collision implements Behavior{
	final static float WHEEL_DIAMETER = 56; final static float AXLE_LENGTH = 140;
	private MovePilot pilot;
	private boolean suppressed;
	 BaseRegulatedMotor mL;
	  BaseRegulatedMotor mR; 
	  
	  
	EV3TouchSensor touchsen = new EV3TouchSensor(SensorPort.S2);
	float[] touched = new float[1];
	SampleProvider ts;
	
	public Collision(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, MovePilot myPilot) {
		this.mL = mLeft;
		this.mR = mRight;
		this.suppressed = suppressed;
		
		this.pilot=myPilot;
		this.ts = touchsen.getTouchMode();
		this.ts.fetchSample(this.touched, 0);
	}
	
	@Override
	public boolean takeControl() {
		this.ts.fetchSample(touched,0);
		if(touched[0]==1) {
			return true;
		}
		return false;
	}
	
	@Override
	public void action() {
	
			this.pilot.setAngularSpeed(140);
			this.pilot.stop();
			Sound.beep();
			this.pilot.backward();
			
			LCD.clear();
			LCD.drawString("Collision Detected", 0, 2);
			Delay.msDelay(500);
			this.pilot.stop();
			Button.ENTER.waitForPressAndRelease();
			System.exit(0);
		}	
	
	@Override
	public void suppress() {
		this.suppressed=true;
	}
}