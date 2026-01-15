package videogame;

import videogame.character.Character;

public class Fight {
    Character fighter1;
    Character fighter2;
    int currentTurn = 0;

    public Fight(Character fighter1, Character fighter2) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    public void fight() {
        while(areBothFightersAlive()) {
            nextTurn();
        }
        String winner = getWinner();
        System.out.println("Winner: " + winner);
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
    private String getWinner() {
        if(areBothFightersAlive())
            throw new IllegalStateException("Both fighters are alive");
        if(fighter1.isAlive())
            return fighter1.toString();
        return fighter2.toString();
    }
    private void attack(Character attacker, Character defender) {
        int damage = attacker.getAttackDamage();
        defender.decreaseLife(damage);
        System.out.println(attacker.getClass() + " did: " + damage + "damage over: " + defender.getClass());
    }
}
