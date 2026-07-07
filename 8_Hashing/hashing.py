import random

# =====================================
# KONFIGURASI HASH TABLE
# =====================================

TABLE_SIZE = 50

# Membuat hash table kosong
hash_table = [[] for _ in range(TABLE_SIZE)]


# =====================================
# HASH FUNCTION
# =====================================

def hash_function(key):
    return key % TABLE_SIZE


# =====================================
# INSERT DATA
# =====================================

def insert_data(data):
    index = hash_function(data)

    # Cek agar data tidak duplikat
    if data not in hash_table[index]:
        hash_table[index].append(data)
        print(f"Data {data} berhasil ditambahkan.")
    else:
        print(f"Data {data} sudah ada di index {index}.")


# =====================================
# HAPUS DATA
# =====================================

def delete_data(data):
    index = hash_function(data)

    if data in hash_table[index]:
        hash_table[index].remove(data)
        print(f"Data {data} berhasil dihapus.")
    else:
        print(f"Data {data} tidak ditemukan.")


# =====================================
# CARI DATA
# =====================================

def search_data(data):
    index = hash_function(data)

    if data in hash_table[index]:
        print(f"Data {data} ditemukan pada index {index}.")
    else:
        print(f"Data {data} tidak ditemukan.")


# =====================================
# TAMPILKAN HASH TABLE
# =====================================

def display_table():
    print("\n========== HASH TABLE ==========")

    for i in range(TABLE_SIZE):
        print(f"Index {i}: {hash_table[i]}")


# =====================================
# INPUT 100 DATA RANDOM UNIK
# =====================================

print("Menginput 100 data random unik...\n")

random_data = random.sample(range(1, 1000), 100)

for data in random_data:
    insert_data(data)

print("\n100 data random berhasil dimasukkan.")


# =====================================
# MENU PROGRAM
# =====================================

while True:

    print("\n===== MENU HASH TABLE =====")
    print("1. Input Data")
    print("2. Hapus Data")
    print("3. Cari Data")
    print("4. Tampilkan Hash Table")
    print("5. Keluar")

    pilihan = input("Pilih menu: ")

    # =====================================
    # INPUT DATA
    # =====================================

    if pilihan == "1":

        try:
            data = int(input("Masukkan data: "))
            insert_data(data)

        except ValueError:
            print("Input harus berupa angka!")

    # =====================================
    # HAPUS DATA
    # =====================================

    elif pilihan == "2":

        try:
            data = int(input("Masukkan data yang ingin dihapus: "))
            delete_data(data)

        except ValueError:
            print("Input harus berupa angka!")

    # =====================================
    # CARI DATA
    # =====================================

    elif pilihan == "3":

        try:
            data = int(input("Masukkan data yang ingin dicari: "))
            search_data(data)

        except ValueError:
            print("Input harus berupa angka!")

    # =====================================
    # TAMPILKAN HASH TABLE
    # =====================================

    elif pilihan == "4":
        display_table()

    # =====================================
    # KELUAR PROGRAM
    # =====================================

    elif pilihan == "5":
        print("Program selesai.")
        break

    # =====================================
    # PILIHAN TIDAK VALID
    # =====================================

    else:
        print("Pilihan menu tidak valid!")