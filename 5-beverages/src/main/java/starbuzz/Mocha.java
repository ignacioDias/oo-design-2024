package starbuzz;

public class Mocha extends CondimentDecorator {
	public Mocha(Beverage beverage) {
		costPerSize.put(Size.SMALL, 0.2);
		costPerSize.put(Size.MEDIUM, 0.25);
		costPerSize.put(Size.LARGE, 0.3);
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}
 
}
