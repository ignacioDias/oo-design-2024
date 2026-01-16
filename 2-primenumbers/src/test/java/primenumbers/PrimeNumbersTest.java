package primenumbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primenumbers.checker.FastChecker;
import primenumbers.checker.TrialDivisionPrimeChecker;
import primenumbers.outputmethod.FileOutput;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeNumbersTest {
    PrimeNumbersGenerator primeNumbersGenerator;
    @BeforeEach
    public void setUp() {
        primeNumbersGenerator = new PrimeNumbersGenerator();
    }
    @Test
    public void negativeInputShouldThrowError() {
        assertThrows(IllegalArgumentException.class,
                () -> primeNumbersGenerator.outputPrimeNumbers(-1)
        );
    }
    @Test
    public void zeroInputShouldThrowError() {
        assertThrows(IllegalArgumentException.class,
                () -> primeNumbersGenerator.outputPrimeNumbers(0)
        );
    }
    @Test
    public void validInputForTrialDivisionPrimeChecker() {
        primeNumbersGenerator.setPrimeChecker(new TrialDivisionPrimeChecker());
        List<Integer> output = primeNumbersGenerator.generateFirstNPrimeNumbers(7);
        assertEquals(output, List.of(2, 3, 5, 7, 11, 13, 17));
    }

    @Test
    public void validInputForFastChecker() {
        primeNumbersGenerator.setPrimeChecker(new FastChecker());
        List<Integer> output = primeNumbersGenerator.generateFirstNPrimeNumbers(7);
        assertEquals(output, List.of(2, 3, 5, 7, 11, 13, 17));
    }
    @Test
    public void testFileCreation() {
        primeNumbersGenerator.setOutputMethod(new FileOutput());
        primeNumbersGenerator.outputPrimeNumbers(5);
        File file = new File("numbers.txt");
        assertTrue(file.exists());
    }
    @Test
    void numbersFileHasExpectedContent() throws Exception {
        primeNumbersGenerator.setOutputMethod(new FileOutput());
        primeNumbersGenerator.outputPrimeNumbers(5);
        File file = new File("numbers.txt");

        List<String> lines = Files.readAllLines(file.toPath());

        List<String> expected = List.of(
                "2",
                "3",
                "5",
                "7",
                "11"
        );
        assertEquals(expected, lines);
    }
}