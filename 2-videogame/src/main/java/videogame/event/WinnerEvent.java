package videogame.event;

import videogame.character.Character;

public class WinnerEvent implements FightEvent {

    Character winner;

    public WinnerEvent(Character winner) {
        this.winner = winner;
    }

    @Override
    public String generateDefaultOutput() {
        return "Winner of fight: " + this.winner;
    }
}
