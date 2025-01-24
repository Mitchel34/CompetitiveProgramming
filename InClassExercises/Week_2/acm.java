import java.util.Scanner;

public class acm {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        int problemsSolved = 0;
        int timeScore = 0;
        int[] penalty = new int[26];
        boolean[] solved = new boolean[26];

        while (scan.hasNext()) {
            String line = scan.nextLine().trim();
            if (line.equals("-1")) {
                break;
            }

            if (line.isEmpty()) {
                continue; // Skip empty lines
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                continue; // Skip invalid log entries
            }

            int time;
            try {
                time = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                continue; // Skip lines with invalid time format
            }

            char problem = parts[1].charAt(0);
            String result = parts[2];

            int index = problem - 'A';

            // Ignore submissions for already solved problems
            if (solved[index]) {
                continue;
            }

            if (result.equals("right")) {
                problemsSolved++;
                timeScore += time + penalty[index];
                solved[index] = true;
            } else if (result.equals("wrong")) {
                penalty[index] += 20;
            }
        }

        System.out.println(problemsSolved + " " + timeScore);

        scan.close();
        
    }
}
