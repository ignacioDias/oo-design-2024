package videogame.gem;

import videogame.weapon.Weapon;

public class FireGem extends Gem {
    public FireGem(Weapon weapon) {
        this.weapon = weapon;
        this.maxGems = weapon.maxGems - 1;
        if(this.maxGems <= 0)
            throw new IllegalArgumentException();
    }
    @Override
    public int getDamage() {
        return 10 + weapon.getDamage();
    }
}
