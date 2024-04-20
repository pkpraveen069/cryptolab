/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Scanner;
/**
 *
 * @author dchar
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */

    // Function to perform encryption

    // Function to perform Caesar Cipher encryption
    public static String caesarEncrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        if (shift < 0 || shift > 25) {
            return "Sorry, key value exceeds the limit";
        }

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ciphertext.append((char) ((ch - base + shift) % 26 + base));
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    // Function to perform Caesar Cipher decryption
    public static String caesarDecrypt(String ciphertext, int shift) {
        return caesarEncrypt(ciphertext, 26 - shift); // Decryption is just encryption with the opposite shift
    }

    // Function to perform Shift Cipher encryption
    public static String shiftEncrypt(String plaintext, int shift) {
        if (shift < 0 || shift > 25) {
            return "Sorry, key value exceeds the limit";
        }

        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ciphertext.append((char) ((ch - base + shift) % 26 + base));
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    // Function to perform Shift Cipher decryption
    public static String shiftDecrypt(String ciphertext, int shift) {
        return shiftEncrypt(ciphertext, 26 - shift); // Decryption is just encryption with the opposite shift
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Main Menu");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Shift Cipher");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");
        int mainChoice = scanner.nextInt();

        switch (mainChoice) {
            case 1:
                System.out.println("Caesar Cipher Menu");
                System.out.println("1. Encrypt");
                System.out.println("2. Decrypt");

                System.out.print("Enter your choice: ");
                int caesarChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter the text: ");
                String caesarText = scanner.nextLine().toLowerCase();

                System.out.print("Enter the key (0 to 25): ");
                int caesarKey = scanner.nextInt();

                if (caesarChoice == 1) {
                    System.out.println("Encrypted text: " + caesarEncrypt(caesarText, caesarKey).toUpperCase());
                } else if (caesarChoice == 2) {
                    System.out.println("Decrypted text: " + caesarDecrypt(caesarText, caesarKey));
                }
                break;

            case 2:
                System.out.println("Shift Cipher Menu");
                System.out.println("1. Encrypt");
                System.out.println("2. Decrypt");

                System.out.print("Enter your choice: ");
                int shiftChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter the text: ");
                String shiftText = scanner.nextLine().toLowerCase();

                System.out.print("Enter the key (0 to 25): ");
                int shiftKey = scanner.nextInt();

                if (shiftChoice == 1) {
                    System.out.println("Encrypted text: " + shiftEncrypt(shiftText, shiftKey).toUpperCase());
                } else if (shiftChoice == 2) {
                    System.out.println("Decrypted text: " + shiftDecrypt(shiftText, shiftKey));
                }
                break;

            case 3:
                System.out.println("Bye");
                break;

            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }
}