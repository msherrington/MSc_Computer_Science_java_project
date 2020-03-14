package fraction;
import java.util.Random;


class Main {
    public static void main(String[] args) {
        String string = randomInt() + "/" + randomInt();
        int num = randomInt();
        int denom = randomInt();

        FractionImpl frac = new FractionImpl(string);
        System.out.println("frac1: " + frac.toString());

        FractionImpl frac2 = new FractionImpl(num, denom);
        System.out.println("frac2: " + frac2.toString());

        Fraction frac3 = frac.add(frac2);
        System.out.println("frac1 + frac2: " + frac3.toString());

        Fraction frac4 = frac.subtract(frac2);
        System.out.println("frac1 - frac2: " + frac4.toString());

        Fraction frac5 = frac.multiply(frac2);
        System.out.println("frac1 * frac2: " + frac5.toString());

        Fraction frac6 = frac.divide(frac2);
        System.out.println("frac1 / frac2: " + frac6.toString());

        Fraction frac7 = frac4.abs();
        System.out.println("frac4: " + frac4.toString());
        System.out.println("frac4 abs: " + frac7.toString());

        Fraction frac8 = frac4.negate();
        System.out.println("frac4 negated: " + frac8.toString());

        System.out.println("frac1 equals frac2: " + frac.equals(frac2));

        System.out.println("frac1 inverse: " + frac.inverse());
        System.out.println("frac2 inverse: " + frac2.inverse());

        System.out.println("frac1 compareTo itself: " + frac.negate().compareTo(frac.negate()));
        System.out.println("frac1 compareTo frac2: " + frac.compareTo(frac2));
        System.out.println("frac2 compareTo frac1: " + frac2.compareTo(frac));
    }

    public static int randomInt() {
        return new Random().nextInt(100 + 10) - 100;
    }
}