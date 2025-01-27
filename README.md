
#  AMAL HAWKS 2025 ROBOT CODE

  

#  What Can It Do?

##  Commdand Groups:

  

- Intake Coral From Source

- Intake Algea From Reefscape Up And Down Levels

- Score Coral to L1, L2, L3 , L4

- Score Algea to Net

- Score Algea to Processor

  

##  Extra Feautures:

  

- Easy to Tune. Everything is Running From Constants

- All Subsystems can Easily Controlled.

- Easy Pathmaking with Sequential Command Groups.

##  Control Assignment(Will Be Changed)

### Controller

**Manual Controll**
- **Sticks** = SwerveDrive

- **L2** = Move Elevator Down Manually (For Tuning)
- **R2** = Move Elevator Up Manually (For Tuning)
- **L1** = Move Intake Down
- **R1** = Move Intake Up

**Algea Controll**
- **A** = Processor

- **Y** = Net

**Intake Controll**

- **PovUp** = Algea Intake From Up Level

- **PovDown** = Algea Intake From Down Level

- **PovLeft** = Coral Intake From Source

- **PovRight** = Led Disco Mode!

**Logitech 3D Joystick(As A Second Controller)**
**Manual Controll**
- **Y axis** = Move Elevator

**Score Controll**
- **B7** = L1
- **B8** = L2
- **B9** = L3
- **B10** = L4
- **B11** = Reset NavX

## Code Structure
### Subsystems

- **SwerveSubsystem:**
Used only WPILib kinematics algorithm. Enhanced autonomous stability and speed manipulated with advanced kinematics equation called **Second Order Kinematics.** Used **odometry, pose estimating and SwerveStates** for perfect autonomous and smooth tele-op.

- **StatusLED**
 Used AdressableLED and LedBuffer system. It's capable of showing **Limelight Focus, Intake Situation, Process Situation**

- **ElevatorSubsystem**
Used two KrakenX60 motors. I've created a special PIDControll system called **OcalPID** based on motor position instead of motor speed. My point of creating that was kraken's perfect encoder resolution.

- **IntakeMoverSubsystem**
There isn't any big difference from **ElevatorSubsystem.**

- **IntakeSubsystem**
Just a regular intake but it's more controllable. You can controll: **IntakeTime, intakeSpeed and outtakeSpeed**

- **AlgeaIntakeSubsystem**
Same code with IntakeSubsystem but **made for algea intake constants.**