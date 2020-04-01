package main.fraction;

import main.pair.Pair;

import java.util.*;


public class FractionImpl implements Fraction {

    private int numerator, denominator;

    private int getNumerator() {
        return numerator;
    }

    private int getDenominator() {
        return denominator;
    }

    /**
     * Parameters <em>numerator</em> and <em>denominator</em> are
     * passed to normalise() method for conversion to lowest terms.
     * Normalised values are then set as instance variables.
     *
     * @param numerator integer
     * @param denominator integer
     * @throws ArithmeticException if denominator is zero
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
     * Parameter <em>wholeNumber</em> is an integer representing a fraction numerator.
     * It is passed to the primary constructor along with an implicit denominator integer of 1.
     *
     * @param wholeNumber integer representing the numerator
     */
    public FractionImpl(int wholeNumber) {
        this(wholeNumber, 1);
    }

    /**
     * Parameter <em>fraction</em> is a string containing either a whole number, or a fraction.
     * The string is processed by the formatString() method and returned integers are passed to the primary constructor.
     *
     * @param fraction string representation of the fraction
     */
    public FractionImpl(String fraction) {
        this(formatString(fraction, 'N'), formatString(fraction, 'D'));
    }

    /**
     * <pre>HELPER METHOD</pre>
     * Parameter <em>fraction</em> is a string containing either a whole number, or a fraction.
     * Parameter <em>identifier</em> is a character identifying which value to return.
     *
     * The relevant fraction value is parsed as integer by the stringToInt() method.
     * If identifier is 'D' and fraction value not provided, the default return value is 1.
     *
     * @param fraction string, fraction value
     * @param identifier character, either "N" or "D"
     * @return integer, value from string after parsing
     * @throws InputMismatchException if fraction string contains more than two fraction values
     * @throws NoSuchElementException if fraction string is empty
     */
    static int formatString(String fraction, Character identifier) {
        if (fraction.trim().length() > 0) {
            // create string array of fraction values
            String[] array = fraction.split("/");

            if (array.length <= 2) {

                if (identifier.equals('N')) {
                    return stringToInt(array[0]);
                } else {
                    // return 1 if denominator does not exist in array
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
     * <pre>HELPER METHOD</pre>
     * Parameter <em>string</em> contains a whole number.
     * Surrounding whitespace is allowed and will be trimmed.
     * Whitespace between integer digits is not allowed.
     *
     * @param string a fraction value
     * @return integer - the string parameter parsed to int
     * @throws NumberFormatException for non-numeric strings
     */
    static Integer stringToInt(String string) {
        try {
            return Integer.parseInt(string.trim());
        } catch (NumberFormatException numeratorError) {
            throw new NumberFormatException(String.format("Fraction value \"%s\" is not a valid non-decimal number.", string));
        }
    }

    /**
     * <pre>HELPER METHOD</pre>
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
     * <pre>HELPER METHOD</pre>
     * Recursive method using Euclid's' algorithm.
     * Returns the greatest common divisor of any two integers.
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
        // create and return a new Fraction with the numerator negated
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
            // return numerator only for non-zero integers
            return String.valueOf(getNumerator());
        }
        return String.format("%s/%s", getNumerator(), getDenominator());
    }
}