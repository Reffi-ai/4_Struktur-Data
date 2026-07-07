# =========================================================
# VALIDASI INPUT INTEGER
# =========================================================
def input_integer(pesan):

    while True:

        try:
            value = int(input(pesan))
            return value

        except ValueError:
            print("Input harus berupa angka!")


# =========================================================
# INPUT MATRIX (HANYA SEKALI DI AWAL PROGRAM)
# =========================================================
def input_matrix():

    while True:

        rows = input_integer("Masukkan jumlah baris : ")

        if rows > 0:
            break

        print("Jumlah baris harus lebih dari 0!")

    while True:

        cols = input_integer("Masukkan jumlah kolom : ")

        if cols > 0:
            break

        print("Jumlah kolom harus lebih dari 0!")

    matrix = []

    print("\nMasukkan elemen matrix:")

    for i in range(rows):

        row = []

        for j in range(cols):

            value = input_integer(
                f"Baris {i+1} Kolom {j+1} : "
            )

            row.append(value)

        matrix.append(row)

    return matrix


# =========================================================
# PRINT MATRIX
# =========================================================
def print_matrix(matrix):

    for row in matrix:

        for val in row:
            print(f"{val:4}", end="")

        print()


# =========================================================
# COPY MATRIX
# (DIGUNAKAN UNTUK MENAMPILKAN MATRIX SEBELUM)
# =========================================================
def copy_matrix(matrix):

    return [row[:] for row in matrix]


# =========================================================
# 1-a SORT ROW-WISE
# =========================================================
def sort_row_wise(matrix):

    before = copy_matrix(matrix)

    print("\n=== SEBELUM SORT ROW-WISE ===")
    print_matrix(before)

    for row in matrix:
        row.sort()

    print("\n=== SESUDAH SORT ROW-WISE ===")
    print_matrix(matrix)

    return matrix


# =========================================================
# 1-b SORT COLUMN-WISE
# =========================================================
def sort_column_wise(matrix):

    before = copy_matrix(matrix)

    print("\n=== SEBELUM SORT COLUMN-WISE ===")
    print_matrix(before)

    rows = len(matrix)
    cols = len(matrix[0])

    for col in range(cols):

        temp = []

        for row in range(rows):
            temp.append(matrix[row][col])

        temp.sort()

        for row in range(rows):
            matrix[row][col] = temp[row]

    print("\n=== SESUDAH SORT COLUMN-WISE ===")
    print_matrix(matrix)

    return matrix


# =========================================================
# 2-a ROTATE CLOCKWISE BY 1
# (OUTER RING ROTATION)
# =========================================================
def rotate_clockwise_by_one(matrix):

    rows = len(matrix)
    cols = len(matrix[0])

    if rows < 2 or cols < 2:

        print("\nMatrix minimal berukuran 2 x 2!")
        return matrix

    before = copy_matrix(matrix)

    print("\n=== SEBELUM ROTATE CLOCKWISE BY 1 ===")
    print_matrix(before)

    top = 0
    bottom = rows - 1
    left = 0
    right = cols - 1

    prev = matrix[top + 1][left]

    # Top Row
    for i in range(left, right + 1):

        matrix[top][i], prev = prev, matrix[top][i]

    top += 1

    # Right Column
    for i in range(top, bottom + 1):

        matrix[i][right], prev = prev, matrix[i][right]

    right -= 1

    # Bottom Row
    for i in range(right, left - 1, -1):

        matrix[bottom][i], prev = prev, matrix[bottom][i]

    bottom -= 1

    # Left Column
    for i in range(bottom, top - 1, -1):

        matrix[i][left], prev = prev, matrix[i][left]

    print("\n=== SESUDAH ROTATE CLOCKWISE BY 1 ===")
    print_matrix(matrix)

    return matrix


# =========================================================
# 2-b ROTATE COUNTER-CLOCKWISE BY 1
# (OUTER RING ROTATION)
# =========================================================
def rotate_counter_clockwise_by_one(matrix):

    rows = len(matrix)
    cols = len(matrix[0])

    if rows < 2 or cols < 2:

        print("\nMatrix minimal berukuran 2 x 2!")
        return matrix

    before = copy_matrix(matrix)

    print("\n=== SEBELUM ROTATE COUNTER-CLOCKWISE BY 1 ===")
    print_matrix(before)

    top = 0
    bottom = rows - 1
    left = 0
    right = cols - 1

    prev = matrix[top][left + 1]

    # Left Column
    for i in range(top, bottom + 1):

        matrix[i][left], prev = prev, matrix[i][left]

    left += 1

    # Bottom Row
    for i in range(left, right + 1):

        matrix[bottom][i], prev = prev, matrix[bottom][i]

    bottom -= 1

    # Right Column
    for i in range(bottom, top - 1, -1):

        matrix[i][right], prev = prev, matrix[i][right]

    right -= 1

    # Top Row
    for i in range(right, left - 1, -1):

        matrix[top][i], prev = prev, matrix[top][i]

    print("\n=== SESUDAH ROTATE COUNTER-CLOCKWISE BY 1 ===")
    print_matrix(matrix)

    return matrix

