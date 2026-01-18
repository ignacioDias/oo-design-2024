import org.junit.jupiter.api.Test;
import videogame.CombatType;
import videogame.Fight;
import videogame.character.Character;
import videogame.character.Knight;
import videogame.display.FileDisplay;
import videogame.weapon.Sword;
import videogame.weapon.Weapon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FightTest {

    @Test
    void fightEndsWhenOneCharacterDies() {
        videogame.character.Character fighter1 = new Knight();
        videogame.character.Character fighter2 = new Knight();

        Fight fight = new Fight(fighter1, fighter2);
        fight.fight();
        assertFalse(fighter1.isAlive() && fighter2.isAlive());
    }
    @Test
    void fightersAttackAlternately() {
        videogame.character.Character fighter1 = new Knight();
        videogame.character.Character fighter2 = new Knight();

        Fight fight = new Fight(fighter1, fighter2);
        fight.fight();
        assertTrue(fighter1.getLife() > fighter2.getLife());
    }

    @Test
    void strongerCharacterWinsTheFight() {
        videogame.character.Character strong = new Knight();
        Character weak = new Knight();
        strong.setWeapon(new Sword());

        Fight fight = new Fight(strong, weak);
        fight.fight();

        assertTrue(strong.isAlive());
        assertFalse(weak.isAlive());
    }

}
