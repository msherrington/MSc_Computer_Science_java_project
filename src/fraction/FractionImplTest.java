package fraction;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FractionImplTest {

    private FractionImpl oneQuarter = new FractionImpl(1, 4);
    private FractionImpl half = new FractionImpl(1,2);
    private FractionImpl threeQuarters = new FractionImpl(3, 4);

    // add comments to each test ???

    @Test
    void stringToInt() {
        assertEquals(1, FractionImpl.stringToInt("    1    "));
        assertThrows(NumberFormatException.class, () -> {
            FractionImpl.stringToInt("    1  1  ");
        });
    }

    @Test
    void add() {
        assertEquals(half.toString(), oneQuarter.add(oneQuarter).toString());
    }

    @Test
    void subtract() {
        assertEquals(oneQuarter.toString(), half.subtract(oneQuarter).toString());
        assertEquals(half.toString(), threeQuarters.subtract(oneQuarter).toString());
    }

    @Test
    void multiply() {
        assertEquals(oneQuarter.toString(), half.multiply(half).toString());
    }

    @Test
    void divide() {
        assertEquals("1", half.divide(half).toString());
    }

    @Test
    void abs() {
    }

    @Test
    void negate() {
    }

    @Test
    void testEquals() {
        // accessing wrong equals method
        assertTrue(half.equals(oneQuarter.add(oneQuarter)));
    }

    @Test
    void inverse() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testToString() {
        assertEquals("1", (new FractionImpl(1)).toString());
        assertEquals("5", (new FractionImpl(5, 1)).toString());
        assertEquals("0/1", (new FractionImpl(0, 1)).toString());
        assertEquals("5", (new FractionImpl("5/1")).toString());
        assertEquals("0/1", (new FractionImpl("0/1")).toString());
    }
}