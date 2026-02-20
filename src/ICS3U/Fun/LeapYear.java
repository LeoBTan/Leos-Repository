package ICS3U.Fun;
import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int year = scan.nextInt();
        scan.nextLine();

        if (year % 4 == 0){
            if (year % 100 == 0 && year % 400 == 0) {
                System.out.println("Is Leap Year");
            }
            else if (year % 100 == 0) {
                System.out.println("Is Not Leap Year");
            } else {
                System.out.println("Is Leap Year");
            }
        } else {
            System.out.println("Is Not Leap Year");
        }
    }
}
