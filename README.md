
# 🐦️ AMAL HAWKS 2025 ROBOT CODE


## 🚀 What Can It Do?

### 📌 Command Groups:

- Intake **Coral** from Source
- Intake **Algae** from Reefscape (Up and Down Levels)
- Score **Coral** to **L1, L2, L3, L4**
- Score **Algae** to **Net**
- Score **Algae** to **Processor**

### 🔧 Extra Features:

- **Easy to Tune** – Everything runs from **Constants**
- **Fully Controllable** – All subsystems can be easily controlled
- **Simple Pathmaking** – Uses **Sequential Command Groups** for ease of use

---

# 🎮 TELE-OP

### 🎛 Controller Mapping

#### **Manual Control**
- 🎮 **Left Stick / Right Stick** → **Swerve Drive**
- 🎮 **L2** → Move Elevator **Down** (Manual Tuning)
- 🎮 **R2** → Move Elevator **Up** (Manual Tuning)
- 🎮 **L1** → Move **Intake Down**
- 🎮 **R1** → Move **Intake Up**

#### **Algae Control**
- 🎮 **A** → Score to **Processor**
- 🎮 **Y** → Score to **Net**

#### **Intake Control**
- 🎮 **B** → Intake **Algae (Upper Level)**
- 🎮 **X** → Intake **Algae (Lower Level)**
- 🎮 **B10** → Intake **Coral from Source**

#### **Scoring Control**
- 🎮 **PovDown** → Score to **L1**
- 🎮 **PovLeft** → Score to **L2**
- 🎮 **PovUp** → Score to **L3**
- 🎮 **PovRight** → Score to **L4**

---

# 📂 Code Structure

## **🛠 Subsystems**

### 🔹 **SwerveSubsystem**
- Uses **WPILib kinematics algorithm**
- Enhanced autonomous stability and speed using **Second Order Kinematics**
- Implements **odometry, pose estimation, and SwerveStates** for perfect **autonomous movement** and **smooth tele-op**

### 🔹 **StatusLED**
- Uses **AddressableLED** and **LedBuffer** system
- Displays **Limelight Focus, Intake Status, Processing Status**

### 🔹 **ElevatorSubsystem**
- Powered by **two Kraken X60 motors**
- Uses a custom PID system called **OcalPID**, designed around motor **position** instead of speed
- Optimized for Kraken's **high encoder resolution**

### 🔹 **IntakeMoverSubsystem**
- Similar in function to **ElevatorSubsystem**

### 🔹 **IntakeSubsystem**
- A regular **intake** but with enhanced control features
- Allows tuning of **Intake Time, Intake Speed, and Outtake Speed**

### 🔹 **AlgaeIntakeSubsystem**
- Identical to **IntakeSubsystem**, but optimized for **Algae intake**

---

# 🤖 Autonomous Mode

### 🔄 **Dynamic Planning**
- Allows last-minute adjustments before a match
- Prevents collisions by adapting slot assignments
- Each **coral slot** is numbered, enabling flexible path planning

## **📌 How It Works**

1. **Coral Slots & Paths**
   - Each **coral slot (L1, L2, L3, etc.)** is pre-defined
   - Paths are designed with placeholders for easy reassignment

2. **Collision Avoidance**
   - If an alliance partner is targeting the same slot, reassigning slots prevents overlap
   - Example: Changing `M3 → M4` avoids conflicts

3. **Last-Minute Adjustments**
   - Select which corals to fill before the match
   - Modifying a command in `SequentialGroup` dynamically changes the firing location
   - Example: Changing `L3 → L4` redirects the coral to `L4`

---

## **💻 Example Code Adjustment**

### 📝 Original Path:

![Original Path](https://i.ibb.co/NnGpsJMm/image.png)
### ✨ Modified Path:

![ModifiedPath](https://i.ibb.co/fVVF3bSX/image.png)
---

🔥 **Amal Hawks 2025 is ready to dominate the competition!** 🚀
