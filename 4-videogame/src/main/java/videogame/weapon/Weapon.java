package videogame.weapon;

import videogame.CombatType;

public abstract class Weapon {
    protected int damage;
    public int maxGems;
    public CombatType combatType;

    public int getDamage() {
        return this.damage;
    }
}

