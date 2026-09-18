package ICS4U;
//Make an int overflow. Print the value right before it wraps and right after. Explain why the sign flips.
//Make a double lie. Find two decimal numbers whose sum prints as something a calculator would never show. Explain what 0.1 really looks like in binary.
//Cast something too big. Take a double larger than Integer.MAX_VALUE, cast it to int, and print the result. Explain what you got and why.
//The compound-assignment trap. byte b = 10; b += 300; — does it compile? What is b afterward? Explain.

public class NumberLine {
    public static void main (String[] args) {
        int overflow = 2147483647;
        System.out.println(overflow);
        ++overflow;
        System.out.println(overflow);

        double decimal = 0.1 + 0.2;
        System.out.println(decimal);

        int smaller = (int) 2147483648.5;
        System.out.println(smaller);

        byte b = 10;
                System.out.println(b);
                b += 300;
                System.out.println(b);
    }
}