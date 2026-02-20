package ICS3U.DMOJ.J.J3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: Leo Tan
 * Date: Monday, February 19nd, 2026 at 11:59pm
 * Problem: CCC '20 J3 - Art<br>
 * Description: In order to solve the problem, the coordinates of the points of paint are stored in temporary variables.
 * These variables are compared to the current minimum and maximum x and y coordinates to find the minimum and maximum x and y coordinates.
 * If the temporary variable is less than the minimum x or y coordinate, the temporary variable becomes the new minimum x or y coordinate.
 * If the temporary variable is greater than the maximum x or y coordinate, the temporary variable becomes the new maximum x or y coordinate.
 * To find the proper coordinates of the frame, the minimum x and y coordinates are subtracted by 1 and the maximum x and y coordinates are added by 1.
 */
public class ArtB {
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in)); // Initialize Buffered Reader(Faster than Scanner).

        int minX = 10000000, minY = 10000000, maxX = 0, maxY = 0; // Initialize variables to store the minimum and maximum x and y coordinates of the points of paint.

        int L = Integer.parseInt(scan.readLine()); // Input for the number of coordinates to be inputted.

        for (int i = 0; i < L; i++) { // Create a for-loop to recieve the input the same amount of times as the variable L.
            String[] l = scan.readLine().split(","); // Get the input of coordinates while splitting the x and y coordinates into an array.
            int x = Integer.parseInt(l[0]), y = Integer.parseInt(l[1]); // Store the x and y coordinates of the point of paint in temporary variables.

            if (x < minX) { // Create an if-statement to check if the temporary variable for the x coordinate is less than to the current minimum x coordinate.
                minX = x; // Sets the temporary variable for the x coordinate as the new minimum x coordinate if the if-statement is true.
            }
            if (x > maxX) { // Create an if-statement to check if the temporary variable for the x coordinate is greater than to the current maximum x coordinate.
                maxX = x; // Sets the temporary variable for the x coordinate as the new maximum x coordinate if the if-statement is true.
            }

            if (y < minY) { // Create an if-statement to check if the temporary variable for the y coordinate is less than to the current minimum y coordinate.
                minY = y; // Sets the temporary variable for the y coordinate as the new minimum y coordinate if the if-statement is true.
            }
            if (y > maxY) { // Create an if-statement to check if the temporary variable for the y coordinate is greater than to the current maximum y coordinate.
                maxY = y; // Sets the temporary variable for the y coordinate as the new maximum y coordinate if the if-statement is true.
            }
        }

        System.out.println((minX - 1) + "," + (minY - 1)); // Print the coordinates of the bottom left corner.
        System.out.println((maxX + 1) + "," + (maxY + 1)); // Print the coordinates of the top right corner.
    }
}