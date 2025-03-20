import java.util.*;

public class proofs {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        // Set to store the conclusions
        Set<String> conclusions = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            String line = scan.nextLine();
            if (line.startsWith("-> ")) {
                // Line has no assumptions remove the arrow
                String conclusion = line.substring(3); 
                conclusions.add(conclusion);
            } else {
                // Line has assumptions
                String[] parts = line.split(" -> ");
                String[] assumptions = parts[0].split(" ");
                String conclusion = parts[1];
        
                for (String assumption : assumptions) {
                    if (!conclusions.contains(assumption)) {
                        System.out.println(i);
                        return;
                    }
                }
                conclusions.add(conclusion);
            }
            scan.close();
        }
        System.out.println("correct");
    }

}
