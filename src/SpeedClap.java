import lejos.hardware.Sound;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class SpeedClap implements Behavior {
	final static float WHEEL_DIAMETER = 56; final static float AXLE_LENGTH = 140;
	private boolean suppressed;
	private MovePilot pilot;
	BaseRegulatedMotor mL;
	BaseRegulatedMotor mR; 

	NXTSoundSensor soundsen = new NXTSoundSensor(SensorPort.S1);
    float[] soundLevel = new float[1];
    float currentsoundLevel;
    SampleProvider ss;
    
	
	public SpeedClap(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, MovePilot pilot) {
		this.mL = mLeft;
		this.mR = mRight;
		this.pilot=pilot;
		this.pilot.setAngularSpeed(200);
		this.pilot.setAngularSpeed(200);
		SensorMode sound = (SensorMode) soundsen.getDBAMode();
		this.ss = new ClapFilter(sound, 0.3f, 100);
		
	}
	
	@Override
	public boolean takeControl() {
		this.ss.fetchSample(soundLevel,0);
		if(soundLevel[0]==1) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public void action() {
		Sound.beepSequence();
		this.pilot.setLinearSpeed(200);
		this.pilot.setAngularSpeed(200);
		this.pilot.travel(150);
		
	}
	
	@Override
	public void suppress() {
		this.suppressed=true;
	}
	
	
	
	
	
}


