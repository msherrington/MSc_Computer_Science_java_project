package fraction;
import java.util.*;

public class FractionImpl implements Fraction {

//    TODO: check comments, tests (including division by zero), int overflow (read Java textbook)

    int numerator;
    int denominator;

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
     * The parameter is a <pre>String</pre> containing either a whole number, or a fraction,
     * A <pre>NoSuchElementException</pre> error is thrown if the string is empty.
     * A <pre>InputMismatchException</pre> error is thrown if more than 2 fraction values are passed in.
     *
     * Each value (whether 1 or 2) is parsed to integers by stringToInt() method.
     * Then passed to normalise() method for normalisation and setting of instance variables.
     *
     * @param fraction the string representation of the fraction
     */
    public FractionImpl(String fraction) {
        if (fraction.trim().length() == 0) {
            // throw error if trimmed string is empty
            throw new NoSuchElementException("Empty string");
        } else {
            // create string array of fraction values
            String[] array = fraction.split("/");

            // parse array strings to integers
            if (array.length <= 2) {
                int num = stringToInt(array[0]);
                int denom;

                if (array.length == 2) {
                    denom = stringToInt(array[1]);
                } else {
                    denom = 1;
                }
                // normalise integers and set as instance variables
                normalise(num, denom);
            } else {
                // throw error if too many fraction values in fraction string
                throw new InputMismatchException("Too many fraction elements");
            }
        }
    }

    /**
     * HELPER METHOD
     * The parameter is a <pre>String</pre> containing a whole number.
     * Surrounding whitespace is allowed and will be trimmed.
     *
     * A <pre>NumberFormatException</pre> error is thrown for non-numeric strings
     * (including decimals, alphabetic letters, spaces between digits, etc).
     *
     * @param string string, a fraction value
     * @return int, the string parameter parsed
     */
    static Integer stringToInt(String string) {
        try {
            return Integer.parseInt(string.trim());
        } catch (NumberFormatException numeratorError) {
            throw new NumberFormatException("Fraction value must be a valid non-decimal number: \"" + string + "\"");
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
    void normalise(int num, int denom) {
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
    int greatestCommonDivisor(int num, int denom) {
        if (denom == 0) return num;
        return greatestCommonDivisor(denom,num % denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction add(Fraction f) {
        // initialise object variable
        FractionImpl frac = (FractionImpl) f;
        // calculate new numerator and denominator
        int num = (this.numerator * frac.denominator) + (this.denominator * frac.numerator);
        int denom = this.denominator * frac.denominator;
        // create and return a new Fraction using calculated integers
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction subtract(Fraction f) {
        // initialise object variable
        FractionImpl frac = (FractionImpl) f;
        // calculate new numerator and denominator
        int num = (this.numerator * frac.denominator) - (this.denominator * frac.numerator);
        int denom = this.denominator * frac.denominator;
        // create and return a new Fraction using calculated integers
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction multiply(Fraction f) {
        // initialise object variable
        FractionImpl frac = (FractionImpl) f;
        // calculate new numerator and denominator
        int num = this.numerator * frac.numerator;
        int denom = this.denominator * frac.denominator;
        // create and return a new Fraction using calculated integers
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction divide(Fraction f) {
        // initialise object variable
        FractionImpl frac = (FractionImpl) f;
        // calculate new numerator and denominator
        int num = this.numerator * frac.denominator;
        int denom = this.denominator * frac.numerator;
        // create and return a new Fraction using calculated integers
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
            // if obj is of class Fraction, initialise object variable
            FractionImpl f = (FractionImpl) obj;
            // return a boolean based on Fractions having equal instance variables
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
        // initialise object variable
        FractionImpl f = (FractionImpl) o;
        // cast fractions into doubles
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
        if (this.denominator == 1 && this.numerator != 0) {
            return String.valueOf(this.numerator);
        }
        return String.format("%s/%s", this.numerator, this.denominator);
    }
}