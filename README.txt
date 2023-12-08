README

Driver
This class defines the order of priority of the behaviour classes by using an arbitrator and shows the welcome screen when the program is run. It also creates the pilot which is used later within the behaviour classes. It also displays a happy face which will constantly run unless overtaken by honk

BatteryLevel
When the battery level drops below a certain level, the LCD displays a message to show this, stop, and the program will stop. This class uses the pilot.

EmergencyStop
When the escape button is pressed, the robot will sound some beeps, stop, and then the program will stop. 

Collision
When the touch sensor is pressed (the robot has collided with something), the robot will beep, display a message saying, ‘collision detected’, reverse then stop. Once the enter button has been pressed the program will stop. This sensor uses the touch sensor and pilot.

Calibrate
This class allows all the values to determine the colours to be determined which are used later in the program when the other behaviours are activated. This sensor requires the colour sensor.

Cards
When the robot drives over a red spot, it will pause for a moment before carrying on moving. This sensor uses the colour sensor and pilot.

SpeedClap
When the robot detects a clap, it will speed up. This class uses the sound sensor and pilot.

ClapFilter
This will determine whether the SpeedClap behaviour has heard a clap and within a correct time limit so it cannot constantly detect clapping if someone rapidly claps.

Honk 
When the enter button is pressed on the robot, it will beep and display an angry face temporarily before returning the happy face shown before.

FollowLine
Throughout the program the robot will follow a black line circuit. Throughout this the class will determine whether the robot is going over a black or white line by moving left and right depending on the colour it sees so it doesn’t go off course. This sensor uses the colour sensor and pilot.
