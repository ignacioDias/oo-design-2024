package videogame.character;

import videogame.CombatType;
import videogame.weapon.WeakBlast;

public class Wizard extends Character {

    public Wizard() {
        this.combatType = CombatType.MAGIC;
        this.life = 250;
        this.setWeapon(new WeakBlast());
    }

}
