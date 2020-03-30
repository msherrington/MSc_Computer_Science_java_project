package main.fraction;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import main.pair.Pair;

import static main.fraction.FractionImpl.*;

class FractionImplTest {

    private static FractionImpl minusTwo;
    private static FractionImpl minusOne;
    private static FractionImpl minusHalf;
    private static FractionImpl zero;
    private static FractionImpl oneQuarter;
    private static FractionImpl half;
    private static FractionImpl threeQuarters;
    private static FractionImpl one;
    private static FractionImpl two;

    @BeforeAll
    static void setUp() {
        minusTwo = new FractionImpl(-2);
        minusOne = new FractionImpl(-1);
        minusHalf = new FractionImpl(-1, 2);
        zero = new FractionImpl(0);
        oneQuarter = new FractionImpl(1, 4);
        half = new FractionImpl(1,2);
        threeQuarters = new FractionImpl(3, 4);
        one = new FractionImpl(1);
        two = new FractionImpl(2);
    }

    // add more crazy function values into testing??
    // or at least change integers to fractions e.g. 5/8
    // and test top heavy fractions?

    // think of other ways to test, outside of testing each method

    @Test
    void testFormatString() {
        // assert formatted numerator value
        assertEquals(formatString("3/2", 'N'), 3);

        // assert formatted denominator value
        assertEquals(formatString("3/5", 'D'), 5);

        // assert default denominator value
        assertEquals(formatString("5", 'D'), 1);

        // assert InputMismatchException for too many fraction values
        assertThrows(InputMismatchException.class, () -> {
            formatString("3/2/1", 'N');
        });

        // assert NoSuchElementException for empty string
        assertThrows(NoSuchElementException.class, () -> {
            formatString("", 'N');
        });
    }

    @Test
    void testStringToInt() {
        // assert string is trimmed and parsed as integer
        assertEquals(1, stringToInt("    1    "));

        // assert error is thrown when string contains whitespace, decimals or letters
        String[] testValues = {"1  1", "1.0", "abc"};
        for (String value: testValues) {
            assertThrows(NumberFormatException.class, () -> {
                stringToInt(value);
            });
        }
    }

    @Test
    void testNormalise() {
        // assert fraction is normalised to it's lowest terms
        Pair normalised = normalise(10,15);
        assertEquals(normalised.first(), 2);
        assertEquals(normalised.second(), 3);

        // assert correct format for one negative value
        normalised = normalise(2,-3);
        assertEquals(normalised.first(), -2);
        assertEquals(normalised.second(), 3);

        // assert correct format for two negative values
        normalised = normalise(-1,-3);
        assertEquals(normalised.first(), 1);
        assertEquals(normalised.second(), 3);
    }

    @Test
    void testGreatestCommonDivisor() {
        // assert gcd for positive values
        assertEquals(greatestCommonDivisor(8, 6), 2);

        // assert gcd for one negative value
        assertEquals(greatestCommonDivisor(-8, 6), -2);
        assertEquals(greatestCommonDivisor(5, -3), -1);

        // assert gcd for negative values
        assertEquals(greatestCommonDivisor(-7, -9), -1);

        // assert gcd for zero values, all possibilities
        assertEquals(greatestCommonDivisor(7, 0), 7);
        assertEquals(greatestCommonDivisor(-5, 0), -5);
        assertEquals(greatestCommonDivisor(0, 5), 5);
        assertEquals(greatestCommonDivisor(0, -7), -7);
        assertEquals(greatestCommonDivisor(0, 0), 0);
    }

    @Test
    void testAdd() {
        // assert addition for positive fractions
        assertEquals(oneQuarter.add(oneQuarter).toString(), half.toString());

        // assert addition when one fraction is negative
        assertEquals(one.add(minusOne).toString(), zero.toString());
        assertEquals(minusOne.add(one).toString(), zero.toString());

        // assert addition when both fractions are negative
        assertEquals(minusOne.add(minusOne).toString(), minusTwo.toString());
    }

    @Test
    void testSubtract() {
        // assert subtraction for positive fractions
        assertEquals(half.subtract(oneQuarter).toString(), oneQuarter.toString());

        // assert subtraction when one fraction is negative
        assertEquals(one.subtract(minusOne).toString(), two.toString());
        assertEquals(minusOne.subtract(one).toString(), minusTwo.toString());

        // assert subtraction when both fractions are negative
        assertEquals(one.subtract(minusOne).toString(), two.toString());

        // assert subtraction of zero
        assertEquals(half.subtract(zero).toString(), half.toString());
    }

