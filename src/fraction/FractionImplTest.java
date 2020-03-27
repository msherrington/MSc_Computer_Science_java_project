package fraction;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FractionImplTest {

    private static FractionImpl minusOne;
    private static FractionImpl zero;
    private static FractionImpl oneQuarter;
    private static FractionImpl half;
    private static FractionImpl threeQuarters;
    private static FractionImpl one;
    private static FractionImpl two;

    @BeforeAll
    static void setUp() {
        minusOne = new FractionImpl(-1);
        zero = new FractionImpl(0);
        oneQuarter = new FractionImpl(1, 4);
        half = new FractionImpl(1,2);
        threeQuarters = new FractionImpl(3, 4);
        one = new FractionImpl(1);
        two = new FractionImpl(1);
    }

    // add comments to each test ???

    @Test
    void stringToInt() {
        assertEquals(1, FractionImpl.stringToInt("    1    "));

        String[] testValues = {"1  1", "1.0", "abc"};
        for (String value: testValues) {
            assertThrows(NumberFormatException.class, () -> {
                FractionImpl.stringToInt(value);
            });
        }
    }

    @Test
    void normalise() {
        assertEquals(1, FractionImpl.stringToInt("    1    "));

        assertThrows(ArithmeticException.class, () -> {
            FractionImpl.normalise(value);
        });
    }

    @Test
    void add() {
        assertEquals(oneQuarter.add(oneQuarter).toString(), half.toString());
        assertEquals(one.add(minusOne).toString(), zero.toString());
    }

    @Test
    void subtract() {
        assertEquals(half.subtract(oneQuarter).toString(), oneQuarter.toString());
        assertEquals(threeQuarters.subtract(oneQuarter).toString(), half.toString());
    }

    @Test
    void multiply() {
        assertEquals(half.multiply(half).toString(), oneQuarter.toString());
        assertEquals(one.multiply(half).toString(), half.toString());
        assertEquals(minusOne.multiply(minusOne).toString(), one.toString());
    }

    @Test
    void divide() {
        assertEquals(half.divide(half).toString(), one.toString());
        assertEquals(oneQuarter.divide(oneQuarter).toString(), one.toString());
        assertEquals(minusOne.divide(minusOne).toString(), one.toString());
    }

    @Test
    void abs() {
        assertEquals(minusOne.abs().toString(), one.toString());
    }

    @Test
    void negate() {
    }

    @Test
    void equals() {
        assertTrue(half.equals(oneQuarter.add(oneQuarter)));
        assertTrue(threeQuarters.equals(oneQuarter.add(half)));
        assertFalse(half.equals(oneQuarter));
    }

    @Test
    void inverse() {
    }

    @Test
    void compareTo() {
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