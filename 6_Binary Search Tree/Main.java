import java.io.BufferedReader; // untuk membaca file
import java.io.FileReader; // untuk membuka file
import java.io.IOException; // menangani error file
import java.util.Scanner; // untuk input user

public class Main {
    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree(); // membuat objek BST

        // =====================
        // Load data dari CSV
        // =====================
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {

            String line;

            br.readLine(); // skip header (baris pertama CSV)

            // membaca setiap baris file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // memisahkan data berdasarkan koma

                if (parts.length < 2)
                    continue; // jika data tidak lengkap, skip

                int id = Integer.parseInt(parts[0].trim()); // ambil id
                String name = parts[1].trim(); // ambil nama

                bst.insert(id, name); // masukkan ke BST
            }

        } catch (IOException e) {
            System.out.println("Error membaca file CSV: " + e.getMessage());
        }

        // =====================
        // Menu Interaktif
        // =====================
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== MENU BST ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Cari Data");
            System.out.println("3. Hapus Data");
            System.out.println("4. Tampilkan Inorder");
            System.out.println("5. Tampilkan Preorder");
            System.out.println("6. Tampilkan Postorder");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");

            // validasi input menu harus angka
            if (!sc.hasNextInt()) {
                System.out.println("Input harus angka!");
                sc.next(); // buang input salah
                continue;
            }

            choice = sc.nextInt();

            if (choice == 7)
                break; // keluar program

            switch (choice) {
                case 1:
                    // =====================
                    // VALIDASI INPUT TAMBAH DATA
                    // =====================
                    System.out.print("Masukkan ID: ");

                    if (!sc.hasNextInt()) { // cek ID harus angka
                        System.out.println("ID harus berupa angka!");
                        sc.next();
                        break;
                    }

                    int idAdd = sc.nextInt();
                    sc.nextLine(); // membersihkan newline

                    System.out.print("Masukkan Nama: ");
                    String nameAdd = sc.nextLine();

                    // validasi nama hanya huruf
                    if (!nameAdd.matches("[a-zA-Z ]+")) {
                        System.out.println("Nama harus berupa huruf!");
                        break;
                    }

                    // cek ID sudah ada
                    if (bst.search(idAdd) != null) {
                        System.out.println("ID sudah ada, tidak boleh duplikat!");
                        break;
                    }

                    // cek nama sudah ada (pakai inorder traversal)
                    if (bst.isNamaExist(nameAdd)) {
                        System.out.println("Nama sudah ada, tidak boleh duplikat!");
                        break;
                    }

                    bst.insert(idAdd, nameAdd); // tambah data
                    System.out.println("Data berhasil ditambahkan.");
                    break;

                case 2:
                    System.out.print("Masukkan ID yang dicari: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("ID harus berupa angka!");
                        sc.next();
                        break;
                    }

                    int idSearch = sc.nextInt();
                    Node found = bst.search(idSearch); // cari data
                    if (found != null)
                        System.out.println("Ditemukan: " + found.id + " - " + found.name);
                    else
                        System.out.println("Data tidak ditemukan.");
                    break;

                case 3:
                    System.out.print("Masukkan ID yang dihapus: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("ID harus berupa angka!");
                        sc.next();
                        break;
                    }

                    int idDel = sc.nextInt();
                    bst.delete(idDel); // hapus data
                    System.out.println("Data berhasil dihapus.");
                    break;

                case 4:
                    System.out.println("=== INORDER ===");
                    bst.inorder(); // tampilkan inorder (terurut)
                    break;

                case 5:
                    System.out.println("=== PREORDER ===");
                    bst.preorder(); // tampilkan preorder
                    break;

                case 6:
                    System.out.println("=== POSTORDER ===");
                    bst.postorder(); // tampilkan postorder
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        sc.close(); // menutup scanner
    }
}

// Fungsi File:
// Sebagai program utama (main program)
// Mengatur:
// Input user (menu)
// Output ke layar
// Menghubungkan user dengan BST
// Membaca data dari file CSV