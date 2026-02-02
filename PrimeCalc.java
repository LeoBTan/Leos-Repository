import java.math.BigDecimal;
import java.util.Scanner;

public class PrimeCalc {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input Number");
        BigDecimal num = scan.nextBigDecimal().subtract(BigDecimal.ONE);
        if ((factorial(num).add(BigDecimal.ONE)).remainder(num) == BigDecimal.ZERO) {
            System.out.println("prime");
        } else {
            System.out.println("not prime");
        }
    }

    public static BigDecimal factorial(BigDecimal num) {
        BigDecimal result = BigDecimal.ONE;
        BigDecimal i = BigDecimal.ONE;
    
        while (i.compareTo(num) <= 0) {
        result = result.multiply(i);
        i = i.add(BigDecimal.ONE);
        }

        return result;
    }
}
