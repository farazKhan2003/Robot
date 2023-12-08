  import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Sound;
  import lejos.hardware.lcd.LCD;
  import lejos.robotics.subsumption.Behavior; 
  import lejos.utility.Delay;
  
  //DRAWSTRING CHARGE% TO SCREEN
  
  public class BatteryLvl implements Behavior { 
	  private float MIN_CHARGE;
	  private boolean Supressed;
  
  public BatteryLvl(float volts) { 
	  this.Supressed = Supressed;
	  MIN_CHARGE = volts; 
	  }
  @Override 
  public boolean takeControl() { 
	  float batteryLevel = Battery.getVoltageMilliVolt();
	  if (batteryLevel < MIN_CHARGE) {
		  return true;
	  }
	  return false;
	  }
  
  @Override
  public void action() { 
	  Sound.beepSequenceUp();
  LCD.drawString("Battery Low", 5, 5);
  Delay.msDelay(3000);
  LCD.clear();
  Button.ENTER.waitForPressAndRelease();
  System.exit(0);
  
  
  
  }
  
  @Override public void suppress() {
	  this.Supressed = true; 
  }
  
  
  
  }
 