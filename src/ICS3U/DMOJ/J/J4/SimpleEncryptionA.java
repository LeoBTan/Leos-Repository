package ICS3U.DMOJ.J.J4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleEncryptionA {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String[] key = read.readLine().split("");
        String message = read.readLine().replaceAll("[^a-zA-Z]", "");
        String[] messageArray = message.split("");

        for (int i = 0; i < key.length; i++) {
            for (int j = i; j < messageArray.length; j+=key.length) {
                int tempInt = messageArray[j].charAt(0) + (key[i].charAt(0) - 65);
                if (tempInt > 90) {
                    tempInt -= 26;
                }
                messageArray[j] = String.valueOf((char)tempInt);
            }
        }
        
        String newString = String.join("", messageArray);
        System.out.println(newString);
    }
}