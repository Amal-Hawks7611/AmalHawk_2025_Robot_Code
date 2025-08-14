
# 🐦️ AMAL HAWKS 2025 ROBOT CODE


## 🚀 What Can It Do?

### 📌 Command Groups:

- Intake **Coral** from Source
- Intake **Algae** from Reefscape (Up and Down Levels)
- Score **Coral** to **L1, L2, L3, L4**
- Score **Algae** to **Processor**

### 🔧 Extra Features:

- **Easy to Tune** – Everything runs from **Constants**
- **Fully Controllable** – All subsystems can be easily controlled
- **Simply Operatable** – Uses **Sequential Command Groups** for ease of use

---

# 🎮 TELE-OP

### 🎛 Controller Mapping

#### **Manual Control**
- 🎮 **Left Stick / Right Stick** → **Swerve Drive**
- 🎮 **L2** → Move Elevator **Down**
- 🎮 **R2** → Move Elevator **Up**
- 🎮 **L1** → Move **Intake Down**
- 🎮 **R1** → Move **Intake Up**

#### **Algae Control**
- 🎮 **PS** → Score to **Processor**

#### **Intake Control**
- 🎮 **CREATE** → Intake **Algae (Upper Level)**
- 🎮 **SQUARE** → Intake **Coral from Source**

#### **Scoring Control**
- 🎮 **OPTIONS** → Score to **L1**
- 🎮 **CIRCLE** → Score to **L2**
- 🎮 **CROSS** → Score to **L3**
- 🎮 **TRIANGLE** → Score to **L4**

---

# 📂 Code Structure

## **🛠 Subsystems**

### 🔹 **SwerveSubsystem**
- Uses modified version of **YAGSL** for working pathplanner

### 🔹 **StatusLED**
- Uses **AddressableLED** and **LedBuffer** system
- Displays **Limelight Focus, Intake Status, Processing Status**

### 🔹 **ElevatorSubsystem**
- Powered by **two Kraken X60 motors**
- Uses a custom PID system called **OcalPID**, designed around motor **position** instead of speed
- Optimized for Kraken's **high encoder resolution**
- Used static voltage solution for **more brake power**

### 🔹 **IntakeMoverSubsystem**
- Similar in function to **ElevatorSubsystem**

### 🔹 **IntakeSubsystem**
- A regular **intake** but with enhanced control features
- Allows tuning of **Intake Time, Intake Speed, and Outtake Speed**
- Uses **static voltage** for gamepiece fall protection.

### 🔹 **AlgaeIntakeSubsystem**
- Identical to **IntakeSubsystem**, but optimized for **Algae intake**


---
🔥 **Amal Hawks 2025 is ready to dominate the competition!** 🚀
