package videogame.character;

import videogame.CombatType;
import videogame.weapon.*;

public class Knight extends Character {
    public Knight() {
        this.combatType = CombatType.MELEE;
        this.life = 500;
        this.setWeapon(new Punch());
    }

}
