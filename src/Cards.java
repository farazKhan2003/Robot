import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import lejos.hardware.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.robotics.subsumption.Behavior;

public class Cards implements Behavior {
    private boolean supressed;
    private boolean finished=true;
    private EV3ColorSensor coloursensor;
    BaseRegulatedMotor mL;
	BaseRegulatedMotor mR; 
	float[] cLevel = new float[3];
	SampleProvider color;
	 
    public Cards (BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, EV3ColorSensor coloursensorarg) { 
       this.coloursensor = coloursensorarg;
       this.mL = mLeft;
       this.mR = mRight;
       this.finished = finished;
       this.supressed = supressed;
       this.color = this.coloursensor.getRGBMode();
       
    }


   @Override
	public boolean takeControl() {
	   
		color.fetchSample(cLevel, 0);
		if ((cLevel[0] > 0.32 && cLevel[0] < 0.42) && (cLevel[1] > 0.05 
				&& cLevel[1] < 0.075) && (cLevel[2] > 0.025 && cLevel[2] < 0.04)
				&& this.finished == true) {
            return true;
        }
		return false;
    }

	@Override
    public void action() {
    //If red returns true
			this.finished=false;
            this.mL.endSynchronization();
            this.mL.synchronizeWith(new BaseRegulatedMotor[] {this.mR});
            this.mL.startSynchronization();
           
            this.mL.stop();
            this.mR.stop();
            this.mL.endSynchronization();
            Delay.msDelay(3000);
            this.mL.synchronizeWith(new BaseRegulatedMotor[] {this.mR});
            this.mL.startSynchronization();
            this.mL.setSpeed(140); //2 revolutions per second(RPS)
            this.mR.setSpeed(140);
            this.mL.forward();
            this.mR.forward();
            this.mL.endSynchronization();
            Delay.msDelay(2000);
            this.finished=true;
        }
   



@Override
        public void suppress() {
            this.supressed = true;}



    }