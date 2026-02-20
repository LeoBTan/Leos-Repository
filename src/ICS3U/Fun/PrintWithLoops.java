package ICS3U.Fun;
public class PrintWithLoops {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.println(i+1);
        }
        System.out.println();

        for (int i = 0; i < 12; i+= 2) {
            System.out.println((int) (2 * Math.pow(i, 2) + 5));
        }
    }
}
