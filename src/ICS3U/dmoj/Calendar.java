package ICS3U.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Calendar {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> intList = new ArrayList<>();
        Collections.addAll(intList, "   ", "  1", "  2", "  3", "  4", "  5", "  6", "  7", "  8", "  9", " 10", " 11", " 12", " 13", " 14", " 15");
        Collections.addAll(intList, " 16", " 17", " 18", " 19", " 20", " 21", " 22", " 23", " 24", " 25", " 26", " 27", " 28", " 29", " 30", " 31", " ");

        String input = read.readLine();
        String[] inputList = input.split(" ");
        int startDate = Integer.parseInt(inputList[0], 10);
        int monthLength = Integer.parseInt(inputList[1], 10);
        int carryValue = 1;
        int fifthYes = 0;

        ArrayList<String> firstWeek = new ArrayList<>();
        ArrayList<String> secondWeek = new ArrayList<>();
        ArrayList<String> thirdWeek = new ArrayList<>();
        ArrayList<String> fourthWeek = new ArrayList<>();
        ArrayList<String> fifthWeek = new ArrayList<>();

        System.out.println("Sun Mon Tue Wed Thr Fri Sat");
        for (int i = 1; i < startDate; i++) {
            firstWeek.add(intList.get(0));
            firstWeek.add(intList.get(32));
        }

        for (int i = 0; i < (8-startDate); i++) {
            firstWeek.add(intList.get(1+i));
            firstWeek.add(intList.get(32));
            ++carryValue;
        }

        for (int i = 0; i < (7); i++) {
            secondWeek.add(intList.get(carryValue));
            secondWeek.add(intList.get(32));
            ++carryValue;
        }

        for (int i = 0; i < (7); i++) {
            thirdWeek.add(intList.get(carryValue));
            thirdWeek.add(intList.get(32));
            ++carryValue;
        }

        for (int i = 0; i < (7); i++) {
            fourthWeek.add(intList.get(carryValue));
            fourthWeek.add(intList.get(32));
            ++carryValue;
        }

        for (int i = 0; i < (7); i++) {
            if (carryValue > monthLength) {
                i = 7;
            } else {
                fifthWeek.add(intList.get(carryValue));
                fifthWeek.add(intList.get(32));
                ++carryValue;
                fifthYes = fifthYes + 2;
            }
        }

        System.out.println(firstWeek.get(0) + firstWeek.get(1) + firstWeek.get(2) + firstWeek.get(3) + firstWeek.get(4) + firstWeek.get(5) + firstWeek.get(6) + firstWeek.get(7) + firstWeek.get(8) + firstWeek.get(9) + firstWeek.get(10) + firstWeek.get(11) + firstWeek.get(12) + firstWeek.get(13));
        System.out.println(secondWeek.get(0) + secondWeek.get(1) + secondWeek.get(2) + secondWeek.get(3) + secondWeek.get(4) + secondWeek.get(5) + secondWeek.get(6) + secondWeek.get(7) + secondWeek.get(8) + secondWeek.get(9) + secondWeek.get(10) + secondWeek.get(11) + secondWeek.get(12) + secondWeek.get(13));
        System.out.println(thirdWeek.get(0) + thirdWeek.get(1) + thirdWeek.get(2) + thirdWeek.get(3) + thirdWeek.get(4) + thirdWeek.get(5) + thirdWeek.get(6) + thirdWeek.get(7) + thirdWeek.get(8) + thirdWeek.get(9) + thirdWeek.get(10) + thirdWeek.get(11) + thirdWeek.get(12) + thirdWeek.get(13));
        System.out.println(fourthWeek.get(0) + fourthWeek.get(1) + fourthWeek.get(2) + fourthWeek.get(3) + fourthWeek.get(4) + fourthWeek.get(5) + fourthWeek.get(6) + fourthWeek.get(7) + fourthWeek.get(8) + fourthWeek.get(9) + fourthWeek.get(10) + fourthWeek.get(11) + fourthWeek.get(12) + fourthWeek.get(13));
        for (int i = 0; i < 14; i++) {
            if (i == fifthYes-1) {
                i = 14;
            } else {
                System.out.print(fifthWeek.get(i));
            }
        }
    }
}