package starbuzz;

public class BeverageFactory {

    public static Beverage createBeverage(String type, Size size) {
        Beverage beverage;
        switch (type) {
            case "darkRoastDoubleMochaWhip":
                beverage = new DarkRoast();
                beverage.setSize(size);
                beverage = new Mocha(beverage);
                beverage = new Mocha(beverage);
                beverage = new Whip(beverage);
                return beverage;

            case "darkRoastMilk":
                beverage = new DarkRoast();
                beverage.setSize(size);
                beverage = new Milk(beverage);
                return beverage;

            default:
                throw new IllegalArgumentException("Unknown beverage type");
        }
    }
}
