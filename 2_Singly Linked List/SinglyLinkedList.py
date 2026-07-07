# Kelas Node untuk menyimpan data mahasiswa
class Node:
    def __init__(self, nim, nama):
        self.nim = nim        # Menyimpan NIM mahasiswa
        self.nama = nama      # Menyimpan nama mahasiswa
        self.next = None      # Pointer ke node berikutnya

# Head adalah node pertama dalam linked list
head = None

# Variabel untuk menghitung jumlah data/node
count = 0

# =========================
# FUNGSI VALIDASI TAMBAHAN
# =========================

# Mengecek apakah NIM hanya angka
def is_nim_valid(nim):
    return nim.isdigit()  # True jika semua karakter angka

# Mengecek apakah nama hanya huruf dan boleh spasi
def is_nama_valid(nama):
    return nama.replace(" ", "").isalpha()  # Hilangkan spasi lalu cek huruf

# Mengecek apakah NIM atau Nama sudah ada (tidak boleh duplikat)
def is_duplicate(nim, nama):
    global head
    bantu = head
    while bantu:
        if bantu.nim == nim or bantu.nama.lower() == nama.lower():
            return True  # Ditemukan data yang sama
        bantu = bantu.next
    return False

# =========================
# INSERT DATA
# =========================

def insert_beginning():
    global head, count
    nim = input("Masukkan NIM  : ")
    nama = input("Masukkan Nama : ")

    # Validasi input
    if not is_nim_valid(nim):
        print("Error: NIM harus berupa angka!")
        return
    if not is_nama_valid(nama):
        print("Error: Nama harus berupa huruf!")
        return
    if is_duplicate(nim, nama):
        print("Error: NIM atau Nama sudah ada!")
        return

    baru = Node(nim, nama)   # Membuat node baru
    baru.next = head         # Node baru menunjuk ke head lama
    head = baru              # Head dipindah ke node baru
    count += 1               # Jumlah data bertambah

def insert_end():
    global head, count
    nim = input("Masukkan NIM  : ")
    nama = input("Masukkan Nama : ")

    # Validasi input
    if not is_nim_valid(nim):
        print("Error: NIM harus berupa angka!")
        return
    if not is_nama_valid(nama):
        print("Error: Nama harus berupa huruf!")
        return
    if is_duplicate(nim, nama):
        print("Error: NIM atau Nama sudah ada!")
        return

    baru = Node(nim, nama)

    if head is None:
        head = baru
    else:
        bantu = head
        while bantu.next:
            bantu = bantu.next
        bantu.next = baru
    count += 1

def insert_position():
    global head, count

    # Penanganan error jika user input bukan angka
    try:
        pos = int(input(f"Masukkan posisi (1 - {count+1}): "))
    except:
        print("Error: Posisi harus angka!")
        return

    if pos < 1 or pos > count + 1:
        print("Posisi tidak valid!")
        return

    if pos == 1:
        insert_beginning()
        return

    nim = input("Masukkan NIM  : ")
    nama = input("Masukkan Nama : ")

    # Validasi input
    if not is_nim_valid(nim):
        print("Error: NIM harus berupa angka!")
        return
    if not is_nama_valid(nama):
        print("Error: Nama harus berupa huruf!")
        return
    if is_duplicate(nim, nama):
        print("Error: NIM atau Nama sudah ada!")
        return

    baru = Node(nim, nama)

    bantu = head
    # Mencari node sebelum posisi
    for _ in range(pos - 2):
        bantu = bantu.next

    # Menyisipkan node
    baru.next = bantu.next
    bantu.next = baru
    count += 1

# =========================
# DELETE DATA
# =========================

def delete_beginning():
    global head, count
    if head is None:
        print("Data kosong!")
        return
    head = head.next  # Geser head ke node berikutnya
    count -= 1

def delete_end():
    global head, count
    if head is None:
        print("Data kosong!")
        return

    if head.next is None:
        head = None
    else:
        bantu = head
        while bantu.next.next:
            bantu = bantu.next
        bantu.next = None
    count -= 1

def delete_position():
    global head, count

    try:
        pos = int(input(f"Masukkan posisi (1 - {count}): "))
    except:
        print("Error: Posisi harus angka!")
        return

    if pos < 1 or pos > count:
        print("Posisi tidak valid!")
        return

    if pos == 1:
        delete_beginning()
        return

    bantu = head
    for _ in range(pos - 2):
        bantu = bantu.next

    bantu.next = bantu.next.next
    count -= 1

def delete_first_occurrence():
    global head, count
    nim = input("Masukkan NIM yang dihapus: ")

    if head is None:
        print("Data kosong!")
        return

    if head.nim == nim:
        head = head.next
        count -= 1
        return

    bantu = head
    while bantu.next and bantu.next.nim != nim:
        bantu = bantu.next

    if bantu.next is None:
        print("Data tidak ditemukan!")
    else:
        bantu.next = bantu.next.next
        count -= 1

# =========================
# TAMPILKAN DATA
# =========================

def show_data():
    if head is None:
        print("Data kosong!")
        return

    bantu = head
    i = 1
    while bantu:
        print(f"nim mhs#{i} : {bantu.nim}")
        print(f"nama mhs#{i} : {bantu.nama}\n")
        bantu = bantu.next
        i += 1

# =========================
# MENU PROGRAM
# =========================

while True:
    print("MENU")
    print("1. Insert at beginning")
    print("2. Insert at given position")
    print("3. Insert at end")
    print("4. Delete from beginning")
    print("5. Delete given position")
    print("6. Delete from end")
    print("7. Delete first occurrence")
    print("8. Show data")
    print("9. Exit")

    try:
        pilih = int(input("Pilih: "))
    except:
        print("Error: Input harus angka!")
        continue

    if pilih == 1:
        insert_beginning()
    elif pilih == 2:
        insert_position()
    elif pilih == 3:
        insert_end()
    elif pilih == 4:
        delete_beginning()
    elif pilih == 5:
        delete_position()
    elif pilih == 6:
        delete_end()
    elif pilih == 7:
        delete_first_occurrence()
    elif pilih == 8:
        show_data()
    elif pilih == 9:
        break