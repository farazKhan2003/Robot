import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Honk implements Behavior{
	private boolean Supressed;
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	private EV3ColorSensor colorsens;
	
	public Honk(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, EV3ColorSensor colorsens) { 
		this.Supressed = false;
		this.mLeft = mLeft;
		this.colorsens = colorsens;
		this.mRight = mRight;
		
	}


	@Override
	public boolean takeControl() {return (Button.ENTER.isDown());}
		

@Override
	public void action() {
		Sound.beepSequenceUp();
		LCD.clear();
		LCD.drawString(">:(", 5, 2);
	   
		
		Delay.msDelay(1500);
	    LCD.clear();
	    LCD.drawString(":D", 5, 2);

		
	}

	@Override
	public void suppress() {
		this.Supressed = true;	
	}
	
	
	
}