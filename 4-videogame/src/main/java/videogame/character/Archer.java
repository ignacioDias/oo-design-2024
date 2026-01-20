package videogame.character;

import videogame.CombatType;
import videogame.weapon.WoodBow;

public class Archer extends Character {

    public Archer() {
        this.combatType = CombatType.RANGED;
        this.life = 200;
        this.setWeapon(new WoodBow());
    }
}
