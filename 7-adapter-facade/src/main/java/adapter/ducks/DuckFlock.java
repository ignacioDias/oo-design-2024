package adapter.ducks;

import java.util.ArrayList;
import java.util.List;

public class DuckFlock implements Duck {
    private List<Duck> ducks;

    public DuckFlock() {
        this.ducks = new ArrayList<>();
    }
    public DuckFlock(List<Duck> ducks) {
        this.ducks = ducks;
    }
    public void addDuck(Duck duck) {
        ducks.add(duck);
    }

    public void removeDuck(Duck duck) {
        ducks.remove(duck);
    }

    @Override
    public void quack() {
        for(Duck d : ducks) {
            d.quack();
        }
    }

    @Override
    public void fly() {
        for(Duck d : ducks) {
            d.fly();
        }
    }
}
