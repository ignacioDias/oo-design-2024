package adapter.ducks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DuckTest {
    @Test
    public void quackAndFlyWorksForDuckFlock() {
        DuckFlock duckFlock = new DuckFlock();
        duckFlock.addDuck(new MallardDuck());
        duckFlock.addDuck(new MallardDuck());

        duckFlock.fly();
        duckFlock.quack();
    }
    @Test
    public void testForAdapterClasses() {
        DuckFlock duckFlock = new DuckFlock();

        Drone superDrone = new SuperDrone();
        Duck droneAdapter = new DroneAdapter(superDrone);
        duckFlock.addDuck(droneAdapter);

        Turkey wildTurkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(wildTurkey);
        duckFlock.addDuck(turkeyAdapter);

        duckFlock.addDuck(new MallardDuck());

        duckFlock.fly();
        duckFlock.quack();

    }
}
