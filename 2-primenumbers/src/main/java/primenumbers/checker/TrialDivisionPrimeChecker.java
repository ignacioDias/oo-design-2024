package primenumbers.checker;

public class TrialDivisionPrimeChecker implements PrimeChecker {
    @Override
    public boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
