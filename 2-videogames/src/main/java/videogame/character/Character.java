package videogame.character;

import videogame.CombatType;
import videogame.weapon.Weapon;

public class Character {

    int life;
    CombatType combatType;
    Weapon weapon;

    public void setWeapon(Weapon weapon) throws IllegalArgumentException {
        CombatType weaponCombatType = weapon.getCombatType();
        if(!weaponCombatType.equals(this.combatType)) {
            throw new IllegalArgumentException("Illegal call");
        }
        this.weapon = weapon;
    }

    public CombatType getCombatType() {
        return combatType;
    }

    public int getLife() {
        return life;
    }

    public int getAttackDamage() {
        return this.weapon.getDamage();
    }

    public void decreaseLife(int damage) {
        this.life -= damage;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isAlive() {
        return this.life > 0;
    }
}
