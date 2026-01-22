package starbuzz;

public class Milk extends CondimentDecorator {
	public Milk(Beverage beverage) {
		costPerSize.put(Size.SMALL, 0.1);
		costPerSize.put(Size.MEDIUM, 0.15);
		costPerSize.put(Size.LARGE, 0.2);
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

}
