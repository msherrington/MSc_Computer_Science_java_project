package main.fraction;
import main.pair.Pair;

import java.util.*;

public class FractionImpl implements Fraction {

//    TODO: re-check comments (params, returns, etc),
//    tests (including division by zero),
//    int overflow (read Java textbook)
    // check method names have not changed
//    double check comments wording- the restructure added main.fraction and main.test all over the place


    private int numerator;
    private int denominator;

    private void setNumerator(int num) {
        this.numerator = num;
    }

    private void setDenominator(int denom) {
        this.denominator = denom;
    }

    private int getNumerator() {
        return this.numerator;
    }

    private int getDenominator() {
        return this.denominator;
    }

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
        Pair normalised = normalise(numerator, denominator);
        this.setNumerator(normalised.first());
        this.setDenominator(normalised.second());
    }

    /**
     * The parameter is the numerator and <pre>1</pre> is the implicit denominator.
     *
     * @param wholeNumber representing the numerator
     */
    public FractionImpl(int wholeNumber) {
        Pair normalised = normalise(wholeNumber, 1);
        this.setNumerator(normalised.first());
        this.setDenominator(normalised.second());
    }

    /**
     * The parameter is a <pre>String</pre> containing either a whole number, or a fraction,
     * Each value (whether 1 or 2) is parsed to integers by stringToInt() method.
     * Then passed to normalise() method for normalisation and setting of instance variables.
     *
     * A <pre>InputMismatchException</pre> error is thrown if more than 2 fraction values are passed in.
     * A <pre>NoSuchElementException</pre> error is thrown if the string is empty.
     *
     * @param fraction the string representation of the fraction
     */
    public FractionImpl(String fraction) {
        if (fraction.trim().length() != 0) {
            // create string array of fraction values
            String[] array = fraction.split("/");

            if (array.length <= 2) {
                // parse array strings to integers
                int num = stringToInt(array[0]);
                int denom = array.length == 2 ? stringToInt(array[1]) : 1;

                // normalise integers and set instance variables
                Pair normalised = normalise(num, denom);
                this.setNumerator(normalised.first());
                this.setDenominator(normalised.second());
            } else {
                // throw error if too many fraction values in fraction string
                throw new InputMismatchException("Too many fraction elements");
            }
        } else {
            // throw error if trimmed string is empty
            throw new NoSuchElementException("Empty string");
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
     * @return Pair instance containing normalised values
     */
    static Pair normalise(int num, int denom) {
        if (denom != 0) {
            int gcd = greatestCommonDivisor(num, denom);
            num /= gcd;
            denom /= gcd;
            if (denom < 0 && num >= 0) {
                num *= -1;
                denom *= -1;
            }
            return new Pair(num, denom);
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
    static int greatestCommonDivisor(int num, int denom) {
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
        int num = (getNumerator() * frac.getDenominator()) + (getDenominator() * frac.getNumerator());
        int denom = getDenominator() * frac.getDenominator();
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
        int num = (getNumerator() * frac.getDenominator()) - (getDenominator() * frac.getNumerator());
        int denom = getDenominator() * frac.getDenominator();
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
        int num = getNumerator() * frac.getNumerator();
        int denom = getDenominator() * frac.getDenominator();
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
        int num = getNumerator() * frac.getDenominator();
        int denom = getDenominator() * frac.getNumerator();
        // create and return a new Fraction using calculated integers
        return new FractionImpl(num, denom);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction abs() {
        // create and return a new Fraction using numerator's absolute value
        return new FractionImpl(Math.abs(getNumerator()), getDenominator());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        // create and return a new Fraction with numerator negated
        return new FractionImpl(getNumerator() * -1, getDenominator());
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
            return getNumerator() == f.getNumerator() && getDenominator() == f.getDenominator();
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
        return new FractionImpl(getDenominator(), getNumerator());
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Fraction o) {
        // initialise object variable
        FractionImpl f = (FractionImpl) o;
        // cast fractions into doubles
        double thisFrac = getNumerator() / (double) getDenominator();
        double otherFrac = f.getNumerator() / (double) f.getDenominator();
        // use Double compare to return an integer
        return Double.compare(thisFrac, otherFrac);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (getDenominator() == 1 && getNumerator() != 0) {
            return String.valueOf(getNumerator());
        }
        return String.format("%s/%s", getNumerator(), getDenominator());
    }
}