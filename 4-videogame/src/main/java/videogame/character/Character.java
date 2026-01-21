package videogame.character;

import videogame.CombatType;
import videogame.weapon.Weapon;

public class Character {

    public int life;
    public CombatType combatType;
    private Weapon weapon;

    public void setWeapon(Weapon weapon) throws IllegalArgumentException {
        CombatType weaponCombatType = weapon.combatType;
        if(!weaponCombatType.equals(this.combatType)) {
            throw new IllegalArgumentException("Illegal call");
        }
        this.weapon = weapon;
    }


    public int getLife() {
        return life;
    }

    public int getAttackDamage() {
        return weapon.getDamage();
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
