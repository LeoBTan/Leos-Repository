package ICS3U.Fun;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        double Count = 0;
        Scanner Words = new Scanner(System.in);
        String word = Words.nextLine();
        String[] counter = word.split(" ");

        for (String i : counter) {
            Count++;
        }
        System.out.println(Count);
    }
}
