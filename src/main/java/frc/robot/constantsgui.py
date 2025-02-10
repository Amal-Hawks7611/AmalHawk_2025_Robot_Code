import re
import tkinter as tk
import os
from tkinter import ttk, messagebox

# Düzenlenecek sabitler (anahtarlar) ve hangi sekmede yer aldıkları:
ENCODER_KEYS = {
    # Elevator (double)
    "ELEVATOR_START_VALUE": "Elevator",
    "ELEVATOR_END_VALUE": "Elevator",
    "ELEVATOR_SOURCE_VALUE": "Elevator",
    "ELEVATOR_PROCESSOR_VALUE": "Elevator",
    "ELEVATOR_REEFSCAPE_VALUE": "Elevator",
    "ELEVATOR_TOZERO_VALUE": "Elevator",
    "ELEVATOR_L1_VALUE": "Elevator",
    "ELEVATOR_L2_VALUE": "Elevator",
    "ELEVATOR_L3_VALUE": "Elevator",
    "ELEVATOR_L4_VALUE": "Elevator",
    "ELEVATOR_ALGEA_VALUE_DOWN": "Elevator",
    "ELEVATOR_ALGEA_VALUE_MIDDLE": "Elevator",
    # IntakeMover (double)
    "INTAKE_PROCESSOR_VALUE": "IntakeMover",
    "INTAKE_SOURCE_VALUE": "IntakeMover",
    "INTAKE_ALGEA_VALUE": "IntakeMover",
    "INTAKE_REEFSCAPE_VALUE": "IntakeMover",
    "INTAKE_CORAL_VALUE": "IntakeMover",
    "INTAKE_L4_VALUE": "IntakeMover",
}

PORTS_KEYS = {
    # SwervePorts (int)
    "FRONT_LEFT_DRIVE_MOTOR": "SwervePorts",
    "FRONT_RIGHT_DRIVE_MOTOR": "SwervePorts",
    "BACK_LEFT_DRIVE_MOTOR": "SwervePorts",
    "BACK_RIGHT_DRIVE_MOTOR": "SwervePorts",
    "FRONT_LEFT_STEER_MOTOR": "SwervePorts",
    "FRONT_RIGHT_STEER_MOTOR": "SwervePorts",
    "BACK_LEFT_STEER_MOTOR": "SwervePorts",
    "BACK_RIGHT_STEER_MOTOR": "SwervePorts",
    "FRONT_LEFT_CANCODER": "SwervePorts",
    "FRONT_RIGHT_CANCODER": "SwervePorts",
    "BACK_LEFT_CANCODER": "SwervePorts",
    "BACK_RIGHT_CANCODER": "SwervePorts",
    # Other ports (int)
    "ELEVATOR_LEADER_MOTOR_PORT": "Elevator",
    "ELEVATOR_FOLLOWER_MOTOR_PORT": "Elevator",
    "INTAKE_LEADER_MOTOR_PORT": "Intake", 
    "ALGEA_LEADER_MOTOR_PORT": "AlgeaIntake",
    "IM_LEADER_MOTOR_PORT": "IntakeMover",

    "POWER_DIO_PORT": "LedSubsystem",
    "PWM_RED": "LedSubsystem",
    "PWM_BLUE": "LedSUbsystem",
    "PWM_GREEN": "LedSubsystem",

    "DT_CHANNEL": "RotarySwitch",
    "CLK_CHANNEL": "RotarySwitch",
    "SW_CHANNEL": "RotarySwitch"
}

CONTROLS_KEYS = {
    "ELEVATOR_MANUAL_UP": "Controlls",
    "ELEVATOR_MANUAL_DOWN": "Controlls",
    "INTAKE_MOVE_UP": "Controlls",
    "INTAKE_MOVE_DOWN": "Controlls",
    "ALGEA_PROCESSOR": "Controlls",
    "ALGEA_INTAKE_MIDDLE": "Controlls",
    "ALGEA_INTAKE_DOWN": "Controlls",
    "L1": "Controlls",
    "L2": "Controlls",
    "L3": "Controlls",
    "L4": "Controlls",
    "GET_SOURCE": "Controlls"
}
script_dir = os.path.dirname(os.path.abspath(__file__))
os.chdir(script_dir)
CONSTANTS_FILE = os.getcwd() + "/Constants.java"

def load_file(filename):
    with open(filename, "r", encoding="utf-8") as f:
        return f.read()

def get_constant_value(file_text, key):
    pattern = r"public\s+static\s+(?:final\s+)?(?:double|int)\s+" + re.escape(key) + r"\s*=\s*([^;]+);"
    if key in CONTROLS_KEYS:
        pattern = rf"\bpublic\s+static\s+Trigger\s+{key}\s*=\s*(.+);"
    match = re.search(pattern, file_text)
    if match:
        return match.group(1).strip()
    return ""

def update_constant_value(file_text, key, new_value):
    # Sayısal değerler (double, int)
    pattern = r"(public\s+static\s+(?:final\s+)?(?:double|int)\s+" + re.escape(key) + r"\s*=\s*)([^;]+)(;)"
    
    if key in CONTROLS_KEYS:  # Eğer key bir kontrol butonuysa
        pattern = rf"(public\s+static\s+Trigger\s+{key}\s*=\s*)([^;]+)(;)"
    
    # Değişkeni güncelle
    new_text, count = re.subn(pattern, lambda m: m.group(1) + new_value + m.group(3), file_text)
    
    if count == 0:
        print(f"Key {key} bulunamadı.")
    
    return new_text


