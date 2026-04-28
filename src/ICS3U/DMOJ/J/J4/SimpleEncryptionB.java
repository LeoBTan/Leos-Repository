package ICS3U.DMOJ.J.J4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * Author: Leo Tan
 * Date: Monday, April 27, 2026
 * Problem: CCC '04 J4 - Simple Encryption
 * Description: Using strings and .charAt() to perform character arithmetic to encrypt the message.
 */
public class SimpleEncryptionB {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in)); // Initialze BufferedReader to read input.

        String key = read.readLine(); // Read the key and store it as a string
        String message = read.readLine().replaceAll("[^a-zA-Z]", ""); // Read the message, remove all non-alphabetic characters, and store it as a string.

        int temp; // Initialize a temporary variable to store the result of the character arithmetic.

        // Loop through each character in the message
        for (int i = 0; i < message.length(); i++) {
            temp = (message.charAt(i) + key.charAt(i % key.length()) - 65); // Perfrom character arithmetic with the proper key character adjustment.
            if (temp > 90) temp -=26; // Keep all values within the range of captial letters.
            System.out.print((char) temp); // Print the encrypted character without a newline to abide by the output format.
        }
    }
}