/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ex1c;

/**
 *
 * @author sibis
 */



import java.util.Scanner;

public class Ex1c {

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int offset = Character.toUpperCase(key.charAt(i % keyLength)) - 'A';
                char encryptedChar = (char) ((currentChar - base + offset) % 26 + base);
                ciphertext.append(Character.toUpperCase(encryptedChar));
            } else {
                // Non-alphabetic characters remain unchanged
                ciphertext.append(currentChar);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int offset = Character.toUpperCase(key.charAt(i % keyLength)) - 'A';
                // Ensure the result is positive by adding 26
                char decryptedChar = (char) ((currentChar - base - offset + 26) % 26 + base);
                decryptedText.append(decryptedChar);
            } else {
                // Non-alphabetic characters remain unchanged
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose operation (1 for encryption, 2 for decryption): ");
        int operation = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (operation != 1 && operation != 2) {
            System.out.println("Bye");
            scanner.close();
            return;
        }

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        // Check if the input text contains only alphabetic characters
        if (!text.matches("[a-zA-Z]+")) {
            System.out.println("Sorry, input must be a word.");
            scanner.close();
            return;
        }

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        if (operation == 1) {
            String encryptedText = encrypt(text, key);
            System.out.println("Encrypted Text: " + encryptedText);
        } else if (operation == 2) {
            String decryptedText = decrypt(text, key);
            System.out.println("Decrypted Text: " + decryptedText);
        }

        scanner.close();
    }
}