package pizzastore.factorymethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PizzaStoreTest {
    NYPizzaStore nyPizzaStore = new NYPizzaStore();
    ArgPizzaStore argPizzaStore = new ArgPizzaStore();
    @Test
    public void argentinianStoreGeneratesDifferentPizzas() {
        assertNotEquals(
                nyPizzaStore.createPizza("veggie").getClass(),
                argPizzaStore.createPizza("veggie").getClass());
        assertNotEquals(
                nyPizzaStore.createPizza("cheese").getClass(),
                argPizzaStore.createPizza("cheese").getClass()
        );

        assertNotEquals(
                nyPizzaStore.createPizza("clam").getClass(),
                argPizzaStore.createPizza("clam").getClass()
        );

        assertNotEquals(
                nyPizzaStore.createPizza("pepperoni").getClass(),
                argPizzaStore.createPizza("pepperoni").getClass()
        );

    }
    @Test
    public void invalidInputReturnsNull() {
        assertNull(argPizzaStore.createPizza("invalid input"));
    }
}
