package videogame.version;

import videogame.character.Character;
import videogame.character.*;
import videogame.weapon.*;

public class ClassicVersion implements VideogameFactory {
	public Character createCharacter(String type) {
		return switch (type) {
			case "knight" -> new Knight();
			case "archer" -> new Archer();
			default -> throw new IllegalArgumentException("Invalid character");
		};
	}
	public Weapon createWeapon(String type) {
		return switch (type) {
			case "punch" -> new Punch();
			case "woodbow" -> new WoodBow();
			default -> throw new IllegalArgumentException("Invalid weapon");
		};
	}

	public Weapon addGem(String type, Weapon weapon) {
		System.out.println("No gems implemented");
		return weapon;
	}
}
