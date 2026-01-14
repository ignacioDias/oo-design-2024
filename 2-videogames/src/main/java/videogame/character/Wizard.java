package videogame.character;

import videogame.CombatType;
import videogame.weapon.WeakBlast;

public class Wizard extends Character {

    public Wizard() {
        this.combatType = CombatType.MAGIC;
        this.life = 20;
        this.weapon = new WeakBlast();
    }
    public int getAttackDamage() {
        return 0;
    }

    @Override
    public void decreaseLife(int damage) {

    }
}
