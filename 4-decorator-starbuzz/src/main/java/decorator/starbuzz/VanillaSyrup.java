package decorator.starbuzz;

public class VanillaSyrup extends CondimentDecorator {

    public VanillaSyrup(Beverage beverage) {
        costPerSize.put(Size.SMALL, 0.22);
        costPerSize.put(Size.MEDIUM, 0.27);
        costPerSize.put(Size.LARGE, 0.32);
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Vanilla Syrup";
    }

}
