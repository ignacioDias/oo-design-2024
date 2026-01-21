package pizzastore.factorymethod;

public class MuzzaPizza extends Pizza {
    public MuzzaPizza() {
        name = "Muzzarella Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
    }

    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
