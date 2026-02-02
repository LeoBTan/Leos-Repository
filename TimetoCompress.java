package dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TimetoCompress {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(read.readLine());

        for (int i = 0; i < L; i++) {
            String[] lines = read.readLine().split(" ");

            int in = Integer.parseInt(lines[0]);

            for (int d = 0; d < in; d++) {
                System.out.print(lines[1]);
            }
            System.out.println();
        }
    }
}