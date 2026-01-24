package videogame.display;

import videogame.Fight;
import videogame.Observer;
import videogame.character.Character;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileDisplay implements Observer, DisplayElement {
    Fight fight;
    String currentOutput;
    private static final Path FILE_PATH = Path.of("fight.txt");
    public FileDisplay(Fight fight) {
        this.fight = fight;
    }
    @Override
    public void updateStartingFight() {
        try {
            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            } else {
                Files.write(FILE_PATH, new byte[0]); // clear file
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating file", e);
        }

        currentOutput = "Fight starts!";
        display();
    }

    @Override
    public void updateAttack(Character attacker, Character defender, int damage) {
        currentOutput = attacker + " attacks " + defender + " and does " + damage + " of damage!";
        display();
    }

    @Override
    public void updateWinnerAndLoser(Character winner, Character loser) {
        currentOutput = winner + " wins and " + loser + " loses..";
        display();
    }

    @Override
    public void display() {
        try {
            Files.writeString(
                    FILE_PATH,
                    currentOutput + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Error writing into file", e);
        }
    }
}
