package ICS3U.DMOJ.J.J1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Delivedroid2023 {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(read.readLine()) * 50;
        int c = Integer.parseInt(read.readLine()) * 10;

        if (p/50 > c/10) {
            System.out.println(500+p-c);
        } else {
            System.out.println(p-c);
        }
    }
}