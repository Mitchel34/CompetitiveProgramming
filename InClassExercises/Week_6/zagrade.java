import java.util.*;

public class zagrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();  // read the entire expression
        sc.close();

        //  Identify all matching parentheses pairs using a stack
        Stack<Integer> stack = new Stack<>();
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int openIndex = stack.pop();
                pairs.add(new int[] {openIndex, i});
            }
        }

        // collect all resulting expressions in a sorted set
        Set<String> results = new TreeSet<>();

        // number of parenthesis pairs
        int n = pairs.size();
        // 2^n possible ways to remove/keep pairs
        int totalSubsets = 1 << n;       

        // For each subset, remove the chosen pairs
        for (int mask = 0; mask < totalSubsets; mask++) {
            // Mark positions of parentheses to remove
            boolean[] removed = new boolean[expr.length()];
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    int openIdx = pairs.get(i)[0];
                    int closeIdx = pairs.get(i)[1];
                    removed[openIdx] = true;
                    removed[closeIdx] = true;
                }
            }

            // Build the new expression
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expr.length(); i++) {
                if (!removed[i]) {
                    sb.append(expr.charAt(i));
                }
            }

            results.add(sb.toString());
        }

        // Print all distinct expressions in lex order
        // do not print the given expression
        results.remove(expr);
        for (String s : results) {
            System.out.println(s);
        }
    }
}
