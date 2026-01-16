package primenumbers.outputmethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileOutput implements OutputMethod {
    private static final Path FILE_PATH = Path.of("numbers.txt");

    public void generateOutput(List<Integer> primeNumbers) {
        try {
            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            } else {
                Files.write(FILE_PATH, new byte[0]); // clear file
            }
            for (Integer number : primeNumbers) {
                Files.writeString(
                        FILE_PATH,
                        number + System.lineSeparator(),
                        StandardOpenOption.APPEND
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Error writing integers to file", e);
        }
    }
}
