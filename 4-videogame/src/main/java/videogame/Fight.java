package videogame;

import videogame.character.Character;

import java.util.ArrayList;
import java.util.List;

public class Fight implements Subject {
    Character fighter1;
    Character fighter2;
    int currentTurn = 0;
    List<Observer> observerList = new ArrayList<>();
    public Fight(Character fighter1, Character fighter2) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    public void fight() {
        this.notifyObserversAboutFightStarting();
        while(areBothFightersAlive()) {
            nextTurn();
        }
        Character[] winnerAndLoser = getWinnerAndLoser();
        this.notifyObserversAboutWinnerAndLoser(winnerAndLoser[0], winnerAndLoser[1]);
    }
    private boolean areBothFightersAlive() {
        return fighter1.isAlive() && fighter2.isAlive();
    }
    private void nextTurn() {
        if(currentTurn % 2 == 0) {
            attack(fighter1, fighter2);
        } else {
            attack(fighter2, fighter1);
        }
        currentTurn++;
    }
    private Character[] getWinnerAndLoser() {
        if(areBothFightersAlive())
            throw new IllegalStateException("Both fighters are alive");
        if(fighter1.isAlive())
            return new Character[]{fighter1, fighter2};
        return new Character[]{fighter2};
    }
    private void attack(Character attacker, Character defender) {
        int damage = attacker.getAttackDamage();
        defender.decreaseLife(damage);
        this.notifyObserversAboutAttack(attacker, defender, damage);
    }

    @Override
    public void addObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObserversAboutFightStarting() {
        for(Observer o : observerList) {
            o.updateStartingFight();
        }
    }

    @Override
    public void notifyObserversAboutAttack(Character attacker, Character defender, int damage) {
        for(Observer o : observerList) {
            o.updateAttack(attacker, defender, damage);
        }
    }

    @Override
    public void notifyObserversAboutWinnerAndLoser(Character winner, Character loser) {
        for(Observer o : observerList) {
            o.updateWinnerAndLoser(winner, loser);
        }
    }

}
