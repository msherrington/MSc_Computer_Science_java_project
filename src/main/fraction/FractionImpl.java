package main.fraction;

import java.util.*;
import main.pair.Pair;



public class FractionImpl implements Fraction {

//    TODO: re-check comments (params, returns, etc),
//    int overflow (read Java textbook)
    // check method names have not changed
//    double check comments wording- the restructure added main.fraction and main.test all over the place
    // readMe file
    // import order?


    private int numerator, denominator;

    private int getNumerator() {
        return numerator;
    }

    private int getDenominator() {
        return denominator;
    }

    /**
     * Parameters are the <em>numerator</em> and the <em>denominator</em>.
     *
     * If denominator is zero, an <pre>ArithmeticException</pre> error is thrown.
     * Otherwise parameters passed to normalise() method for conversion to lowest terms.
     * Normalised values are set as instance variables.
     *
     * @param numerator: integer
     * @param denominator: integer
     */
    public FractionImpl(int numerator, int denominator) {
        if (denominator != 0) {
            Pair normalised = normalise(numerator, denominator);
            this.numerator = normalised.first();
            this.denominator = normalised.second();
        } else {
            throw new ArithmeticException("Denominator cannot be zero");
        }
    }

    /**
     * Parameter <em>wholeNumber</em> is an integer representing a fraction numerator, which is
     * passed to the primary constructor along with implicit denominator integer of <pre>1</pre>.
     *
     * @param wholeNumber integer representing the numerator
     */
    public FractionImpl(int wholeNumber) {
        this(wholeNumber, 1);
    }

    /**
     * The parameter is a <pre>String</pre> containing either a whole number, or a fraction.
     * String is formatted by formatString() method and passed to the primary constructor.
     *
     * @param fraction the string representation of the fraction
     */
    public FractionImpl(String fraction) {
        this(formatString(fraction, 'N'), formatString(fraction, 'D'));
    }

    /**
     * HELPER METHOD
     * The parameters are a <pre>String</pre> containing either a whole number, or a fraction,
     * and a <pre>Character</pre> identifying which value to return.
     *
     * Relevant fraction value is parsed to integer by stringToInt() method.
     * If identifier is 'D' and fraction value not provided, default return value is <pre>1</pre>.
     *
     * A <pre>InputMismatchException</pre> error is thrown if more than 2 fraction values are passed in.
     * A <pre>NoSuchElementException</pre> error is thrown if the string is empty.
     *
     * @param fraction: string, a fraction value
     * @param identifier: character, either "N" or "D"
     * @return integer, the relevant parsed string value
     */
    static int formatString(String fraction, Character identifier) {
        if (fraction.trim().length() > 0) {
            // create string array of fraction values
            String[] array = fraction.split("/");

            if (array.length <= 2) {

                if (identifier.equals('N')) {
                    return stringToInt(array[0]);
                } else {
                    // return 1 if array[1] does not exist
                    return array.length == 2 ? stringToInt(array[1]) : 1;
                }

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
     * @return integer, the string parameter parsed to int
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
     * Both parameters passed to greatestCommonDivisor() method for a gcd value
     * which is used to divide the params and put the fraction values in their lowest terms.
     *
     * If <em>denom</em> is negative and <em>num</em> is positive, both integers are negated.
     *
     * @param num integer, the numerator to normalise
     * @param denom integer, the denominator to normalise
     * @return Pair instance containing normalised values
     */
    static Pair normalise(int num, int denom) {
        int gcd = greatestCommonDivisor(num, denom);
        num /= gcd;
        denom /= gcd;
        if (denom < 0 && num >= 0) {
            num *= -1;
            denom *= -1;
        }
        return new Pair(num, denom);
    }

    /**
     * HELPER METHOD
     * Recursive method using Euclid's' algorithm.
     * Returns the greatest common divisor of any 2 integers.
     *
     * @param num integer, the pre-normalised numerator
     * @param denom integer, the pre-normalised denominator
     * @return integer, the greatest common divisor
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
            FractionImpl frac = (FractionImpl) obj;
            // return a boolean based on Fractions having equal instance variables
            return getNumerator() == frac.getNumerator() && getDenominator() == frac.getDenominator();
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
        FractionImpl frac = (FractionImpl) o;
        // cast fractions into doubles
        double thisFraction = getNumerator() / (double) getDenominator();
        double otherFraction = frac.getNumerator() / (double) frac.getDenominator();
        // use Double compare to return an integer
        return Double.compare(thisFraction, otherFraction);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (getNumerator() != 0 && getDenominator() == 1) {
            return String.valueOf(getNumerator());
        }
        return String.format("%s/%s", getNumerator(), getDenominator());
    }
}