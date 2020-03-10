package fraction;

public class FractionImpl implements Fraction {

    private int numerator;
    private int denominator;

    /**
     * Parameters are the <em>numerator</em> and the <em>denominator</em>.
     * Normalize the fraction as you create it.
     * For instance, if the parameters are <pre>(8, -12)</pre>, create a <pre>Fraction</pre> with numerator
     * <pre>-2</pre> and denominator <pre>3</pre>.
     *
     * The constructor should throw an <pre>ArithmeticException</pre> if the denominator is zero.
     *
     * @param numerator:
     * @param denominator:
     */
    public FractionImpl(int numerator, int denominator) {
        normalise(numerator, denominator);
    }

    /**
     * The parameter is the numerator and <pre>1</pre> is the implicit denominator.
     *
     * @param wholeNumber representing the numerator
     */
    public FractionImpl(int wholeNumber) {
        normalise(wholeNumber, 1);
    }

    /**
     * The parameter is a <pre>String</pre> containing either a whole number, such as `5` or `-3`, or a fraction,
     * such as "8/-12".
     * Allow blanks around (but not within) integers.
     * The constructor should throw an <pre>ArithmeticException</pre>
     * if given a string representing a fraction whose denominator is zero.
     * <p>
     * You may find it helpful to look at the available String API methods in the Java API.
     *
     * @param fraction the string representation of the fraction
     */
    public FractionImpl(String fraction) {
        fraction = fraction.replace(" ", "");
        String[] array = fraction.split("/");
        if (array.length == 0) {
            // TODO: throw error properly here
        } else if (array.length < 3) {
            int[] ints = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                try {
                    ints[i] = Integer.parseInt(array[i]);
                } catch (NumberFormatException e) {
                    // TODO: throw e exception properly here
                    System.out.println("Cannot parse " + array[i]);
                }
            }
            int num = ints[0];
            int denom = 1;
            try {
                denom = ints[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                // TODO: suppress or ignore the exception
            }
            normalise(num, denom);
        } else {
            System.out.println("error:" + array.length); // TODO: throw error properly
        }
    }

    public void normalise(int num, int denom) {
        // TODO: throw ArithmeticException if denominator zero or less
        if (num == 0) {
            numerator = num;
            denominator = 1;
        } else {
            int gcd = greatestCommonDivisor(num, denom);
            numerator = num / gcd;
            denominator = denom / gcd;
        }
    }

    private static int greatestCommonDivisor(int num, int denom) {
        if (denom == 0) return num;
        return greatestCommonDivisor(denom,num % denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction add(Fraction f) {
        return ((this.numerator * f.denominator) + (this.denominator * f.numerator)) / (this.denominator * f.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction subtract(Fraction f) {
        return ((this.numerator * f.denominator) - (this.denominator * f.numerator)) / (this.denominator * f.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction multiply(Fraction f) {
        return (this.numerator * f.numerator) / (this.denominator * f.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction divide(Fraction f) {
        return (this.numerator * f.denominator) / (this.denominator * f.numerator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction abs() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction inverse() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Fraction o) {
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return null;
    }
}