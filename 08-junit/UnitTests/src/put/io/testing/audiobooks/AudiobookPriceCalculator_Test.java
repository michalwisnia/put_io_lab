package put.io.testing.audiobooks;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

//5.1 - whitebox
//5.2 - 4
public class AudiobookPriceCalculator_Test {
    private Customer customer;
    private static Audiobook audiobook;
    private static AudiobookPriceCalculator calculator;

    @BeforeAll
    public static void setUp()
    {
        audiobook = new Audiobook("audiobook", 50);
        calculator = new AudiobookPriceCalculator();
    }

    @Test
    public void testCalculateSub()
    {
        customer = new Customer("customer1", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testCalculateStandard()
    {
        customer = new Customer("customer2", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(50, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testCalculateSilver()
    {
        customer = new Customer("customer3", Customer.LoyaltyLevel.SILVER, false);
        assertEquals(45, calculator.calculate(customer,audiobook));
    }

    @Test
    public void testCalculateGold()
    {
        customer = new Customer("customer4", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(40, calculator.calculate(customer, audiobook));
    }

}

