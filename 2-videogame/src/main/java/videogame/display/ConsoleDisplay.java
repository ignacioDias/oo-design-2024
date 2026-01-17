package videogame.display;

import videogame.Fight;
import videogame.Observer;
import videogame.event.FightEvent;

public class ConsoleDisplay implements Observer, DisplayElement {
    FightEvent currentEvent;
    Fight fightSubject;

    public ConsoleDisplay(Fight fight) {
        this.fightSubject = fight;
    }

    @Override
    public void display( ) {
        System.out.println(currentEvent.generateDefaultOutput());
    }

    @Override
    public void update() {
        currentEvent = fightSubject.getCurrentEvent();
    }
}
