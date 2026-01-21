package pizzastore.factorymethod;

public class ArgPizzaStore extends PizzaStore {

    Pizza createPizza(String item) {
        return switch (item) {
            case "cheese" -> new MuzzaPizza();
            case "veggie" -> new VeganFugazzetaPizza();
            case "clam" -> new NapolitanaPizza();
            case "pepperoni" -> new CalabresaPizza();
            default -> null;
        };
    }
}
