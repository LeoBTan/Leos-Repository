package ICS3U.dmoj;

import java.util.Scanner; // Import Scanner in order to read inputs.

/**
 * Author: Leo Tan
 * Date: Monday, February 2nd, 2026 at 11:59pm
 * Problem: CCC '19 J2 - Time to Decompress
 * Description: Using for loops to loop the amount of lines and the amount of times the chosen character has to be printed
 */
public class TimeToDecompress {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Initialize scanner

        int Lines = scan.nextInt(); // Get the first integer L, the number of lines in the message

        for (int i = 0; i < Lines; i++) { // Create a for loop that loops until there are the same number of lines as stated in variable L
            int numberOfRepeats = scan.nextInt(); // Get the integer that states how many times the character should be printed

            String stringToRepeat = scan.nextLine().strip(); // Get the character from the line and remove any white space

            for (int d = 0; d < numberOfRepeats; d++) { // Create a for loop to loop printing the same character
                System.out.print(stringToRepeat); // Print the desired character
            }
            System.out.println(""); // Add a new line for formatting
        }
    }
}