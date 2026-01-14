package videogame.weapon;

import videogame.CombatType;

public class Axe extends Weapon {
    public Axe() {
        this.damage = 20;
        this.combatType = CombatType.MELEE;
    }
}
