package ICS3U.DMOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PositioningPetersPaintings {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        String[] nums = input.split(" ");
        List<Integer> intNums = new ArrayList<>();
        
        for(String s : nums) {
            int num = Integer.parseInt(s);
            intNums.add(num);
        }

        int a = intNums.get(0);
        //System.out.println(a);
        int b = intNums.get(1);
        //System.out.println(b);
        int c = intNums.get(2);
        //System.out.println(c);
        int d = intNums.get(3);
        //System.out.println(d);

        if (a == c && b == d) {
            System.out.println((4 * a) + (2 * b));
        } else if (a > c && b > d && a == b && c == d) {
            System.out.println((2 * c) + (2 * a) + (2 * b));
        } else if (a < c && b < d && a == b && c == d) {
            System.out.println((2 * a) + (2 * c) + (2 * d));
        } else if (a != b && c != d) {
            if (a > b && c > d) {
                
            }
        }
    }
    
}
