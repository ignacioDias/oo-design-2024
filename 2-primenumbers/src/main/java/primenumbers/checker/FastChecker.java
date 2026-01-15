package primenumbers.checker;

public class FastChecker implements PrimeChecker {

    @Override
    public boolean isPrime(int number) {
        if(number <= 1) {
            return false;
        }
        if(number == 2) {
            return true;
        }
        if(number % 2 == 0) {
            return false;
        }
        for(int i = 3; i <= Math.sqrt(number) + 1; i = i + 2) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
