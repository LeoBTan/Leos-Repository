package ICS3U.DMOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArtB {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());

        int[] x = new int[L];
        int[] y = new int[L];

        for (int i = 0; i < L; i++) {
            String[] l = br.readLine().split(",");

            x[i] = Integer.parseInt(l[0]);
            y[i] = Integer.parseInt(l[1]);
        }

        Arrays.sort(x);
        Arrays.sort(y);

        System.out.println((x[0] - 1) + "," + (y[0] - 1));
        System.out.println((x[L - 1] + 1) + "," + (y[L - 1] + 1));
    }
}
