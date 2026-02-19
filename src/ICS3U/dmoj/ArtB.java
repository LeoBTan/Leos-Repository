package ICS3U.DMOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArtB {
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

        int minX = 10000000, minY = 10000000, maxX = 0, maxY = 0;

        int L = Integer.parseInt(scan.readLine());

        for (int i = 0; i < L; i++) {
            String[] l = scan.readLine().split(",");
            int x = Integer.parseInt(l[0]), y = Integer.parseInt(l[1]);

            if (x <= minX) {
                minX = x;
            }
            if (x >= maxX) {
                maxX = x;
            }

            if (y <= minY) {
                minY = y;
            }
            if (y >= maxY) {
                maxY = y;
            }
        }

        System.out.println((minX - 1) + "," + (minY - 1));
        System.out.println((maxX + 1) + "," + (maxY + 1));
    }
}