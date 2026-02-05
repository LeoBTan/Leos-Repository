package ICS3U;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {

    public static void main(String[] args) {   
        Scanner scanner = new Scanner(System.in);

        int numOfnum;
        while (true) {
            System.out.print("Number of Numbers in Equation: ");
            String input = scanner.nextLine().trim();
            try {
                numOfnum = Integer.parseInt(input);
                if (numOfnum < 1) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid positive integer!");
            }
        }

        System.out.println();
        System.out.print("Number #1: ");
        BigDecimal startNumber = scanner.nextBigDecimal();
        scanner.nextLine();

        for (int i = 1; i < numOfnum; i++) {
            System.out.println();
            System.out.print("Equation(+, -, *, /, ^, %, !, sqrt): ");
            String equation = scanner.nextLine().trim();
            System.out.println();

            switch (equation) {
                case "!" -> {
                    startNumber = factorial(startNumber);
                    System.out.println("Result: " + startNumber);
                }
                case "^" -> {
                    System.out.print(startNumber + " raised to the power of (Max: 2,147,483,647): ");
                    int power = scanner.nextInt();
                    scanner.nextLine();
                    startNumber = startNumber.pow(power);
                    System.out.println("Result: " + startNumber);
                }
                case "sqrt" -> {
                    MathContext mc = new MathContext(50, RoundingMode.HALF_UP);
                    startNumber = sqrt(startNumber, mc);
                    System.out.println("Result: " + startNumber);
                }
                default -> {
                    System.out.print("Number: ");
                    BigDecimal newNumber = scanner.nextBigDecimal();
                    scanner.nextLine();

                    switch (equation) {
                        case "+" -> startNumber = startNumber.add(newNumber);
                        case "-" -> startNumber = startNumber.subtract(newNumber);
                        case "*" -> startNumber = startNumber.multiply(newNumber);
                        case "/" -> startNumber = startNumber.divide(newNumber, MathContext.DECIMAL128);
                        case "%" -> startNumber = startNumber.remainder(newNumber);
                        default -> System.out.println("Invalid operation!");
                    }
                    System.out.println("Result: " + startNumber);
                }
            }
        }

        scanner.close();
    }

    public static BigDecimal factorial(BigDecimal n) {
        BigDecimal result = BigDecimal.ONE;
        BigDecimal i = BigDecimal.ONE;

        while (i.compareTo(n) <= 0) {
            result = result.multiply(i);
            i = i.add(BigDecimal.ONE);
        }

        return result;
    }

    public static BigDecimal sqrt(BigDecimal value, MathContext mc) {
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new ArithmeticException("Cannot calculate square root of a negative number");

        if (value.equals(BigDecimal.ZERO))
            return BigDecimal.ZERO;

        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        BigDecimal two = BigDecimal.valueOf(2);

        for (int i = 0; i < mc.getPrecision() + 5; i++) {
            x = x.add(value.divide(x, mc)).divide(two, mc);
        }

        return x.round(mc);
    }
}