package videogame.weapon;

import videogame.CombatType;

public abstract class Weapon {
    int damage;
    CombatType combatType;

    public CombatType getCombatType() {
        return combatType;
    }

    public void setCombatType(CombatType combatType) {
        this.combatType = combatType;
    }

}

