import java.util.Scanner;

public class HillCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input for encryption or decryption
        System.out.println("Enter 1 for Encryption or 2 for Decryption:");
        int choice = scanner.nextInt();
        if (choice == 3) {
            System.out.println("Bye");
            return;
        }

        // Encryption
        if (choice == 1) {
            // Take input plaintext
            System.out.println("Enter plain text (lower case):");
            scanner.nextLine(); // Consume newline
            String plainText = scanner.nextLine();

            // Check if input is a word
            if (!plainText.matches("[a-z]+")) {
                System.out.println("Sorry, input must be a word");
                return;
            }

            // Convert plaintext to uppercase
            plainText = plainText.toUpperCase();

            // Take input for matrix size
            System.out.println("Enter size of the matrix (rows columns):");
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();

            // Check if size is positive
            if (rows < 0 || columns < 0) {
                System.out.println("Sorry, input must be a positive number");
                return;
            }
            // Check if matrix is square
            if (rows != columns) {
                System.out.println("Sorry, Matrix is not a square matrix");
                return;
            }

            // Take input for matrix values
            System.out.println("Enter matrix values in row wise:");
            int[][] matrix = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = scanner.nextInt();
                    if(matrix[i][j] < 0){
                        System.out.println("Sorry,input must be a positive number");
                        return;
                    }    
                }
            }
            // Perform encryption
            String cipherText = encrypt(plainText, matrix);
            System.out.println("Cipher text: " + cipherText);
        }
        // Decryption
        else if (choice == 2) {
            // Take input for cipher text
            System.out.println("Enter cipher text (upper case):");
            scanner.nextLine(); // Consume newline
            String cipherText = scanner.nextLine();

            // Check if input is uppercase
            if (!cipherText.matches("[A-Z]+")) {
                System.out.println("Sorry, input must be a word");
                return;
            }

            // Convert cipher text to uppercase
            cipherText = cipherText.toUpperCase();

            // Take input for matrix size
            System.out.println("Enter size of the matrix (rows columns):");
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();

            // Check if size is positive
            if (rows < 0 || columns < 0) {
                System.out.println("Sorry, input must be a positive number");
                return;
            }
            // Check if matrix is square
            if (rows != columns) {
                System.out.println("Sorry, Matrix is not a square matrix");
                return;
            }

            // Take input for matrix values
            System.out.println("Enter matrix values in row wise:");
            int[][] matrix = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = scanner.nextInt();
                    if(matrix[i][j] < 0){
                        System.out.println("Sorry,input must be a positive number");
                        return;
                    }
                }
            }
            // Perform decryption
            String plainText = decrypt(cipherText, matrix);
            System.out.println("Plain text: " + plainText);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static String encrypt(String plainText, int[][] key) {
        int n = key.length;
        StringBuilder cipherText = new StringBuilder();

        // Convert plain text to numerical representation based on A=0, B=1, ..., Z=25
        int[] plainNums = new int[plainText.length()];
        for (int i = 0; i < plainText.length(); i++) {
            plainNums[i] = plainText.charAt(i) - 'A';
        }

        // Encrypt each chunk of the plaintext
        for (int i = 0; i < plainText.length(); i += n) {
            int[] chunk = new int[n];
            for (int j = 0; j < n; j++) {
                if (i + j < plainText.length()) {
                    chunk[j] = plainNums[i + j];
                } else {
                    chunk[j] = 0; // Pad with zero if chunk is incomplete
                }
            }
            // Multiply chunk by key matrix
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += key[j][k] * chunk[k];
                }
                // Append the encrypted character to the cipher text
                cipherText.append((char) ((sum % 26) + 'A'));
            }
        }

        return cipherText.toString();
    }

    public static String decrypt(String cipherText, int[][] key) {
        int n = key.length;
        StringBuilder plainText = new StringBuilder();

        // Calculate the modular inverse of the determinant of the key matrix
        int det = determinant(key, n);
        int detInv = modInverse(det, 26);

        // Calculate the adjugate of the key matrix
        int[][] adj = adjugate(key, n);

        // Calculate the inverse of the key matrix
        int[][] invKey = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                invKey[i][j] = (adj[i][j] * detInv) % 26;
                if (invKey[i][j] < 0) {
                    invKey[i][j] += 26; // Ensure positive result
                }
            }
        }

        // Convert cipher text to numerical representation based on A=0, B=1, ..., Z=25
        int[] cipherNums = new int[cipherText.length()];
        for (int i = 0; i < cipherText.length(); i++) {
            cipherNums[i] = cipherText.charAt(i) - 'A';
        }

        // Decrypt each chunk of the cipher text
        for (int i = 0; i < cipherText.length(); i += n) {
            int[] chunk = new int[n];
            for (int j = 0; j < n; j++) {
                chunk[j] = cipherNums[i + j];
            }
            // Multiply chunk by inverse of key matrix
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += invKey[j][k] * chunk[k];
                }
                // Append the decrypted character to the plain text
                plainText.append((char) ((sum % 26) + 'A'));
            }
        }

        return plainText.toString();
    }

    // Function to calculate determinant of a matrix
    public static int determinant(int[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        int det = 0;
        for (int i = 0; i < n; i++) {
            det += matrix[0][i] * cofactor(matrix, 0, i);
        }
        return det;
    }

    // Function to calculate the cofactor of a matrix element
    public static int cofactor(int[][] matrix, int row, int col) {
        return (int) Math.pow(-1, row + col) * determinant(minor(matrix, row, col), matrix.length - 1);
    }

    // Function to compute the minor of a matrix element
    public static int[][] minor(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int[][] minor = new int[n - 1][n - 1];
        int rowIndex = 0, colIndex = 0;
        for (int i = 0; i < n; i++) {
            if (i != row) {
                colIndex = 0;
                for (int j = 0; j < n; j++) {
                    if (j != col) {
                        minor[rowIndex][colIndex] = matrix[i][j];
                        colIndex++;
                    }
                }
                rowIndex++;
            }
        }
        return minor;
    }

    // Function to calculate the adjugate of a matrix
    public static int[][] adjugate(int[][] matrix, int n) {
        int[][] adj = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adj[j][i] = cofactor(matrix, i, j);
            }
        }
        return adj;
    }

    // Function to compute the modular inverse of a number
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }
}