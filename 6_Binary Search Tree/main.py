import csv  # untuk membaca file CSV
from bst import BST  # import class BST dari file bst.py

# fungsi untuk membaca data dari file CSV lalu memasukkan ke BST
def load_csv(filename):
    bst = BST()  # membuat objek BST kosong
    with open(filename, "r", encoding="utf-8") as file:  # buka file CSV
        reader = csv.DictReader(file)  # membaca CSV sebagai dictionary
        for row in reader:  # loop setiap baris
            # memasukkan data ke BST (id sebagai integer, nama sebagai string)
            bst.insert(int(row["id"]), row["nama"])
    return bst  # mengembalikan BST yang sudah berisi data

def main():
    bst = load_csv("data.csv")  # load data awal dari file CSV

    while True:  # loop menu terus sampai user keluar
        print("\n=== MENU BST ===")
        print("1. Tambah Data")
        print("2. Cari Data")
        print("3. Hapus Data")
        print("4. Traversal (Inorder)")
        print("5. Traversal (Preorder)")
        print("6. Traversal (Postorder)")
        print("7. Keluar")

        pilihan = input("Pilih menu: ")  # input pilihan user

        if pilihan == "1":
            try:
                # validasi ID harus angka
                id_input = input("Masukkan ID: ")
                if not id_input.isdigit():
                    raise ValueError("ID harus berupa angka!")

                id = int(id_input)

                # validasi nama harus huruf
                nama = input("Masukkan Nama: ")
                if not nama.replace(" ", "").isalpha():
                    raise ValueError("Nama harus berupa huruf!")

                # cek apakah ID sudah ada
                if bst.search(id):
                    print("ID sudah ada, tidak boleh duplikat!")
                    continue

                # cek apakah nama sudah ada (cek via traversal)
                duplicate_nama = False
                for _, n in bst.inorder():
                    if n.lower() == nama.lower():
                        duplicate_nama = True
                        break

                if duplicate_nama:
                    print("Nama sudah ada, tidak boleh duplikat!")
                    continue

                bst.insert(id, nama)  # menambahkan data ke BST
                print("Data ditambahkan.")

            except ValueError as e:
                print("Error:", e)

        elif pilihan == "2":
            try:
                id = int(input("Masukkan ID yang dicari: "))  # input id yang dicari
                result = bst.search(id)  # mencari data di BST
                if result:  # jika ditemukan
                    print(f"Ditemukan: {result.id} - {result.nama}")
                else:  # jika tidak ditemukan
                    print("Data tidak ditemukan.")
            except ValueError:
                print("ID harus berupa angka!")

        elif pilihan == "3":
            try:
                id = int(input("Masukkan ID yang akan dihapus: "))  # input id yang akan dihapus
                bst.delete(id)  # menghapus data dari BST
                print("Data dihapus.")
            except ValueError:
                print("ID harus berupa angka!")

        elif pilihan == "4":
            print("INORDER:")
            # traversal inorder (hasil terurut)
            for id, nama in bst.inorder():
                print(id, nama)

        elif pilihan == "5":
            print("PREORDER:")
            # traversal preorder
            for id, nama in bst.preorder():
                print(id, nama)

        elif pilihan == "6":
            print("POSTORDER:")
            # traversal postorder
            for id, nama in bst.postorder():
                print(id, nama)

        elif pilihan == "7":
            break  # keluar dari program

        else:
            print("Pilihan tidak valid.")  # jika input salah

# menjalankan fungsi main jika file dijalankan langsung
if __name__ == "__main__":
    main()
