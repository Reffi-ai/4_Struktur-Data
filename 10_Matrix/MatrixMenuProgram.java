import java.util.*;

public class MatrixMenuProgram {

    static Scanner input = new Scanner(System.in);

    // =========================================================
    // VALIDASI INPUT INTEGER
    // =========================================================
    static int inputInteger(String pesan) {

        while (true) {

            try {

                System.out.print(pesan);
                return Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Input harus berupa bilangan bulat!");
            }
        }
    }

    // =========================================================
    // INPUT MATRIX (HANYA SEKALI DI AWAL PROGRAM)
    // =========================================================
    static int[][] inputMatrix() {

        int rows;
        int cols;

        while (true) {

            rows = inputInteger("Masukkan jumlah baris : ");

            if (rows > 0) {
                break;
            }

            System.out.println("Jumlah baris harus bilangan bulat positif!");
        }

        while (true) {

            cols = inputInteger("Masukkan jumlah kolom : ");

            if (cols > 0) {
                break;
            }

            System.out.println("Jumlah kolom harus bilangan bulat positif!");
        }

        int[][] matrix = new int[rows][cols];

        System.out.println("\nMasukkan elemen matrix:");

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                matrix[i][j] = inputInteger(
                        "Baris " + (i + 1) + " Kolom " + (j + 1) + " : "
                );
            }
        }

        return matrix;
    }

    // =========================================================
    // PRINT MATRIX
    // =========================================================
    static void printMatrix(int[][] matrix) {

        for (int[] row : matrix) {

            for (int val : row) {

                System.out.printf("%5d", val);
            }

            System.out.println();
        }
    }

    // =========================================================
    // COPY MATRIX
    // =========================================================
    static int[][] copyMatrix(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] copy = new int[rows][cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                copy[i][j] = matrix[i][j];
            }
        }

        return copy;
    }

    // =========================================================
    // 1-a SORT MATRIX ROW-WISE
    // =========================================================
    static int[][] sortRowWise(int[][] matrix) {

        int[][] before = copyMatrix(matrix);

        System.out.println("\n=== SEBELUM SORT ROW-WISE ===");
        printMatrix(before);

        for (int[] row : matrix) {

            Arrays.sort(row);
        }

        System.out.println("\n=== SESUDAH SORT ROW-WISE ===");
        printMatrix(matrix);

        return matrix;
    }

    // =========================================================
    // 1-b SORT MATRIX COLUMN-WISE
    // =========================================================
    static int[][] sortColumnWise(int[][] matrix) {

        int[][] before = copyMatrix(matrix);

        System.out.println("\n=== SEBELUM SORT COLUMN-WISE ===");
        printMatrix(before);

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int col = 0; col < cols; col++) {

            int[] temp = new int[rows];

            for (int row = 0; row < rows; row++) {

                temp[row] = matrix[row][col];
            }

            Arrays.sort(temp);

            for (int row = 0; row < rows; row++) {

                matrix[row][col] = temp[row];
            }
        }

        System.out.println("\n=== SESUDAH SORT COLUMN-WISE ===");
        printMatrix(matrix);

        return matrix;
    }

    // =========================================================
    // 2-a ROTATE CLOCKWISE BY 1
    // (OUTER RING ROTATION)
    // =========================================================
    static int[][] rotateClockwiseByOne(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows < 2 || cols < 2) {

            System.out.println("\nMatrix minimal berukuran 2 x 2!");
            return matrix;
        }

        int[][] before = copyMatrix(matrix);

        System.out.println("\n=== SEBELUM ROTATE CLOCKWISE BY 1 ===");
        printMatrix(before);

        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        int prev = matrix[top + 1][left];

        // Top Row
        for (int i = left; i <= right; i++) {

            int current = matrix[top][i];
            matrix[top][i] = prev;
            prev = current;
        }

        top++;

        // Right Column
        for (int i = top; i <= bottom; i++) {

            int current = matrix[i][right];
            matrix[i][right] = prev;
            prev = current;
        }

        right--;

        // Bottom Row
        for (int i = right; i >= left; i--) {

            int current = matrix[bottom][i];
            matrix[bottom][i] = prev;
            prev = current;
        }

        bottom--;

        // Left Column
        for (int i = bottom; i >= top; i--) {

            int current = matrix[i][left];
            matrix[i][left] = prev;
            prev = current;
        }

        System.out.println("\n=== SESUDAH ROTATE CLOCKWISE BY 1 ===");
        printMatrix(matrix);

        return matrix;
    }

    // =========================================================
    // 2-b ROTATE COUNTER-CLOCKWISE BY 1
    // (OUTER RING ROTATION)
    // =========================================================
    static int[][] rotateCounterClockwiseByOne(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows < 2 || cols < 2) {

            System.out.println("\nMatrix minimal berukuran 2 x 2!");
            return matrix;
        }

        int[][] before = copyMatrix(matrix);

        System.out.println("\n=== SEBELUM ROTATE COUNTER-CLOCKWISE BY 1 ===");
        printMatrix(before);

        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        int prev = matrix[top][left + 1];

        // Left Column
        for (int i = top; i <= bottom; i++) {

            int current = matrix[i][left];
            matrix[i][left] = prev;
            prev = current;
        }

        left++;

        // Bottom Row
        for (int i = left; i <= right; i++) {

            int current = matrix[bottom][i];
            matrix[bottom][i] = prev;
            prev = current;
        }

        bottom--;

        // Right Column
        for (int i = bottom; i >= top; i--) {

            int current = matrix[i][right];
            matrix[i][right] = prev;
            prev = current;
        }

        right--;

        // Top Row
        for (int i = right; i >= left; i--) {

            int current = matrix[top][i];
            matrix[top][i] = prev;
            prev = current;
        }

        System.out.println("\n=== SESUDAH ROTATE COUNTER-CLOCKWISE BY 1 ===");
        printMatrix(matrix);

        return matrix;
    }

    // =========================================================
    // 2-c ROTATE MATRIX 90 DERAJAT (CLOCKWISE)
    // =========================================================
    static int[][] rotate90(int[][] matrix) {

        int[][] before = copyMatrix(matrix);

        System.out.println("\n=== SEBELUM ROTATE 90 DERAJAT ===");
        printMatrix(before);

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rotated = new int[cols][rows];

        for (int j = 0; j < cols; j++) {

            int index = 0;

            for (int i = rows - 1; i >= 0; i--) {

                rotated[j][index++] = matrix[i][j];
            }
        }

        System.out.println("\n=== SESUDAH ROTATE 90 DERAJAT ===");
        printMatrix(rotated);

        return rotated;
    }

    // =========================================================
    // 2-d ROTATE MATRIX 180 DERAJAT
    // =========================================================
    static int[][] rotate180(int[][] matrix) {

        int[][] before = copyMatrix(matrix);

        System.out.println("\n=== SEBELUM ROTATE 180 DERAJAT ===");
        printMatrix(before);

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rotated = new int[rows][cols];

        int r = 0;

        for (int i = rows - 1; i >= 0; i--) {

            int c = 0;

            for (int j = cols - 1; j >= 0; j--) {

                rotated[r][c++] = matrix[i][j];
            }

            r++;
        }

        System.out.println("\n=== SESUDAH ROTATE 180 DERAJAT ===");
        printMatrix(rotated);

        return rotated;
    }

    // =========================================================
    // 3-a ROW-WISE TRAVERSAL
    // =========================================================
    static int[][] rowWiseTraversal(int[][] matrix) {

        System.out.println("\n=== MATRIX ===");
        printMatrix(matrix);

        System.out.println("\n=== ROW-WISE TRAVERSAL ===");

        for (int[] row : matrix) {

            for (int val : row) {

                System.out.print(val + " ");
            }
        }

        System.out.println();

        return matrix;
    }

    // =========================================================
    // 3-b COLUMN-WISE TRAVERSAL
    // =========================================================
    static int[][] columnWiseTraversal(int[][] matrix) {

        System.out.println("\n=== MATRIX ===");
        printMatrix(matrix);

        System.out.println("\n=== COLUMN-WISE TRAVERSAL ===");

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int j = 0; j < cols; j++) {

            for (int i = 0; i < rows; i++) {

                System.out.print(matrix[i][j] + " ");
            }
        }

        System.out.println();

        return matrix;
    }

    // =========================================================
    // 4 PRINT MATRIX IN SPIRAL FORM
    // =========================================================
    static int[][] spiralPrint(int[][] matrix) {

        System.out.println("\n=== MATRIX ===");
        printMatrix(matrix);

        System.out.println("\n=== SPIRAL TRAVERSAL ===");

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // Kiri → Kanan
            for (int i = left; i <= right; i++) {

                System.out.print(matrix[top][i] + " ");
            }

            top++;

            // Atas → Bawah
            for (int i = top; i <= bottom; i++) {

                System.out.print(matrix[i][right] + " ");
            }

            right--;

            // Kanan → Kiri
            if (top <= bottom) {

                for (int i = right; i >= left; i--) {

                    System.out.print(matrix[bottom][i] + " ");
                }

                bottom--;
            }

            // Bawah → Atas
            if (left <= right) {

                for (int i = bottom; i >= top; i--) {

                    System.out.print(matrix[i][left] + " ");
                }

                left++;
            }
        }

        System.out.println();

        return matrix;
    }

    // =========================================================
    // 5 TRANSPOSE MATRIX
    // =========================================================
    static int[][] transposeMatrix(int[][] matrix) {

        int[][] before = copyMatrix(matrix);

        System.out.println("\n=== SEBELUM TRANSPOSE ===");
        printMatrix(before);

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] transpose = new int[cols][rows];

        for (int j = 0; j < cols; j++) {

            for (int i = 0; i < rows; i++) {

                transpose[j][i] = matrix[i][j];
            }
        }

        System.out.println("\n=== SESUDAH TRANSPOSE ===");
        printMatrix(transpose);

        return transpose;
    }

    // =========================================================
    // MAIN PROGRAM
    // =========================================================
    public static void main(String[] args) {

        System.out.println("========== INPUT MATRIX AWAL ==========");

        // Input matrix hanya sekali
        int[][] matrix = inputMatrix();

        List<String> validMenu = Arrays.asList(
                "1-a", "1-b",
                "2-a", "2-b", "2-c", "2-d",
                "3-a", "3-b",
                "4", "5", "6"
        );

        while (true) {

            System.out.println("\n========== MENU MATRIX ==========");
            System.out.println("1-a. Sort the matrix row-wise");
            System.out.println("1-b. Sort the matrix column-wise");
            System.out.println("2-a. Rotate Matrix Clockwise by 1");
            System.out.println("2-b. Rotate Matrix Counter-Clockwise by 1");
            System.out.println("2-c. Rotate a matrix by 90");
            System.out.println("2-d. Rotate a matrix by 180");
            System.out.println("3-a. Row-wise traversal of matrix");
            System.out.println("3-b. Column-wise traversal of matrix");
            System.out.println("4. Print matrix in spiral form");
            System.out.println("5. Transpose matrix");
            System.out.println("6. Quit");

            String choice;

            while (true) {

                System.out.print("\nPilih menu : ");
                choice = input.nextLine().toLowerCase();

                if (validMenu.contains(choice)) {
                    break;
                }

                System.out.println("Menu tidak valid!");
            }

            switch (choice) {

                case "1-a":
                    matrix = sortRowWise(matrix);
                    break;

                case "1-b":
                    matrix = sortColumnWise(matrix);
                    break;

                case "2-a":
                    matrix = rotateClockwiseByOne(matrix);
                    break;

                case "2-b":
                    matrix = rotateCounterClockwiseByOne(matrix);
                    break;

                case "2-c":
                    matrix = rotate90(matrix);
                    break;

                case "2-d":
                    matrix = rotate180(matrix);
                    break;

                case "3-a":
                    matrix = rowWiseTraversal(matrix);
                    break;

                case "3-b":
                    matrix = columnWiseTraversal(matrix);
                    break;

                case "4":
                    matrix = spiralPrint(matrix);
                    break;

                case "5":
                    matrix = transposeMatrix(matrix);
                    break;

                case "6":
                    System.out.println("\nProgram selesai.");
                    return;
            }
        }
    }
}