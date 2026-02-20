package ICS3U.Work;
import java.util.Scanner;

public class Simpleton {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("What is your income?");
        double income = scan.nextDouble();
        System.out.println("What is the cost of your house?");
        double houseCost = scan.nextDouble();
        System.out.println("How many children do you have?");
        int totalChildren = scan.nextInt();
        System.out.println("How many of your children are in school?");
        int schoolChildren = scan.nextInt();
        double tax;

        if (houseCost > 8000) {
            tax = income * 0.18;
        } else {
            tax = (income - 10000) * 0.18;
            if (tax < 0) {tax = 0;}
        }

        for (int i = 0; i < (totalChildren); i++) {
            tax = tax-500;
        }

        for (int i = 0; i < (schoolChildren); i++) {
            tax = tax - 500;
        }

        if (tax < 0) {
            if (houseCost < 6000 && totalChildren > 2 && schoolChildren >= 1) {
            } else {
                tax = 0;
            }
        }

        if (tax > 2000) {
            tax = tax * 1.15;
        }

        System.out.println("Your total tax amount is: " + tax);
    }
}