# =========================================================
# 2-c ROTATE MATRIX 90 DERAJAT (CLOCKWISE)
# =========================================================
def rotate_90(matrix):

    before = copy_matrix(matrix)

    print("\n=== SEBELUM ROTATE 90 DERAJAT ===")
    print_matrix(before)

    rows = len(matrix)
    cols = len(matrix[0])

    rotated = []

    for j in range(cols):

        row = []

        for i in range(rows - 1, -1, -1):
            row.append(matrix[i][j])

        rotated.append(row)

    print("\n=== SESUDAH ROTATE 90 DERAJAT ===")
    print_matrix(rotated)

    return rotated


# =========================================================
# 2-d ROTATE MATRIX 180 DERAJAT
# =========================================================
def rotate_180(matrix):

    before = copy_matrix(matrix)

    print("\n=== SEBELUM ROTATE 180 DERAJAT ===")
    print_matrix(before)

    rotated = []

    for row in matrix[::-1]:
        rotated.append(row[::-1])

    print("\n=== SESUDAH ROTATE 180 DERAJAT ===")
    print_matrix(rotated)

    return rotated


# =========================================================
# 3-a ROW-WISE TRAVERSAL
# =========================================================
def row_wise_traversal(matrix):

    print("\n=== MATRIX ===")
    print_matrix(matrix)

    print("\n=== ROW-WISE TRAVERSAL ===")

    for row in matrix:

        for val in row:
            print(val, end=" ")

    print()

    return matrix


# =========================================================
# 3-b COLUMN-WISE TRAVERSAL
# =========================================================
def column_wise_traversal(matrix):

    print("\n=== MATRIX ===")
    print_matrix(matrix)

    print("\n=== COLUMN-WISE TRAVERSAL ===")

    rows = len(matrix)
    cols = len(matrix[0])

    for j in range(cols):

        for i in range(rows):
            print(matrix[i][j], end=" ")

    print()

    return matrix


# =========================================================
# 4 PRINT MATRIX IN SPIRAL FORM
# =========================================================
def spiral_print(matrix):

    print("\n=== MATRIX ===")
    print_matrix(matrix)

    print("\n=== SPIRAL TRAVERSAL ===")

    top = 0
    bottom = len(matrix) - 1
    left = 0
    right = len(matrix[0]) - 1

    while top <= bottom and left <= right:

        # Kiri → Kanan
        for i in range(left, right + 1):
            print(matrix[top][i], end=" ")

        top += 1

        # Atas → Bawah
        for i in range(top, bottom + 1):
            print(matrix[i][right], end=" ")

        right -= 1

        # Kanan → Kiri
        if top <= bottom:

            for i in range(right, left - 1, -1):
                print(matrix[bottom][i], end=" ")

            bottom -= 1

        # Bawah → Atas
        if left <= right:

            for i in range(bottom, top - 1, -1):
                print(matrix[i][left], end=" ")

            left += 1

    print()

    return matrix


# =========================================================
# 5 TRANSPOSE MATRIX
# =========================================================
def transpose_matrix(matrix):

    before = copy_matrix(matrix)

    print("\n=== SEBELUM TRANSPOSE ===")
    print_matrix(before)

    rows = len(matrix)
    cols = len(matrix[0])

    transpose = []

    for j in range(cols):

        row = []

        for i in range(rows):
            row.append(matrix[i][j])

        transpose.append(row)

    print("\n=== SESUDAH TRANSPOSE ===")
    print_matrix(transpose)

    return transpose


# =========================================================
# PROGRAM UTAMA
# =========================================================

print("========== INPUT MATRIX AWAL ==========")

matrix = input_matrix()

valid_menu = [
    "1-a", "1-b",
    "2-a", "2-b", "2-c", "2-d",
    "3-a", "3-b",
    "4", "5", "6"
]

while True:

    print("\n========== MENU MATRIX ==========")
    print("1-a. Sort the matrix row-wise")
    print("1-b. Sort the matrix column-wise")
    print("2-a. Rotate Matrix Clockwise by 1")
    print("2-b. Rotate Matrix Counter-Clockwise by 1")
    print("2-c. Rotate a matrix by 90")
    print("2-d. Rotate a matrix by 180")
    print("3-a. Row-wise traversal of matrix")
    print("3-b. Column-wise traversal of matrix")
    print("4. Print matrix in spiral form")
    print("5. Transpose matrix")
    print("6. Quit")

    while True:

        choice = input("\nPilih menu : ").lower()

        if choice in valid_menu:
            break

        print("Menu tidak valid!")

    if choice == "1-a":
        matrix = sort_row_wise(matrix)

    elif choice == "1-b":
        matrix = sort_column_wise(matrix)

    elif choice == "2-a":
        matrix = rotate_clockwise_by_one(matrix)

    elif choice == "2-b":
        matrix = rotate_counter_clockwise_by_one(matrix)

    elif choice == "2-c":
        matrix = rotate_90(matrix)

    elif choice == "2-d":
        matrix = rotate_180(matrix)

    elif choice == "3-a":
        matrix = row_wise_traversal(matrix)

    elif choice == "3-b":
        matrix = column_wise_traversal(matrix)

    elif choice == "4":
        matrix = spiral_print(matrix)

    elif choice == "5":
        matrix = transpose_matrix(matrix)

    elif choice == "6":
        print("\nProgram selesai.")
        break