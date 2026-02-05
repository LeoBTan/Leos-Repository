package ICS3U;
import java.util.Scanner;

public class ConverterCalc {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input Start Base (max: 36):");
        int startBase = scan.nextInt();
        scan.nextLine();

        System.out.println("Input Base " + startBase + " Number:");
        String startNum = scan.nextLine().trim();

        System.out.println("Input End Base (max: 36):");
        int endBase = scan.nextInt();
        scan.nextLine();

        String newNum = Convert(startNum, startBase, endBase);

        if (startBase == 2) {
            System.out.println("Signed or Normal?:");
            String signedNormal = scan.nextLine();

            if (signedNormal.equals("signed")|| signedNormal.equals("Signed")) {
                if (startNum.charAt(0) == '1') {
                    String result = "-" + newNum;
                    System.out.println(result);
                } else {
                    System.out.println(newNum);
                }
            } else {
                System.out.println(newNum);
            }
        } else if (endBase == 2) {
            System.out.println("Signed or Normal?");
            String signedNormal = scan.nextLine();

            if (signedNormal.equals("signed") || signedNormal.equals("Signed")) {
                if (startNum.charAt(0) == '-') {

                }
            }
            System.out.println(newNum);
        }
    }

    public static String Convert(String num, int start, int end) {
        int decimal = Integer.parseInt(num, start);

        return Integer.toString(decimal, end);
    }
}
