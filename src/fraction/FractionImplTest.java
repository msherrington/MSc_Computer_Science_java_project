package fraction;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FractionImplTest {

    private static FractionImpl minusTwo;
    private static FractionImpl minusOne;
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
        zero = new FractionImpl(0);
        oneQuarter = new FractionImpl(1, 4);
        half = new FractionImpl(1,2);
        threeQuarters = new FractionImpl(3, 4);
        one = new FractionImpl(1);
        two = new FractionImpl(2);
    }

    // add more crazy function values into testing

    // think of other ways to test, outside of testing each method

    @Test
    void testStringToInt() {
        // assert string is trimmed and parsed as integer
        assertEquals(1, FractionImpl.stringToInt("    1    "));

        // assert error is thrown when string contains whitespace, decimals or letters
        String[] testValues = {"1  1", "1.0", "abc"};
        for (String value: testValues) {
            assertThrows(NumberFormatException.class, () -> {
                FractionImpl.stringToInt(value);
            });
        }
    }

    @Test
    void testNormalise() {
        // assert fraction is normalised to it's lowest terms
        Pair normalised = FractionImpl.normalise(10,15);
        assertEquals(normalised.first(), 2);
        assertEquals(normalised.second(), 3);

        // assert correct format for one negative value
        normalised = FractionImpl.normalise(2,-3);
        assertEquals(normalised.first(), -2);
        assertEquals(normalised.second(), 3);

        // assert correct format for two negative values
        normalised = FractionImpl.normalise(-1,-3);
        assertEquals(normalised.first(), 1);
        assertEquals(normalised.second(), 3);

        // assert error is thrown when passing in denominator of zero
        assertThrows(ArithmeticException.class, () -> {
            FractionImpl.normalise(1, 0);
        });
    }

    @Test
    void testAdd() {
        // assert addition for positive fractions
        assertEquals(oneQuarter.add(oneQuarter).toString(), half.toString());

        // assert addition when one fraction is negative
        assertEquals(one.add(minusOne).toString(), zero.toString());

        // assert addition when both fractions are negative
        assertEquals(minusOne.add(minusOne).toString(), minusTwo.toString());
    }

    @Test
    void testSubtract() {
        // assert subtraction for positive fractions
        assertEquals(half.subtract(oneQuarter).toString(), oneQuarter.toString());

        // assert subtraction when one fraction is negative
        assertEquals(one.subtract(minusOne).toString(), two.toString());

        // assert subtraction when both fractions are negative
        assertEquals(one.subtract(minusOne).toString(), two.toString());
    }

    @Test
    void testMultiply() {
        // assert multiplication for positive fractions
        assertEquals(half.multiply(half).toString(), oneQuarter.toString());

        // assert multiplication when one fraction is negative
        assertEquals(one.multiply(minusOne).toString(), minusOne.toString());

        // assert multiplication when both fractions are negative
        assertEquals(minusOne.multiply(minusOne).toString(), one.toString());
    }

    @Test
    void testDivide() {
        // assert division for positive fractions
        assertEquals(half.divide(half).toString(), one.toString());

        // assert division when one fraction is negative
        assertEquals(one.divide(minusOne).toString(), minusOne.toString());

        // assert division when both fractions are negative
        assertEquals(minusOne.divide(minusOne).toString(), one.toString());
    }

    @Test
    void testAbs() {
        // assert absolute value for a positive fraction
        assertEquals(one.abs().toString(), one.toString());

        // assert absolute value for a negative fraction
        assertEquals(minusOne.abs().toString(), one.toString());
    }

    @Test
    void testNegate() {
        // assert negation of a positive fraction
        assertEquals(one.negate().toString(), minusOne.toString());

        // assert negation of a negative fraction
        assertEquals(minusOne.negate().toString(), one.toString());
    }

    @Test
    void testEquals() {
        assertTrue(half.equals(oneQuarter.add(oneQuarter)));
        assertFalse(half.equals(oneQuarter));
    }

    @Test
    void testInverse() {
        assertEquals(two.inverse().toString(), half.toString());
    }

    @Test
    void testCompareTo() {
        assertEquals(one.compareTo(minusOne), 1);
        assertEquals(minusOne.compareTo(one), -1);
        assertEquals(half.compareTo(new FractionImpl(1,2)), 0);
    }

    @Test
    void testToString() {
        assertEquals("0/1", (new FractionImpl(0)).toString());
        assertEquals("0/1", (new FractionImpl(0, 1)).toString());
        assertEquals("0/1", (new FractionImpl("0")).toString());
        assertEquals("0/1", (new FractionImpl("0/1")).toString());
        assertEquals("0/1", (new FractionImpl(" 0 / 1 ")).toString());

        assertEquals("1", (new FractionImpl(1)).toString());
        assertEquals("1", (new FractionImpl(1, 1)).toString());
        assertEquals("1", (new FractionImpl("1")).toString());
        assertEquals("1", (new FractionImpl("1/1")).toString());
        assertEquals("1", (new FractionImpl(" 1 / 1 ")).toString());
        assertEquals("1", (new FractionImpl("    1    /    1    ")).toString());

        assertEquals("5", (new FractionImpl(5, 1)).toString());
        assertEquals("5", (new FractionImpl("5/1")).toString());

        assertNotEquals(5, (new FractionImpl(5)).toString());
    }
}