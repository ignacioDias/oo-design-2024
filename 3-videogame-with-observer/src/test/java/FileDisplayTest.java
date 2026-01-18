import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import videogame.Fight;
import videogame.character.Character;
import videogame.character.Knight;
import videogame.display.FileDisplay;
import videogame.weapon.Axe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileDisplayTest {
    private static final Path FILE_PATH = Path.of("fight.txt");

    @BeforeAll
    public static void setUp() throws IOException {
        Character c1 = new Knight();
        Character c2 = new Knight();
        c1.setWeapon(new Axe());

        Fight fight = new Fight(c1, c2);
        FileDisplay display = new FileDisplay(fight);
        fight.addObserver(display);

        fight.fight();
    }
    @Test
    void startingFight_createsAndWritesInitialLine() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        assertTrue(Files.exists(FILE_PATH));
        assertEquals("Fight starts!", lines.get(0));
    }

    @Test
    void updateAttack_appendsAttackLine() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        assertTrue(lines.get(1).contains("attacks"));
        assertTrue(lines.get(1).contains("20"));
    }
    @Test
    void updateWinnerAndLoser_appendsWinnerLine() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);

        String lastLine = lines.get(lines.size() - 1);
        assertTrue(lastLine.contains("wins"));
        assertTrue(lastLine.contains("loses"));
    }
    @Test
    void fileIsBigEnough() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        assertTrue(lines.size() >= 3);
    }
}
