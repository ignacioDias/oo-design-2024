package starbuzz;

public abstract class Beverage {
	String description = "Unknown Beverage";
	Size size = Size.SMALL;

	public void setSize(Size size) {
		this.size = size;
	}
	public Size getSize() {
		return this.size;
	}
	public String getDescription() {
		return description;
	}
	public abstract double cost();
}
