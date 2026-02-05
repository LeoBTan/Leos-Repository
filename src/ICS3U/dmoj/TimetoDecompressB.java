package ICS3U.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: Leo Tan
 * Date: Monday, February 2nd, 2026 at 11:59pm
 * Problem: CCC '19 J2 - Time to Decompress
 * Description: In order to solve the problem, the number of lines inuputted is the number of times a for-loop is run.
 * Inside the for-loop, arrays are created in order to store all inputted lines.
 * Integers are separated from strings and used to create another for-loop.
 * The new for-loop loops the amount of times that the string has to be printed.
 */
public class TimetoDecompressB {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in)); // Initialize Buffered Reader(Faster than Scanner).

        int Lines = Integer.parseInt(read.readLine()); // Input for the number of lines to decompress.

        for (int i = 0; i < Lines; i++) { // Create a for-loop to recieve the input the same amount of times as the variable Lines.
            String[] linesArray = read.readLine().split(" "); // Recieve the input of the line.

            int lineInt = Integer.parseInt(linesArray[0]); // Separate the Integer from the rest of the line.

            for (int d = 0; d < lineInt; d++) { // Create a for-loop to print the requested string the correct number of times.
                System.out.print(linesArray[1]); // Print the requested string.
            }
            System.out.println(); // Print a new line to format the output correctly
        }
    }
}