// class utama untuk Binary Search Tree
public class BinarySearchTree {
    Node root; // root (akar) dari tree

    // ========================
    // INSERT
    // ========================
    public void insert(int id, String name) {
        root = insertRec(root, id, name);
    }

    private Node insertRec(Node root, int id, String name) {
        if (root == null) {
            root = new Node(id, name);
            return root;
        }
        if (id < root.id)
            root.left = insertRec(root.left, id, name);
        else if (id > root.id)
            root.right = insertRec(root.right, id, name);
        return root;
    }

    // ========================
    // SEARCH
    // ========================
    public Node search(int id) {
        return searchRec(root, id);
    }

    private Node searchRec(Node root, int id) {
        if (root == null || root.id == id)
            return root;
        if (id < root.id)
            return searchRec(root.left, id);
        return searchRec(root.right, id);
    }

    // ========================
    // CEK DUPLIKAT NAMA
    // ========================
    public boolean isNamaExist(String nama) {
        return isNamaExistRec(root, nama.toLowerCase());
    }

    private boolean isNamaExistRec(Node root, String nama) {
        if (root == null)
            return false;

        if (root.name.toLowerCase().equals(nama))
            return true;

        return isNamaExistRec(root.left, nama) || isNamaExistRec(root.right, nama);
    }

    // ========================
    // DELETE
    // ========================
    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, int id) {
        if (root == null)
            return root;

        if (id < root.id)
            root.left = deleteRec(root.left, id);
        else if (id > root.id)
            root.right = deleteRec(root.right, id);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            Node min = minValue(root.right);
            root.id = min.id;
            root.name = min.name;
            root.right = deleteRec(root.right, min.id);
        }

        return root;
    }

    private Node minValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // ========================
    // TRAVERSAL
    // ========================
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.id + " - " + root.name);
            inorderRec(root.right);
        }
    }

    public void preorder() {
        preorderRec(root);
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.println(root.id + " - " + root.name);
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorder() {
        postorderRec(root);
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.println(root.id + " - " + root.name);
        }
    }
}

// Fungsi File:
// Sebagai inti logika BST (otak program)
// Mengatur semua operasi:
// insert
// search
// delete
// traversal