class ConstantsEditorGUI:
    def __init__(self, master, file_text):
        self.master = master
        self.file_text = file_text
        self.entries = {} 
        
        self.notebook = ttk.Notebook(master)
        self.notebook.pack(fill="both", expand=True)
        
        self.create_encoder_tab()
        self.create_ports_tab()
        self.create_controls_tab()
        
        save_btn = tk.Button(master, text="Save All Changes", command=self.save_all)
        save_btn.pack(pady=10)
    
    def create_encoder_tab(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text="Encoder Hesaplamaları")
        
        # Elevator alanı
        elevator_frame = ttk.LabelFrame(frame, text="Elevator")
        elevator_frame.pack(fill="x", padx=10, pady=5)
        row = 0
        for key in ENCODER_KEYS:
            if ENCODER_KEYS[key] == "Elevator":
                tk.Label(elevator_frame, text=key+":").grid(row=row, column=0, sticky="e", padx=5, pady=2)
                val = get_constant_value(self.file_text, key)
                entry = tk.Entry(elevator_frame)
                entry.insert(0, val)
                entry.grid(row=row, column=1, sticky="we", padx=5, pady=2)
                self.entries[key] = entry
                row += 1
        elevator_frame.columnconfigure(1, weight=1)
        
        intakemover_frame = ttk.LabelFrame(frame, text="Intake Mover")
        intakemover_frame.pack(fill="x", padx=10, pady=5)
        row = 0
        for key in ENCODER_KEYS:
            if ENCODER_KEYS[key] == "IntakeMover":
                tk.Label(intakemover_frame, text=key+":").grid(row=row, column=0, sticky="e", padx=5, pady=2)
                val = get_constant_value(self.file_text, key)
                entry = tk.Entry(intakemover_frame)
                entry.insert(0, val)
                entry.grid(row=row, column=1, sticky="we", padx=5, pady=2)
                self.entries[key] = entry
                row += 1
        intakemover_frame.columnconfigure(1, weight=1)
    
    def create_ports_tab(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text="Portlar")
        
        # SwervePorts alanı
        swerve_frame = ttk.LabelFrame(frame, text="Swerve Ports")
        swerve_frame.pack(fill="x", padx=10, pady=5)
        row = 0
        for key in PORTS_KEYS:
            if PORTS_KEYS[key] == "SwervePorts":
                tk.Label(swerve_frame, text=key+":").grid(row=row, column=0, sticky="e", padx=5, pady=2)
                val = get_constant_value(self.file_text, key)
                entry = tk.Entry(swerve_frame)
                entry.insert(0, val)
                entry.grid(row=row, column=1, sticky="we", padx=5, pady=2)
                self.entries[key] = entry
                row += 1
        swerve_frame.columnconfigure(1, weight=1)
        
        other_frame = ttk.LabelFrame(frame, text="Other Ports")
        other_frame.pack(fill="x", padx=10, pady=5)
        row = 0
        for key in PORTS_KEYS:
            if PORTS_KEYS[key] != "SwervePorts":
                tk.Label(other_frame, text=key+":").grid(row=row, column=0, sticky="e", padx=5, pady=2)
                val = get_constant_value(self.file_text, key)
                entry = tk.Entry(other_frame)
                entry.insert(0, val)
                entry.grid(row=row, column=1, sticky="we", padx=5, pady=2)
                self.entries[key] = entry
                row += 1
        other_frame.columnconfigure(1, weight=1)
    
    def create_controls_tab(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text="Kontroller")

        options = ["leftBumper()", "rightBumper()", "x()", "a()", "b()", "y()", "povUp()", "povDown()", "povLeft()", "povRight()",
                   "rightTrigger()", "leftTrigger()"]  # Seçenekler

        row = 0
        for key in CONTROLS_KEYS:
            tk.Label(frame, text=key + ":").grid(row=row, column=0, sticky="e", padx=5, pady=2)

            # Constants.java içindeki değeri oku ve DRIVER_CONTROLLER. kısmını temizle
            current_value = get_constant_value(self.file_text, key)
            if current_value:
                current_value = current_value.replace("DRIVER_CONTROLLER.", "").strip()  # DRIVER_CONTROLLER.'ı kaldır

            # Eğer mevcut değer listede varsa onu seç, yoksa varsayılanı seç
            selected_value = current_value if current_value in options else options[0]

            combo = ttk.Combobox(frame, values=options)
            combo.set(selected_value)  # Seçili değer olarak ayarla

            combo.grid(row=row, column=1, sticky="we", padx=5, pady=2)
            self.entries[key] = combo  # Combobox'u entries içine ekle

            row += 1
        frame.columnconfigure(1, weight=1)


    
    def save_all(self):
        global CONSTANTS_FILE
        new_text = self.file_text
        for key, widget in self.entries.items():
            new_val = widget.get().strip()
            if key in CONTROLS_KEYS:  
                new_val = f"DRIVER_CONTROLLER.{new_val}"  # Kontroller için DRIVER_CONTROLLER ekle
            new_text = update_constant_value(new_text, key, new_val)
        try:
            with open(CONSTANTS_FILE, "w", encoding="utf-8") as f:
                f.write(new_text)
            messagebox.showinfo("Başarılı", "Constants.java dosyası güncellendi.")
            self.file_text = new_text
        except Exception as ex:
            messagebox.showerror("Hata", str(ex))


if __name__ == "__main__":
    file_content = load_file(CONSTANTS_FILE)
    root = tk.Tk()
    root.title("Constants.java Editor")
    app = ConstantsEditorGUI(root, file_content)
    root.mainloop()
