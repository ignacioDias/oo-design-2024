package simulator;

import simulator.duck.Duck;

import java.util.ArrayList;
import java.util.List;

public class DucksFlock {
    private List<Duck> ducks;

    public DucksFlock() {
        this.ducks = new ArrayList<>();
    }

    public void addDuck(Duck duck) {
        this.ducks.add(duck);
    }
    public void clearDucks() {
        this.ducks = new ArrayList<>();
    }

    public void fly() {
        for(Duck duck : this.ducks) {
            duck.performFly();
        }
    }

    public void quack() {
        for(Duck duck : this.ducks) {
            duck.performQuack();
        }
    }
}
