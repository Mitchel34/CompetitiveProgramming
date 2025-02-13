import java.util.*;

public class roompainting {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // number of paint can sizes
        int m = scan.nextInt(); // number of colours that Joe needs

        int[] canSizes = new int[n];
        for (int i = 0; i < n; i++) {
            canSizes[i] = scan.nextInt();
        }
        Arrays.sort(canSizes);

        int[] colors = new int[m];
        for (int i = 0; i < m; i++) {
            colors[i] = scan.nextInt();
        }
        Arrays.sort(colors);

        long minWaste = 0L; // Use long to avoid integer overflow

        // Binary Search for each required paint amount
        for (int i = 0; i < m; i++) {
            int low = 0;
            int high = n - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (canSizes[mid] < colors[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            // low is now the index of the smallest can size that is >= colors[i]
            long waste = (long) canSizes[low] - colors[i];
            minWaste += waste;
        }

        System.out.println(minWaste);
        scan.close();
    }
}
