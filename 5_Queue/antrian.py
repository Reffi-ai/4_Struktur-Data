import tkinter as tk              # Library untuk GUI
from tkinter import messagebox   # Untuk popup pesan
import subprocess                # Untuk menjalankan PowerShell (suara)

# ================= KONVERSI ANGKA =================
# Fungsi untuk mengubah angka menjadi kata (Bahasa Indonesia)
def angka_ke_kata(n):
    angka = ["nol", "satu", "dua", "tiga", "empat",
             "lima", "enam", "tujuh", "delapan", "sembilan", "sepuluh"]

    if n <= 10:
        return angka[n]  # langsung ambil dari list
    elif n < 20:
        return angka[n - 10] + " belas"  # contoh: 11 = satu belas
    elif n < 100:
        return angka[n // 10] + " puluh " + angka[n % 10]  # contoh: 25 = dua puluh lima
    elif n < 200:
        return "seratus " + angka_ke_kata(n - 100)
    elif n < 1000:
        return angka[n // 100] + " ratus " + angka_ke_kata(n % 100)
    else:
        return str(n)  # jika angka besar, fallback ke angka biasa

# ================= NODE =================
# Node digunakan untuk menyimpan data dalam linked list
class Node:
    def __init__(self, nomor, nama):
        self.nomor = nomor  # nomor antrian
        self.nama = nama    # nama pasien
        self.next = None    # pointer ke node berikutnya

# ================= QUEUE =================
# Implementasi Queue menggunakan Linked List
class Queue:
    def __init__(self):
        self.front = None   # elemen depan (yang akan dipanggil)
        self.rear = None    # elemen belakang (yang terakhir masuk)
        self.counter = 0    # penghitung nomor antrian

    # Menambahkan data ke antrian (enqueue)
    def enqueue(self, nama):
        self.counter += 1  # nomor antrian bertambah
        baru = Node(self.counter, nama)

        if self.rear is None:
            # jika antrian kosong
            self.front = self.rear = baru
        else:
            # sambungkan node baru ke belakang
            self.rear.next = baru
            self.rear = baru

    # Menghapus data dari antrian (dequeue)
    def dequeue(self):
        if self.front is None:
            return None  # antrian kosong

        temp = self.front
        self.front = self.front.next

        # jika setelah dihapus jadi kosong
        if self.front is None:
            self.rear = None

        return temp

    # Mengambil semua data untuk ditampilkan di listbox
    def get_all(self):
        data = []
        temp = self.front
        while temp:
            data.append((temp.nomor, temp.nama))
            temp = temp.next
        return data

# ================= INIT =================
queue = Queue()  # membuat objek queue

# ================= GUI =================
root = tk.Tk()
root.title("Sistem Antrian Puskesmas")
root.geometry("800x600")
root.configure(bg="#f0f4f7")

# ===== TITLE =====
tk.Label(root, text="SISTEM ANTRIAN PUSKESMAS",
         font=("Arial", 22, "bold"),
         bg="#f0f4f7").pack(pady=10)

# ===== LISTBOX (menampilkan daftar antrian) =====
frame_list = tk.Frame(root)
frame_list.pack(pady=10)

scrollbar = tk.Scrollbar(frame_list)
scrollbar.pack(side=tk.RIGHT, fill=tk.Y)

listbox = tk.Listbox(frame_list,
                     width=60,
                     height=12,
                     font=("Arial", 14),
                     yscrollcommand=scrollbar.set)

listbox.pack(side=tk.LEFT, fill=tk.BOTH)
scrollbar.config(command=listbox.yview)

# ===== INPUT NAMA =====
frame_input = tk.Frame(root, bg="#f0f4f7")
frame_input.pack(pady=10)

tk.Label(frame_input, text="Nama:", bg="#f0f4f7").grid(row=0, column=0)

entry = tk.Entry(frame_input, width=30)
entry.grid(row=0, column=1, padx=5)

# ===== NOMOR BESAR (yang sedang dipanggil) =====
label_nomor = tk.Label(root, text="-",
                       font=("Arial", 60, "bold"),
                       fg="red", bg="#f0f4f7")
label_nomor.pack()

# ===== INFO TEXT =====
label_call = tk.Label(root, text="Belum ada panggilan",
                      font=("Arial", 16, "bold"),
                      fg="blue", bg="#f0f4f7")
label_call.pack()

label_jumlah = tk.Label(root, text="Jumlah Antrian: 0",
                        bg="#f0f4f7")
label_jumlah.pack()

# ================= FUNCTIONS =================

# Update tampilan list antrian
def update_list():
    listbox.delete(0, tk.END)  # kosongkan listbox

    data = queue.get_all()
    for nomor, nama in data:
        listbox.insert(tk.END, f"No {nomor} - {nama}")

    # auto scroll ke bawah
    if len(data) > 0:
        listbox.see(tk.END)

    # update jumlah antrian
    label_jumlah.config(text=f"Jumlah Antrian: {queue.counter}")

# Fungsi untuk ambil nomor antrian
def ambil_antrian():
    nama = entry.get()

    # validasi input
    if not nama.strip():
        messagebox.showwarning("Warning", "Nama tidak boleh kosong")
        return

    queue.enqueue(nama)      # masukkan ke queue
    entry.delete(0, tk.END) # kosongkan input
    update_list()

# Fungsi untuk memanggil antrian
def panggil():
    data = queue.dequeue()

    if data:
        nomor_huruf = angka_ke_kata(data.nomor)

        # pesan yang akan dibacakan
        msg = f"Nomor {nomor_huruf}, atas nama {data.nama}, silakan menuju loket"

        # update tampilan
        label_call.config(text=f"Memanggil: {msg}")
        label_nomor.config(text=str(data.nomor))

        # 🔊 SUARA (DING DONG + TTS)
        try:
            subprocess.Popen([
                "powershell",
                "-Command",
                f'''
                [console]::beep(1000,300);           # ding
                Start-Sleep -Milliseconds 150;
                [console]::beep(800,300);            # dong
                Start-Sleep -Milliseconds 300;

                Add-Type -AssemblyName System.Speech;
                $speak = New-Object System.Speech.Synthesis.SpeechSynthesizer;
                $speak.Speak("{msg}");               # suara panggilan
                '''
            ])
        except Exception as e:
            print("Error suara:", e)

        # 💾 simpan log ke file
        try:
            with open("antrian_log.txt", "a") as f:
                f.write(f"{data.nomor} - {data.nama}\n")
        except:
            print("Error simpan file")

        update_list()

    else:
        messagebox.showinfo("Info", "Antrian kosong")

# Reset semua antrian
def reset():
    queue.front = None
    queue.rear = None
    queue.counter = 0

    label_nomor.config(text="-")
    label_call.config(text="Belum ada panggilan")

    update_list()

# ===== BUTTON =====
frame_btn = tk.Frame(root, bg="#f0f4f7")
frame_btn.pack(pady=10)

tk.Button(frame_btn, text="Ambil Antrian",
          command=ambil_antrian,
          bg="#4CAF50", fg="white",
          width=20).grid(row=0, column=0, padx=5)

tk.Button(frame_btn, text="Panggil",
          command=panggil,
          bg="#f44336", fg="white",
          width=20).grid(row=0, column=1, padx=5)

tk.Button(frame_btn, text="Reset",
          command=reset,
          bg="gray", fg="white",
          width=20).grid(row=0, column=2, padx=5)

# ===== RUN PROGRAM =====
root.mainloop()
