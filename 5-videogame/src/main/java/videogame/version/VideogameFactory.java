package videogame.version;

import videogame.character.Character;
import videogame.weapon.Weapon;

public interface VideogameFactory {
    public Character createCharacter(String type);
    public Weapon createWeapon(String type);
    public Weapon addGem(String type, Weapon weapon);
}
