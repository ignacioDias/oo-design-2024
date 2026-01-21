package pizzastore.factorymethod;

public class NapolitanaPizza extends Pizza {
    public NapolitanaPizza() {
        name = "Napolitana Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
        toppings.add("Tomato");
    }

    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
