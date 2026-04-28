package ICS3U.DMOJ.J.J4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SimpleEncryptionB {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String key = read.readLine();
        String message = read.readLine().replaceAll("[^a-zA-Z]", "");

        int temp;

        for (int i = 0; i < message.length(); i++) {
            temp = (message.charAt(i) + key.charAt(i % key.length()) - 65);
            if (temp > 90) temp -=26;
            System.out.print((char) temp);
        }
    }
}