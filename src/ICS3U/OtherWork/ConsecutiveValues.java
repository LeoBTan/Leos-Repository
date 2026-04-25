package ICS3U.OtherWork;

import java.util.Scanner;

public class ConsecutiveValues {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int countConsecutive = 1, numConsecutive = 0, maxConcecutive = 0;
        int input = scan.nextInt();

        while (input != 0) {
            if (numConsecutive == input) {
                countConsecutive++;
            } else {
                if (maxConcecutive < countConsecutive) {
                    maxConcecutive = countConsecutive;
                }

                numConsecutive = input;
                countConsecutive = 1;
            }
            input = scan.nextInt();
        }

        if (maxConcecutive < countConsecutive) {
            maxConcecutive = countConsecutive;
        }

        System.out.println(maxConcecutive);
    }
}
