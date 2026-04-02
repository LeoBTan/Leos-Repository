package ICS3U.DMOJ.J.J2;

import java.util.Scanner;

public class OccupyParking {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int spaces = scan.nextInt();
        scan.nextLine();

        String[] yesterday = scan.nextLine().split("");
        String[] today = scan.nextLine().split("");

        int count = 0;

        for (int i = 0; i < spaces; i++) {
            if (yesterday[i].equals("C") && today[i].equals("C")) {
                count++;
            }
        }

        System.out.println(count);
    }
}
