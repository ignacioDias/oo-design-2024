package decorator.starbuzz;

public class Soy extends CondimentDecorator {
	public Soy(Beverage beverage) {
		costPerSize.put(Size.SMALL, 0.15);
		costPerSize.put(Size.MEDIUM, 0.2);
		costPerSize.put(Size.LARGE, 0.25);
		this.beverage = beverage;
	}
	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

}
