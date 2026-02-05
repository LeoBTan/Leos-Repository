package ICS3U.DMOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CanadianCalorie {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String burger = read.readLine();
        String side = read.readLine();
        String drink = read.readLine();
        String dessert = read.readLine();
        int tot = 0;

        switch (burger) {
            case "1" -> tot = tot + 461;
            case "2" -> tot = tot + 431;
            case "3" -> tot = tot + 420;
            default -> {
            }
        }

        switch (side) {
            case "1" -> tot = tot + 100;
            case "2" -> tot = tot + 57;
            case "3" -> tot = tot + 70;
            default -> {
            }
        }

        switch (drink) {
            case "1" -> tot = tot + 130;
            case "2" -> tot = tot + 160;
            case "3" -> tot = tot + 118;
            default -> {
            }
        }

        switch (dessert) {
            case "1" -> tot = tot + 167;
            case "2" -> tot = tot + 266;
            case "3" -> tot = tot + 75;
            default -> {
            }
        }

        System.out.println("Your total Calorie count is " + tot + ".");
    }
    
}
