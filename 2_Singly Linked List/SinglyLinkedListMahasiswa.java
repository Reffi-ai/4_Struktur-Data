import java.util.Scanner; // digunakan untuk mengambil input dari pengguna (keyboard) di program Java

// Kelas Node untuk menyimpan data mahasiswa
class Node {
    String nim; // Menyimpan NIM mahasiswa
    String nama; // Menyimpan nama mahasiswa
    Node next; // Pointer ke node berikutnya

    // Constructor
    Node(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.next = null;
    }
}

public class SinglyLinkedListMahasiswa {
    static Node head = null; // Node pertama
    static int count = 0; // Jumlah data
    static Scanner input = new Scanner(System.in);

    // =========================
    // VALIDASI
    // =========================

    static boolean isNimValid(String nim) {
        return nim.matches("\\d+"); // hanya angka
    }

    static boolean isNamaValid(String nama) {
        return nama.matches("[a-zA-Z ]+"); // huruf + spasi
    }

    static boolean isDuplicate(String nim, String nama) {
        Node bantu = head;
        while (bantu != null) {
            if (bantu.nim.equals(nim) || bantu.nama.equalsIgnoreCase(nama)) {
                return true;
            }
            bantu = bantu.next;
        }
        return false;
    }

    // =========================
    // INSERT
    // =========================

    static void insertBeginning() {
        System.out.print("Masukkan NIM  : ");
        String nim = input.next();
        input.nextLine();
        System.out.print("Masukkan Nama : ");
        String nama = input.nextLine();

        if (!isNimValid(nim)) {
            System.out.println("Error: NIM harus angka!");
            return;
        }
        if (!isNamaValid(nama)) {
            System.out.println("Error: Nama harus huruf!");
            return;
        }
        if (isDuplicate(nim, nama)) {
            System.out.println("Error: Data sudah ada!");
            return;
        }

        Node baru = new Node(nim, nama);
        baru.next = head;
        head = baru;
        count++;
    }

    static void insertEnd() {
        System.out.print("Masukkan NIM  : ");
        String nim = input.next();
        input.nextLine();
        System.out.print("Masukkan Nama : ");
        String nama = input.nextLine();

        if (!isNimValid(nim) || !isNamaValid(nama) || isDuplicate(nim, nama)) {
            System.out.println("Error: Input tidak valid / duplikat!");
            return;
        }

        Node baru = new Node(nim, nama);

        if (head == null) {
            head = baru;
        } else {
            Node bantu = head;
            while (bantu.next != null) {
                bantu = bantu.next;
            }
            bantu.next = baru;
        }
        count++;
    }

    static void insertPosition() {
        System.out.print("Masukkan posisi (1 - " + (count + 1) + "): ");

        int pos;
        try {
            pos = input.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Posisi harus angka!");
            input.nextLine();
            return;
        }

        if (pos < 1 || pos > count + 1) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (pos == 1) {
            insertBeginning();
            return;
        }

        System.out.print("Masukkan NIM  : ");
        String nim = input.next();
        input.nextLine();
        System.out.print("Masukkan Nama : ");
        String nama = input.nextLine();

        if (!isNimValid(nim) || !isNamaValid(nama) || isDuplicate(nim, nama)) {
            System.out.println("Error: Input tidak valid / duplikat!");
            return;
        }

        Node baru = new Node(nim, nama);
        Node bantu = head;

        for (int i = 1; i < pos - 1; i++) {
            bantu = bantu.next;
        }

        baru.next = bantu.next;
        bantu.next = baru;
        count++;
    }

    // =========================
    // DELETE
    // =========================

    static void deleteBeginning() {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }
        head = head.next;
        count--;
    }

    static void deleteEnd() {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        if (head.next == null) {
            head = null;
        } else {
            Node bantu = head;
            while (bantu.next.next != null) {
                bantu = bantu.next;
            }
            bantu.next = null;
        }
        count--;
    }

    static void deletePosition() {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        System.out.print("Masukkan posisi (1 - " + count + "): ");

        int pos;
        try {
            pos = input.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Posisi harus angka!");
            input.nextLine();
            return;
        }

        if (pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (pos == 1) {
            deleteBeginning();
            return;
        }

        Node bantu = head;
        for (int i = 1; i < pos - 1; i++) {
            bantu = bantu.next;
        }

        bantu.next = bantu.next.next;
        count--;
    }

    static void deleteFirstOccurrence() {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        System.out.print("Masukkan NIM yang dihapus: ");
        String nim = input.next();

        if (head.nim.equals(nim)) {
            head = head.next;
            count--;
            return;
        }

        Node bantu = head;
        while (bantu.next != null && !bantu.next.nim.equals(nim)) {
            bantu = bantu.next;
        }

        if (bantu.next == null) {
            System.out.println("Data tidak ditemukan!");
        } else {
            bantu.next = bantu.next.next;
            count--;
        }
    }

    // =========================
    // TAMPILKAN DATA
    // =========================

    static void showData() {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        Node bantu = head;
        int i = 1;

        while (bantu != null) {
            System.out.println("nim mhs#" + i + " : " + bantu.nim);
            System.out.println("nama mhs#" + i + " : " + bantu.nama);
            System.out.println();
            bantu = bantu.next;
            i++;
        }
    }

    // =========================
    // MAIN PROGRAM
    // =========================

    public static void main(String[] args) {
        int pilih = 0; // ✅ FIX ERROR (wajib inisialisasi)

        do {
            System.out.println("MENU");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at given position");
            System.out.println("3. Insert at end");
            System.out.println("4. Delete from beginning");
            System.out.println("5. Delete given position");
            System.out.println("6. Delete from end");
            System.out.println("7. Delete first occurrence");
            System.out.println("8. Show data");
            System.out.println("9. Exit");
            System.out.print("Pilih: ");

            try {
                pilih = input.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Input harus angka!");
                input.nextLine();
                continue;
            }

            switch (pilih) {
                case 1 -> insertBeginning();
                case 2 -> insertPosition();
                case 3 -> insertEnd();
                case 4 -> deleteBeginning();
                case 5 -> deletePosition();
                case 6 -> deleteEnd();
                case 7 -> deleteFirstOccurrence();
                case 8 -> showData();
            }

        } while (pilih != 9);
    }
}
