package videogame.weapon;

import videogame.CombatType;

public class Punch extends Weapon {
    public Punch() {
        this.damage = 5;
        this.combatType = CombatType.MELEE;
        this.maxGems = 0;
    }
}
