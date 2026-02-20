package ICS3U.Fun;
import java.util.Scanner;

public class PrintCost {
    public static void main(String[] args) {
        double Total;
        double PLAPrice = 0.026;
        double PETGPrice = 0.046;
        double TPUPrice = 0.054;
        System.out.println("PLA, PETG or TPU");
        Scanner Filament = new Scanner(System.in);
        String FliamentAnswer = Filament.nextLine();
        System.out.println("Amount of Filament in Grams:");
        Scanner Gram = new Scanner(System.in);
        double GramAnswer = Double.parseDouble(Gram.nextLine());
        System.out.println("Type in Service Fee");
        Scanner serviceFee = new Scanner(System.in);
        double serviceFeeAnswer = Double.parseDouble(serviceFee.nextLine());
        if (null != FliamentAnswer) switch (FliamentAnswer) {
            case "PLA" -> {
                Total = ((PLAPrice * GramAnswer) + serviceFeeAnswer);
                System.out.println("Total:$" + Total);
            }
            case "PETG" -> {
                Total = ((PETGPrice * GramAnswer) + serviceFeeAnswer);
                System.out.println("Total:$" + Total);
            }
            case "TPU" -> {
                Total = ((TPUPrice * GramAnswer) + serviceFeeAnswer);
                System.out.println("Total:$" + Total);
            }
            default -> {
            }
        }
    }
}