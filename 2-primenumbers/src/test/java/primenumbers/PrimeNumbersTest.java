package primenumbers;

import org.junit.jupiter.api.Test;
import primenumbers.checker.FastChecker;
import primenumbers.checker.TrialDivisionPrimeChecker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimeNumbersTest {
    PrimeNumbersGenerator primeNumbersGenerator = new PrimeNumbersGenerator();
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

}