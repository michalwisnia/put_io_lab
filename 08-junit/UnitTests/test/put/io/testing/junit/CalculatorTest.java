package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private static Calculator calculator;

    @BeforeEach
    public static void setup() {
        calculator = new Calculator();
        // Testy nie przestają działać, gdy zamienia się BeofreEach na BeforeAll, ponieważ nie zmieniają się żadne zmienne
    }

    @Test
    public void testAdd()
    {
        assertEquals(4, calculator.add(2, 2));
        assertEquals(16, calculator.add(-2, 18));
        assertEquals(-59, calculator.add(-9, -50));
        assertEquals(124, calculator.add(124, 0));
    }

    @Test
    public void testMult()
    {
        assertEquals(25, calculator.multiply(5, 5));
        assertEquals(221, calculator.multiply(13, 17));
        assertEquals(-308, calculator.multiply(-11, 28));
        assertEquals(0, calculator.multiply(0, -5));

    }

    @Test
    public void testAddPositiveNumbers()
    {
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(-1, 1));
    }
}