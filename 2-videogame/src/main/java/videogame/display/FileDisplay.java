package videogame.display;

import videogame.Fight;
import videogame.Observer;
import videogame.event.FightEvent;

public class FileDisplay implements DisplayElement, Observer {
    public Fight fight;
    public FightEvent currentEvent;

    public FileDisplay(Fight fight) {
        this.fight = fight;
    }

    @Override
    public void display() {

    }
    @Override
    public void update() {
        this.
    }
}
