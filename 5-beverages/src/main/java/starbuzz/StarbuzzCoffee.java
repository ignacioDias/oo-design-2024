package starbuzz;

public class StarbuzzCoffee {
	public static void main(String args[]) {
		Beverage beverage = BeverageFactory.createBeverage("darkRoastDoubleMochaWhip", Size.MEDIUM);
		System.out.println(beverage.getDescription() + " $" + beverage.cost());
	}
}
