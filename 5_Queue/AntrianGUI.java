import javax.swing.*; // Library GUI
import java.awt.*; // Untuk layout, warna, font
import java.io.IOException; // Untuk handle error suara

// ================= NODE =================
// Node adalah elemen dalam linked list (queue)
class Node {
    int nomor; // nomor antrian
    String nama; // nama pasien
    Node next; // pointer ke node berikutnya

    Node(int nomor, String nama) {
        this.nomor = nomor;
        this.nama = nama;
        this.next = null;
    }
}

// ================= QUEUE =================
// Queue menggunakan konsep FIFO (First In First Out)
class Queue {
    Node front, rear; // depan dan belakang antrian
    int counter = 0; // penghitung nomor antrian

    // Menambahkan data ke antrian (enqueue)
    void enqueue(String nama) {
        counter++; // nomor bertambah
        Node baru = new Node(counter, nama);

        if (rear == null) {
            // jika antrian kosong
            front = rear = baru;
        } else {
            // sambungkan node baru ke belakang
            rear.next = baru;
            rear = baru;
        }
    }

    // Menghapus data dari antrian (dequeue)
    Node dequeue() {
        if (front == null)
            return null; // jika kosong

        Node temp = front;
        front = front.next;

        // jika setelah dihapus kosong
        if (front == null)
            rear = null;

        return temp;
    }

    // Mengambil semua data untuk ditampilkan di list
    DefaultListModel<String> getAll() {
        DefaultListModel<String> model = new DefaultListModel<>();
        Node temp = front;

        while (temp != null) {
            model.addElement("No " + temp.nomor + " - " + temp.nama);
            temp = temp.next;
        }

        return model;
    }
}

// ================= GUI =================
public class AntrianGUI extends JFrame {

    Queue queue = new Queue(); // objek queue

    // Komponen GUI
    JTextField inputNama = new JTextField();
    JList<String> list = new JList<>();
    JLabel labelNomor = new JLabel("-", SwingConstants.CENTER);
    JLabel labelCall = new JLabel("Belum ada panggilan", SwingConstants.CENTER);
    JLabel labelJumlah = new JLabel("Jumlah Antrian: 0", SwingConstants.CENTER);

    // ================= KONVERSI ANGKA =================
    // Mengubah angka menjadi kata (bahasa Indonesia)
    String angkaKeKata(int n) {
        String[] angka = { "nol", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan",
                "sepuluh" };

        if (n <= 10)
            return angka[n];
        else if (n < 20)
            return angka[n - 10] + " belas";
        else if (n < 100)
            return angka[n / 10] + " puluh " + angka[n % 10];
        else if (n < 200)
            return "seratus " + angkaKeKata(n - 100);
        else if (n < 1000)
            return angka[n / 100] + " ratus " + angkaKeKata(n % 100);
        else
            return String.valueOf(n);
    }

    public AntrianGUI() {

        // ===== SETTING WINDOW =====
        setTitle("Sistem Antrian Puskesmas");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== WARNA BACKGROUND =====
        Color bgColor = new Color(240, 244, 247);

        // Panel utama (vertical layout)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(bgColor);
        add(mainPanel);

        // ===== TITLE =====
        JLabel title = new JLabel("SISTEM ANTRIAN PUSKESMAS");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // rata tengah
        mainPanel.add(title);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // jarak

        // ===== LIST ANTRIAN =====
        list.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(700, 250));
        scroll.setMaximumSize(new Dimension(700, 250));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(scroll);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ===== INPUT =====
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(bgColor);

        inputNama.setPreferredSize(new Dimension(250, 30));

        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(inputNama);

        mainPanel.add(inputPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ===== NOMOR BESAR =====
        labelNomor.setFont(new Font("Arial", Font.BOLD, 60));
        labelNomor.setForeground(Color.RED); // warna merah
        labelNomor.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(labelNomor);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ===== INFO =====
        labelCall.setFont(new Font("Arial", Font.BOLD, 16));
        labelCall.setForeground(Color.BLUE); // warna biru
        labelCall.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelJumlah.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(labelCall);
        mainPanel.add(labelJumlah);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ===== BUTTON =====
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(bgColor);

        JButton btnAmbil = new JButton("Ambil Antrian");
        JButton btnPanggil = new JButton("Panggil");
        JButton btnReset = new JButton("Reset");

        // Warna tombol
        btnAmbil.setBackground(new Color(76, 175, 80)); // hijau
        btnAmbil.setForeground(Color.WHITE);

        btnPanggil.setBackground(new Color(244, 67, 54)); // merah
        btnPanggil.setForeground(Color.WHITE);

        btnReset.setBackground(Color.GRAY);
        btnReset.setForeground(Color.WHITE);

        btnPanel.add(btnAmbil);
        btnPanel.add(btnPanggil);
        btnPanel.add(btnReset);

        mainPanel.add(btnPanel);

        // ================= EVENT =================

        // Tombol ambil antrian
        btnAmbil.addActionListener(e -> {
            String nama = inputNama.getText();

            // Validasi input
            if (nama.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong");
                return;
            }

            queue.enqueue(nama); // masuk queue
            inputNama.setText(""); // kosongkan input
            updateList(); // refresh tampilan
        });

        // Tombol panggil
        btnPanggil.addActionListener(e -> {
            Node data = queue.dequeue(); // ambil dari queue

            if (data != null) {

                // Konversi angka ke bahasa Indonesia
                String nomorHuruf = angkaKeKata(data.nomor);

                // Pesan suara
                String msg = "Nomor " + nomorHuruf +
                        ", atas nama " + data.nama +
                        ", silakan menuju loket";

                // Update tampilan
                labelCall.setText("Memanggil: " + msg);
                labelNomor.setText(String.valueOf(data.nomor));

                // 🔊 SUARA (PowerShell)
                new Thread(() -> {
                    try {
                        String command = "powershell -Command \" " +
                                "[console]::beep(1000,300); " + // ding
                                "Start-Sleep -Milliseconds 150; " +
                                "[console]::beep(800,300); " + // dong
                                "Start-Sleep -Milliseconds 300; " +

                                "Add-Type -AssemblyName System.Speech; " +
                                "$speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; " +
                                "$speak.Speak('" + msg + "');\"";

                        Runtime.getRuntime().exec(command);

                    } catch (IOException ex) {
                        System.out.println("Error suara");
                    }
                }).start();

                updateList();
            } else {
                JOptionPane.showMessageDialog(this, "Antrian kosong");
            }
        });

        // Tombol reset
        btnReset.addActionListener(e -> {
            queue.front = null;
            queue.rear = null;
            queue.counter = 0;

            labelNomor.setText("-");
            labelCall.setText("Belum ada panggilan");

            updateList();
        });
    }

    // Fungsi update tampilan list
    void updateList() {
        list.setModel(queue.getAll());

        int lastIndex = list.getModel().getSize() - 1;
        if (lastIndex >= 0) {
            list.ensureIndexIsVisible(lastIndex); // auto scroll ke bawah
        }

        labelJumlah.setText("Jumlah Antrian: " + queue.counter);
    }

    // Main method (entry point program)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AntrianGUI().setVisible(true));
    }
}
