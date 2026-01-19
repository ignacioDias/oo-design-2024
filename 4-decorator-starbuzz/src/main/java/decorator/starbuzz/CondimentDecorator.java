package decorator.starbuzz;

import java.util.HashMap;
import java.util.Map;

public abstract class CondimentDecorator extends Beverage {
	Beverage beverage;
	Map<Size, Double> costPerSize = new HashMap<>();
	@Override
	public Size getSize() {
		return beverage.getSize();
	}
	@Override
	public void setSize(Size size) {
		beverage.setSize(size);
	}
	public double cost() {
		return getCostPerSize() + beverage.cost();
	}
	private double getCostPerSize() {
		Size size = this.getSize();
		return costPerSize.get(size);
	}
	public abstract String getDescription();
}
