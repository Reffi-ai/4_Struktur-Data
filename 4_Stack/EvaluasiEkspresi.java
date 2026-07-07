import java.util.Scanner; // Digunakan untuk membaca input dari pengguna (keyboard).
import java.util.Stack; // Digunakan untuk memakai struktur data Stack (tumpukan).

public class EvaluasiEkspresi {

    // Fungsi untuk menentukan prioritas operator
    static int precedence(String op) {
        // Jika operator + atau -, prioritas rendah
        if (op.equals("+") || op.equals("-"))
            return 1;
        // Jika operator * atau /, prioritas lebih tinggi
        if (op.equals("*") || op.equals("/"))
            return 2;
        // Selain itu, prioritas 0
        return 0;
    }

    // Fungsi untuk mengubah ekspresi infix menjadi postfix
    static String[] infixToPostfix(String infix) {
        Stack<String> stack = new Stack<>(); // Stack untuk operator
        String[] tokens = infix.split(" "); // Memisahkan input berdasarkan spasi
        StringBuilder postfix = new StringBuilder(); // Menyimpan hasil postfix

        // Loop setiap token
        for (String token : tokens) {

            // Jika token adalah angka (bisa lebih dari 1 digit)
            if (token.matches("\\d+")) {
                postfix.append(token).append(" "); // langsung ke postfix

                // Jika token adalah "("
            } else if (token.equals("(")) {
                stack.push(token); // simpan ke stack

                // Jika token adalah ")"
            } else if (token.equals(")")) {
                // Pop sampai menemukan "("
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // hapus "(" dari stack

                // Jika token adalah operator
            } else {
                // Selama stack tidak kosong dan prioritas lebih tinggi atau sama
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token); // simpan operator ke stack
            }
        }

        // Mengeluarkan sisa operator di stack
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        // Mengembalikan hasil postfix dalam bentuk array
        return postfix.toString().trim().split(" ");
    }

    // Fungsi untuk menghitung hasil dari postfix
    static int evaluatePostfix(String[] postfix) {
        Stack<Integer> stack = new Stack<>(); // Stack untuk perhitungan

        System.out.println("\nSTEP BY STEP EVALUASI:");

        // Loop setiap token postfix
        for (String token : postfix) {

            // Jika token adalah angka
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token)); // masukkan ke stack
                System.out.println("Push " + token);

                // Jika token adalah operator
            } else {
                int b = stack.pop(); // ambil nilai kedua
                int a = stack.pop(); // ambil nilai pertama
                int result = 0;

                // Lakukan operasi sesuai operator
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                }

                stack.push(result); // simpan hasil kembali ke stack
                System.out.println("Pop " + a + " dan " + b + ", hitung " + a + token + b + " = " + result);
            }
        }

        // Hasil akhir berada di puncak stack
        return stack.peek();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // untuk input user

        // Loop agar program bisa dijalankan berulang
        while (true) {
            // Input ekspresi infix
            System.out.print("\nMasukkan ekspresi infix (gunakan spasi, contoh: 12 + 3 * 5): ");
            String infix = input.nextLine();

            // Konversi infix ke postfix
            String[] postfix = infixToPostfix(infix);

            // Menampilkan hasil postfix
            System.out.print("\nPostfix Expression: ");
            for (String s : postfix) {
                System.out.print(s + " ");
            }

            // Evaluasi postfix
            int hasil = evaluatePostfix(postfix);
            System.out.println("\nHasil Akhir: " + hasil);

            // Pilihan untuk lanjut atau keluar
            System.out.print("\nIngin lanjut? (y/n): ");
            String pilihan = input.nextLine();

            // Jika tidak memilih 'y', program berhenti
            if (!pilihan.equalsIgnoreCase("y")) {
                System.out.println("Program selesai.");
                break;
            }
        }
    }
}
