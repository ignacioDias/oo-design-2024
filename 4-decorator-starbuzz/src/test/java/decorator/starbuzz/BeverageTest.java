package decorator.starbuzz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeverageTest {
    @Test
    public void calculateCostIsCorrectForAGivenBeverage() {
        Beverage chaiLatte = new ChaiLatte();
        chaiLatte = new Honey(chaiLatte);
        chaiLatte = new VanillaSyrup(chaiLatte);

        assertEquals(2.36, chaiLatte.cost(), 0.001);
    }
    @Test
    public void creationOfABeverageGeneratesACorrectDescription() {
        Beverage chaiLatte = new ChaiLatte();
        chaiLatte = new Honey(chaiLatte);
        chaiLatte = new VanillaSyrup(chaiLatte);

        assertEquals(chaiLatte.getDescription(), "Chai Latte, Honey, Vanilla Syrup");
    }
    @Test
    public void doubleMochaSoyLatteWithWhip() {
        Beverage beverage = new HouseBlend();
        beverage = new Soy(beverage);
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);

        assertEquals(1.54, beverage.cost(), 0.001);
        assertEquals("House Blend Coffee, Soy, Mocha, Mocha, Whip", beverage.getDescription());
    }
    @Test
    public void sizeOfBeverageShouldModifyPrice() {

        Beverage smallSizeBeverage = new DarkRoast();
        smallSizeBeverage.setSize(Size.SMALL);
        smallSizeBeverage = new Milk(smallSizeBeverage);

        Beverage mediumSizeBeverage = new DarkRoast();
        mediumSizeBeverage.setSize(Size.MEDIUM);
        mediumSizeBeverage = new Milk(mediumSizeBeverage);

        Beverage largeSizeBeverage = new DarkRoast();
        largeSizeBeverage.setSize(Size.LARGE);
        largeSizeBeverage = new Milk(largeSizeBeverage);

        assertTrue(smallSizeBeverage.cost() < mediumSizeBeverage.cost());
        assertTrue(mediumSizeBeverage.cost() < largeSizeBeverage.cost());
    }


}
