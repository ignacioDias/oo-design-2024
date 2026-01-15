package videogame.weapon;

import videogame.CombatType;

public abstract class Weapon {
    int damage;
    CombatType combatType;

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public CombatType getCombatType() {
        return combatType;
    }
    public void setCombatType(CombatType combatType) {
        this.combatType = combatType;
    }

}

