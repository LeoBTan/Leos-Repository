package ICS3U.Work;
import java.util.Scanner;

public class ChequeDate {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int year = scan.nextInt();
        scan.nextLine();
        int month = scan.nextInt();
        String wordMonth = null;
        scan.nextLine();
        int day = scan.nextInt();
        scan.nextLine();

        switch (month) {
            case 1 -> wordMonth = "January";
            case 2 -> wordMonth = "February";
            case 3 -> wordMonth = "March";
            case 4 -> wordMonth = "April";
            case 5 -> wordMonth = "May";
            case 6 -> wordMonth = "June";
            case 7 -> wordMonth = "July";
            case 8 -> wordMonth = "August";
            case 9 -> wordMonth = "September";
            case 10 -> wordMonth = "October";
            case 11 -> wordMonth = "November";
            case 12 -> wordMonth = "December";
        }
        System.out.println(wordMonth + " " + day + ", " + year);
    }
}