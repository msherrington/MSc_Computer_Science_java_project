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
        if (fraction.equals("")) {
            System.out.println("No array");
            // TODO: throw error properly here
        } else {
            String[] array = fraction.split("/");
            if (array.length < 3) {
                int num = Integer.parseInt(array[0]);
                try {
                    normalise(num, Integer.parseInt(array[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("index error");
                    normalise(num, 1);
                }
                System.out.printf("%s/%s\n", this.numerator, this.denominator);
            } else {
                System.out.print("too many slashes");
                // TODO: handle this error properly
            }
        }
    }

    public void normalise(int num, int denom) {
        if (denom == 0) {
            System.out.println("ArithmeticException, denom cannot be zero");
//            throw new ArithmeticException("Divide by zero");
        } else {
            int gcd = greatestCommonDivisor(num, denom);
            this.numerator = num / gcd;
            this.denominator = denom / gcd;
//            System.out.printf("%s/%s\n", this.numerator, this.denominator);
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
        // TODO: CHECK THE CALCULATIONS AND USING VARIABLES IN CORRECT PLACES
//        a = this.numerator;
//        b = this.denominator;
//        c = f.numerator;
//        d = f.denominator;
//        return (ad + bc)/bd
        FractionImpl frac = (FractionImpl) f;
        int num = (this.numerator * frac.denominator) + (this.denominator * frac.numerator);
        int denom = this.denominator * frac.denominator;
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction subtract(Fraction f) {
        // TODO: CHECK THE CALCULATIONS AND USING VARIABLES IN CORRECT PLACES
//        a = this.numerator;
//        b = this.denominator;
//        c = f.numerator;
//        d = f.denominator;
//        return (ad - bc)/bd;
        FractionImpl frac = (FractionImpl) f;
        int num = (this.numerator * frac.denominator) - (this.denominator * frac.numerator);
        int denom = this.denominator * frac.denominator;
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction multiply(Fraction f) {
        // TODO: CHECK THE CALCULATIONS AND USING VARIABLES IN CORRECT PLACES
//        a = this.numerator;
//        b = this.denominator;
//        c = f.numerator;
//        d = f.denominator;
//        return (a*c)/(b*d);
        FractionImpl frac = (FractionImpl) f;
        int num = this.numerator * frac.numerator;
        int denom = this.denominator * frac.denominator;
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction divide(Fraction f) {
        // TODO: CHECK THE CALCULATIONS AND USING VARIABLES IN CORRECT PLACES
//        a = this.numerator;
//        b = this.denominator;
//        c = f.numerator;
//        d = f.denominator;
//        return (a*d)/(b*c);
        FractionImpl frac = (FractionImpl) f;
        int num = this.numerator * frac.denominator;
        int denom = this.denominator * frac.numerator;
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction abs() {
        return new FractionImpl(Math.abs(this.numerator), this.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        return new FractionImpl(this.numerator * -1, this.denominator * -1);
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
        if (obj instanceof Fraction) {
            FractionImpl f = (FractionImpl) obj;
            return this.numerator == f.numerator && this.denominator == f.denominator;
        }
        return false;
        // TODO throw error for wrong class type (try/catch?)
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
        return new FractionImpl(this.denominator, this.numerator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Fraction o) {
        FractionImpl f = (FractionImpl) o;
        double thisFrac = this.numerator / (double) this.denominator;
        double otherFrac = f.numerator / (double) f.denominator;
        return Integer.compare(Double.compare(thisFrac, otherFrac), 0);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (this.denominator == 1) {
            return this.numerator + "";
        }
        return this.numerator + "/" + this.denominator;
    }
}