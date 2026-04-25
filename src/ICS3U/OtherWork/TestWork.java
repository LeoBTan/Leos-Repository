package ICS3U.OtherWork;

import java.util.Arrays;
import java.util.Scanner;

public class TestWork {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
int totScores = 0, totScore = 0;
int zeros = 0, ones = 0, twos = 0, threes = 0, fours = 0, fives = 0;
int sixes = 0, sevens = 0, eights = 0, nines = 0, tens = 0;
int input = scan.nextInt();

while(input >= 0) {
    if(input <= 10) {
    	totScore += input;
        totScores++;
        
        switch(input) {
            case 0 -> zeros++;
            case 1 -> ones++;
            case 2 -> twos++;
            case 3 -> threes++;
            case 4 -> fours++;
            case 5 -> fives++;
            case 6 -> sixes++;
            case 7 -> sevens++;
            case 8 -> eights++;
            case 9 -> nines++;
            case 10 -> tens++;
        }
    }
    input = scan.nextInt();
}

System.out.println("Score   # of Occurrences");
System.out.println("0   " + zeros);
System.out.println("1   " + ones);
System.out.println("2   " + twos);
System.out.println("3   " + threes);
System.out.println("4   " + fours);
System.out.println("5   " + fives);
System.out.println("6   " + sixes);
System.out.println("7   " + sevens);
System.out.println("8   " + eights);
System.out.println("9   " + nines);
System.out.println("10   " + tens);

System.out.println("Mean=" + totScore/totScores);
    }
}
