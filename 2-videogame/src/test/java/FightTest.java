import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import videogame.display.ConsoleDisplay;
import videogame.Fight;
import videogame.character.*;
import videogame.character.Character;
import videogame.weapon.Sword;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FightTest {
    Character fighter1;
    Character fighter2;

    Fight fight;
    @BeforeEach
    public void setUp() {
        fighter1 = new Knight();
        fighter2 = new Knight();
        fight = new Fight(fighter1, fighter2);
        ConsoleDisplay consoleDisplay = new ConsoleDisplay(this.fight);
        fight.registerObserver(consoleDisplay);
    }
    @Test
    void fightEndsWhenOneCharacterDies() {
        fight.fight();
        assertFalse(fighter1.isAlive() && fighter2.isAlive());
    }
    @Test
    void fightersAttackAlternately() {
        Fight fight = new Fight(fighter1, fighter2);
        fight.fight();
        assertTrue(fighter1.getLife() > fighter2.getLife());
    }

    @Test
    void strongerCharacterWinsTheFight() {
        fighter1.setWeapon(new Sword());
        Fight fight = new Fight(fighter1, fighter2);
        fight.fight();

        assertTrue(fighter1.isAlive());
        assertFalse(fighter2.isAlive());
    }
}
