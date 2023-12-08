import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.Button; import lejos.hardware.lcd.LCD; import
  lejos.hardware.sensor.EV3ColorSensor; import lejos.robotics.SampleProvider;
  import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import
  lejos.hardware.port.SensorPort;
 

  public class Calibrate implements Behavior { 
	  private boolean isCalibrated = false; 
	  private BaseRegulatedMotor mLeft;
		private BaseRegulatedMotor mRight;
	  private EV3ColorSensor colour; 
	  private SampleProvider cp; 
	  private float[] clevel = new float[3];
	  private boolean supressed; 
	  float RedColourLevelMaxR = 0f; 
	  float RedColourLevelMaxG = 0f;
	  float RedColourLevelMaxB = 0f; 
	  float RedColourLevelMinR = 1f; 
	  float RedRAverage = 0;
	  float RedGAverage = 0;
	  float RedBAverage = 0;
	  float RedRTotal = 0;
	  float RedGTotal = 0;
	  float RedBTotal = 0;
	  float WhiteRAverage = 0;
	  float WhiteGAverage = 0;
	  float WhiteBAverage = 0;
	  float WhiteRTotal = 0;
	  float WhiteGTotal = 0;
	  float WhiteBTotal = 0;
	  float BlackRAverage = 0;
	  float BlackGAverage = 0;
	  float BlackBAverage = 0;
	  float BlackRTotal = 0;
	  float BlackGTotal = 0;
	  float BlackBTotal = 0;
	  float RedColourLevelMinG = 1f;
	  float RedColourLevelMinB = 1f; 
	  float WhiteColourLevelMaxR = 0f;
	  float WhiteColourLevelMaxG = 0f;
	  float WhiteColourLevelMaxB = 0f;
	  float WhiteColourLevelMinR = 1f; 
	  float WhiteColourLevelMinG = 1f;
	  float WhiteColourLevelMinB = 1f; 
	  float BlackColourLevelMaxR = 0f; 
	  float BlackColourLevelMaxG = 0f; 
	  float BlackColourLevelMaxB = 0f; 
	  float BlackColourLevelMinR = 1f; 
	  float BlackColourLevelMinG = 1f;
	  float BlackColourLevelMinB = 1f;
	  boolean isRedCalibrated = false;
	  boolean isWhiteCalibrated = false;
	  boolean isBlackCalibrated = false;
 
	  public Calibrate(EV3ColorSensor sensorarg, BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight) { 
		  this.supressed = supressed;
		  this.colour = sensorarg; 
		  this.mLeft = mLeft;
		  this.mRight = mRight;
		  cp = colour.getRGBMode(); 
		  
	  } //black,red white
  
  @Override 
  public boolean takeControl() { 
	  return (Button.RIGHT.isDown() && !isCalibrated); 
	  }
 
  @Override 
  public void action() { 
	  this.mLeft.synchronizeWith(new BaseRegulatedMotor[] {this.mRight});
      this.mLeft.startSynchronization();
     
      this.mLeft.stop();
      this.mRight.stop();
      this.mLeft.endSynchronization();
	  while (!isCalibrated) {
  LCD.drawString("Place on red", 0, 3);
  
  while (isRedCalibrated == false) {
	  for (int i = 0; i < 4; i++) {
		  Delay.msDelay(1500);
		  cp.fetchSample(clevel, 0);
		  if (clevel[0] > RedColourLevelMaxR) {
			  RedColourLevelMaxR = clevel[0];
			  RedRTotal += RedColourLevelMaxR;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[1] > RedColourLevelMaxG) {
			  RedColourLevelMaxG = clevel[1];
			  RedGTotal += RedColourLevelMaxG;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[2] > RedColourLevelMaxB) {
			  RedColourLevelMaxB = clevel[2];
			  RedBTotal += RedColourLevelMaxB;
		  }
		  cp.fetchSample(clevel, 0);
		  Delay.msDelay(1500);
		  if (clevel[0] < RedColourLevelMinR) {
			  RedColourLevelMinR = clevel[0];
			  RedRTotal += RedColourLevelMinR;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[1] < RedColourLevelMinG) {
			  RedColourLevelMinG = clevel[1];
			  RedGTotal += RedColourLevelMinG;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[2] < RedColourLevelMinB) {
			  RedColourLevelMinB = clevel[2];
			  RedBTotal += RedColourLevelMinB;
		  }
	  }
	  isRedCalibrated = true;
	  RedRAverage = RedRTotal / 6;
	  RedGAverage = RedGTotal / 6;
	  RedBAverage = RedBTotal / 6;
  }
 
  LCD.drawString("R Red Ave: " + RedRAverage, 0, 4);
  LCD.drawString("G Red Ave: " + RedGAverage, 0, 5);
  LCD.drawString("B Red Ave: " + RedBAverage, 0, 6);
  Delay.msDelay(3500);
  //white
  LCD.clear();
  LCD.drawString("Place on White", 0, 3);
  
  while (isWhiteCalibrated == false) {
	  for (int i = 0; i < 4; i++) {
		  Delay.msDelay(1500);
		  cp.fetchSample(clevel, 0);
		  if (clevel[0] > WhiteColourLevelMaxR) {
			  WhiteColourLevelMaxR = clevel[0];
			  WhiteRTotal += WhiteColourLevelMaxR;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[1] > WhiteColourLevelMaxG) {
			  WhiteColourLevelMaxG = clevel[1];
			  WhiteGTotal += WhiteColourLevelMaxG;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[2] > WhiteColourLevelMaxB) {
			  WhiteColourLevelMaxB = clevel[2];
			  WhiteBTotal += WhiteColourLevelMaxB;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[0] < WhiteColourLevelMinR) {
			  WhiteColourLevelMinR = clevel[0];
			  WhiteRTotal += WhiteColourLevelMinR;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[1] < WhiteColourLevelMinG) {
			  WhiteColourLevelMinG = clevel[1];
			  WhiteGTotal += WhiteColourLevelMinG;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[2] < WhiteColourLevelMinB) {
			  WhiteColourLevelMinB = clevel[2];
			  WhiteBTotal += WhiteColourLevelMinB;
		  }
	  }
	  isWhiteCalibrated = true;
	  WhiteRAverage = WhiteRTotal / 6;
	  WhiteGAverage = WhiteGTotal / 6;
	  WhiteBAverage = WhiteBTotal / 6;
  }
  
  LCD.drawString("R White Ave: " + WhiteRAverage, 0, 4);
  LCD.drawString("G White Ave: " + WhiteGAverage, 0, 5);
  LCD.drawString("B White Ave: " + WhiteBAverage, 0, 6);
  Delay.msDelay(3500);
  //Black
  LCD.clear();
  LCD.drawString("Place on Black", 0, 3);
  while (isBlackCalibrated == false) {
	  for (int i = 0; i < 4; i++) {
		  Delay.msDelay(1500);
		  cp.fetchSample(clevel, 0);
		  if (clevel[0] > BlackColourLevelMaxR) {
			  BlackColourLevelMaxR = clevel[0];
			  BlackRTotal += BlackColourLevelMaxR;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[1] > BlackColourLevelMaxG) {
			  BlackColourLevelMaxG = clevel[1];
			  BlackGTotal += BlackColourLevelMaxG;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[2] > BlackColourLevelMaxB) {
			  BlackColourLevelMaxB = clevel[2];
			  BlackBTotal += BlackColourLevelMaxB;
		  }
		  cp.fetchSample(clevel, 0);
		  //Delay.msDelay(1500);
		  if (clevel[0] < BlackColourLevelMinR) {
			  BlackColourLevelMinR = clevel[0];
			  BlackRTotal += BlackColourLevelMinR;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[1] < BlackColourLevelMinG) {
			  BlackColourLevelMinG = clevel[1];
			  BlackGTotal += BlackColourLevelMinG;
		  }
		  cp.fetchSample(clevel, 0);
		  if (clevel[2] < BlackColourLevelMinB) {
			  BlackColourLevelMinB = clevel[2];
			  BlackBTotal += BlackColourLevelMinB;
		  }
	  }
	  isBlackCalibrated = true;
	  LCD.clear();
	  BlackRAverage = BlackRTotal / 6;
	  BlackGAverage = BlackGTotal / 6;
	  BlackBAverage = BlackBTotal / 6;
  }
  

  LCD.drawString("BlackR: " + BlackRAverage, 0, 4);
  LCD.drawString("BlackG: " + BlackGAverage, 0, 5);
  LCD.drawString("BlackB: " + BlackBAverage, 0, 6);
  Delay.msDelay(3500);
  LCD.clear();
  isCalibrated = true; 
  
	  }
  	} 
  
  
  
  
  @Override 
  public void suppress() {
	  this.supressed = true;
	  }
  
  }
