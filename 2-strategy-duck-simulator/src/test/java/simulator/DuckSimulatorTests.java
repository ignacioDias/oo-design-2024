package simulator;

import org.junit.jupiter.api.Test;
import simulator.duck.*;
import simulator.quackbehavior.*;
import simulator.flybehavior.*;

public class DuckSimulatorTests {

    @Test
    void testChangingQuackBehaviorFromDuck() {
        Duck duck = new MallardDuck();
        duck.performQuack();
        QuackBehavior newQuackInstance = new SynthesizedQuack(); //Different kind of quack
        duck.setQuackBehavior(newQuackInstance);
        duck.performQuack();
    }

    @Test
    void testChangingFlyBehaviorFromDuck() {
        Duck duck = new ModelDuck();
        duck.performFly();
        FlyBehavior newFlyInstance = new FlyWithWings(); //Different kind of fly
        duck.setFlyBehavior(newFlyInstance);
        duck.performFly();
    }

    @Test
    void flockMakesAllDucksFlyAndQuack() {
        Duck duck1 = new MallardDuck();
        Duck duck2 = new ModelDuck();
        DucksFlock flock = new DucksFlock();

        flock.addDuck(duck1);
        flock.addDuck(duck2);

        flock.fly();
        flock.quack();
    }

}
