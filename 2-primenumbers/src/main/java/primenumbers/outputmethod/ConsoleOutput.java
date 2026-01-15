package primenumbers.outputmethod;

import java.util.List;

public class ConsoleOutput implements OutputMethod {
    @Override
    public void generateOutput(List<Integer> primeNumbers) {
        for(int num : primeNumbers) {
            System.out.println(num);
        }
    }
}
