import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HashTableProgram {

    // =====================================
    // KONFIGURASI HASH TABLE
    // =====================================

    static final int TABLE_SIZE = 50;

    // Membuat hash table
    static ArrayList<Integer>[] hashTable = new ArrayList[TABLE_SIZE];

    // =====================================
    // HASH FUNCTION
    // =====================================

    static int hashFunction(int key) {
        return key % TABLE_SIZE;
    }

    // =====================================
    // INSERT DATA
    // =====================================

    static void insertData(int data) {

        int index = hashFunction(data);

        // Cek data duplikat
        if (!hashTable[index].contains(data)) {

            hashTable[index].add(data);

            System.out.println("Data " + data + " berhasil ditambahkan.");

        } else {

            System.out.println(
                    "Data " + data +
                    " sudah ada di index " + index + "."
            );
        }
    }

    // =====================================
    // HAPUS DATA
    // =====================================

    static void deleteData(int data) {

        int index = hashFunction(data);

        if (hashTable[index].contains(data)) {

            hashTable[index].remove(Integer.valueOf(data));

            System.out.println("Data " + data + " berhasil dihapus.");

        } else {

            System.out.println("Data " + data + " tidak ditemukan.");
        }
    }

    // =====================================
    // CARI DATA
    // =====================================

    static void searchData(int data) {

        int index = hashFunction(data);

        if (hashTable[index].contains(data)) {

            System.out.println("Data " + data +
                    " ditemukan pada index " + index);

        } else {

            System.out.println("Data " + data + " tidak ditemukan.");
        }
    }

    // =====================================
    // TAMPILKAN HASH TABLE
    // =====================================

    static void displayTable() {

        System.out.println("\n========== HASH TABLE ==========");

        for (int i = 0; i < TABLE_SIZE; i++) {

            System.out.println("Index " + i + ": " + hashTable[i]);
        }
    }

    // =====================================
    // MAIN PROGRAM
    // =====================================

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // =====================================
        // INISIALISASI HASH TABLE
        // =====================================

        for (int i = 0; i < TABLE_SIZE; i++) {

            hashTable[i] = new ArrayList<>();
        }

        // =====================================
        // INPUT 100 DATA RANDOM UNIK
        // =====================================

        System.out.println("Menginput 100 data random unik...\n");

        int count = 0;

        while (count < 100) {

            int data = random.nextInt(999) + 1;

            int index = hashFunction(data);

            // Pastikan tidak duplikat
            if (!hashTable[index].contains(data)) {

                insertData(data);

                count++;
            }
        }

        System.out.println("\n100 data random berhasil dimasukkan.");

        // =====================================
        // MENU PROGRAM
        // =====================================

        while (true) {

            System.out.println("\n===== MENU HASH TABLE =====");
            System.out.println("1. Input Data");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Hash Table");
            System.out.println("5. Keluar");

            System.out.print("Pilih menu: ");

            String pilihan = input.nextLine();

            // =====================================
            // INPUT DATA
            // =====================================

            if (pilihan.equals("1")) {

                try {

                    System.out.print("Masukkan data: ");

                    int data = Integer.parseInt(input.nextLine());

                    insertData(data);

                } catch (NumberFormatException e) {

                    System.out.println("Input harus berupa angka!");
                }
            }

            // =====================================
            // HAPUS DATA
            // =====================================

            else if (pilihan.equals("2")) {

                try {

                    System.out.print("Masukkan data yang ingin dihapus: ");

                    int data = Integer.parseInt(input.nextLine());

                    deleteData(data);

                } catch (NumberFormatException e) {

                    System.out.println("Input harus berupa angka!");
                }
            }

            // =====================================
            // CARI DATA
            // =====================================

            else if (pilihan.equals("3")) {

                try {

                    System.out.print("Masukkan data yang ingin dicari: ");

                    int data = Integer.parseInt(input.nextLine());

                    searchData(data);

                } catch (NumberFormatException e) {

                    System.out.println("Input harus berupa angka!");
                }
            }

            // =====================================
            // TAMPILKAN HASH TABLE
            // =====================================

            else if (pilihan.equals("4")) {

                displayTable();
            }

            // =====================================
            // KELUAR PROGRAM
            // =====================================

            else if (pilihan.equals("5")) {

                System.out.println("Program selesai.");

                break;
            }

            // =====================================
            // PILIHAN TIDAK VALID
            // =====================================

            else {

                System.out.println("Pilihan menu tidak valid!");
            }
        }

        input.close();
    }
}