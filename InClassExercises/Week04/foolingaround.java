import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class foolingaround {
    static final int MAX_N = 1000000; // Set an upper bound for precomputing
    static List<Integer> validMoves = new ArrayList<>();
    static Set<Integer> losingPositions = new HashSet<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt();  // Number of test cases
        
        // Precompute valid moves (one less than prime numbers)
        sievePrimes(MAX_N);
        
        // Process test cases
        while (Q-- > 0) {
            long N = sc.nextLong();
            if (canAliceWin(N)) {
                System.out.println("Alice");
            } else {
                System.out.println("Bob");
            }
        }
        sc.close();
    }

    // Sieve of Eratosthenes to generate prime numbers and valid moves
    static void sievePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                validMoves.add(i - 1); // One less than a prime
            }
        }
    }

    // Function to determine if Alice can force a win
    static boolean canAliceWin(long N) {
        if (N == 1) return true; // Base case: Alice takes 1 stone and wins
        
        Set<Long> checked = new HashSet<>();
        return canWin(N, checked);
    }

    static boolean canWin(long N, Set<Long> checked) {
        if (checked.contains(N)) return false; // Avoid rechecking

        for (int move : validMoves) {
            if (move > N) break; // No more valid moves

            long next = N - move;
            if (!canWin(next, checked)) {
                checked.add(N); // Memoize winning state
                return true;  // If there's at least one move leading to a loss for Bob, Alice wins
            }
        }
        return false; // If all moves lead to Bob's win, Alice loses
    }

    // enumerate the possible ways for bob to win
    static void enumerateLosingPositions() {
        for (int i = 2; i < MAX_N; i++) {
            if (!canAliceWin(i)) {
                losingPositions.add(i);
            }
        }
    }

    
}

