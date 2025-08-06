import java.util.HashMap;
public class FibonacciTopDown {
    static HashMap<Integer, Integer> memo = new HashMap<>();
    static int fibonacci(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);
        return result;
    }
    public static void main(String[] args) {
        int n = 10;

        System.out.print("Fibonacci Series: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
