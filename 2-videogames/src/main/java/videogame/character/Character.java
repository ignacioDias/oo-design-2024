package videogame.character;

import videogame.CombatType;
import videogame.weapon.Weapon;

import java.util.Objects;

public abstract class Character {

    int life;
    CombatType combatType;
    Weapon weapon;

    public void setWeapon(Weapon weapon) {
        CombatType weaponCombatType = weapon.getCombatType();
        if(!weaponCombatType.equals(this.combatType)) {
            throw new IllegalStateException("Illegal call");
        }
        this.weapon = weapon;
    }

    public void setLife(int life) {
        this.life = life;
    }
    public int getLife() {
        return life;
    }

    public abstract int getAttackDamage();

    public abstract void decreaseLife(int damage);

    public boolean isAlive() {
        return this.life > 0;
    }
}
