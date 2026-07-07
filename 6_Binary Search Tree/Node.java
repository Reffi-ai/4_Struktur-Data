// class Node untuk menyimpan data dalam BST
public class Node {
    int id; // key utama
    String name; // value (nama)
    Node left, right; // anak kiri & kanan

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
        left = right = null;
    }
}

// Fungsi File:
// Merepresentasikan 1 data (node) dalam BST
// Menyimpan:
// id (key)
// nama (value)
// referensi ke anak kiri & kanan