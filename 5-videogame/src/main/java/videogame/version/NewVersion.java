package videogame.version;

import videogame.character.Character;
import videogame.character.*;
import videogame.gem.*;
import videogame.weapon.*;

public class NewVersion implements VideogameFactory {
	public Character createCharacter(String type) {
        return switch (type) {
            case "knight" -> new Knight();
            case "archer" -> new Archer();
            case "wizard" -> new Wizard();
            default -> throw new IllegalArgumentException("Invalid character");
        };
	}
	public Weapon createWeapon(String type) {
        return switch (type) {
            case "sword" -> new Sword();
            case "axe" -> new Axe();
            case "punch" -> new Punch();
            case "weakblast" -> new WeakBlast();
            case "woodbow" -> new WoodBow();
            default -> throw new IllegalArgumentException("Invalid weapon");
        };
	}

	public Weapon addGem(String type, Weapon weapon) {
		return switch(type) {
			case "fire" -> new FireGem(weapon);
			case "water" -> new WaterGem(weapon);
			default -> throw new IllegalArgumentException("Invalid gem");
		};
	}
}
