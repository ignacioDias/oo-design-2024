package videogame.display;

import videogame.Fight;
import videogame.Observer;
import videogame.character.Character;

public class StatisticsDisplay implements Observer, DisplayElement {
    Fight fight;
    String currentOutput;
    public StatisticsDisplay(Fight fight) {
        this.fight = fight;
    }
    @Override
    public void updateStartingFight() {
        currentOutput = "Fight starts!";
        display();
    }

    @Override
    public void updateAttack(Character attacker, Character defender, int damage) {
        display();
    }

    @Override
    public void updateWinnerAndLoser(Character winner, Character loser) {
        display();
    }

    @Override
    public void display() {
        System.out.println(currentOutput);
    }
}
