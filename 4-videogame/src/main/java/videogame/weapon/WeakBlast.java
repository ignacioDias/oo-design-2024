package videogame.weapon;

import videogame.CombatType;

public class WeakBlast extends Weapon {
    public WeakBlast() {
        this.damage = 5;
        this.combatType = CombatType.MAGIC;
        this.maxGems = 1;
    }
}
