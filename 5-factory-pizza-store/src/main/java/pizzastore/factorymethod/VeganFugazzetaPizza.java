package pizzastore.factorymethod;

public class VeganFugazzetaPizza extends Pizza {
    public VeganFugazzetaPizza() {
        name = "Vegan Fugazzeta Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Vegan Mozzarella Cheese");
        toppings.add("Onion");
    }

    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
