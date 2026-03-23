package ICS3U.DMOJ.J.J2;

import java.util.Scanner;

public class ModInverse {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int m = scan.nextInt();
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
