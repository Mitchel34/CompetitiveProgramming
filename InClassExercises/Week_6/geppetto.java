import java.util.Scanner;

public class geppetto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();  // Number of ingredients
        int M = sc.nextInt();  // Number of conflicting pairs

        // Store conflict pairs in a 2D array
        int[][] conflicts = new int[M][2];
        for (int i = 0; i < M; i++) {
            conflicts[i][0] = sc.nextInt();
            conflicts[i][1] = sc.nextInt();
        }

        int count = 0;
        // We will iterate through all possible subsets of the N ingredients
        // mask goes from 0 to 2^N - 1
        int totalSubsets = 1 << N;

        for (int mask = 0; mask < totalSubsets; mask++) {
            boolean valid = true;

            // Check each conflict pair
            for (int i = 0; i < M; i++) {
                // Convert 1-based ingredient index to 0-based bit position
                int a = conflicts[i][0] - 1;
                int b = conflicts[i][1] - 1;

                // Check if both ingredients a and b are in the subset (mask)
                if (((mask >> a) & 1) == 1 && ((mask >> b) & 1) == 1) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
            }
        }

        // Print the number of valid subsets
        System.out.println(count);

        sc.close();
    }
}
