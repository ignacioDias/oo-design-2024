package pizzastore.abstractfactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgPizzaStoreTest {
    @Test
    void ingredientFactoryCreatesArgentinianIngredients() {
        PizzaIngredientFactory factory = new ArgPizzaIngredientFactory();

        assertNotNull(factory.createDough());
        assertTrue(factory.createDough() instanceof ThinCrustDough);

        assertNotNull(factory.createSauce());
        assertTrue(factory.createSauce() instanceof MarinaraSauce);

        assertNotNull(factory.createCheese());
        assertTrue(factory.createCheese() instanceof ReggianoCheese);

        Veggies[] veggies = factory.createVeggies();
        assertNotNull(veggies);
        assertEquals(2, veggies.length);
        assertTrue(veggies[0] instanceof Garlic);
        assertTrue(veggies[1] instanceof Onion);

        assertNotNull(factory.createPepperoni());
        assertTrue(factory.createPepperoni() instanceof SlicedPepperoni);

        assertNotNull(factory.createClam());
        assertTrue(factory.createClam() instanceof FreshClams);
    }

    @Test
    void argPizzaStoreCreatesCorrectCheesePizza() {
        PizzaStore store = new ArgPizzaStore();

        Pizza pizza = store.orderPizza("cheese");

        assertNotNull(pizza);
        assertEquals("Pizza Muzzarella", pizza.getName());
        assertTrue(pizza instanceof CheesePizza);
    }

    @Test
    void argPizzaStoreCreatesCorrectVeggiePizza() {
        PizzaStore store = new ArgPizzaStore();

        Pizza pizza = store.orderPizza("veggie");

        assertNotNull(pizza);
        assertEquals("Pizza Fugazzeta vegana", pizza.getName());
        assertTrue(pizza instanceof VeggiePizza);
    }

    @Test
    void argPizzaStoreCreatesCorrectClamPizza() {
        PizzaStore store = new ArgPizzaStore();

        Pizza pizza = store.orderPizza("clam");

        assertNotNull(pizza);
        assertEquals("Pizza Napolitana", pizza.getName());
        assertTrue(pizza instanceof ClamPizza);
    }

    @Test
    void argPizzaStoreCreatesCorrectPepperoniPizza() {
        PizzaStore store = new ArgPizzaStore();

        Pizza pizza = store.orderPizza("pepperoni");

        assertNotNull(pizza);
        assertEquals("Pizza Calabresa", pizza.getName());
        assertTrue(pizza instanceof PepperoniPizza);
    }

    @Test
    void unknownPizzaReturnsNull() {
        PizzaStore store = new ArgPizzaStore();
        Pizza pizza = store.orderPizza("anchoas");
        assertNull(pizza);
    }
}
