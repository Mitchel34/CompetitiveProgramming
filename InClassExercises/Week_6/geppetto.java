import java.util.Scanner;

public class geppetto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of ingredients
        int N = sc.nextInt();  
        // Number of conflicting pairs
        int M = sc.nextInt();  

        int[][] conflicts = new int[M][2];
        for (int i = 0; i < M; i++) {
            conflicts[i][0] = sc.nextInt();
            conflicts[i][1] = sc.nextInt();
        }

        int count = 0;
        // 2^N possible subsets
        int totalSubsets = 1 << N;  

        for (int mask = 0; mask < totalSubsets; mask++) {
            boolean valid = true;
            for (int i = 0; i < M; i++) {
                // Ingredients are 1 based in the problem; convert to 0 based
                int a = conflicts[i][0] - 1;
                int b = conflicts[i][1] - 1;

                // Check if both ingredients a and b are chosen in this subset
                if (((mask >> a) & 1) == 1 && ((mask >> b) & 1) == 1) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
            }
        }

        System.out.println(count);
        sc.close();
    }
}
