package dmoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RollerCoaster {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(read.readLine());
        int c = Integer.parseInt(read.readLine());
        int p = Integer.parseInt(read.readLine());

        if (n > c*p) {
            System.out.println("no");
        } else {
            System.out.println("yes");
        }
    }
}
