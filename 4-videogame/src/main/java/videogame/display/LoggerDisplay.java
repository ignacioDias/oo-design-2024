package videogame.display;

import videogame.Fight;
import videogame.Observer;
import videogame.character.Character;

public class LoggerDisplay implements Observer, DisplayElement {
    Fight fight;
    String currentOutput;
    public LoggerDisplay(Fight fight) {
        this.fight = fight;
    }
    @Override
    public void updateStartingFight() {
        currentOutput = "Fight starts!";
        display();
    }

    @Override
    public void updateAttack(Character attacker, Character defender, int damage) {
        currentOutput = attacker + " attacks " + defender + " and does " + damage + " of damage!";
        display();
    }

    @Override
    public void updateWinnerAndLoser(Character winner, Character loser) {
        currentOutput = winner + " wins and " + loser + " loses..";
        display();
    }

    @Override
    public void display() {
        System.out.println(currentOutput);
    }
}
