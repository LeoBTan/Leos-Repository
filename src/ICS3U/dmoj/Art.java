package ICS3U.DMOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Art {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(read.readLine());

        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        for (int i = 0; i < L; i++) {
            String[] p = read.readLine().split(",");

            x.add(Integer.valueOf(p[0]));
            y.add(Integer.valueOf(p[1]));
        }

        Collections.sort(x);
        Collections.sort(y);

        System.out.println((x.get(0) - 1) + "," + (y.get(0) - 1));
        System.out.println((x.get(L - 1) + 1) + "," + (y.get(L - 1) + 1));
    }
}
