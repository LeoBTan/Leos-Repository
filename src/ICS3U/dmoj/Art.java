package ICS3U.DMOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Leo Tan
 * Date: Monday, February 19nd, 2026 at 11:59pm
 * Problem: CCC '20 J3 - Art<br>
 * Description: In order to solve the problem, the coordinates of the points of paint are stored in two seperate lists.
 * Both lists are sorted to figure out the minimum and maximum x and y coordinates.
 * To find the proper coordinates of the frame, the minimum x and y coordinates are subtracted by 1 and the maximum x and y coordinates are added by 1.
 */
public class Art {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in)); // Initialize Buffered Reader(Faster than Scanner).
        int L = Integer.parseInt(read.readLine()); // Input for the number of coordinates to be inputted.

        List<Integer> x = new ArrayList<>(); // Initialize a list to store the x coordinates of the points of paint.
        List<Integer> y = new ArrayList<>(); // Initialize a list to store the y coordinates of the points of paint.

        for (int i = 0; i < L; i++) { // Create a for-loop to recieve the input the same amount of times as the variable L.
            String[] p = read.readLine().split(","); // Get the input of coordinates while splitting the x and y coordinates into an array.

            x.add(Integer.valueOf(p[0])); // Add the x coordinate of the point of paint to the list of x coordinates.
            y.add(Integer.valueOf(p[1])); // Add the y coordinate of the point of paint to the list of y coordinates.
        }

        Collections.sort(x); // Sort the list of x coordinates.
        Collections.sort(y); // Sort the list of y coordinates.

        System.out.println((x.get(0) - 1) + "," + (y.get(0) - 1)); // Print the coordinates of the bottom left corner.
        System.out.println((x.get(L - 1) + 1) + "," + (y.get(L - 1) + 1)); // Print the coordinates of the top right corner.
    }
}
