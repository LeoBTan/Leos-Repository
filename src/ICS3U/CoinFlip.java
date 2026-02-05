package ICS3U;
public class CoinFlip {
    public static void main(String[] args) {
        double a;
        double heads = 0;
        double tails = 0;
        long x = 10000000l;
        for (int i = 0; i < x; i++) {
            a = Math.random();
            if (a > 0.5) {
                ++heads;
            } else {
                ++tails;
            }
        }
        System.out.println("P(Heads) = " + (heads/x));
        System.out.println("P(Tails) = " + (tails/x));
    }
}
