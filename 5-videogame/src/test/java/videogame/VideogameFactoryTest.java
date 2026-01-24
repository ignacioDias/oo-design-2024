package videogame;

import org.junit.jupiter.api.Test;
import videogame.character.Character;
import videogame.character.*;
import videogame.version.*;
import videogame.weapon.*;

import static org.junit.jupiter.api.Assertions.*;

public class VideogameFactoryTest {

    @Test
    void classicVersionCreatesValidCharacters() {
        VideogameFactory factory = new ClassicVersion();

        Character knight = factory.createCharacter("knight");
        Character archer = factory.createCharacter("archer");

        assertNotNull(knight);
        assertNotNull(archer);
        assertNotEquals(knight.getClass(), archer.getClass());
    }

    @Test
    void classicVersionRejectsInvalidCharacter() {
        VideogameFactory factory = new ClassicVersion();
        assertThrows(IllegalArgumentException.class,
                () -> factory.createCharacter("wizard"));
    }

    @Test
    void newVersionCreatesExtraCharacter() {
        VideogameFactory factory = new NewVersion();
        Character wizard = factory.createCharacter("wizard");
        assertNotNull(wizard);
    }

    @Test
    void classicVersionCreatesWeaponsWithoutGems() {
        VideogameFactory factory = new ClassicVersion();

        Weapon punch = factory.createWeapon("punch");
        Weapon bow = factory.createWeapon("woodbow");

        assertNotNull(punch);
        assertNotNull(bow);
    }

    @Test
    void newVersionCreatesMoreWeaponsThanClassic() {
        VideogameFactory newFactory = new NewVersion();
        VideogameFactory classicFactory = new ClassicVersion();

        Weapon sword = newFactory.createWeapon("sword");
        assertNotNull(sword);

        assertThrows(IllegalArgumentException.class,
                () -> classicFactory.createWeapon("sword"));
    }

    @Test
    void newVersionAddsGemToWeapon() {
        VideogameFactory factory = new NewVersion();

        Weapon baseWeapon = factory.createWeapon("sword");
        Weapon gemmedWeapon = factory.addGem("fire", baseWeapon);

        assertNotNull(gemmedWeapon);
        assertNotEquals(baseWeapon.getClass(), gemmedWeapon.getClass());
    }

    @Test
    void classicVersionDoesNotDecorateWeaponWithGem() {
        VideogameFactory factory = new ClassicVersion();

        Weapon baseWeapon = factory.createWeapon("punch");
        Weapon result = factory.addGem("fire", baseWeapon);

        assertSame(baseWeapon, result);
    }

    @Test
    void newVersionRejectsInvalidGem() {
        VideogameFactory factory = new NewVersion();
        Weapon weapon = factory.createWeapon("sword");

        assertThrows(IllegalArgumentException.class,
                () -> factory.addGem("earth", weapon));
    }

    @Test
    void invalidWeaponTypeThrowsException() {
        VideogameFactory factory = new ClassicVersion();

        assertThrows(IllegalArgumentException.class,
                () -> factory.createWeapon("laser"));
    }
}
