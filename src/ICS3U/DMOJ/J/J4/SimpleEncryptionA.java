package ICS3U.DMOJ.J.J4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Simple Encryption first iteration.
public class SimpleEncryptionA {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in)); // Initialze BufferedReader to read input.

        String[] key = read.readLine().split(""); // Read the key and store it as an array of strings.
        String message = read.readLine().replaceAll("[^a-zA-Z]", ""); // Read the message, remove all non-alphabetic characters, and store it as a string.
        String[] messageArray = message.split(""); // Split the message into an array of strings.

        // Loop through each character in the key
        for (int i = 0; i < key.length; i++) {
            // Loop through each character in the message array, incrementing by the length of the key to get the proper key character for each message character.
            for (int j = i; j < messageArray.length; j+=key.length) {
                int tempInt = messageArray[j].charAt(0) + (key[i].charAt(0) - 65); // Perform character arithmetic.
                // Keep all values within the range of captial letters.
                if (tempInt > 90) {
                    tempInt -= 26;
                }
                messageArray[j] = String.valueOf((char)tempInt); // Convert the integer back to a character and store it in the message array.
            }
        }
        
        String newString = String.join("", messageArray); // Join the message array back into a string.
        System.out.println(newString); // Print the encrypted message.
    }
}