package videogame.display;

import videogame.Fight;
import videogame.Observer;
import videogame.event.FightEvent;

public class StatisticsDisplay implements Observer, DisplayElement {
    Fight fight;

    public StatisticsDisplay(Fight fight) {
        this.fight = fight;
    }

    @Override
    public void update() {
        this.currentEvent = fight.getCurrentEvent();
    }

    @Override
    public void display() {

    }
}
