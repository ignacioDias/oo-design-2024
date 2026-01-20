import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import videogame.Fight;
import videogame.character.*;
import videogame.character.Character;
import videogame.weapon.*;

class VideoGameTest {

    @Test
    void characterStartsWithFistsAsDefaultWeapon() {
        Character knight = new Knight();
        assertInstanceOf(Punch.class, knight.getWeapon());

        Character wizard = new Wizard();
        assertInstanceOf(WeakBlast.class, wizard.getWeapon());

        Character archer = new Archer();
        assertInstanceOf(WoodBow.class, archer.getWeapon());
    }

    @Test
    void characterCanChangesWeaponAtRuntime() {
        Character knight = new Knight();
        Weapon oldWeapon = knight.getWeapon();
        knight.setWeapon(new Sword());
        assertNotEquals(oldWeapon.getClass(), knight.getWeapon());
    }

    @Test
    void characterDiesWhenHealthReachesZero() {
        Character character = new Knight();
        character.decreaseLife(2000);
        assertFalse(character.isAlive());
        assertTrue(character.getLife() <= 0);
    }

    @Test
    void meleeCharacterCanUseSword() {
        Character knight = new Knight();
        assertDoesNotThrow(() -> knight.setWeapon(new Sword()));
    }

    @Test
    void weaponsAreNotCompatibleWithDifferentClasses() {
        Character archer = new Archer();
        assertThrows(IllegalArgumentException.class,
                () -> archer.setWeapon(new Sword())
        );
    }

}
