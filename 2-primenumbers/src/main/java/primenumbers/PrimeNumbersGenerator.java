package primenumbers;

import primenumbers.checker.PrimeChecker;
import primenumbers.checker.TrialDivisionPrimeChecker;
import primenumbers.outputmethod.ConsoleOutput;
import primenumbers.outputmethod.OutputMethod;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbersGenerator {

    private PrimeChecker primeChecker;
    private OutputMethod outputMethod;

    public PrimeNumbersGenerator() {
        this.primeChecker = new TrialDivisionPrimeChecker();
        this.outputMethod = new ConsoleOutput();
    }
    public PrimeNumbersGenerator(OutputMethod outputMethod, PrimeChecker primeChecker) {
        this.primeChecker = primeChecker;
        this.outputMethod = outputMethod;
    }

    public void setOutputMethod(OutputMethod outputMethod) {
        this.outputMethod = outputMethod;
    }

    public void setPrimeChecker(PrimeChecker primeChecker) {
        this.primeChecker = primeChecker;
    }
    public void outputPrimeNumbers(int limit) throws IllegalArgumentException {
        if(limit <= 0) {
            throw new IllegalArgumentException("Illegal input for limit");
        }
        List<Integer> listOfPrimeNumbers = generateFirstNPrimeNumbers(limit);
        outputMethod.generateOutput(listOfPrimeNumbers);
    }
    protected List<Integer> generateFirstNPrimeNumbers(int limit) throws IllegalArgumentException {
        if(limit <= 0) {
            throw new IllegalArgumentException("Illegal input for limit");
        }
        List<Integer> listOfPrimeNumbers = new ArrayList<>();
        int i = 2;
        while(listOfPrimeNumbers.size() < limit) {
            if(primeChecker.isPrime(i)) {
                listOfPrimeNumbers.add(i);
            }
            i++;
        }
        return listOfPrimeNumbers;
    }
}
