package ICS3U.Fun;
import java.util.Scanner;

public class RandomNumber {
    public static void main(String[] args) {
        System.out.println("Min:");
        Scanner min = new Scanner(System.in);
        int Min = min.nextInt();
        System.out.println("Max:");
        Scanner max = new Scanner(System.in);
        int Max = max.nextInt();
        int random = (Min + (int)(Math.random() * ((Max - Min) + 1)));
        System.out.println(random);
    }
}
