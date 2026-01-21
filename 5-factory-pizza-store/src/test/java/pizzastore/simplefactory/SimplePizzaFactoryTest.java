package pizzastore.simplefactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimplePizzaFactoryTest {
    SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
    @Test
    public void simplePizzaFactoryShouldGiveTheCorrectPizza() {
        Pizza pizza = simplePizzaFactory.createPizza("fugazzeta");
        assertInstanceOf(FugazzetaPizza.class, pizza);
    }
    @Test
    public void invalidPizzaShouldGiveANullObject() {
        Pizza pizza = simplePizzaFactory.createPizza("Invalid pizza");
        assertNull(pizza);
    }
}
