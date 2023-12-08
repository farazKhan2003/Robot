import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

//when escape is pressed system.exit();
public class EmergencyStop implements Behavior {

	private boolean supressed;
	public EmergencyStop() {
		this.supressed = false;
	}

	@Override
	public boolean takeControl() {
		return Button.ESCAPE.isDown();
	}

	@Override
	public void action() {
		Sound.twoBeeps();
		Sound.beepSequence();
		System.exit(0);
		
	}

	@Override
	public void suppress() {
		this.supressed = true;
		
	}
}
