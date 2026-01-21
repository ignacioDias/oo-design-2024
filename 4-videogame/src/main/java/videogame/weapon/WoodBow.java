package videogame.weapon;

import videogame.CombatType;

public class WoodBow extends Weapon {
    public WoodBow() {
        this.damage = 5;
        this.combatType = CombatType.RANGED;
        this.maxGems = 1;
    }
}
