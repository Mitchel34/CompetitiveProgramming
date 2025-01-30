import java.util.Scanner;
public class shitori {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            int n = scan.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }

            boolean[] diff = new boolean[n - 1];

            for (int i = 0; i < n - 1; i++) {
                int d = Math.abs(arr[i] - arr[i + 1]);
                if (d < n && d > 0) {
                    diff[d - 1] = true;
                }
            }

            boolean isShitori = true;

            for (int i = 0; i < n - 1; i++) {
                if (!diff[i]) {
                    isShitori = false;
                    break;
                }
            }

            if (isShitori) {
                System.out.println("Shitori");
            } else {
                System.out.println("Not shitori");
            }
        }
    }
}