package pizzastore.factorymethod;

public class CalabresaPizza extends Pizza {
    public CalabresaPizza() {
        name = "Calabresa Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
        toppings.add("calabresa");
    }

    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
