package ICS3U.DMOJ.J.J4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleEncryption {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        List<String> key = new ArrayList<>(Arrays.asList(read.readLine().split("")));
        List<String> code = new ArrayList<>(Arrays.asList(read.readLine().split("")));

        for (int i = 0; i < code.size(); i++) {
            if (65 > (char) code.get(i).charAt(0) || (char) code.get(i).charAt(0) > 90) {
                code.remove(i);
                i--;
            }
        }

        List<Character> encrypted = new ArrayList<>();

        for (int i = 0; i < code.size(); i++) {
            int cha = (code.get(i).charAt(0) + (key.get(i % key.size()).charAt(0) - 65));
            System.out.println(cha);
            System.out.println("next");
            int temp = 65;
            if (cha > 90) {
                while (cha > 90) {
                    cha--;
                    System.out.println(cha);
                    temp ++;
                    System.out.println("2: " + temp);
                }
                cha = temp;
            }

            encrypted.add((char) (code.get(i).charAt(0) + (key.get(i % key.size()).charAt(0) - 65)));
        }

        for (int i = 0; i < encrypted.size(); i++) {
            System.out.print(encrypted.get(i));
        }
    }
}