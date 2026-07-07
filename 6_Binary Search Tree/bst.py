# ============================================================
# FILE: bst.py
# ============================================================
# Fungsi file ini:
# - Menyimpan implementasi struktur data Binary Search Tree (BST)
# - Berisi class Node untuk merepresentasikan data dalam tree
# - Berisi class BST untuk mengelola operasi pada tree
#
# Operasi yang disediakan:
# - insert()   → menambahkan data ke dalam BST
# - search()   → mencari data berdasarkan id
# - delete()   → menghapus data dari BST
# - inorder()  → menampilkan data secara terurut (ascending)
# - preorder() → menampilkan data (root-kiri-kanan)
# - postorder()→ menampilkan data (kiri-kanan-root)
#
# File ini digunakan oleh file lain (misalnya main.py)
# agar logika BST terpisah dari tampilan/menu program.
# ============================================================


# class Node untuk merepresentasikan 1 data dalam BST
class Node:
    def __init__(self, id, nama):
        self.id = id  # key utama
        self.nama = nama  # value/data
        self.left = None  # anak kiri
        self.right = None  # anak kanan

# class BST sebagai pengelola tree
class BST:
    def __init__(self):
        self.root = None  # root awal kosong

    # fungsi insert (menambah data)
    def insert(self, id, nama):
        self.root = self._insert(self.root, id, nama)

    # fungsi insert rekursif
    def _insert(self, node, id, nama):
        if node is None:
            return Node(id, nama)  # buat node baru
        if id < node.id:
            node.left = self._insert(node.left, id, nama)  # ke kiri
        elif id > node.id:
            node.right = self._insert(node.right, id, nama)  # ke kanan
        return node

    # fungsi search (mencari data)
    def search(self, id):
        return self._search(self.root, id)

    def _search(self, node, id):
        if node is None or node.id == id:
            return node  # ditemukan atau tidak ada
        if id < node.id:
            return self._search(node.left, id)
        return self._search(node.right, id)

    # fungsi delete (menghapus data)
    def delete(self, id):
        self.root = self._delete(self.root, id)

    def _delete(self, node, id):
        if node is None:
            return None

        if id < node.id:
            node.left = self._delete(node.left, id)
        elif id > node.id:
            node.right = self._delete(node.right, id)
        else:
            # kasus tanpa anak / satu anak
            if node.left is None:
                return node.right
            if node.right is None:
                return node.left

            # kasus dua anak
            min_node = self._min_value(node.right)
            node.id, node.nama = min_node.id, min_node.nama
            node.right = self._delete(node.right, min_node.id)

        return node

    # mencari nilai terkecil
    def _min_value(self, node):
        while node.left:
            node = node.left
        return node

    # traversal inorder (terurut)
    def inorder(self):
        result = []
        self._inorder(self.root, result)
        return result

    def _inorder(self, node, result):
        if node:
            self._inorder(node.left, result)
            result.append((node.id, node.nama))
            self._inorder(node.right, result)

    # traversal preorder
    def preorder(self):
        result = []
        self._preorder(self.root, result)
        return result

    def _preorder(self, node, result):
        if node:
            result.append((node.id, node.nama))
            self._preorder(node.left, result)
            self._preorder(node.right, result)

    # traversal postorder
    def postorder(self):
        result = []
        self._postorder(self.root, result)
        return result

    def _postorder(self, node, result):
        if node:
            self._postorder(node.left, result)
            self._postorder(node.right, result)
            result.append((node.id, node.nama))
