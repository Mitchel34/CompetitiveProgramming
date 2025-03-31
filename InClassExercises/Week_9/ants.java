import java.util.Scanner;

public class ants {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();
        for (int i = 0; i < cases; i++) {
            int L = scan.nextInt();
            int n = scan.nextInt(); 
            int earliest = 0;
            int latest = 0;
            for (int j = 0; j < n; j++) {
                int pos = scan.nextInt();
                // For earliest time, choose the nearer end.
                int timeToFallEarly = Math.min(pos, L - pos);
                // For latest time, choose the farther end.
                int timeToFallLate = Math.max(pos, L - pos);
                earliest = Math.max(earliest, timeToFallEarly);
                latest = Math.max(latest, timeToFallLate);
            }
            System.out.println(earliest + " " + latest);
        }
        scan.close();
    }
}