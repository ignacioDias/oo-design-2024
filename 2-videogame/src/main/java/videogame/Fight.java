package videogame;

import videogame.character.Character;
import videogame.event.*;

import java.util.ArrayList;
import java.util.List;

public class Fight implements Subject {
    List<Observer> observers;
    Character fighter1;
    Character fighter2;
    FightEvent currentEvent;
    int currentTurn;

    public Fight(Character fighter1, Character fighter2) {
        currentTurn = 0;
        this.observers = new ArrayList<>();
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }
    public FightEvent getCurrentEvent() {
        return this.currentEvent;
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void fight() {
        FightEvent startFight = new StartingFightEvent();
        updateCurrentEvent(startFight);
        while(areBothFightersAlive()) {
            nextTurn();
        }
        Character winner = getWinner();
        FightEvent winnerEvent = new WinnerEvent(winner);
        updateCurrentEvent(winnerEvent);
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
    private Character getWinner() {
        if(areBothFightersAlive())
            throw new IllegalStateException("Both fighters are alive");
        if(fighter1.isAlive())
            return fighter1;
        return fighter2;
    }
    private void attack(Character attacker, Character defender) {
        int damage = attacker.getAttackDamage();
        defender.decreaseLife(damage);
        FightEvent currentAttack = new AttackEvent(attacker, defender, damage);
        updateCurrentEvent(currentAttack);
    }
    private void updateCurrentEvent(FightEvent newAction) {
        this.currentEvent = newAction;
        notifyObservers();
    }
    @Override
    public void notifyObservers() {
        for(Observer o : this.observers) {
            o.update();
        }
    }
}
