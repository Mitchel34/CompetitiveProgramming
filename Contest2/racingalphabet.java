import java.util.*;

public class racingalphabet {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine());
        double arcLen = 60 * Math.PI / 28.0;
        double speed = 15.0;

        // Map each character to its position on the circle
        Map<Character, Integer> pos = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            pos.put((char)('A' + i), i);
        }
        pos.put(' ', 26);
        pos.put('\'', 27);

        for (int caseNum = 0; caseNum < N; caseNum++) {
            String line = scan.nextLine();
            int L = line.length();
            double time = 0.0;

            // initial pickup of first disk
            time += 1.0;

            // run and pick for each subsequent character
            for (int i = 1; i < L; i++) {
                char prev = line.charAt(i - 1);
                char curr = line.charAt(i);
                int p1 = pos.get(prev);
                int p2 = pos.get(curr);
                int diff = Math.abs(p2 - p1);
                int steps = Math.min(diff, 28 - diff);
                double dist = steps * arcLen;
                time += dist / speed;
                // pickup at arrival
                time += 1.0;
            }

            // output with 1e-6 precision
            System.out.printf("%.6f%n", time);
        }
        scan.close();
    }
}