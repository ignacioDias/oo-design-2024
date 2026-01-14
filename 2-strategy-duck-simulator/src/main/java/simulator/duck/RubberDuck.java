package simulator.duck;

import simulator.flybehavior.FlyNoWay;
import simulator.quackbehavior.*;

public class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Squeak();
    }

    public void display() {
        System.out.println("I'm a rubber duck");
    }
}