    @Test
    void testMultiply() {
        // assert multiplication for positive fractions
        assertEquals(half.multiply(half).toString(), oneQuarter.toString());

        // assert multiplication when one fraction is negative
        assertEquals(one.multiply(minusOne).toString(), minusOne.toString());
        assertEquals(minusOne.multiply(two).toString(), minusTwo.toString());

        // assert multiplication when both fractions are negative
        assertEquals(minusOne.multiply(minusOne).toString(), one.toString());

        // assert multiplication by zero
        assertEquals(minusOne.multiply(zero).toString(), zero.toString());
    }

    @Test
    void testDivide() {
        // assert division for positive fractions
        assertEquals(half.divide(half).toString(), one.toString());

        // assert division when one fraction is negative
        assertEquals(one.divide(minusOne).toString(), minusOne.toString());
        assertEquals(minusOne.divide(two).toString(), minusHalf.toString());

        // assert division when both fractions are negative
        assertEquals(minusOne.divide(minusOne).toString(), one.toString());

        // assert ArithmeticException for division by zero
        assertThrows(ArithmeticException.class, () -> {
            one.divide(zero);
        });
    }

    @Test
    void testAbs() {
        // assert absolute value of a positive fraction
        assertEquals(one.abs().toString(), one.toString());

        // assert absolute value of a negative fraction
        assertEquals(minusOne.abs().toString(), one.toString());

        // assert absolute value of zero
        assertEquals(zero.abs().toString(), zero.toString());
    }

    @Test
    void testNegate() {
        // assert negation of a positive fraction
        assertEquals(one.negate().toString(), minusOne.toString());

        // assert negation of a negative fraction
        assertEquals(minusOne.negate().toString(), one.toString());

        // assert negation of zero
        assertEquals(zero.negate().toString(), zero.toString());
    }

    @Test
    void testEquals() {
        // assert positive fractions are of equal value
        assertTrue(half.equals(oneQuarter.add(oneQuarter)));

        // assert negative fractions are of equal value
        assertTrue(minusOne.equals(minusHalf.add(minusHalf)));

        // assert fractions are NOT of equal value
        assertFalse(half.equals(minusOne));

        // assert parameter not a Fraction object
        assertFalse(half.equals(new Pair(2,3)));

    }

    @Test
    void testInverse() {
        // assert inverse of positive fraction
        assertEquals(two.inverse().toString(), half.toString());

        // assert inverse of negative fraction
        assertEquals(minusTwo.inverse().toString(), minusHalf.toString());

        // assert ArithmeticException for inverse of zero
        assertThrows(ArithmeticException.class, () -> {
            zero.inverse();
        });
    }

    @Test
    void testCompareTo() {
        // assert "greater than" comparison
        assertEquals(one.compareTo(minusOne), 1);

        // assert "less than" comparison
        assertEquals(minusOne.compareTo(one), -1);

        // assert "equal" comparison
        assertEquals(half.compareTo(new FractionImpl("1/2")), 0);
    }

    @Test
    void testToString() {
        // assert positive integer to string
        assertEquals(two.toString(), "2");

        // assert positive fraction to string
        assertEquals(threeQuarters.toString(), "3/4");

        // assert negative integer to string
        assertEquals(minusOne.toString(), "-1");

        // assert negative fraction to string
        assertEquals(minusHalf.toString(), "-1/2");

        // assert zero to string
        assertEquals(zero.toString(), "0/1");
    }

    @Test
    void testProjectRequirements() {
        // assert zero representation
        assertEquals(zero.toString(), "0/1");

        // assert ArithmeticException for zero denominator
        assertThrows(ArithmeticException.class, () -> {
            new FractionImpl(1, 0);
        });

        // assert denominator always positive
        assertEquals(new FractionImpl("1/-2").toString(), "-1/2");
        assertEquals(new FractionImpl("-3/-4").toString(), "3/4");

        // assert reduced form fraction
        assertEquals(new FractionImpl("8/-12").toString(), "-2/3");
    }
}