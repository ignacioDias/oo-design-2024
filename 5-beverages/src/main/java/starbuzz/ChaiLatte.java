package starbuzz;

public class ChaiLatte extends Beverage {
    public ChaiLatte() {
        this.description = "Chai Latte";
    }
    @Override
    public double cost() {
        return 1.99;
    }
}
