import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class FollowLine implements Behavior{
	private boolean suppressed;
	
	private float[] cLevel;
	private float maxColorLevel;
	private float minColorLevel;
	private float avColorLevel;
	private EV3ColorSensor cs;
	private SampleProvider color;
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	
	public FollowLine(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, EV3ColorSensor coloursensorarg) {
		
		//Initialisation/calibration
		
		this.suppressed = false;
		this.cLevel = new float[3];
		this.cs = coloursensorarg;
		this.color = cs.getRGBMode();
		this.mLeft = mLeft;
		this.mRight = mRight;
	
		color.fetchSample(cLevel, 0);
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void suppress() {
		this.suppressed = true;
	}
	
	@Override
	public void action() {
		//moves right if its black 
		//left if its white
			mLeft.setSpeed(140);
			mRight.setSpeed(140);
	
			color.fetchSample(cLevel, 0);
			if (cLevel[0] < 0.1) {
				mLeft.flt();
				mRight.forward();
				
			}
			else {
				mRight.flt();
				mLeft.forward();
			}
		
	}
}