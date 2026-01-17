package videogame.event;

import videogame.character.Character;

public class AttackEvent implements FightEvent {
    public Character attacker;
    public Character defender;
    public int damage;

    public AttackEvent(Character attacker, Character defender, int damage) {
        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
    }

    @Override
    public String generateDefaultOutput() {
        String output = "Attack from: " + attacker + ", to: " + defender + ". Damage: " + damage;
        return output;
    }
}
