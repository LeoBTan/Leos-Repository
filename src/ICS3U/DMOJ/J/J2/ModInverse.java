package ICS3U.DMOJ.J.J2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModInverse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int n = 1;

        while (x*n % m != 1) {
            if (n > m) {
                break;
            }
            n++;
        }

        if (n > m) {
            System.out.println("No such integer exists.");
        } else {
            System.out.println(n);
        }
    }
}
