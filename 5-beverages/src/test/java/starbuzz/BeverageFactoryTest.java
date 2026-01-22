package starbuzz;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BeverageFactoryTest {

    @Test
    void createDarkRoastWithDoubleMochaAndWhipMedium() {
        Beverage beverage = BeverageFactory.createBeverage("darkRoastDoubleMochaWhip", Size.MEDIUM);
        assertNotNull(beverage);
        assertEquals(Size.MEDIUM, beverage.getSize());
        assertEquals("Dark Roast Coffee, Mocha, Mocha, Whip", beverage.getDescription());

        double expectedCost = 0.99 + 0.25 + 0.25 + 0.15;
        assertEquals(expectedCost, beverage.cost(), 0.0001);
    }

    @Test
    void createDarkRoastWithMilkSmall() {
        Beverage beverage = BeverageFactory.createBeverage("darkRoastMilk", Size.SMALL);
        assertNotNull(beverage);
        assertEquals(Size.SMALL, beverage.getSize());
        assertEquals("Dark Roast Coffee, Milk", beverage.getDescription());

        double expectedCost = 0.99 + 0.10;
        assertEquals(expectedCost, beverage.cost(), 0.0001);
    }

    @Test
    void unknownBeverageTypeThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> BeverageFactory.createBeverage("invalidType", Size.LARGE)
        );
    }
}
