package decorator.starbuzz;

public class Honey extends CondimentDecorator {

    public Honey(Beverage beverage) {
        this.beverage = beverage;

        costPerSize.put(Size.SMALL, 0.15);
        costPerSize.put(Size.MEDIUM, 0.2);
        costPerSize.put(Size.LARGE, 0.25);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Honey";
    }

}
