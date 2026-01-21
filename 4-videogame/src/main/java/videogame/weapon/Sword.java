package videogame.weapon;

import videogame.CombatType;

public class Sword extends Weapon {
    public Sword() {
        this.damage = 50;
        this.combatType = CombatType.MELEE;
        this.maxGems = 3;
    }
}
