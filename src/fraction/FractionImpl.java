package fraction;
import java.util.*;

public class FractionImpl implements Fraction {

//    TODO: check comments, tests (including division by zero), int overflow (read Java textbook)

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
        if (fraction.trim().length() == 0) {
            throw new NoSuchElementException("Empty string");
        } else {
            String[] array = fraction.split("/");
            if (array.length > 2) {
                throw new InputMismatchException("Too many fraction elements");
            } else {
                String intErrorMsg = "Fraction value must be a valid non-decimal number: \"";

                int num;
                try {
                    num = Integer.parseInt(array[0].trim());
                } catch (NumberFormatException numeratorError) {
                    throw new NumberFormatException(intErrorMsg + array[0] + "\"");
                }

                boolean proceed = true;
                int denom = 0;
                try {
                    denom = Integer.parseInt(array[1].trim());
                } catch (ArrayIndexOutOfBoundsException indexError) {
                    denom = 1;
                } catch (NumberFormatException denominatorError) {
                    proceed = false;
                    throw new NumberFormatException(intErrorMsg + array[1] + "\"");
                } finally {
                    if (proceed) normalise(num, denom);
                }
            }
        }
    }

    /**
     * HELPER METHOD
     * Both params passed to greatestCommonDivisor() method for a gcd value
     * which is used to divide the params and put the fraction values in their lowest terms.
     *
     * If denom is zero, an <pre>ArithmeticException</pre> error is thrown.
     * If denom is negative and num is positive, both integers are negated.
     *
     * Finally mutated params are set to the instance variables.
     *
     * @param num integer, the numerator to normalise
     * @param denom integer, the denominator to normalise
     */
    public void normalise(int num, int denom) {
        if (denom != 0) {
            int gcd = greatestCommonDivisor(num, denom);
            num /= gcd;
            denom /= gcd;
            boolean negate = denom < 0 && num >= 0;
            this.numerator = negate ? num * -1 : num;
            this.denominator = negate ? denom * -1 : denom;
        } else {
            throw new ArithmeticException("Denominator cannot be zero");
        }
    }

    /**
     * HELPER METHOD
     * Recursive method using Euclid's' algorithm.
     * Returns the greatest common denominator of any 2 integers.
     *
     * @param num integer, the pre-normalised numerator
     * @param denom integer, the pre-normalised denominator
     * @return integer
     */
    private static int greatestCommonDivisor(int num, int denom) {
        if (denom == 0) return num;
        return greatestCommonDivisor(denom,num % denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction add(Fraction f) {
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
        // create and return a new Fraction using numerator's absolute value
        return new FractionImpl(Math.abs(this.numerator), this.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        // create and return a new Fraction with numerator negated
        return new FractionImpl(this.numerator * -1, this.denominator);
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
            // if obj is of class Fraction, obtain object instance
            FractionImpl f = (FractionImpl) obj;
            // return a boolean based on Fraction objects being equal
            return super.equals(f);
        }
        return false;
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
        // create and return a new Fraction with numerator and denominator swapped
        return new FractionImpl(this.denominator, this.numerator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Fraction o) {
        // assign fraction to variable
        FractionImpl f = (FractionImpl) o;
        // convert both fraction values into doubles
        double thisFrac = this.numerator / (double) this.denominator;
        double otherFrac = f.numerator / (double) f.denominator;
        // use Double compare to return an integer
        return Double.compare(thisFrac, otherFrac);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (this.denominator == 1) {
            return String.valueOf(this.numerator);
        }
        return String.format("%s/%s", this.numerator, this.denominator);
    }
}