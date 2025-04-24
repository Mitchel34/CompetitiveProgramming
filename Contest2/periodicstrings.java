import java.util.*;

public class periodicstrings {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine().trim();
        scan.close();

        int n = s.length();
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            boolean ok = true;
            String prev = s.substring(0, k);
            for (int i = k; i < n; i += k) {
                String curr = s.substring(i, i + k);
                // rotate prev right by 1: last char + prev[0..k-2]
                String rotated = prev.charAt(k - 1) + prev.substring(0, k - 1);
                if (!curr.equals(rotated)) {
                    ok = false;
                    break;
                }
                prev = curr;
            }
            if (ok) {
                System.out.println(k);
                return;
            }
        }
    }
}