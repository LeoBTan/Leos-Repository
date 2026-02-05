package ICS3U.DMOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConveryorSushi {
    public static void main(String[] args) throws IOException {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

       String r = reader.readLine();
       int rr = Integer.parseInt(r);

       String g = reader.readLine();
       int gg = Integer.parseInt(g);

       String b = reader.readLine();
       int bb = Integer.parseInt(b);

       System.out.println(rr*3 + gg*4 + bb*5);
    }
    
}
